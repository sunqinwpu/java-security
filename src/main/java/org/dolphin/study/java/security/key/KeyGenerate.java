/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package org.dolphin.study.java.security.key;

import io.netty.handler.codec.base64.Base64Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.util.Base64;

/**
 *
 * @author sunqi
 * @version $Id: KeyGenerate.java, v 0.1 2015年12月9日 下午11:42:38 sunqi Exp $
 */
public class KeyGenerate {
    /**
     * Creates an secret key as byte[].
     * 
     * @return
     * @throws Exception
     */
    private static byte[] geneKey(String algorithm, int length) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(algorithm);
        kgen.init(length);
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    /**
     * Creates an AES 128-bit secret key as a hexadecimal string.
     * 
     * @return
     * @throws Exception
     */
    public static String geneAesHexKey(int length) throws Exception {
        return toHexString(geneKey("AES", length));
    }

    /**
     * Creates an AES 128-bit secret key as a base64 string.
     * 
     * @return
     * @throws Exception
     */
    public static String geneAesBase64Key(int length) throws Exception {
        return geneBase64(geneKey("AES", length));
    }

    /**
     * Turns array of bytes into string
     * 
     * @param buf
     *           Array of bytes to convert to hex string
     * @return Generated hex string
     */
    public static String toHexString(byte buf[]) {
        StringBuilder strbuf = new StringBuilder(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)
                strbuf.append("0");

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }

    /**
     * Converts a hexadecimal String to a byte array.
     * 
     * @param hexStr
     * @return
     */
    public static byte[] geneByteArray(String hexStr) {
        byte bArray[] = new byte[hexStr.length() / 2];
        for (int i = 0; i < (hexStr.length() / 2); i++) {
            byte firstNibble = Byte.parseByte(hexStr.substring(2 * i, 2 * i + 1), 16); // [x,y)
            byte secondNibble = Byte.parseByte(hexStr.substring(2 * i + 1, 2 * i + 2), 16);
            int finalByte = (secondNibble) | (firstNibble << 4); // bit-operations
                                                                 // only with
                                                                 // numbers, not
                                                                 // bytes.
            bArray[i] = (byte) finalByte;
        }
        return bArray;
    }

    /**
     * Converts a byte array to a base64 string.
     * 
     * @param array
     * @return
     */
    public static String geneBase64(byte[] array) {
        return com.sun.org.apache.xml.internal.security.utils.Base64.encode(array);
    }
}
