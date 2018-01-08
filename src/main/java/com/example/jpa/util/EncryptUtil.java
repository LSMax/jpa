/*
 * Copyright (c) 2016 Chengdu Xpand Technology Information Service Co., Ltd.
 * All rights reserved.
 */

package com.example.jpa.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Mars on 2016/11/17.
 */
public class EncryptUtil {

    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    public static String encrypt(String plaintext) {
        plaintext = Base64.encodeBase64String(plaintext.getBytes());
        plaintext = plaintext.toLowerCase();
        byte[] ascii = BinaryCodec.toAsciiBytes(plaintext.getBytes());
        plaintext = Hex.encodeHexString(ascii);
        return DigestUtils.sha1Hex(plaintext);
    }

    /**
     * 加密密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    //-- User Service --//

    /**
     * 验证密码是否相同
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }
}
