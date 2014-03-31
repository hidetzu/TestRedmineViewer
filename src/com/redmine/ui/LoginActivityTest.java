package com.redmine.ui;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.RenamingDelegatingContext;
import android.widget.EditText;

import com.redmine.data.Acount;
import com.redmine.R;
import com.redmine.data.SharedDataManager;
import com.redmine.TestUtil;
import com.redmine.database.AcountDatabaseHelper;
import com.redmine.presenter.LoginPresenter;

public class LoginActivityTest extends ActivityUnitTestCase<LoginActivity> {
    private AcountDatabaseHelper mMockDB;
    private SharedDataManager    mMockSharedDataManager;
	
	public LoginActivityTest() {
		super(LoginActivity.class);
	}
	
	class MockAcountView implements ILoginView {
		private String mName;
		private String mPassword;
		private String mServer;

		@Override
		public void setName(String name) {
			mName = name;
		}

		@Override
		public void setPassword(String password) {
			mPassword = password;
		}

		@Override
		public String getName() {
			return mName;
		}

		@Override
		public String getPassword() {
			return mPassword;
		}

		@Override
		public void showErrDlg(String title, String msg) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void setServerURL(String server) {
			mServer = server;
		}

		@Override
		public String getServerURL() {
			return mServer;
		}

		@Override
		public void moveToTicketListView() {
			// TODO 自動生成されたメソッド・スタブ
			
		}
		
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		Context targetContext = getInstrumentation().getTargetContext();
		RenamingDelegatingContext rdContext = new RenamingDelegatingContext(targetContext, "test_");
		mMockDB = AcountDatabaseHelper.getInstance(rdContext);
    		mMockSharedDataManager = SharedDataManager.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		mMockDB.close();
		TestUtil.setValue((Object)mMockDB, "sSingleton", null);
		mMockDB = null;

		TestUtil.setValue((Object)mMockSharedDataManager, "sInstance", null);
		mMockSharedDataManager = null;
	}

	public void testNoAutoLogin() throws NoSuchFieldException, IllegalAccessException {
	        
		mMockDB.setAcount(0, "aa", "hoge", "hoge2hoge");
		Acount tmp = mMockDB.getAcount(0);
		mMockSharedDataManager.setAcount(tmp);
	
		MockAcountView  mockView = new MockAcountView();
		LoginPresenter mockPresenter = new LoginPresenter(mockView, mMockDB);
		TestUtil.setValue((Object)mockPresenter, "mSharedDataManager", mMockSharedDataManager);
		
		// 起動→onResumeをテスト
		LoginActivity activity = startActivity(new Intent(), null, null);
		EditText loginEditText = (EditText)activity.findViewById(R.id.userEditText);
		EditText passwordEditText = (EditText)activity.findViewById(R.id.passwordEditText);
		assertEquals("", loginEditText.getText().toString());
		assertEquals("", passwordEditText.getText().toString());
		boolean loginFlg = (Boolean) TestUtil.pickValue(activity, "mLoginMode");
		assertEquals(false, loginFlg);

		TestUtil.setValue((Object)activity, "mAcountPresenter", mockPresenter);

		getInstrumentation().callActivityOnResume(activity);
		assertEquals("aa", mockView.getServerURL());
		assertEquals("hoge",mockView.getName());
		assertEquals("hoge2hoge",mockView.getPassword());
		
		// onPause→onResumeのフローを確認する
		getInstrumentation().callActivityOnPause(activity);
		getInstrumentation().callActivityOnResume(activity);

		// onPause→onResumeのフローを確認する
		getInstrumentation().callActivityOnPause(activity);
		getInstrumentation().callActivityOnStop(activity);
		getInstrumentation().callActivityOnRestart(activity);
		getInstrumentation().callActivityOnStart(activity);
		getInstrumentation().callActivityOnResume(activity);
		
		// 終了時
		getInstrumentation().callActivityOnPause(activity);
		getInstrumentation().callActivityOnStop(activity);
		getInstrumentation().callActivityOnDestroy(activity);
		
		Acount acount =  mMockDB.getAcount(0);
		assertEquals("aa", acount.getServer());
		assertEquals("hoge", acount.getUsername());
		assertEquals("hoge2hoge", acount.getPassword());
	}

	public void testAutoLogin() throws NoSuchFieldException, IllegalAccessException {
	
    		mMockDB.setAcount(0, "aa", "hoge", "hoge2hoge");

		MockAcountView  mockView = new MockAcountView();
		LoginPresenter mockPresenter = new LoginPresenter(mockView, mMockDB);
		
		Intent intent = new Intent();
		intent.putExtra("LoginMode", true);
		
		// 起動→onResumeをテスト
		LoginActivity activity = startActivity(intent, null, null);
		EditText loginEditText = (EditText)activity.findViewById(R.id.userEditText);
		EditText passwordEditText = (EditText)activity.findViewById(R.id.passwordEditText);
		assertEquals("", loginEditText.getText().toString());
		assertEquals("", passwordEditText.getText().toString());
		boolean loginFlg = (Boolean) TestUtil.pickValue(activity, "mLoginMode");
		assertEquals(true, loginFlg);
/*
		getInstrumentation().callActivityOnResume(activity);
		TestUtil.setValue((Object)activity, "mAcountPresenter", mockPresenter);

		assertEquals("hoge", mockView.getName());
		assertEquals("hoge2hoge", mockView.getPassword());
		
		// onPause→onResumeのフローを確認する
		getInstrumentation().callActivityOnPause(activity);
		getInstrumentation().callActivityOnResume(activity);

		// onPause→onResumeのフローを確認する
		getInstrumentation().callActivityOnPause(activity);
		getInstrumentation().callActivityOnStop(activity);
		getInstrumentation().callActivityOnRestart(activity);
		getInstrumentation().callActivityOnStart(activity);
		getInstrumentation().callActivityOnResume(activity);
*/
		
	}
}
