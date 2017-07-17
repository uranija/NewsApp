package com.example.android.newsapp;

public class News {

    /** The article's title */
    private String mWebTitle;

    /** The section the article belongs to */
    private String mSectionName;

    /** Url for the article's webpage */
    private String mUrl;

    /** Publication date and time of the article */
    private String mWebPublicationDate;

    /**
     * Create a new News object
     * @param webTitle is the article's title
     * @param sectionName is the section the article belongs to
     * @param url is the article's webpage Url
     * @param webPublicationDate is the article's publication date
     */
    public News(String webTitle, String sectionName, String url, String webPublicationDate) {
        mWebTitle = webTitle;
        mSectionName = sectionName;
        mUrl = url;
        mWebPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return mWebTitle;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getPublicationDate() {
        return mWebPublicationDate;
    }
}