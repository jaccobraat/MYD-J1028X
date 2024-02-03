FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI:append = " \
	file://arch/arm64/boot/dts/freescale/fsl-ls1028a-myd-dpdk.dts;subdir=git \
	file://arch/arm64/boot/dts/freescale/fsl-ls1028a-myd.dts;subdir=git \
	file://arch/arm64/configs/lsdk.config;subdir=git \
"
