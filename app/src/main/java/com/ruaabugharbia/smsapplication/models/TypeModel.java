package com.ruaabugharbia.smsapplication.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ruaabugharbia on 26-Aug-18.
 */

@Entity(tableName = "types")
public class TypeModel {

    @PrimaryKey
    @ColumnInfo(name = "type_id")
    private Integer typeId ;

    @ColumnInfo(name = "type_name")
    private String typeName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
