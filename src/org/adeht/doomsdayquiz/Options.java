package org.adeht.doomsdayquiz;

import android.content.Context;
import android.content.SharedPreferences;

public class Options {
	final static private String PREFS_NAME = "org.adeht.doomsdayquiz.prefs";

	private SharedPreferences mPrefs;

	public Options(Context context) {
		mPrefs = context.getSharedPreferences(PREFS_NAME, 0);
	}

	public boolean getBoolean(String name, boolean defaultValue) {
		return mPrefs.getBoolean(name, defaultValue);
	}

	public void setBoolean(String name, boolean value) {
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putBoolean(name, value);
		editor.commit();
	}
}
