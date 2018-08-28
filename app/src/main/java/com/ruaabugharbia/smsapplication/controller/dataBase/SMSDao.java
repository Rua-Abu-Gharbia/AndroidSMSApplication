package com.ruaabugharbia.smsapplication.controller.dataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ruaabugharbia.smsapplication.models.SMSModel;

import java.util.List;

/**
 * Created by ruaabugharbia on 26-Aug-18.
 */

@Dao
public interface SMSDao {

    @Query("SELECT * FROM messages")
    List<SMSModel> getAll();

    @Query("SELECT * FROM messages WHERE type =:type")
    List<SMSModel> getByType(String type);

    @Insert
    void insertAll(List<SMSModel> smsModels);

    @Insert
    void insertModel(SMSModel smsModel);

    @Update
    void updateModel(SMSModel smsModel);

    @Delete
    void deleteModel(SMSModel smsModel);

}
