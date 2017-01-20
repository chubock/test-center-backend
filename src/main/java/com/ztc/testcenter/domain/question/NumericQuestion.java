package com.ztc.testcenter.domain.question;

import javax.persistence.Entity;

/**
 * Created by Yubar on 1/18/2017.
 */

@Entity
public class NumericQuestion extends Question {

    private long nominator;
    private boolean fraction;
    private long denominator;

    public long getNominator() {
        return nominator;
    }

    public void setNominator(long nominator) {
        this.nominator = nominator;
    }

    public boolean isFraction() {
        return fraction;
    }

    public void setFraction(boolean fraction) {
        this.fraction = fraction;
    }

    public long getDenominator() {
        return denominator;
    }

    public void setDenominator(long denominator) {
        this.denominator = denominator;
    }
}
