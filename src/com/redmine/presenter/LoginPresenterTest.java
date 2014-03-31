package com.redmine.presenter;

import android.test.AndroidTestCase;

import com.redmine.data.*;
import com.redmine.ui.*;
import com.redmine.TestUtil;

public class LoginPresenterTest extends AndroidTestCase {
    	private SharedDataManager    mMockSharedDataManager;
	
	class MockLoginView implements ILoginView {
		private String mTitle;
		private String mMsg;
		private String mServer;
		private String mUser;
		private String mPassword;

		public MockLoginView() {
		}

		public void setServerURL(String server) {
			mServer = server;
		}

		public void setName(String name) {
			mUser = name;
		}

		public void setPassword(String password) {
			mPassword = password;
		}

		public String getServerURL() {
			return mServer;
		}

		public String getName() {
			return mUser;
		}

		public String getPassword() {
			return mPassword;
		}

		public void moveToTicketListView() {
		}

		public void showErrDlg(String title, String msg) {
			mTitle = title;
			mMsg = msg;
		}

		public String getDlgTitle() {
			return mTitle;
		}

		public String getDlgMsg() {
			return mMsg;
		}
	}

	protected void setUp() throws Exception {
		super.setUp();
    		mMockSharedDataManager = SharedDataManager.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		TestUtil.setValue((Object)mMockSharedDataManager, "sInstance", null);
		mMockSharedDataManager = null;
	}

	public void testShowErrDlg() {
		MockLoginView mockView = new MockLoginView();
		LoginPresenter target = new LoginPresenter(mockView, null);
		target.showErrDlg("Login", "Error");

		assertEquals("Login", mockView.getDlgTitle());
		assertEquals("Error", mockView.getDlgMsg());
	}

	public void testmoveToNextView() {
		MockLoginView mockView = new MockLoginView();
		LoginPresenter target = new LoginPresenter(mockView, null);
		target.moveToNextView();
	}

	public void testGetAcount() {
		MockLoginView mockView = new MockLoginView();
		mockView.setServerURL("server");
		mockView.setName("hoge");
		mockView.setPassword("hoge2hoge");

		LoginPresenter target = new LoginPresenter(mockView, null);
		Acount acount = target.getAcount();

		assertEquals("server", acount.getServer());
		assertEquals("hoge", acount.getUsername());
		assertEquals("hoge2hoge", acount.getPassword());
	}

	public void testApiKey() throws NoSuchFieldException, IllegalAccessException {
		LoginPresenter target = new LoginPresenter(null, null);
		TestUtil.setValue((Object)target, "mSharedDataManager", mMockSharedDataManager);
		target.saveAPIKey("12345");

		assertEquals("12345", mMockSharedDataManager.getAPIKey());
	}
}
