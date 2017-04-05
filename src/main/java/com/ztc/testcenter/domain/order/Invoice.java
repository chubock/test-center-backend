package com.ztc.testcenter.domain.order;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yubar on 4/5/17.
 */

@Entity
@Table(name = "INVOICES")
public class Invoice implements Serializable {

    private Long id;
    private Order order;
    private Date invoiceDate;
    private String paymentRef;
    private BigDecimal totalPrice;

    protected Invoice() {
    }

    public Invoice(Order order, Date invoiceDate, String paymentRef, BigDecimal totalPrice) {
        setOrder(order);
        setInvoiceDate(invoiceDate);
        setPaymentRef(paymentRef);
        setTotalPrice(totalPrice);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        if (invoiceDate == null)
            throw new IllegalArgumentException();
        this.invoiceDate = invoiceDate;
    }

    @NotNull
    @Column(nullable = false)
    public String getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(String paymentRef) {
        if (paymentRef == null)
            throw new IllegalArgumentException();
        this.paymentRef = paymentRef;
    }

    @NotNull
    @Min(0)
    @Column(nullable = false)
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        if (totalPrice == null || totalPrice.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException();
        this.totalPrice = totalPrice;
    }

    @NotNull
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        if (order == null)
            throw new IllegalArgumentException();
        this.order = order;
    }
}
