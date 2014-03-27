package com.redmine.http;

import java.util.HashMap;

import android.test.AndroidTestCase;

public class RequestURLFactoryTest extends AndroidTestCase {
	
	public void test_userlist_1() {
		RequestURLFactory target = new RequestURLFactory();
		HashMap<String, String> params = new HashMap<String, String>();
    	params.put("key", "a12345b");
		
		String result = target.getURL(RequestURLFactory.RequestType.USERLIST,
				"http://www.hidenet.mydns.jp:80/redmine", params);
		assertEquals("http://www.hidenet.mydns.jp:80/redmine/users.xml?key=a12345b", result);
	}

	public void test_ticketlist_1() {
		RequestURLFactory target = new RequestURLFactory();

		HashMap<String, String> params = new HashMap<String, String>();
    	params.put("key", "a12345b");
    	
    	
		String result = target.getURL(RequestURLFactory.RequestType.TICKETLIST,
				"http://www.hidenet.mydns.jp:80/redmine", params);
		assertEquals("http://www.hidenet.mydns.jp:80/redmine/issues.xml?key=a12345b", result);	
	}
	
	public void test_ticketlist_2() {
		RequestURLFactory target = new RequestURLFactory();

		HashMap<String, String> params = new HashMap<String, String>();
    	params.put("key", "c12345b");
    	
    	
		String result = target.getURL(RequestURLFactory.RequestType.TICKETLIST,
				"http://www.hidenet.mydns.jp:80/redmine", params);
		assertEquals("http://www.hidenet.mydns.jp:80/redmine/issues.xml?key=c12345b", result);	
	}	
	
	public void test_ticketdetail() {
		RequestURLFactory target = new RequestURLFactory();
		HashMap<String, String> params = new HashMap<String, String>();
    	params.put("key", "c12345b");
    	params.put("id",  "5");
		
		String result = target.getURL(RequestURLFactory.RequestType.TICKETDETAIL,
				"http://www.hidenet.mydns.jp:80/redmine", params);
		assertEquals(
		 "http://www.hidenet.mydns.jp:80/redmine/issues/5.xml?key=c12345b&include=journals",
		 result);		
	}	
}
