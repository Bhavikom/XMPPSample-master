package com.xmpp.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;

import com.xmpp.myxmpp.MyXMPP;

import org.jivesoftware.smack.chat.Chat;


public class MyService extends Service {
    private static final String DOMAIN = "xmpp.jp"; // its public and testing server and work on emulator and mobile both

    /* username and password which are registerd on xmpp.jp site*/
    //private static final String USERNAME = "jayeshmakvana";
    //private static final String PASSWORD = "123456";
    private static final String USERNAME = "bhavikom";
    private static final String PASSWORD = "9624300677";
    public static ConnectivityManager cm;
    public static MyXMPP xmpp;
    public static boolean ServerchatCreated = false;
    String text = "";


    @Override
    public IBinder onBind(final Intent intent) {
        return new LocalBinder<MyService>(this);
    }

    public Chat chat;

    @Override
    public void onCreate() {
        super.onCreate();
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        xmpp = MyXMPP.getInstance(MyService.this, DOMAIN, USERNAME, PASSWORD);
        xmpp.connect("onCreate");
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags,final int startId) {
        return Service.START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(final Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        xmpp.connection.disconnect();
    }

    public static boolean isNetworkConnected() {
        return cm.getActiveNetworkInfo() != null;
    }
}