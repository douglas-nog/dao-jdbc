package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Departament implements Serializable {

    private int id;
    private String name;

    public Departament() {
    }

    public Departament(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departament)) return false;
        Departament that = (Departament) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Departament: " + id +
                ", name: " + name;
    }
}
