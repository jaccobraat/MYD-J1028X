FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI:append = " \
	file://ls1028amyd/Makefile;subdir=rcw-git \
	file://ls1028amyd/R_SQPH_0x85be;subdir=rcw-git \
	file://ls1028amyd/R_SQPH_0x85be/rcw_myd_sata.rcw;subdir=rcw-git \
	file://ls1028amyd/README;subdir=rcw-git \
"
