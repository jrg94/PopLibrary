using System;

namespace PopLibrary.ClassLibrary
{

    /// <summary>
    /// An interface for different types of media
    /// Allows us to store different types of media while
    /// having them all share similar functionality
    /// </summary>
    abstract class PopMedia
    {
        private String title;
        private String summary;

        public PopMedia(String title, String summary)
        {
            this.title = title;
            this.summary = summary;
        }

        public String getTitle()
        {
            return title;
        }

        public String getSummary()
        {
            return summary;
        }
    }
}
