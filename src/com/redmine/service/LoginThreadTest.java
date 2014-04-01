package com.redmine.service;


import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.*;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.params.*;
import org.mockito.*;

import com.redmine.http.*;
import com.redmine.http.HttpClientFactory;

import android.content.*;
import android.os.*;
import android.provider.SyncStateContract.Helpers;
import android.test.mock.*;
import junit.framework.*;

public class LoginThreadTest extends TestCase {
	private MockHandler mockHandler;

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

	public static class MockHandler extends Handler {
		private Message mMsg;

		@Override
		public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
			mMsg = msg;
			return super.sendMessageAtTime(msg, uptimeMillis);
		}
		
		public Message getMsg() {
			return mMsg;
		}
	}
	
	
	public void testLoginOK() {
		MockHandler mockHandler = new MockHandler();
		MockRequest mockPost    = new MockRequest();
		MockRequest mockGet     = new MockRequest();

		mockPost.result = "aaa";
		mockGet.result  = "<pre id='api-access-key' class='autoscroll'>345test</pre>";

		LoginThread target = new LoginThread(mockHandler, mockPost, mockGet);
		target.run();

		Message result = mockHandler.getMsg();
		assertEquals(LoginThread.LOGIN_SUCCESS, result.what);
		assertEquals("345test", (String)result.obj);
	}

	public void testLoginPostError() {
		MockHandler mockHandler = new MockHandler();
		MockRequest mockPost    = new MockRequest();
		MockRequest mockGet     = new MockRequest();

		mockPost.result = null;
		mockGet.result  = "<pre id='api-access-key' class='autoscroll'>345test</pre>";

		LoginThread target = new LoginThread(mockHandler, mockPost, mockGet);
		target.run();

		Message result = mockHandler.getMsg();
		assertEquals(LoginThread.LOGIN_SERVER_ERROR, result.what);
	}

	public void testLoginGetError() {
		MockHandler mockHandler = new MockHandler();
		MockRequest mockPost    = new MockRequest();
		MockRequest mockGet     = new MockRequest();

		mockPost.result = "aaa";
		mockGet.result = null;

		LoginThread target = new LoginThread(mockHandler, mockPost, mockGet);
		target.run();

		Message result = mockHandler.getMsg();
		assertEquals(LoginThread.LOGIN_SERVER_ERROR, result.what);
	}
	
	public void testLoginUsernameOrPasswordError() {
		MockHandler mockHandler = new MockHandler();
		MockRequest mockPost    = new MockRequest();
		MockRequest mockGet     = new MockRequest();

		mockPost.result = "aaa";
		mockGet.result = "aaa";

		LoginThread target = new LoginThread(mockHandler, mockPost, mockGet);
		target = spy(target);
		target.run();

		Message result = mockHandler.getMsg();
		assertEquals(LoginThread.LOGIN_ACOUNT_ERROR, result.what);
		
	}
}
