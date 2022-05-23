package ru.mirea.bugraev.mireaproject.Records;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RecordModel.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract RecordDao recordDao();

}
