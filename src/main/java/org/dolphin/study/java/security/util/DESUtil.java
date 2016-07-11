package org.dolphin.study.java.security.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DESTest.java
 *
 * @author Techzero
 * @Email techzero@163.com
 * @Time 2013-12-12 下午2:22:58
 */
public class DESUtil {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String content = "12345678901234567890123456789012";
        // 密码长度必须是8的倍数
        String password = "12345678";
        System.out.println("密　钥：" + password);
        System.out.println("加密前：" + content);
        long startEncrypt  = System.currentTimeMillis();
        byte[] result = encrypt(content, password);
        long endEncrypt  = System.currentTimeMillis();
        System.out.println("加密后：" + new String(result)+",timeUsed:"+(endEncrypt-startEncrypt));
        long startDecrypt  = System.currentTimeMillis();
        String decryResult = decrypt(result, password);
        long endDecrypt  = System.currentTimeMillis();
        System.out.println("解密后：" + decryResult+"timeUsed:"+(endDecrypt-startDecrypt));
    }

    /**
     * 加密
     *
     * @param content
     *            待加密内容
     * @param key
     *            加密的密钥
     * @return
     */
    public static byte[] encrypt(String content, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            byte[] result = cipher.doFinal(content.getBytes());
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content
     *            待解密内容
     * @param key
     *            解密的密钥
     * @return
     */
    public static String decrypt(byte[] content, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            byte[] result = cipher.doFinal(content);
            return new String(result);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
