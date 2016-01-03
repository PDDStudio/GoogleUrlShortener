package com.pddstudio.urlshortener.model;

/**
 * This Class was created by Patrick J
 * on 03.01.16. For more Details and Licensing
 * have a look at the README.md
 */
public class RequestModel {

    private String longUrl;

    public RequestModel() {}

    public RequestModel(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }
}
