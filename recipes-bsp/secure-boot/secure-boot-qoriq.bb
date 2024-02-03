DESCRIPTION = "NXP secure bootloader for QorIQ devices"
SECTION = "bootloaders"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"


python __anonymous () {
    board="ls1028amyd"
    m = d.getVar("MACHINE")
    if m not in board:
        raise bb.parse.SkipRecipe("This platform not exit secure-boot manifest")
}

SRC_URI = "file://create_secure_boot_image.sh \
    file://memorylayout.cfg \
    file://${MACHINE}.manifest \
"

inherit deploy

#set ROOTFS_IMAGE = "fsl-image-mfgtool" in local.conf
#set KERNEL_ITS = "kernel-all.its" in local.conf
ITB_IMAGE = "fsl-image-kernelitb"
DEPENDS = "u-boot-mkimage-native qoriq-cst-native qoriq-atf"
do_deploy[depends] += "virtual/kernel:do_deploy ${ITB_IMAGE}:do_build distro-bootscr:do_deploy"

BOOT_TYPE ??= ""
BOOT_TYPE:ls1028amyd ?= "xspi sd emmc"

SECURE = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'true', 'false', d)}"

S = "${WORKDIR}"

do_deploy[nostamp] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_deploy () {
    cd ${RECIPE_SYSROOT_NATIVE}/usr/bin/cst
    cp ${S}/*.sh ./
    cp ${S}/${MACHINE}.manifest ./
    cp ${S}/memorylayout.cfg ./
    if [ ${SECURE} = "true" ]; then
        if [ ! -f srk.pri ] && [ -f ${DEPLOY_DIR_IMAGE}/srk.pri ]; then
            cp ${DEPLOY_DIR_IMAGE}/srk.pri ./
            cp ${DEPLOY_DIR_IMAGE}/srk.pub ./
        elif [ ! -f srk.pri ] && [ ! -f ${DEPLOY_DIR_IMAGE}/srk.pri ]; then
            ./gen_keys 1024
        fi
    fi

    for d in ${BOOT_TYPE}; do
        ./create_secure_boot_image.sh -m ${MACHINE} -t ${d} -d . -s ${DEPLOY_DIR_IMAGE} -o ${SECURE}
    done

}

addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(qoriq-arm|qoriq-arm64)"
