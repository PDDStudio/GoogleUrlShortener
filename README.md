##Google URL Shortener
An Android/Java Library to create short URL's using [Google's URL Shortener](http://goo.gl/).

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-GoogleUrlShortener-green.svg?style=true)](https://android-arsenal.com/details/1/2991)

###Demo Application Screenshot
![](https://raw.githubusercontent.com/PDDStudio/GoogleUrlShortener/master/preview.png) 
You can download the demo application [here](https://raw.githubusercontent.com/PDDStudio/GoogleUrlShortener/master/app-debug.apk)

###Get the Library
**Using Gradle:**
```
dependencies {
	//other dependencies here
	compile 'com.pddstudio:urlshortener:1.0.2'
}
```


###Using the Library

You can create short URL's either synchronous (in case you want to embedd it into an already existing asynchronous operation) or asynchroneous.

**Create short URL's synchronously**
```java
@Override
protected void doInBackground(Void... void) {
	//your other async stuff
	String longUrl = "http://somelink.com/very/long/url";
	String shortUrl = URLShortener.short(longUrl);
}
```
**Create short URL's asynchronously**
```java
public void shortUrl() {
	String longUrl = "http://somelink.com/very/long/url";
	TextView outputText = (TextView) findViewById(R.id.someTextView);
	URLShortener.shortUrl(longUrl, new URLShortener.LoadingCallback() {
                    @Override
                    public void startedLoading() {
                        outputLink.setText("Loading...");
                    }

                    @Override
                    public void finishedLoading(@Nullable String shortUrl) {
						//make sure the string is not null
                        if(shortUrl != null) outputLink.setText(shortUrl);
                        else outputLink.setText("Unable to generate Link!");
                    }
                });
}
```

###Dependencies
* [OkHttp](http://square.github.io/okhttp/)
* [gson](https://github.com/google/gson)

###Author
* Patrick J
* [Google+ Profile](http://google.com/+PatrickJung42)
* Email: patrick.pddstudio[-at-]gmail.com

###License
    Copyright 2015 Patrick J

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.