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

    @Insert
    void insertAll(List<SMSModel> smsModels);

    @Insert
    void insertUser(SMSModel smsModel);

    @Update
    void updateUser(SMSModel smsModel);

    @Delete
    void deleteUser(SMSModel smsModel);

}
