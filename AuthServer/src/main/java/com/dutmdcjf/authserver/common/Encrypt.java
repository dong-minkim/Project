package com.dutmdcjf.authserver.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    public String encryptBySha256(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        String encryptPassword = String.format("%064x", new BigInteger(1, md.digest()));

        return encryptPassword;
    }
}
