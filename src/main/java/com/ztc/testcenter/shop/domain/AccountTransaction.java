package com.ztc.testcenter.shop.domain;

import com.ztc.testcenter.user.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yubar on 4/5/17.
 */

@Entity
@Table(name = "ACCOUNT_TRANSACTIONS")
public class AccountTransaction implements Serializable {

    private Long id;
    private BigDecimal credit = BigDecimal.ZERO;
    private BigDecimal deposit = BigDecimal.ZERO;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(nullable = false)
    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    @NotNull
    @Column(nullable = false)
    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
