package com.example.roomapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NameDao {

    @Query("SELECT * FROM names")
    List<Names> getNames();

    @Insert
    void addName(Names... names);

    @Delete
    void deleteName(Names names);

    @Query("UPDATE Names SET name = :editName WHERE uid = :position")
    void editDbName(String editName, int position);
}
