package com.pddstudio.urlshortener;

import okhttp3.MediaType;

/**
 * This Class was created by Patrick J
 * on 03.01.16. For more Details and Licensing
 * have a look at the README.md
 */
public final class Utils {

    /**
     * YOU CAN REPLACE THIS WITH YOUR OWN API KEY IF YOU WANT TO USE YOUR OWN KEY.
     */
    public static final String API_KEY = "AIzaSyAHp3aO2sNnaZwTOEdaXZmf-kaZ3ohI4xU";

    public static final String BASE_URL = "https://www.googleapis.com/urlshortener/v1/url?key=";
    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

}
