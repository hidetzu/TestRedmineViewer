package com.redmine.ui;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

public class BootActivityTest extends ActivityUnitTestCase<BootActivity> {

	public BootActivityTest() {
		super(BootActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreate() {
		BootActivity activity = startActivity(new Intent(), null, null);
	}
}
