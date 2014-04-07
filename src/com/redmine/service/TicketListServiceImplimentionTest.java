package com.redmine.service;

import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;

import junit.framework.TestCase;

import com.redmine.service.MockHandler;
import com.redmine.ticketlist.TicketItem;
import com.redmine.ticketlist.TicketListPaser;
import com.redmine.ticketlist.TicketListPaserFactory;
import com.redmine.http.Request;

public class TicketListServiceImplimentionTest extends TestCase {

	public class MockRequest implements Request {
		public String result;

		public String execute() {
			return result;
		}
	}
	
	public class MockTicketListPaser 
		implements TicketListPaserFactory, TicketListPaser {
		public ArrayList<TicketItem> list = new ArrayList<TicketItem>();

		@Override
		public ArrayList<TicketItem> parse(String dataBody)
				throws XmlPullParserException, IOException {
			return list;
		}

		@Override
		public TicketListPaser newInstance() {
			return this;
		}
	}

	public class MockTicketListPaserThrowXmlPullparserError
		implements TicketListPaserFactory, TicketListPaser {

		@Override
		public ArrayList<TicketItem> parse(String dataBody)
				throws XmlPullParserException, IOException {
			throw new XmlPullParserException("test error");
		}

		@Override
		public TicketListPaser newInstance() {
			return this;
		}
	}
	
	public class MockTicketListPaserThrowIOError
		implements TicketListPaserFactory, TicketListPaser {

		@Override
		public ArrayList<TicketItem> parse(String dataBody)
				throws XmlPullParserException, IOException {
			throw new IOException("test error");
		}

		@Override
		public TicketListPaser newInstance() {
			return this;
		}
	}
	
	

	private void addTestData(ArrayList<TicketItem> list,
			String subject, int id,
			int year,
			int month,
			int date,
			int hourOfDay,
			int minute,
			int second
			) {
		TicketItem item = new TicketItem();
		item.setSubject(subject);
		item.setTicketID(id);
		item.setUpdateDate(year, month, date, hourOfDay, minute, second);
		list.add(item);
	}

	public void testServerError() {
		MockRequest request = new MockRequest();
		MockHandler mockHandler = new MockHandler();
		request.result = null;

		TicketListServiceImplimention target = new TicketListServiceImplimention(mockHandler, request);
		target.execute();
		assertEquals(TicketListService.TICKETLIST_NG, mockHandler.getMsg().what);

	}
	
	public void testOK() {
		MockRequest request = new MockRequest();
		MockHandler mockHandler = new MockHandler();
		request.result = "test";
		
		MockTicketListPaser testPaser = new MockTicketListPaser();
		for(int i = 0; i < 5; i++ ) {
			addTestData(testPaser.list, "test" + Integer.toString(i),
					1,
					5-i, 0, 0, 0,0,0);
		}
		GTicketListPaserFactory.setFactory(testPaser);
		
		TicketListServiceImplimention target = new TicketListServiceImplimention(mockHandler, request);
		target.execute();
		assertEquals(TicketListService.TICKETLIST_OK, mockHandler.getMsg().what);
		ArrayList<TicketItem> result = (ArrayList<TicketItem>)mockHandler.getMsg().obj;

		for(int i = 0; i < 5; i++ ) {
			TicketItem item = testPaser.list.get(i);
			TicketItem tmp = result.get(i);
			assertEquals(item.getSubject(), tmp.getSubject());
		}
	}
	
	public void testXmlError() {
		MockRequest request = new MockRequest();
		MockHandler mockHandler = new MockHandler();
		request.result = "test";
		
		MockTicketListPaserThrowXmlPullparserError testPaser = new MockTicketListPaserThrowXmlPullparserError();
		GTicketListPaserFactory.setFactory(testPaser);
		
		TicketListServiceImplimention target = new TicketListServiceImplimention(mockHandler, request);
		target.execute();
		assertEquals(TicketListService.TICKETLIST_NG, mockHandler.getMsg().what);
	}
	
	public void testIoError() {
		MockRequest request = new MockRequest();
		MockHandler mockHandler = new MockHandler();
		request.result = "test";
		
		MockTicketListPaserThrowIOError testPaser = new MockTicketListPaserThrowIOError();
		GTicketListPaserFactory.setFactory(testPaser);
		
		TicketListServiceImplimention target = new TicketListServiceImplimention(mockHandler, request);
		target.execute();
		assertEquals(TicketListService.TICKETLIST_NG, mockHandler.getMsg().what);
	}
}
