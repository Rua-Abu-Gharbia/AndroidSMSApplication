package com.ruaabugharbia.smsapplication.models;

/**
 * Created by ruaabugharbia on 06-Aug-18.
 */

public class SMSModel {

    private String smsId ;
    private String smsBody;

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getSmsBody() {
        return smsBody;
    }

    public void setSmsBody(String smsBody) {
        this.smsBody = smsBody;
    }
}
