package com.pddstudio.urlshortener.async;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.pddstudio.urlshortener.URLShortener;
import com.pddstudio.urlshortener.Utils;
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
public class AsyncLoader extends AsyncTask<Void, Void, String> {

    final URLShortener.LoadingCallback loadingCallback;
    final String longUrl;

    public AsyncLoader(String longUrl, URLShortener.LoadingCallback loadingCallback) {
        this.loadingCallback = loadingCallback;
        this.longUrl = longUrl;
    }

    @Override
    public void onPreExecute() {
        this.loadingCallback.startedLoading();
    }

    @Override
    protected String doInBackground(Void... params) {
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

    @Override
    public void onPostExecute(String shortUrl) {
        this.loadingCallback.finishedLoading(shortUrl);
    }

}
