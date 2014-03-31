package com.redmine.presenter;

import com.redmine.ui.*;
import com.redmine.data.*;
import com.redmine.database.*;
import com.redmine.TestUtil;

import android.test.AndroidTestCase;

public class BootPresenterTest extends AndroidTestCase {
    	private SharedDataManager    mMockSharedDataManager;

	class MockBootView  implements IBootView {
		public boolean mAutologin;
		public void moveToLoginView(boolean autoLogin) {
			mAutologin = autoLogin;
		}
	}

	class MockAcountDatabase implements AcountDatabase {
		private Acount mAcount = null;
		public void setAcount(int id, String serverURL, String name, String password) {
			mAcount = new Acount( serverURL, name, password);
		} 

		public Acount getAcount(int id) {
			return mAcount;
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

	public void testNoAcount() {
		MockAcountDatabase database = new MockAcountDatabase();
		MockBootView       view     = new MockBootView();
		BootPresenter target = new BootPresenter(view, database);

		target.moveToLoginView();
		assertEquals(false, view.mAutologin);
	}

	public void testAcount() {
		MockAcountDatabase database = new MockAcountDatabase();
		database.setAcount(0,"test", "hoge", "hoge2hoge");

		MockBootView       view     = new MockBootView();
		BootPresenter target = new BootPresenter(view, database);

		target.moveToLoginView();
		assertEquals(true, view.mAutologin);
	}
}
