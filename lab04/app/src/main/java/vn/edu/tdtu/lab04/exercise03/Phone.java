package vn.edu.tdtu.lab04.exercise03;

public class Phone {
  private String name;
  private boolean selected;

  public Phone(String name) {
    this.name = name;
    this.selected = false;
  }

  public Phone(String name, boolean selected) {
    this.name = name;
    this.selected = selected;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }
}
