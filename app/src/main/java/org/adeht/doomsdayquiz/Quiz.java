package org.adeht.doomsdayquiz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Quiz {
	Calendar mDate;

	public Quiz() {
	}

	public void newDate(boolean quizThisYear) {
		int year = quizThisYear ? 0 : chooseRandomYear();
		mDate = chooseRandomDate(year);
	}

	public String getDate() {
		return formatDate(mDate);
	}

	public int getDayOfWeek() {
		return mDate.get(Calendar.DAY_OF_WEEK);
	}

	static private String formatDate(Calendar date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		return format.format(date.getTime());
	}

	static private Calendar startOfYear(int year) {
		Calendar day = Calendar.getInstance();
		day.set(year == 0 ? day.get(Calendar.YEAR) : year, Calendar.JANUARY, 1);
		return day;
	}

	static private Calendar chooseRandomDate(int year) {
		Calendar day = startOfYear(year);
		int daysInYear = day.getActualMaximum(Calendar.DAY_OF_YEAR);
		int k = (int)Math.floor(Math.random() * daysInYear);
		day.add(Calendar.DAY_OF_YEAR, k);
		return day;
	}

	static private int chooseRandomYear() {
		int endYear = 2099;
		int startYear = 1900;
		return (int)Math.floor(Math.random() * (endYear - startYear + 1)) + startYear;
	}
}
