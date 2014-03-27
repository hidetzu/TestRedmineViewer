package com.redmine.http;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.params.HttpParams;
import org.mockito.*;

import junit.framework.*;

public class GetRequestTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSucces() throws ClientProtocolException, IOException {
		String url = "http://www.yahoo.co.jp";
		
		 // ダミーのレスポンスボディを返すHttpEntityモック
		final String testData = "12test34";
       final HttpEntity mockHEntity = new MockHttpEntity() {                
        	@Override
        	public void writeTo(OutputStream outstream) throws IOException {
                // 成功としてtestData変数の内容を返す
        		outstream.write(testData.getBytes());
        	}
        };
		
		StatusLine mockStatusLine = mock(StatusLine.class);
		when(mockStatusLine.getStatusCode()).thenReturn(200);
		
		HttpResponse mockResponse = mock(HttpResponse.class);
		when(mockResponse.getStatusLine()).thenReturn(mockStatusLine);
		doReturn(mockHEntity).when(mockResponse).getEntity();

		HttpParams  mockHttpParams = mock(HttpParams.class);

		// HttpResponseのモックを返すHttpClientをモック  
		HttpClient mockHttpClient = mock(HttpClient.class);  
		when(mockHttpClient.execute(Mockito.notNull(HttpUriRequest.class))).
		thenReturn(mockResponse);  
		when(mockHttpClient.getParams()).thenReturn(mockHttpParams);
		
		GetRequest get = new GetRequest(mockHttpClient, url);
		String result = get.execute();
		assertEquals(testData, result);
	}

	public void test_ioError() throws ClientProtocolException, IOException {
		String url = "http://www.yahoo.co.jp";
		
		 // ダミーのレスポンスボディを返すHttpEntityモック
		final String testData = "12test34";
       final HttpEntity mockHEntity = new MockHttpEntity() {                
        	@Override
        	public void writeTo(OutputStream outstream) throws IOException {
                // 成功としてtestData変数の内容を返す
        		outstream.write(testData.getBytes());
        	}
        };
		
		StatusLine mockStatusLine = mock(StatusLine.class);
		when(mockStatusLine.getStatusCode()).thenReturn(200);
		
		HttpResponse mockResponse = mock(HttpResponse.class);
		when(mockResponse.getStatusLine()).thenReturn(mockStatusLine);
		doReturn(mockHEntity).when(mockResponse).getEntity();

		HttpParams  mockHttpParams = mock(HttpParams.class);
		
		// HttpResponseのモックを返すHttpClientをモック  
		HttpClient mockHttpClient = mock(HttpClient.class);  
		doThrow(new IOException()).when(mockHttpClient).execute(Mockito.notNull(HttpUriRequest.class));
		when(mockHttpClient.getParams()).thenReturn(mockHttpParams);
		
		GetRequest get = new GetRequest(mockHttpClient, url);
		String result = get.execute();
		assertNull(result);
	}
}
