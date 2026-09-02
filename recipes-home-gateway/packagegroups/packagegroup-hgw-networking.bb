ACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    curl \
    ntp sntp \
    wget \
    net-tools \
    tcpdump \
    bridge-utils \
    ethtool \
    iproute2 \
    iproute2-bridge \
    iproute2-tc \
    iptables \
    iputils \
    inetutils \
    inetutils-hostname \
    inetutils-ifconfig \
    inetutils-logger \
    inetutils-ping \
    inetutils-ping6 \
    inetutils-rsh \
    inetutils-telnet \
    inetutils-traceroute \
    netcat \
    fping \
    dnsmasq \
    freeradius \
"