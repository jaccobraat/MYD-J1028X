FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI:append = " \
	file://arch/arm64/boot/dts/freescale/fsl-ls1028a-myd-dpdk.dts \
	file://arch/arm64/boot/dts/freescale/fsl-ls1028a-myd.dts \
	file://fragment.cfg \
"

/*
file://arch/arm64/configs/lsdk.config \
*/

DELTA_KERNEL_DEFCONFIG += " fragment.cfg"

do_move_dts_files() {
        bbwarn "copy files:${UNPACKDIR}/arch ${STAGING_KERNEL_DIR}" 
        cp -vr ${UNPACKDIR}/arch ${STAGING_KERNEL_DIR}
}

do_kernel_checkout[postfuncs] += "do_move_dts_files"
