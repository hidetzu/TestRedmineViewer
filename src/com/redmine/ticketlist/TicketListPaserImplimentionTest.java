package com.redmine.ticketlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

public class TicketListPaserImplimentionTest  extends InstrumentationTestCase{

	private AssetManager testAssets;
	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Context targetContext = getInstrumentation().getContext();
        testAssets = targetContext.getAssets();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    private String readTestData(String path) {
		StringBuilder fileString = new StringBuilder();
		InputStream io;
		try {
			io = testAssets.open(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(io,"UTF-8"));
			String _line;
			while ((_line = reader.readLine()) != null) {
				fileString.append(_line);
			}
			reader.close();
		    //_sb.toString();ファイルの中身が反映されているはずです。
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return new String(fileString);
    }
    
    public void testNoIssue() {
    	String str = readTestData("testData/ticketListTransaction/testData0.xml");
    	TicketListPaserImplimention target = new TicketListPaserImplimention();
    	try {
			ArrayList<TicketItem> result = target.parse(str);
			assertEquals(result.size(), 0);
		} catch (XmlPullParserException e) {
			fail();
		} catch (IOException e) {
			fail();
		}

    }
    
    private void assertData(Calendar calendar,
    		int year, int month, int day, int hour, int minute, int second) {
    	assertEquals(year, calendar.get(Calendar.YEAR));
    	assertEquals(month ,calendar.get(Calendar.MONTH));
		assertEquals(day, calendar.get(Calendar.DATE));
		assertEquals(hour, calendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(minute,calendar.get(Calendar.MINUTE));
		assertEquals(second ,calendar.get(Calendar.SECOND));
    }
    

    public void testOneIssue() {
    	String str = readTestData("testData/ticketListTransaction/testData1.xml");
    	TicketListPaserImplimention target = new TicketListPaserImplimention();
    	try {
			ArrayList<TicketItem> result = target.parse(str);
			assertEquals(result.size(), 1);
			TicketItem item = result.get(0);
			assertEquals("テスト1", item.getSubject());
			assertEquals(302, item.getTicketID());
			Calendar calendar = item.getUpdateDate();

			assertData(calendar, 2014, 1, 11, 12, 50, 42);
			
			
		} catch (XmlPullParserException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
    }

    public void testTwoIssue() {
    	String str = readTestData("testData/ticketListTransaction/testData2_1.xml");
    	TicketListPaserImplimention target = new TicketListPaserImplimention();
    	try {
			ArrayList<TicketItem> result = target.parse(str);
			assertEquals(result.size(), 2);
			TicketItem item = result.get(0);
			assertEquals("テスト1", item.getSubject());
			assertEquals(302, item.getTicketID());
			Calendar calendar = item.getUpdateDate();

			assertData(calendar, 2014, 1, 11, 12, 50, 42);
			
			item = result.get(1);
			assertEquals("テスト2", item.getSubject());
			assertEquals(301, item.getTicketID());
			calendar = item.getUpdateDate();

			assertData(calendar, 2013, 1, 11, 12, 50, 42);
			
		} catch (XmlPullParserException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
    }
    
    public void testOther() {
    	TicketListPaserFactory target = new TicketListPaserImplimention();
    	target.newInstance();
    }

}
