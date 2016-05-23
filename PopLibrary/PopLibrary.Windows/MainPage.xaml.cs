﻿using System;
using System.IO;
using System.Net;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Newtonsoft.Json;
using Windows.Storage;
using PopLibrary.DatabasesClasses;
using PopLibrary.UPCDatabaseModels;
using System.Threading.Tasks;

// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=234238

namespace PopLibrary
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class MainPage : Page
    {
        PopLibraryDataManager db;
        APIKeys keys;

        /// <summary>
        /// The constructor for the MainPage UI
        /// </summary>
        public MainPage()
        {
            this.InitializeComponent();
            db = new PopLibraryDataManager("PopLibrary.db");
            keys = JsonConvert.DeserializeObject<APIKeys>(ReadConfigFile().Result);
            submitButton.KeyUp += SubmitButton_KeyUp;
            SetupBookList();
        }

        /// <summary>
        /// The behavior for the submit button
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private async void SubmitButton_KeyUp(object sender, RoutedEventArgs e)
        {
            // Makes sure there is something in the textbox
            if (barcodeBox.Text.Length == 0)
            {
                contentPane.Text = "No barcode entered";
                return;
            }

            // Create an object based on the response
            UPCDatabaseObject tmp = CheckUPCDatabase().Result;
            CheckAmazonDatabase();

            // Print the temp object
            contentPane.Text = tmp.ToString();

            // Clear the barcode textbox
            barcodeBox.Text = "";

            // So we don't add an invalid book to the data base
            if (tmp.valid == false)
            {
                return;
            }

            // Attempt to create and add a book from the temp object 
            db.AddBook(new DatabasesClasses.Model.Book()
            {
                Title = tmp.itemname
            });

            // Reset the UI
            SetupBookList();
        }

        private async void CheckAmazonDatabase()
        {
            HttpWebRequest reqAmazon = (HttpWebRequest)WebRequest.Create("http://webservices.amazon.com/onca/xml?" +
                                                                         "Service=AWSECommerceService&" +
                                                                         "AWSAccessKeyId =" + keys.amazonAccessKeyId + "&" +
                                                                         "AssociateTag =[Associate ID]&" +
                                                                         "Operation=ItemLookup&" +
                                                                         "ItemId=B00008OE6I" +
                                                                         "IdType=UPC&" +
                                                                         "&Timestamp=[YYYY-MM-DDThh:mm:ssZ]" +
                                                                         "&Signature=[Request Signature]");
            HttpWebResponse resAmazon = (HttpWebResponse)(await reqAmazon.GetResponseAsync().ConfigureAwait(false));

            Stream streamAmazon = resAmazon.GetResponseStream();
            StreamReader streamReadAmazon = new StreamReader(streamAmazon);

            string responseAmazon = await streamReadAmazon.ReadToEndAsync();
            System.Diagnostics.Debug.WriteLine(responseAmazon);
        }

        /// <summary>
        /// Hits the UPCDatabase for the barcode in the UI
        /// </summary>
        /// <returns></returns>
        private async Task<UPCDatabaseObject> CheckUPCDatabase()
        {
            // Hit upcdatabase.org for the barcode
            HttpWebRequest req = (HttpWebRequest)WebRequest.Create("http://api.upcdatabase.org/json/" + keys.upcDatabaseKey + "/" + barcodeBox.Text);
            HttpWebResponse res = (HttpWebResponse)(await req.GetResponseAsync().ConfigureAwait(false));

            // Read the response
            Stream streamResponse = res.GetResponseStream();
            StreamReader streamRead = new StreamReader(streamResponse);

            // Save and print the response
            string responseString = await streamRead.ReadToEndAsync();
            System.Diagnostics.Debug.WriteLine(responseString);

            // Create an object based on the response
            return JsonConvert.DeserializeObject<UPCDatabaseObject>(responseString);
        }

        /// <summary>
        /// Displays the entire list of books in the frame
        /// </summary>
        private void SetupBookList()
        {
            bookList.ItemsSource = null;
            bookList.ItemsSource = db.QueryAllBooks();
        }

        /// <summary>
        /// Reads a local file
        /// </summary>
        /// <returns></returns>
        private async Task<string> ReadConfigFile()
        {
            var folder = Windows.ApplicationModel.Package.Current.InstalledLocation;
            var file = await folder.GetFileAsync("config.txt");
            var contents = await Windows.Storage.FileIO.ReadTextAsync(file);
            return contents;
        }
    }
}
