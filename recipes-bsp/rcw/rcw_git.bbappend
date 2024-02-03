FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI:append = " \
	file://ls1028amyd/Makefile;subdir=git \
	file://ls1028amyd/R_SQPH_0x85be;subdir=git \
	file://ls1028amyd/R_SQPH_0x85be/rcw_myd_sata.rcw;subdir=git \
	file://ls1028amyd/README;subdir=git \
"
