package com.hori.utils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/**
 * 加密、解密给定的字符串。
 *
 * @author 
 */
public class Obfuscator {
    private static final byte[] bytes = new byte[]{-61, 8, 78, 5,
            68, -73, 98, -96,
            107, 103, -97, -11,
            109, -38, -61, -22};

    static Cipher cipher;
    static SecretKeySpec key;
    
    public static void init() {
        key = new SecretKeySpec(bytes, "Blowfish");
        try {
            cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密给定的字符串
     *
     * @param s 要加密的字符串
     * @return 加密后的字符串
     * @throws RuntimeException 如果在解密过程中有任何异常发生则抛出运行时异常
     */
    public static String obfuscate(String s) throws RuntimeException {
    	init();
    	try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(s.getBytes("UTF8"));
            return Base64.encodeBytes(encrypted);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error formatting data", e);

        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Error formatting data", e);
        }
    }

    /**
     * 解密给定的字符串
     *
     * @param s 要解密的字符串。
     * @return 字符串的原文
     * @throws RuntimeException 如果在解密过程中有任何异常发生则抛出运行时异常
     */
    public static String deobfuscate(String s) throws RuntimeException {
    	init();
    	try {
            byte[] encoded = Base64.decode(s);
            SecretKeySpec key = new SecretKeySpec(bytes, "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = cipher.doFinal(encoded);
            return new String(decoded, "UTF8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error formatting data", e);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new RuntimeException("Error formatting data", e);
        }
    }

    /**
     * 测试这个类ok
     *
     * @param args 测试的参数
     */
    public static void main(String[] args) {
        try {
            //init();
            String test = "def";
            String pd = Obfuscator.obfuscate(test);
            System.out.println(pd + " " + Obfuscator.deobfuscate("yyos4TqRUCI="));
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }

    }
}

