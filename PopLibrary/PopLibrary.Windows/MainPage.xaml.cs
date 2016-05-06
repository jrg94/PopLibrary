using PopLibrary.com.amazon.webservices;
using System;
using System.IO;
using System.Net;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;

// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=234238

namespace PopLibrary
{

    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class MainPage : Page
    {
        const string apiKey = "9e233670e5568ae80f24204b76b9aada";

        public MainPage()
        {
            this.InitializeComponent();
            submitButton.Click += SubmitButton_Click;
        }

        private void SubmitButton_Click(object sender, RoutedEventArgs e)
        {
            WebRequest wrGetURL = WebRequest.Create("http://api.upcdatabase.org/json/" + apiKey + "/" + barcodeBox.Text);

            wrGetURL.Method = "POST";

            wrGetURL.BeginGetRequestStream(RequestCallBack, wrGetURL);
        }

        void RequestCallBack(IAsyncResult result)
        {
            HttpWebRequest request = result.AsyncState as HttpWebRequest;
            Stream stream = request.EndGetRequestStream(result);
            request.BeginGetResponse(ResponceCallBack, request);
        }

        void ResponceCallBack(IAsyncResult result)
        {
            HttpWebRequest request = result.AsyncState as HttpWebRequest;
            HttpWebResponse response = request.EndGetResponse(result) as HttpWebResponse;
            using (StreamReader sr = new StreamReader(response.GetResponseStream()))
            {
                System.Diagnostics.Debug.WriteLine(sr.ReadToEnd());
            }

        }
    }
}
