using PopLibrary.ClassLibrary.DataClasses;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Core;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=234238

namespace PopLibrary
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class MainPage : Page
    {

        public MainPage()
        {
            this.InitializeComponent();
            PopBarcodeScanner pbs = new PopBarcodeScanner();
            //SetScannerMode(true);
        }

        /// <summary>
        /// Sets up the KeyDown listener
        /// </summary>
        /// <param name="scannerMode"></param>
        private void SetScannerMode(bool scannerMode)
        {
            if (scannerMode)
            {
                CoreWindow.GetForCurrentThread().KeyDown += MainPage_KeyDown;
            }
            else
            {
                CoreWindow.GetForCurrentThread().KeyDown += null;
            }
        }

        private void MainPage_KeyDown(CoreWindow sender, KeyEventArgs args)
        {
            Debug.WriteLine("DOWN:" + args.VirtualKey.ToString());
        }

    }
}
