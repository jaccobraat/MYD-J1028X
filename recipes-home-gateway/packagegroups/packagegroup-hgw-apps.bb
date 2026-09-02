ACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    bluez5 \
    mosquitto \
