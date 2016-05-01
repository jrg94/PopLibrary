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
            ushort usagePage = 0x0001;
            ushort usageId = 0x0006;

            string selector = HidDevice.GetDeviceSelector(usagePage, usageId, vendorId, productId);

            var devices = await DeviceInformation.FindAllAsync(selector);

            if (devices.Count > 0)
            {
                //scanner = ;
                System.Diagnostics.Debug.WriteLine(devices.Count + " HID device found!");
                ReadWriteToHidDevice(scanner = await HidDevice.FromIdAsync(devices.ElementAt(0).Id, FileAccessMode.Read));
            }
            else
            {
                System.Diagnostics.Debug.WriteLine("HID device not found");
            }
        }

        private async void ReadWriteToHidDevice(HidDevice device)
        {
            if (device != null)
            {
                // construct a HID output report to send to the device
                HidOutputReport outReport = device.CreateOutputReport();

                /// Initialize the data buffer and fill it in
                byte[] buffer = new byte[] { 10, 20, 30, 40 };

                DataWriter dataWriter = new DataWriter();
                dataWriter.WriteBytes(buffer);

                outReport.Data = dataWriter.DetachBuffer();

                // Send the output report asynchronously
                await device.SendOutputReportAsync(outReport);

                //
                // Sent output report successfully 
                // Now lets try read an input report 
                //
                HidInputReport inReport = await device.GetInputReportAsync();

                if (inReport != null)
                {
                    UInt16 id = inReport.Id;
                    var bytes = new byte[4];
                    DataReader dataReader = DataReader.FromBuffer(inReport.Data);
                    dataReader.ReadBytes(bytes);
                }
                else
                {
                    System.Diagnostics.Debug.WriteLine("Invalid input report received");
                }
            }
            else
            {
                System.Diagnostics.Debug.WriteLine("Device is null");
            }
        }
    }
}
