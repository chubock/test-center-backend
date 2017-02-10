package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
@DiscriminatorValue("Q_SINGLE_ANSWER")
public class QuantitativeSingleAnswerQuestion extends AbstractQuantitativeSingleAnswerQuestion {
}
