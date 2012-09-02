package de.mukis.szreader;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class SZArticelActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szarticle);
        
        WebView wv = (WebView) findViewById(R.id.articleArea);
        String url = getIntent().getExtras().get("url").toString();
        wv.loadUrl(url);
    }
	

}
