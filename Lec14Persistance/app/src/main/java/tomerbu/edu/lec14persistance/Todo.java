package tomerbu.edu.lec14persistance;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by tomerbuzaglo on 10/08/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */

@Entity()//tableName = "m_todos" -> CREATE TABLE m_todos(
// id INTEGER PRIMARY KEY AUTO_INCREMENT,
// title TEXT,
//  sub_title TEXT
public class Todo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    //title
    private String title;

    //subtitle
    //@ColumnInfo(name = "sub_title")
    private String subtitle;

    //ctor, also default constructor
    public Todo(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public Todo(){}

    //getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    //toString
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                '}';
    }
}

//SELECT * FROM