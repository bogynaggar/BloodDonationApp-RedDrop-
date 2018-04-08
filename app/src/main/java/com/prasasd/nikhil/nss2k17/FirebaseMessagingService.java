package com.prasasd.nikhil.nss2k17;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Nikhil08 on 12/14/2017.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    FirebaseMessagingService(){}

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}

