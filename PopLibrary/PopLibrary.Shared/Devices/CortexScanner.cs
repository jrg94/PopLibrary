using System;
using System.Collections.Generic;
using System.Text;

namespace PopLibrary.Devices
{
    /// <summary>
    /// Represents a barcode scanner device
    /// Some advice: Do not purchase this scanner unless
    /// you literally only want to enter barcodes into a spreadsheet
    /// Using this thing as been a complete and total nightmare because
    /// it is not HID-Scanner compliant
    /// The computer thinks its a keyboard and it uses pages that
    /// are blocked in the HIDDevice library in .NET
    /// As a result, you have to use RawInput to handle it which
    /// basically means writing threads at near C level code to 
    /// handle the keyboard data stream and the barcode data stream 
    /// </summary>
    class CortexScanner
    {
        public const UInt16 Vid = 0x05FE;
        public const UInt16 Pid = 0x1010;
        public const UInt16 UsagePage = 0x0001;
        public const UInt16 UsageId = 0x0006;
    }
}
