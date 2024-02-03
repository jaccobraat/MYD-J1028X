DESCRIPTION = "NXP secure bootloader for QorIQ device"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

python __anonymous () {
    board="ls1028amyd"
    m = d.getVar("MACHINE")
    if m not in board:
        raise bb.parse.SkipRecipe("This platform not exit secure-boot manifest")
}

FILESEXTRAPATHS:prepend := "${THISDIR}/secure-boot-qoriq:"
SRC_URI = "file://${MACHINE}.manifest \
           file://create_distro_bootscr.sh \
           "

inherit deploy
DEPENDS = "u-boot-mkimage-native"

IMA_EVM = "${@bb.utils.contains('DISTRO_FEATURES', 'ima-evm', 'true', 'false', d)}"
ENCAP = "${@bb.utils.contains('DISTRO_FEATURES', 'encap', 'true', 'false', d)}"
EDS = "${@bb.utils.contains('DISTRO_FEATURES', 'edgescale', 'true', 'false', d)}"

do_deploy[nostamp] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_deploy () {
    cp ${WORKDIR}/create_distro_bootscr.sh ./
    cp ${WORKDIR}/${MACHINE}.manifest ./

    ./create_distro_bootscr.sh -m ${MACHINE} -d . -s ${DEPLOY_DIR_IMAGE} -e ${ENCAP} -i ${IMA_EVM}

    if [ "${EDS}" = "true" ];then
        install -d ${DEPLOY_DIR_IMAGE}/bootpartition
        cp ${DEPLOY_DIR_IMAGE}/Image ${DEPLOY_DIR_IMAGE}/bootpartition
        cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_boot.scr ${DEPLOY_DIR_IMAGE}/bootpartition
    fi
}

addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(qoriq-arm|qoriq-arm64)"
