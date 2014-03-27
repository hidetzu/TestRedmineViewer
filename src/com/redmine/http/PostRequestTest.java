package com.redmine.http;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.*;
import org.mockito.*;

import android.test.AndroidTestCase;

public class PostRequestTest extends AndroidTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSucces() throws ClientProtocolException, IOException {
		final String testData = "13524";
		final HttpEntity mockEntity = new MockHttpEntity() {                
			@Override
			public void writeTo(OutputStream outstream) throws IOException {
				// 成功としてtestData変数の内容を返す
				outstream.write(testData.getBytes());
			}
		};

		// HTTPステータスを200に返すStatusLineモック
		StatusLine mockStatusLine = mock(StatusLine.class);
		when(mockStatusLine.getStatusCode()).thenReturn(200);

		// 上で定義したモックオブジェクトを返すHttpResponseモック
		HttpResponse mockResponse = mock(HttpResponse.class);
		when(mockResponse.getStatusLine()).thenReturn(mockStatusLine);
		when(mockResponse.getEntity()).thenReturn(mockEntity);

		HttpParams  mockHttpParams = mock(HttpParams.class);
		
		// 上で定義したモックオブジェクトを返すHttpClientモック
		HttpClient mockHttpClient = mock(HttpClient.class);
		when(mockHttpClient.execute(Mockito.notNull(HttpUriRequest.class)))
		.thenReturn(mockResponse);
		when(mockHttpClient.getParams()).thenReturn(mockHttpParams);
		
		String url = "http://www.yahoo.co.jp";
		ArrayList <NameValuePair> params = new ArrayList <NameValuePair>();
		params.add( new BasicNameValuePair("username", "hoge"));
		params.add( new BasicNameValuePair("password", "hogehoge"));

		PostRequest target = new PostRequest(mockHttpClient, url, params);
		String result = target.execute();
		assertEquals(testData, result);

	}

	public void testStatusError() throws ClientProtocolException, IOException {
		// HTTPステータスを500に返すStatusLineモック
		StatusLine mockStatusLine = mock(StatusLine.class);

		when(mockStatusLine.getStatusCode()).thenReturn(500);
		// 上で定義したモックオブジェクトを返すHttpResponseモック
		HttpResponse mockResponse = mock(HttpResponse.class);
		when(mockResponse.getStatusLine()).thenReturn(mockStatusLine);

		HttpParams  mockHttpParams = mock(HttpParams.class);
		
		// 上で定義したモックオブジェクトを返すHttpClientモック
		HttpClient mockHttpClient = mock(HttpClient.class);
		when(mockHttpClient.execute(Mockito.notNull(HttpUriRequest.class)))
		.thenReturn(mockResponse);
		when(mockHttpClient.getParams()).thenReturn(mockHttpParams);

		String url = "http://www.yahoo.co.jp";
		ArrayList <NameValuePair> params = new ArrayList <NameValuePair>();
		params.add( new BasicNameValuePair("username", "hoge"));
		params.add( new BasicNameValuePair("password", "hogehoge"));

		PostRequest target = new PostRequest(mockHttpClient, url, params);
		String result = target.execute();
		assertNull(result);

	}

	public void test_clientProtocolException() throws ClientProtocolException, IOException {

		HttpParams  mockHttpParams = mock(HttpParams.class);
		ClientProtocolException expected = new ClientProtocolException();
		
		// 上で定義したモックオブジェクトを返すHttpClientモック
		HttpClient mockHttpClient = mock(HttpClient.class);
		when(mockHttpClient.execute(Mockito.notNull(HttpUriRequest.class)))
		.thenThrow(expected);
		when(mockHttpClient.getParams()).thenReturn(mockHttpParams);

		String url = "http://www.yahoo.co.jp";
		ArrayList <NameValuePair> params = new ArrayList <NameValuePair>();
		params.add( new BasicNameValuePair("username", "hoge"));
		params.add( new BasicNameValuePair("password", "hogehoge"));

		PostRequest target = new PostRequest(mockHttpClient, url, params);
		String result = target.execute();
		assertNull(result);
	}

	public void test_ioException() throws ClientProtocolException, IOException {
		HttpParams  mockHttpParams = mock(HttpParams.class);
		IOException expected = new IOException();

		// 上で定義したモックオブジェクトを返すHttpClientモック
		HttpClient mockHttpClient = mock(HttpClient.class);
		when(mockHttpClient.getParams()).thenReturn(mockHttpParams);

		when(mockHttpClient.execute(Mockito.notNull(HttpUriRequest.class)))
		.thenThrow(expected);
		String url = "http://www.yahoo.co.jp";
		ArrayList <NameValuePair> params = new ArrayList <NameValuePair>();
		params.add( new BasicNameValuePair("username", "hoge"));
		params.add( new BasicNameValuePair("password", "hogehoge"));

		PostRequest target = new PostRequest(mockHttpClient, url, params);

		String result = target.execute();
		assertNull(result);

	}
}
