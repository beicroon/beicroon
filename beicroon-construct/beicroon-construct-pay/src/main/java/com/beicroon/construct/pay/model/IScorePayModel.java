package com.beicroon.construct.pay.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IScorePayModel {

    Long getId();

    void setId(Long id);

    BigDecimal getPayScore();

    void setPayScore(BigDecimal payAmount);

    Long getPayOrderId();

    void setPayOrderId(Long payOrderId);

    LocalDateTime getPayedAt();

    void setPayedAt(LocalDateTime payedAt);

}
