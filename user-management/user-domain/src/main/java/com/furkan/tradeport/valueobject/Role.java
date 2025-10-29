package com.furkan.tradeport.valueobject;

import com.furkan.tradeport.exception.InvalidCodeException;

public enum Role {
    ROLE_CUSTOMER(1),
    ROLE_SELLER(2),
    ROLE_ADMIN(3);

    private final int code;

    Role(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static String getRoleByCode(int code) {
        for(Role role : Role.values()) {
            if(role.getCode() == code) {
                return role.name();
            }
        }
        throw new InvalidCodeException("Böyle bir rol tipi bulunamadı : " + code);
    }
}
