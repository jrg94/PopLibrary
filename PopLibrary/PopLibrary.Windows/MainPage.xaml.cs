using PopLibrary.com.amazon.webservices;
using System;
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

        // Use one of the following destinations, according to the region you are
        // interested in:
        // 
        //      US: ecs.amazonaws.com 
        //      CA: ecs.amazonaws.ca 
        //      UK: ecs.amazonaws.co.uk 
        //      DE: ecs.amazonaws.de 
        //      FR: ecs.amazonaws.fr 
        //      JP: ecs.amazonaws.jp
        //
        // Note: protocol must be https for signed SOAP requests.
        const String DESTINATION = "https://ecs.amazonaws.com/onca/soap?Service=AWSECommerceService";

        // Set your AWS Access Key ID and AWS Secret Key here.
        // You can obtain them at:
        // http://aws-portal.amazon.com/gp/aws/developer/account/index.html?action=access-key
        const String MY_AWS_ID = "AKIAJTPD7BGTWVVQLASQ";
        const String MY_AWS_SECRET = "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";

        public MainPage()
        {
            this.InitializeComponent();
            submitButton.Click += SubmitButton_Click;
        }

        private void SubmitButton_Click(object sender, RoutedEventArgs e)
        {
            System.Diagnostics.Debug.WriteLine("BookSearch v1.0");

            AWSECommerceService service = new AWSECommerceService();

            ItemSearch request = new ItemSearch();

            request.SubscriptionId = "0525E2PQ81DD7ZTWTK82";

            request.Request = new ItemSearchRequest[1];
            request.Request[0] = new ItemSearchRequest();
            request.Request[0].ResponseGroup = new string[] { "Small" };
            request.Request[0].SearchIndex = "Books";
            request.Request[0].Author = "Tom Clancy";

            ItemSearchResponse response = service.ItemSearch(request);

            System.Diagnostics.Debug.WriteLine(response.Items[0].Item.Length + " books written by Tom Clancy found.");
        }

        private void submitButton_Click_1(object sender, RoutedEventArgs e)
        {

        }
    }
}
