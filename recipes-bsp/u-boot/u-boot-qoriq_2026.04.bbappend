FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI:append = " \
	file://configs/ls1028amyd_tfa_SECURE_BOOT_defconfig;subdir=u-boot-qoriq-2026.04++fslgit \
	file://configs/ls1028amyd_tfa_defconfig;subdir=u-boot-qoriq-2026.04++fslgit \
	file://arch/arm/dts/fsl-ls1028a-myd.dts;subdir=u-boot-qoriq-2026.04++fslgit \
"

