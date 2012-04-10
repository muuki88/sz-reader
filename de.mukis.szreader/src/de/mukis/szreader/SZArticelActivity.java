package de.mukis.szreader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SZArticelActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.articel);
		
		TextView articleHeader = (TextView) findViewById(R.id.articleHeader);
//		articleHeader.setText(header);
	}
	

}
