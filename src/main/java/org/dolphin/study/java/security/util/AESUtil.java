package org.dolphin.study.java.security.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by sunqi on 16/7/11.
 */
public class AESUtil {
    private static String key = "lianghuilonglong";//私钥   AES固定格式为128/192/256 bits.即：16/24/32bytes。DES固定格式为128bits，即8bytes。
    private static String iv   = "aabbccddeeffgghh";//初始化向量参数，AES 为16bytes. DES 为8bytes.

    public static void main(String[] args){
       String source = "hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world,hello world,, hello world, hello world, hello world, hello world, hello world, hello world, hello world, hello world hello world";
        long start = System.currentTimeMillis();
        String encrypt = encrypt(source,key);
        String decrypt = decrypt(encrypt,key);
        long end = System.currentTimeMillis();
        System.out.println("source:"+source);
        System.out.println("encrypt:"+encrypt);
        System.out.println("decrypt:"+decrypt);
        System.out.println("timeUsed:"+(end - start));
    }

    public static String encrypt(String source, String password){

        try {
            Key keySpec = new SecretKeySpec(password.getBytes(), "AES");    //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //实例化加密类，参数为加密方式，要写全
            cipher.init(Cipher.ENCRYPT_MODE,  keySpec, ivSpec); //初始化，此方法可以采用三种方式，按服务器要求来添加。（1）无第三个参数（2）第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)（3）采用此代码中的IVParameterSpec
            //cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            //SecureRandom random = new SecureRandom();
            //cipher.init(Cipher.ENCRYPT_MODE, keySpec, random);
            byte [] b = cipher.doFinal(source.getBytes()); //加密操作,返回加密后的字节数组，然后需要编码。主要编解码方式有Base64, HEX, UUE, 　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　7bit等等。此处看服务器需要什么编码方式
            String ret = Base64.encodeBase64String(b);               //Base64、HEX等编解码
            return ret;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String siperText,String password){
        try {
            Key keySpec = new SecretKeySpec(password.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,  keySpec, ivSpec);
            byte[] bytes = cipher.doFinal(Base64.decodeBase64(siperText));
            return new String(bytes,"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
