package com.redmine.service;

import android.os.Handler;
import android.os.Message;

public class MockHandler extends Handler {
		private Message mMsg;

		@Override
		public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
			mMsg = msg;
			return super.sendMessageAtTime(msg, uptimeMillis);
		}
		
		public Message getMsg() {
			return mMsg;
		}
}
