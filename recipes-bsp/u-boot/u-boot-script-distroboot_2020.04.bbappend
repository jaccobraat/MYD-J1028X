FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI:append:ls1028amyd = "file://ls1028amyd_boot.cmd \
                             file://ls1028amyd_baremetal_boot.cmd"

