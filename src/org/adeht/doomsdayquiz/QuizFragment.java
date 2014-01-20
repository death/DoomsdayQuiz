package org.adeht.doomsdayquiz;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class QuizFragment extends Fragment {
	private View mRootView;
	private Calendar mChosenDay;
	private Handler mHandler;

	final private int[] mAnswerButtons = new int[] {
			R.id.sunday,
			R.id.monday,
			R.id.tuesday,
			R.id.wednesday,
			R.id.thursday,
			R.id.friday,
			R.id.saturday
	};

	public QuizFragment() {
		mHandler = new Handler();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_quiz, container, false);

		newQuiz();

		for (int i = 0; i < mAnswerButtons.length; i++) {
			int buttonId = mAnswerButtons[i];
			Button button = (Button)mRootView.findViewById(buttonId);
			final int index = i;
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					answer(index);
				}
			});
		}

		return mRootView;
	}

	static private String formatDate(Calendar date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		return format.format(date.getTime());
	}

	static private Calendar startOfYear() {
		Calendar day = Calendar.getInstance();
		day.set(day.get(Calendar.YEAR), Calendar.JANUARY, 1);
		return day;
	}

	static private Calendar chooseRandomDay() {
		Calendar day = startOfYear();
		int daysInYear = day.getActualMaximum(Calendar.DAY_OF_YEAR);
		int k = (int)Math.floor(Math.random() * daysInYear);
		day.add(Calendar.DAY_OF_YEAR, k);
		return day;
	}

	private void newQuiz() {
		mChosenDay = chooseRandomDay();

		TextView dateText = (TextView)mRootView.findViewById(R.id.date);
		dateText.setText(formatDate(mChosenDay));
	}

	private void answer(int index) {
		setAllEnabled(false);

		int dow = mChosenDay.get(Calendar.DAY_OF_WEEK) - 1;

		final Button goodButton = (Button)mRootView.findViewById(mAnswerButtons[dow]);
		final Drawable goodButtonBackground = goodButton.getBackground();
		goodButton.setBackgroundColor(Color.GREEN);

		final Button selectedButton = (Button)mRootView.findViewById(mAnswerButtons[index]);
		final Drawable selectedButtonBackground = selectedButton.getBackground();
		if (goodButton != selectedButton) {
			selectedButton.setBackgroundColor(Color.RED);
		}

		mHandler.postDelayed(new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				goodButton.setBackgroundDrawable(goodButtonBackground);
				if (goodButton != selectedButton)
					selectedButton.setBackgroundDrawable(selectedButtonBackground);

				setAllEnabled(true);
				newQuiz();
			}
		}, 2000);
	}

	private void setAllEnabled(boolean enabled) {
		for (int i = 0; i < mAnswerButtons.length; i++) {
			Button button = (Button)mRootView.findViewById(mAnswerButtons[i]);
			button.setEnabled(enabled);
		}
	}
}
