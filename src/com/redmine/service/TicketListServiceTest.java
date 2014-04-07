package com.redmine.service;

import com.redmine.data.Acount;
import com.redmine.http.*;

import com.redmine.TestUtil;
import android.os.*;
import junit.framework.*;

public class TicketListServiceTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public class MockRequest implements Request {
		public String result;

		public String execute() {
			return result;
		}
	}

	public void testOK() throws NoSuchFieldException, IllegalAccessException {
		MockHandler mockHandler = new MockHandler();
		MockRequest mockGet     = new MockRequest();
		mockGet.result = "aaa";

		Acount acount = new Acount("http://test.com","hoge","hoge2hoge");
		TicketListService target = new TicketListService(mockHandler, acount, "test");
		TestUtil.setValue((Object)target, "mGet",  mockGet);
		target.run();
	}
}
