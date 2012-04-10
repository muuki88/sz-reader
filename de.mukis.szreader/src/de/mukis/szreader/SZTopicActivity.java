package de.mukis.szreader;

import java.util.Calendar;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import android.app.ListActivity;
import android.os.Bundle;
import de.mukis.szreader.adapter.StatusAdapter;

public class SZTopicActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		String topic = extras.get("topic").toString();
		try {
			loadTweets(topic);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	private void loadTweets(String topic) throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -2);
		Paging paging = new Paging(cal.getTimeInMillis());
		ResponseList<Status> timeline = twitter.getUserTimeline("SZ_Politik", paging);
		StatusAdapter adapter = new StatusAdapter(this, android.R.layout.simple_list_item_1, timeline);
		setListAdapter(adapter);
	}
}
