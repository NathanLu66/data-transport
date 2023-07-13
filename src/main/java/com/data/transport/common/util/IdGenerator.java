package com.data.transport.common.util;

import com.data.transport.domain.User;

import java.util.Random;

public class IdGenerator {
    public static String nextUid() {
        Random random = new Random();
        long nextLong = random.nextLong();
        String value = String.valueOf(nextLong);
        return value.substring(1, 7);
    }
}
