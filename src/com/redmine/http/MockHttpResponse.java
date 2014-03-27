package com.redmine.http;

import java.util.Locale;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.params.HttpParams;

public class MockHttpResponse implements HttpResponse {

	@Override
	public void addHeader(Header header) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void addHeader(String name, String value) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public boolean containsHeader(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public Header[] getAllHeaders() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Header getFirstHeader(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Header[] getHeaders(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Header getLastHeader(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public HttpParams getParams() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ProtocolVersion getProtocolVersion() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public HeaderIterator headerIterator() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public HeaderIterator headerIterator(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void removeHeader(Header header) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void removeHeaders(String name) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void setHeader(Header header) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void setHeader(String name, String value) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void setHeaders(Header[] headers) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void setParams(HttpParams params) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public HttpEntity getEntity() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Locale getLocale() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public StatusLine getStatusLine() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void setEntity(HttpEntity entity) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void setLocale(Locale loc) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void setReasonPhrase(String reason) throws IllegalStateException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void setStatusCode(int code) throws IllegalStateException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void setStatusLine(StatusLine statusline) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void setStatusLine(ProtocolVersion ver, int code) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void setStatusLine(ProtocolVersion ver, int code, String reason) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
