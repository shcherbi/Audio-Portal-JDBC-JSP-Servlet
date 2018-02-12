package by.scherbakov.audioportal.entity;

import java.util.Objects;

/**
 * Class {@code MusicAssembly} is used to store MusicAssembly entity.
 *
 * @author ScherbakovIlia
 */
public class MusicAssembly {
    private int id;
    private String name;

    public MusicAssembly(int id, String name) {
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
        if (o == null || getClass() != o.getClass()) return false;
        MusicAssembly that = (MusicAssembly) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
