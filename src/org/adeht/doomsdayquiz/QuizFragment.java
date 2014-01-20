package org.adeht.doomsdayquiz;

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
	private Quiz mQuiz;
	private View mRootView;
	private Handler mHandler;
	private Options mOptions;

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
		mQuiz = null;
		mHandler = new Handler();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_quiz, container, false);

		mOptions = new Options(getActivity());
		if (mQuiz == null) {
			mQuiz = new Quiz();
			newDate();
		} else {
			setDateText();
		}

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

	private void newDate() {
		mQuiz.newDate(mOptions.getBoolean("quiz_this_year", true));
		setDateText();
	}

	private void setDateText() {
		TextView dateText = (TextView)mRootView.findViewById(R.id.date);
		dateText.setText(mQuiz.getDate());
	}

	private void answer(int index) {
		setAllEnabled(false);

		int dow = mQuiz.getDayOfWeek() - 1;

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

				newDate();
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
