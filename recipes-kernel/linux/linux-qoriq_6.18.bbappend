FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI:append = " \
	file://arch/arm64/boot/dts/freescale/fsl-ls1028a-myd-dpdk.dts \
	file://arch/arm64/boot/dts/freescale/fsl-ls1028a-myd.dts \
	file://arch/arm64/configs/lsdk.configx \
"


do_move_dts_files() {
        bbwarn "copy files:${UNPACKDIR}/arch ${STAGING_KERNEL_DIR}" 
        cp -vr ${UNPACKDIR}/arch ${STAGING_KERNEL_DIR}
}

#do_kernel_checkout[postfuncs] += "do_move_dts_files"
do_kernel_metadata[prefuncs] += "do_move_dts_files"