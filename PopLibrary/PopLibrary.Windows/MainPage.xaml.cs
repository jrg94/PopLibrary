using System;
using System.IO;
using System.Net;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Newtonsoft.Json;
using PopLibrary.DatabasesClasses;

// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=234238

namespace PopLibrary
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class MainPage : Page
    {
        const string apiKey = "9e233670e5568ae80f24204b76b9aada";

        PopLibraryDataManager db;

        public MainPage()
        {
            this.InitializeComponent();
            db = new PopLibraryDataManager("PopLibrary.db");
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
            HttpWebRequest req = (HttpWebRequest)WebRequest.Create("http://api.upcdatabase.org/json/" + apiKey + "/" + barcodeBox.Text);
            HttpWebResponse res = (HttpWebResponse)(await req.GetResponseAsync());

            Stream streamResponse = res.GetResponseStream();
            StreamReader streamRead = new StreamReader(streamResponse);

            string responseString = await streamRead.ReadToEndAsync();

            UPCDatabaseObject tmp = JsonConvert.DeserializeObject<UPCDatabaseObject>(responseString);

            System.Diagnostics.Debug.WriteLine(responseString);
            contentPane.Text = tmp.ToString();

            db.AddBook(new DatabasesClasses.Model.Book()
            {
                Title = "The legend of pizza"
            });
            SetupBookList();
        }

        /// <summary>
        /// Displays the entire list of books in the frame
        /// </summary>
        private void SetupBookList()
        {
            bookList.ItemsSource = null;
            bookList.ItemsSource = db.QueryAllBooks();
        }
    }
}
