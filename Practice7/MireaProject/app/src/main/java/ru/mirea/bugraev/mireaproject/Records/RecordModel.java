package ru.mirea.bugraev.mireaproject.Records;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RecordModel {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String text;

    public RecordModel(String name, String text)
    {
        this.name=name;
        this.text=text;
    }
}
