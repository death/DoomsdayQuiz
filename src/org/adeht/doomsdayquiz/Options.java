package org.adeht.doomsdayquiz;

import android.content.Context;
import android.content.SharedPreferences;

public class Options {
	final static private String PREFS_NAME = "org.adeht.doomsdayquiz.prefs";

	private SharedPreferences mPrefs;

	public Options(Context context) {
		mPrefs = context.getSharedPreferences(PREFS_NAME, 0);
	}

	public boolean getQuizThisYear() {
		return getBoolean("quiz_this_year", true);
	}

	public void setQuizThisYear(boolean newValue) {
		setBoolean("quiz_this_year", newValue);
	}

	public void resetCurrentStreak() {
		setInt("current_streak", 0);
	}

	public int getCurrentStreak() {
		return getInt("current_streak", 0);
	}

	public void resetLongestStreak() {
		setInt("longest_streak", 0);
	}

	public int getLongestStreak() {
		return getInt("longest_streak", 0);
	}

	public void extendCurrentStreak() {
		int current = getCurrentStreak();
		int longest = getLongestStreak();

		int newCurrent = current + 1;
		int newLongest = Math.max(longest, newCurrent);

		setInt("current_streak", newCurrent);
		if (newLongest > longest)
			setInt("longest_streak", newLongest);
	}

	private boolean getBoolean(String name, boolean defaultValue) {
		return mPrefs.getBoolean(name, defaultValue);
	}

	private void setBoolean(String name, boolean value) {
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putBoolean(name, value);
		editor.commit();
	}

	private int getInt(String name, int defaultValue) {
		return mPrefs.getInt(name, defaultValue);
	}

	private void setInt(String name, int value) {
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putInt(name, value);
		editor.commit();
	}
}
