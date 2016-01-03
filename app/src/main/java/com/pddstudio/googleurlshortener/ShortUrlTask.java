package com.pddstudio.googleurlshortener;

import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.pddstudio.urlshortener.URLShortener;

/**
 * This Class was created by Patrick J
 * on 03.01.16. For more Details and Licensing
 * have a look at the README.md
 */
public class ShortUrlTask extends AsyncTask<Void, Void, String> {

    final String url;
    TextView textView;

    public ShortUrlTask(String url) {
        this.url = url;
    }

    public ShortUrlTask displayIn(TextView textView) {
        this.textView = textView;
        return this;
    }

    @Override
    protected String doInBackground(Void... params) {
        return URLShortener.shortUrl(url);
    }

    @Override
    public void onPostExecute(String str) {
        Log.d("ShortUrlTask", "Done with task.");
        if(str != null && textView != null) textView.setText(Html.fromHtml("<a href='" + str + "'>" + str + "</a>"));
    }

}
