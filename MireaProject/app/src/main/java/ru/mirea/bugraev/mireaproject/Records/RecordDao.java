package ru.mirea.bugraev.mireaproject.Records;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecordDao {
        @Query("SELECT * FROM recordmodel")
        List<RecordModel> getAll();
        @Query("SELECT * FROM recordmodel WHERE id = :id")
        RecordModel getById(long id);
        @Query("DELETE FROM recordmodel WHERE id=:id")
        void deleteById(long id);
        @Insert
        void insert(RecordModel record);
        @Update
        void update(RecordModel record);
        @Delete
        void delete(RecordModel record);
}
