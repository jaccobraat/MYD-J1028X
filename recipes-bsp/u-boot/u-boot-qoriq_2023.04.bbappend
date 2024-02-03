FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI:append = " \
	file://configs/ls1028amyd_tfa_SECURE_BOOT_defconfig;subdir=git \
	file://configs/ls1028amyd_tfa_defconfig;subdir=git \
	file://arch/arm/dts/fsl-ls1028a-myd.dts;subdir=git \
"

