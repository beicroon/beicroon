package com.beicroon.construct.base.interfaces;

import java.math.BigDecimal;

public interface IPayUser extends IAuthUser {

    void setWxUnionid(String value);

    String getWxUnionid();

    void setWxOpenid(String value);

    String getWxOpenid();

    void setBalance(BigDecimal value);

    BigDecimal getBalance();

}
