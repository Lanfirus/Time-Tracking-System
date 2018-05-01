package ua.training.tts.model.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHashing {

    public static String hashPassword(String password) {
        return DigestUtils.md5Hex(password);
    }
}
