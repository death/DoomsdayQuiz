package org.adeht.doomsdayquiz;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CheatsheetFragment extends Fragment {
	public CheatsheetFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_cheatsheet, container, false);

		TextView staticText = (TextView)rootView.findViewById(R.id.static_text);
		staticText.setText(slurp(R.raw.cheatsheet));

		int year = Calendar.getInstance().get(Calendar.YEAR);
		TextView doomsday = (TextView)rootView.findViewById(R.id.doomsday);
		doomsday.setText(String.format("Doomsday for %d: %s", year, doomsday(year)));

		return rootView;
	}

	private String slurp(int resourceId) {
		InputStream is = getResources().openRawResource(resourceId);
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			int i = is.read();
			while (i != -1) {
				os.write(i);
				i = is.read();
			}

			is.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return os.toString();
	}

	static private String doomsday(int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.DAY_OF_MONTH, 4);
		c.set(Calendar.MONTH, Calendar.APRIL);
		return c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
	}
}
