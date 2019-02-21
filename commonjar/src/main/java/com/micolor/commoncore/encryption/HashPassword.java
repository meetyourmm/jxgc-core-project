package com.micolor.commoncore.encryption;

import java.nio.charset.Charset;

/**
 * Created by GEYUPENG on 2016/4/14.
 */
public class HashPassword {
    private static final int INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;
    private String salt;
    private String password;


    public static HashPassword encryptPassword(String plainPassword) {
        HashPassword result = new HashPassword();
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        result.salt = Encodes.encodeHex(salt);

        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(Charset.forName("UTF-8")), salt, INTERATIONS);
        result.password = Encodes.encodeHex(hashPassword);
        return result;
    }
    public static boolean validatePassword(String plainPassword, String password, String salt) {
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(Charset.forName("UTF-8")), Encodes.decodeHex(salt), INTERATIONS);
        String oldPassword = Encodes.encodeHex(hashPassword);
        return password.equals(oldPassword);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
