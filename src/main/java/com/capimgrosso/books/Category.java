package main.java.com.capimgrosso.books;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {

    private int _id = -1;
    private String _name;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(_name, category._name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                '}';
    }
}
