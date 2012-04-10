package de.mukis.szreader.adapter;

import java.util.List;

import de.mukis.szreader.R;

import twitter4j.Status;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StatusAdapter extends ArrayAdapter<Status> {

	private final List<Status> timeline;
	private final Context context;

	public StatusAdapter(Context context, int textViewResourceId, List<Status> timeline) {
		super(context, textViewResourceId, timeline);
		this.context = context;
		this.timeline = timeline;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		textView.setText(timeline.get(position).getText());
		
		// Change the icon for Windows and iPhone
		String user = timeline.get(position).getUser().getScreenName();
		imageView.setImageResource(R.drawable.politik);
		
		return rowView;
	}
}
