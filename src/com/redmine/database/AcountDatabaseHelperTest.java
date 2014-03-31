
package com.redmine.database;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.RenamingDelegatingContext;

import com.redmine.data.Acount;
import com.redmine.TestUtil;
import com.redmine.database.AcountDatabaseHelper;

public class AcountDatabaseHelperTest extends InstrumentationTestCase {
    private AcountDatabaseHelper mHelper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Context targetContext = getInstrumentation().getTargetContext();
        RenamingDelegatingContext rdContext = new RenamingDelegatingContext(targetContext, "test_");
        mHelper = AcountDatabaseHelper.getInstance(rdContext);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mHelper.close();
		 TestUtil.setValue((Object)mHelper, "sSingleton", null);
        mHelper = null;
    }

    public void testInsert() {
    	assertNull(mHelper.getAcount(0));
    	mHelper.setAcount(0, "hogeserver", "hoge", "hoge2hoge");
    	Acount acount = mHelper.getAcount(0);
    	assertEquals("hogeserver", acount.getServer());
    	assertEquals("hoge", acount.getUsername());
    	assertEquals("hoge2hoge", acount.getPassword());
    }

    public void testUpdate() {
    	assertNull(mHelper.getAcount(0));
    	mHelper.setAcount(0, "hogeserver", "hoge", "hoge2hoge");
    	Acount acount = mHelper.getAcount(0);
    	assertEquals("hogeserver", acount.getServer());
    	assertEquals("hoge", acount.getUsername());
    	assertEquals("hoge2hoge", acount.getPassword());

     	mHelper.setAcount(0, "testserver", "test", "test2test");
    	acount = mHelper.getAcount(0);
    	assertEquals("testserver", acount.getServer());
    	assertEquals("test", acount.getUsername());
    	assertEquals("test2test", acount.getPassword());
    }
}
