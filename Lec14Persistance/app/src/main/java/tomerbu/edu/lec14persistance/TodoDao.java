package tomerbu.edu.lec14persistance;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 איזה פעולות דטה-בייס נרצה לעשות עם TODO
 */


@Dao()
public interface TodoDao {

    @Insert
    void add(Todo t);

    @Query("SELECT * FROM Todo")
    List<Todo> getAll();

    @Query("SELECT * FROM Todo WHERE title == :title")
    List<Todo> get(String title);
}

//ContactDao