package vn.edu.tdtu.lab07.exercise04;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName="event")
public class Event implements Serializable {

  @PrimaryKey(autoGenerate = true)
  private int id;

  @ColumnInfo(name = "name")
  private String name;

  @ColumnInfo(name = "place")
  private String place;

  @ColumnInfo(name = "date")
  private String date;

  @ColumnInfo(name = "time")
  private String time;

  @ColumnInfo(name = "completed")
  private int completed;

  public Event() {
  }

  public Event(int id, String name, String place, String date, String time, int completed) {
    this.id = id;
    this.name = name;
    this.place = place;
    this.date = date;
    this.time = time;
    this.completed = completed;
  }

  @Ignore
  public Event(String name, String place, String date, String time) {
    this.name = name;
    this.place = place;
    this.date = date;
    this.time = time;

    setCompleted(0);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getCompleted() {
    return completed;
  }

  public void setCompleted(int completed) {
    this.completed = completed;
  }
}
