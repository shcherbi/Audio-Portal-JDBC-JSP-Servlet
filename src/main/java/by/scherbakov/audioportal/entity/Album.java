package by.scherbakov.audioportal.entity;

import java.util.Date;
import java.util.Objects;

/**
 * Class {@code Album} is used to store Album entity.
 *
 * @author ScherbakovIlia
 */
public class Album {
    private int id;
    private String albumName;
    private String studio;
    private Date date;

    public Album(int id, String albumName, String studio, Date date) {
        this.id = id;
        this.albumName = albumName;
        this.studio = studio;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id &&
                Objects.equals(albumName, album.albumName) &&
                Objects.equals(studio, album.studio) &&
                Objects.equals(date, album.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, albumName, studio, date);
    }
}
