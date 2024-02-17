# MYD-J1028X
MYiR development board MYD-J1028X

# PROBLEM1: SATA
Lots of frustration getting SATA to work. 
Changing RCW from 0x85bb to 0x85be was not enough.

In "AN43 M.2 Pinout Description and Reference Designs" :
If the M.2 socket is used for a SATA based storage device, pin 43 must be connected
to the negative signal of the differential pair used for SATA Rx.  

i.e. PCIE0_RX+/PCIE0_RX- needs to be swapped for SATA  

Found a solution for that in the "QorIQ LS1028A Reference Manual"  
Control Register LNDGCR1 bit 31 RDAT_INV: Invert Rx data. Has the same effect as swapping SD_RX[m]_P and SD_RX[m]_N.  
Base address = 01EA0000h offset 8C4h   

Added his to RCW:  
.pbi   
write 0x01ea08c4, 0x8445002a  
.end  

# PROBLEM2: Bluetooth
I can't get bluetooth to work on 1XL M.2 Wifi module with Murata chip.  
Looking at the EVK Hardware User Guide it looks like a faulty design:
Pin 22 om M.2 slot is connected to UARTA_TX on the board and BT_UART_TXD on the Wifi module 
Pin 32 om M.2 slot is connected to UARTA_RX on the board and BT_UART_RXD on the Wifi module 




### u-boot device tree
Read MAC addresses from eeprom offset 0x40 and 0x48

=> i2c md 50 0.2  64   

        0000: 4d 59 43 2d 4a 31 30 32 38 4e 2d 38 45 32 44 2d    MYC-J1028N-8E2D-  
        0010: 31 35 30 2d 49 ff ff ff ff ff ff ff ff ff ff ff    150-I........... 
        0020: 57 4b 32 30 32 32 30 34 31 39 30 31 31 30 31 32    WK20220419011012
        0030: 35 ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff    5...............
        0040: 38 d5 47 00 28 e7 ff ff 38 d5 47 00 28 e8 ff ff    8.G.(...8.G.(...

&i2c0 {  
  eeprom: at24@50 {  
    #address-cells = <1>;  
    #size-cells = <1>;  
    reg = <0x50>;  
  
		eth0_addr: mac-address@40 {  
		    reg = <0x40 6>;  
		};  
        };  
};

&enetc_port0 {  
	nvmem-cells = <&eth0_addr>;  
	nvmem-cell-names = "mac-address";  
} 