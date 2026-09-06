ACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    libvirt \
    libvirt-libvirtd \
    libvirt-virsh \
    lxc \
    qemu \
"

PREFERRED_RPROVIDER_qemu = "qemu-qoriq" 