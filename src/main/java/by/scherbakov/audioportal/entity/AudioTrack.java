package by.scherbakov.audioportal.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class AudioTrack {
    private int id;
    private String name;
    private String artist;
    private int idAlbum;
    private int idGenre;
    private BigDecimal price;

    public AudioTrack(int id, String name, String artist, int idAlbum, int idGenre, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.idAlbum = idAlbum;
        this.idGenre = idGenre;
        this.price = price;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AudioTrack that = (AudioTrack) o;
        return id == that.id &&
                idAlbum == that.idAlbum &&
                idGenre == that.idGenre &&
                Objects.equals(name, that.name) &&
                Objects.equals(artist, that.artist) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, artist, idAlbum, idGenre, price);
    }
}
