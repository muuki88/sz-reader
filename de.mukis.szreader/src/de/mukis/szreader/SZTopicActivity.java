package de.mukis.szreader;

import java.util.ArrayList;
import java.util.Calendar;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import de.mukis.szreader.adapter.StatusAdapter;

public class SZTopicActivity extends ListActivity {

	ResponseList<Status> timeline;
	ArrayList<String> urls;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView lv = getListView();
		Bundle extras = getIntent().getExtras();
		String topic = extras.get("topic").toString();
		System.out.println(topic);
		try {
			loadTweets(topic);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String text = timeline.get(position).getText();
				String url = text.substring(text.indexOf("http")).trim();
				if (url.indexOf(' ') > 0)
					url = url.substring(0, url.indexOf(" ")).trim();
				Intent intent = new Intent(getApplicationContext(),
						SZArticelActivity.class);
				intent.putExtra("url", url);
				startActivity(intent);
			}
		});
	}

	private void loadTweets(String topic) throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -2);
		Paging paging = new Paging(cal.getTimeInMillis());
		timeline = twitter.getUserTimeline(topic, paging);
		StatusAdapter adapter = new StatusAdapter(this, android.R.layout.simple_list_item_1, timeline);
		setListAdapter(adapter);
	}
}
