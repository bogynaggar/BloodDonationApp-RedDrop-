package com.prasasd.nikhil.nss2k17;

/**
 * Created by Nikhil08 on 1/12/2018.
 */

public class Message {
    String messageId;
    String messageBloodGroup;
    String messageNum;

    public Message(){ }

    public Message(String MessageId,String MessageBloodGroup,String MessageNum){
        this.messageId = MessageId;
        this.messageBloodGroup= MessageBloodGroup;
        this.messageNum= MessageNum;
    }

    public String  getMessageId(){
        return messageId;
    }
    public String getMessageBloodGroup(){
        return messageBloodGroup;
    }
    public String getMessageNum(){
        return messageNum;
    }
}
