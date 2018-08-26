package com.ruaabugharbia.smsapplication.controller.dataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ruaabugharbia.smsapplication.models.SMSModel;
import com.ruaabugharbia.smsapplication.models.TypeModel;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ruaabugharbia on 26-Aug-18.
 */

@Dao
public interface TypeDao {

    @Query("SELECT * FROM types")
    List<TypeModel> getAll();

    @Insert
    void insertAll(List<TypeModel> typeModels);

    @Insert
    void insertUser(TypeModel typeModel);

    @Update
    void updateUser(TypeModel typeModel);

    @Delete
    void deleteUser(TypeModel typeModel);

}
