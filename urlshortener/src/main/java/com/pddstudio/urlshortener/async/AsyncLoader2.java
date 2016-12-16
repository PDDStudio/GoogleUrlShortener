package com.pddstudio.urlshortener.async;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.pddstudio.urlshortener.URLShortener;
import com.pddstudio.urlshortener.Utils;
import com.pddstudio.urlshortener.model.ResponseModel;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncLoader2 extends AsyncTask<Void, Void, String> {

    final URLShortener.LoadingCallback loadingCallback;
    final String shortUrl;

    public AsyncLoader2(String shortUrl, URLShortener.LoadingCallback loadingCallback) {
        this.loadingCallback = loadingCallback;
        this.shortUrl = shortUrl;
    }

    @Override
    public void onPreExecute() {
        this.loadingCallback.startedLoading();
    }

    @Override
    protected String doInBackground(Void... params) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new Gson();

        Request request = new Request.Builder()
                .url(Utils.BASE_URL + Utils.API_KEY + "&shortUrl=" + shortUrl)
                .get()
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            if(!response.isSuccessful()) return null;
            String responseStr = response.body().string();
            ResponseModel responseModel = gson.fromJson(responseStr, ResponseModel.class);
            return responseModel.getLongUrl();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }

    @Override
    public void onPostExecute(String longUrl) {
        this.loadingCallback.finishedLoading(longUrl);
    }

}
