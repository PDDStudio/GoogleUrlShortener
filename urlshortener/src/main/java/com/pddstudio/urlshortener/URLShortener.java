package com.pddstudio.urlshortener;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.pddstudio.urlshortener.async.AsyncLoader;
import com.pddstudio.urlshortener.async.AsyncLoader2;
import com.pddstudio.urlshortener.model.RequestModel;
import com.pddstudio.urlshortener.model.ResponseModel;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * This Class was created by Patrick J
 * on 03.01.16. For more Details and Licensing
 * have a look at the README.md
 */
public class URLShortener {

    /**
     * Loading Callback for {@link #shortUrl(String, LoadingCallback)}
     */
    public interface LoadingCallback {
        void startedLoading();
        void finishedLoading(@Nullable String shortUrl);
    }

    /**
     * Create a short Link for the given Url. This call should be executed asynchronously.
     * Otherwise use {@link #shortUrl(String, LoadingCallback)}
     * @param longUrl - The Long Url
     * @return The shortened URL or null
     */
    public static String shortUrl(String longUrl) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new Gson();

        RequestModel requestModel = new RequestModel(longUrl);
        String postBody = gson.toJson(requestModel);
        Request request = new Request.Builder()
                .url(Utils.BASE_URL + Utils.API_KEY)
                .post(RequestBody.create(Utils.MEDIA_TYPE, postBody))
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            if(!response.isSuccessful()) return null;
            String responseStr = response.body().string();
            ResponseModel responseModel = gson.fromJson(responseStr, ResponseModel.class);
            return responseModel.getId();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }

    /**
     * Create a short link for the given URL. This call is executed asynchronously and delivers the result to the given callback.
     * @param longUrl - The long Url which should be shortened
     * @param loadingCallback - The callback where the result will be given to
     */
    public static void shortUrl(String longUrl, LoadingCallback loadingCallback) {
        new AsyncLoader(longUrl, loadingCallback).execute();
    }

    /**
     * Get a long URL back for the given short URL. This call is executed asynchronously and delivers the result to the given callback.
     * @param shortUrl - The short Url for which we should get back the long Url
     * @param loadingCallback - The callback where the result will be given to
     */
    public static void longUrl(String shortUrl, LoadingCallback loadingCallback) {
        new AsyncLoader2(shortUrl, loadingCallback).execute();
    }

}
