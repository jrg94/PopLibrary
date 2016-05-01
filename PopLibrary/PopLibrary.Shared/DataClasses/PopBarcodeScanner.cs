using System;
using System.Threading.Tasks;
using Windows.Storage.Streams;
using System.Text;
using Windows.Devices.HumanInterfaceDevice;
using Windows.Devices.Enumeration;
using System.Linq;
using Windows.Storage;

namespace PopLibrary.ClassLibrary.DataClasses
{
    public class PopBarcodeScanner
    {
        HidDevice scanner = null;

        public async void setupScanner()
        {
            ushort vendorId = 0x05FE;
            ushort productId = 0x1010;
            ushort usagePage = 0xFF00;
            ushort usageId = 0x0001;

            string selector = HidDevice.GetDeviceSelector(usagePage, usageId, vendorId, productId);

            var devices = await DeviceInformation.FindAllAsync(selector);

            if (devices.Count > 0)
            {
                scanner = await HidDevice.FromIdAsync(devices.ElementAt(0).Id, FileAccessMode.Read);
            }
            else
            {
                System.Diagnostics.Debug.WriteLine("HID device not found");
            }
        }
    }
}
