package vn.edu.tdtu.lab07.exercise04;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface EventDao {
  @Query("SELECT * FROM LAB07_EVENT2")
  List<Event> getAll();

  @Query("SELECT * FROM LAB07_EVENT2 WHERE id IN (:eventIds)")
  List<Event> loadAllByIds(int[] eventIds);

  @Query("SELECT * FROM LAB07_EVENT2 WHERE completed IS :completed")
  List<Event> loadAllByCompleted(int completed);

  @Query("SELECT * FROM LAB07_EVENT2 WHERE id IS :id LIMIT 1")
  Event findById(int id);

  @Query("SELECT * FROM LAB07_EVENT2 WHERE name LIKE :name LIMIT 1")
  Event findByName(String name);

  @Update
  void updateAll(Event... events);

  @Insert
  void insertAll(Event... events);

  @Delete
  void delete(Event event);

  @Query("DELETE FROM LAB07_EVENT2")
  void deleteAll();
}