package com.beicroon.construct.pay.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IMoneyPayModel {

    Long getId();

    void setId(Long id);

    BigDecimal getPayAmount();

    void setPayAmount(BigDecimal payAmount);

    String getPaymentType();

    void setPaymentType(String paymentType);

    Long getPayOrderId();

    void setPayOrderId(Long payOrderId);

    LocalDateTime getPayedAt();

    void setPayedAt(LocalDateTime payedAt);

}
