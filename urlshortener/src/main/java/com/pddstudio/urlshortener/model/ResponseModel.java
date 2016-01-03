package com.pddstudio.urlshortener.model;

/**
 * This Class was created by Patrick J
 * on 03.01.16. For more Details and Licensing
 * have a look at the README.md
 */
public class ResponseModel {

    private String kind;
    private String id;
    private String longUrl;

    public ResponseModel() {}

    public String getKind() {
        return kind;
    }

    public String getId() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }
}
