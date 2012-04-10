package de.mukis.szreader;

import java.util.Arrays;
import java.util.Calendar;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SZReaderActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView topicsList = (ListView) findViewById(R.id.topicsList);


		String topicEconomic = getString(R.string.topic_economic);
		System.out.println(topicEconomic);
		String[] topics = getResources().getStringArray(R.array.topics);
		System.out.println(Arrays.toString(topics));
		String[] values = new String[] { topicEconomic };
		topicsList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Object item = parent.getItemAtPosition(position);
				onClick(view, item);
			}
		});


		// First paramenter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the View to which the data is written
		// Forth - the Array of data
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
		topicsList.setAdapter(adapter);

	}

	public void onClick(View view, Object item) {
		Intent i = new Intent(this, SZTopicActivity.class);
		i.putExtra("topic", item.toString());
		Toast.makeText(getApplicationContext(), "Click ListItem Number " + item, Toast.LENGTH_SHORT).show();
		startActivity(i);
	}
	
	public void loadTwitter() {
		Twitter twitter = TwitterFactory.getSingleton();
		try {
			twitter.verifyCredentials();
			ResponseList<User> users = twitter.lookupUsers(new String[] { "SZ_Politik" });
			for (User user : users) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_YEAR, -2);

				Paging paging = new Paging(cal.getTimeInMillis());
				ResponseList<Status> timeline = twitter.getUserTimeline(user.getId(), paging);
				
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
}