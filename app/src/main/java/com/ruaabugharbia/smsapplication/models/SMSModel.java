package com.ruaabugharbia.smsapplication.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ruaabugharbia on 06-Aug-18.
 */

@Entity(tableName = "messages")
public class SMSModel {

    @PrimaryKey
    @ColumnInfo(name = "sms_id")
    private Integer smsId ;

    @ColumnInfo(name = "sms_body")
    private String smsBody;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "favourite")
    private boolean favourite ;

    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    public String getSmsBody() {
        return smsBody;
    }

    public void setSmsBody(String smsBody) {
        this.smsBody = smsBody;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
