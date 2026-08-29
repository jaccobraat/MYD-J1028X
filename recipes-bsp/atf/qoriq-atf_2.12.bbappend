FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI:append = " \
	file://plat/nxp/soc-ls1028a/ls1028amyd/policy.h;subdir=git \
	file://plat/nxp/soc-ls1028a/ls1028amyd/ddr_init.c;subdir=git \
	file://plat/nxp/soc-ls1028a/ls1028amyd/platform.c;subdir=git \
	file://plat/nxp/soc-ls1028a/ls1028amyd/platform_def.h;subdir=git \
	file://plat/nxp/soc-ls1028a/ls1028amyd/platform.mk;subdir=git \
	file://plat/nxp/soc-ls1028a/ls1028amyd/plat_def.h;subdir=git \
"
