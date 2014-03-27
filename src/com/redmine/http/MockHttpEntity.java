package com.redmine.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

public class MockHttpEntity implements HttpEntity {

	@Override
	public void consumeContent() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public InputStream getContent() throws IOException, IllegalStateException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Header getContentEncoding() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public long getContentLength() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public Header getContentType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean isChunked() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean isRepeatable() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean isStreaming() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void writeTo(OutputStream outstream) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
