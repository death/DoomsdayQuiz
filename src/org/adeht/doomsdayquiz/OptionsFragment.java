package org.adeht.doomsdayquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class OptionsFragment extends Fragment {
	public OptionsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_options, container, false);

		final Options options = new Options(getActivity());
		final CheckBox checkBox = (CheckBox)rootView.findViewById(R.id.checkbox_quiz_this_year);
		checkBox.setChecked(options.getQuizThisYear());
		checkBox.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				boolean checked = checkBox.isChecked();
				options.setQuizThisYear(checked);
			}
		});

		return rootView;
	}
}
