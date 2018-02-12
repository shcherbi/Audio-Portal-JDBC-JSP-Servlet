package by.scherbakov.audioportal.entity;

import java.util.Date;
import java.util.Objects;

/**
 * Class {@code Comment} is used to store Comment entity.
 *
 * @author ScherbakovIlia
 */
public class Comment {
    private int id;
    private int idAudioTrack;
    private String login;
    private String text;
    private Date date;

    public Comment(int id, int idAudioTrack, String login, String text, Date date) {
        this.id = id;
        this.idAudioTrack = idAudioTrack;
        this.login = login;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAudioTrack() {
        return idAudioTrack;
    }

    public void setIdAudioTrack(int idAudioTrack) {
        this.idAudioTrack = idAudioTrack;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        Comment comment = (Comment) o;
        return id == comment.id &&
                idAudioTrack == comment.idAudioTrack &&
                Objects.equals(login, comment.login) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idAudioTrack, login, text, date);
    }
}
