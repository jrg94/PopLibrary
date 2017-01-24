using System;
using System.Collections.Generic;
using System.Text;

namespace PopLibrary.UPCDatabaseModels
{
    class APIKeys
    {
        public string upcDatabaseKey { get; set; }
        public string digitEyesAppKey { get; set; }
        public string digitEyesAuthorizationKey { get; set; }
        public string amazonAccessKeyId { get; set; }
        public string amazonSecretAccessKey { get; set; }

        public override string ToString()
        {
            return string.Format("UPC Database Key: {0}\n" +
                                 "Digit Eyes App Key: {1}\n" +
                                 "Digit Eyes Authorization Key: {2}\n" +
                                 "Amazon Access Key ID: {3}\n" +
                                 "Amazon Secret Access Key: {4}",
                                 upcDatabaseKey,
                                 digitEyesAppKey,
                                 digitEyesAuthorizationKey,
                                 amazonAccessKeyId,
                                 amazonSecretAccessKey);
        }
    }
}
