package com.redmine.service;


import junit.framework.TestCase;
import android.os.Message;

import com.redmine.TestUtil;
import com.redmine.data.Acount;
import com.redmine.http.Request;

public class LoginServiceTest extends TestCase {

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
	
	public void testLoginOK() throws NoSuchFieldException, IllegalAccessException {
		MockHandler mockHandler = new MockHandler();
		MockRequest mockPost    = new MockRequest();
		MockRequest mockGet     = new MockRequest();

		mockPost.result = "aaa";
		mockGet.result  = "<pre id='api-access-key' class='autoscroll'>345test</pre>";

		Acount acount = new Acount("http://test.com","hoge","hoge2hoge");
		LoginService target = new LoginService(mockHandler, acount);

		TestUtil.setValue((Object)target, "mPost", mockPost);
		TestUtil.setValue((Object)target, "mGet",  mockGet);

		target.run();

		Message result = mockHandler.getMsg();
		assertEquals(LoginService.LOGIN_SUCCESS, result.what);
		assertEquals("345test", (String)result.obj);
	}

	public void testLoginPostError() throws NoSuchFieldException, IllegalAccessException {
		MockHandler mockHandler = new MockHandler();
		MockRequest mockPost    = new MockRequest();
		MockRequest mockGet     = new MockRequest();

		mockPost.result = null;
		mockGet.result  = "<pre id='api-access-key' class='autoscroll'>345test</pre>";

		Acount acount = new Acount("http://test.com","hoge","hoge2hoge");
		LoginService target = new LoginService(mockHandler, acount);

		TestUtil.setValue((Object)target, "mPost", mockPost);
		TestUtil.setValue((Object)target, "mGet",  mockGet);

		target.run();

		Message result = mockHandler.getMsg();
		assertEquals(LoginService.LOGIN_SERVER_ERROR, result.what);
	}

	public void testLoginGetError() throws NoSuchFieldException, IllegalAccessException {
		MockHandler mockHandler = new MockHandler();
		MockRequest mockPost    = new MockRequest();
		MockRequest mockGet     = new MockRequest();

		mockPost.result = "aaa";
		mockGet.result = null;

		Acount acount = new Acount("http://test.com","hoge","hoge2hoge");
		LoginService target = new LoginService(mockHandler, acount);

		TestUtil.setValue((Object)target, "mPost", mockPost);
		TestUtil.setValue((Object)target, "mGet",  mockGet);
		target.run();

		Message result = mockHandler.getMsg();
		assertEquals(LoginService.LOGIN_SERVER_ERROR, result.what);
	}
	
	public void testLoginUsernameOrPasswordError() throws NoSuchFieldException, IllegalAccessException {
		MockHandler mockHandler = new MockHandler();
		MockRequest mockPost    = new MockRequest();
		MockRequest mockGet     = new MockRequest();

		mockPost.result = "aaa";
		mockGet.result = "aaa";

		Acount acount = new Acount("http://test.com","hoge","hoge2hoge");
		LoginService target = new LoginService(mockHandler, acount);

		TestUtil.setValue((Object)target, "mPost", mockPost);
		TestUtil.setValue((Object)target, "mGet",  mockGet);
		target.run();

		Message result = mockHandler.getMsg();
		assertEquals(LoginService.LOGIN_ACOUNT_ERROR, result.what);
	}
}
