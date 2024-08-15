package com.beicroon.construct.constant;

public interface RegexConstant {

    String SPLIT_UPPER = "(?=[A-Z])";

    String CASE_SNAKE = "_(\\w)";

    String PHONE = "^(+?86)?(-\\s)?1[3-9](-\\s)?[0-9]{4}(-\\s)?[0-9]{4}$";

    String ID_CARD = "^[0-9]{17}([0-9]|X)$";

    String MOBILE = "^(0[0-9]{2,3}-?|\\(0[0-9]{2,3}\\))?[1-9][0-9]{6,7}|400-?[0-9]{3}-?[0-9]{4}$";

    String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z[0-9],.?:;_-]{6,18}}$";

}
