package by.scherbakov.audioportal.entity;

import java.util.Date;
import java.util.Objects;

/**
 * Class {@code Order} is used to store Order entity.
 *
 * @author ScherbakovIlia
 */
public class Order {
    private int id;
    private String login;
    private String cardNumber;
    private String SVCCode;
    private Date date;

    public Order(int id, String login, String cardNumber, String SVCCode, Date date) {
        this.id = id;
        this.login = login;
        this.cardNumber = cardNumber;
        this.SVCCode = SVCCode;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSVCCode() {
        return SVCCode;
    }

    public void setSVCCode(String SVCCode) {
        this.SVCCode = SVCCode;
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
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(login, order.login) &&
                Objects.equals(cardNumber, order.cardNumber) &&
                Objects.equals(SVCCode, order.SVCCode) &&
                Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, cardNumber, SVCCode, date);
    }
}
