package com.markfront.SPFH_RestApi;

public class PageHtmlData {
    private final long id;
    private final String url;
    private final String html;

    public PageHtmlData(long id, String url, String html) {
        this.id = id;
        this.url = url;
        this.html = html;
    }
    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml() {
        return html;
    }
}
