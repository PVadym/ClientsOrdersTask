package ua.com.cashup.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.com.cashup.application.enums.Currency;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Вадим on 04.10.2017.
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column
    private String status;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column
    private boolean confirmation;


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    public Order() {
        this.confirmation = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (Double.compare(order.amount, amount) != 0) return false;
        if (confirmation != order.confirmation) return false;
        if (createDate != null ? !createDate.equals(order.createDate) : order.createDate != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        if (currency != order.currency) return false;
        return client != null ? client.equals(order.client) : order.client == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = createDate != null ? createDate.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (confirmation ? 1 : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", currency=" + currency +
                ", confirmation=" + confirmation +
                '}';
    }
}
