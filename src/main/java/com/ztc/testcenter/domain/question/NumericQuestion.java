package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yubar on 1/18/2017.
 */

@Entity
@DiscriminatorValue("NUMERIC")
public class NumericQuestion extends AbstractNumericQuestion {

}
