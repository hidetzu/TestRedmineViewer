package com.redmine.util;

import android.test.AndroidTestCase;
import java.util.Calendar;

public class StringHelperTest extends AndroidTestCase {

	public void testConvertToDateString() {
		StringHelper helper = new StringHelper();
		Calendar call = Calendar.getInstance();
		call.set(Calendar.YEAR, 2000);
		call.set(Calendar.MONTH, 2);
		call.set(Calendar.DATE, 3);
		call.set(Calendar.HOUR_OF_DAY, 12);
		call.set(Calendar.MINUTE, 15);
		call.set(Calendar.SECOND, 3);

		String result = helper.convertToDateString(call);
		assertEquals("2000-2-3 12:15:3", result);

	}

}
