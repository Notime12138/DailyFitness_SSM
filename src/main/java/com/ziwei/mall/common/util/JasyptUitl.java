package com.ziwei.mall.common.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;

import java.util.Scanner;

/**
 * @author Ziwei GONG
 * @date 2023/4/13
 * @name mallSpringboot
 * 用于配置加密的Jasypt工具类，使用前需要输入密码
 */

public class JasyptUitl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 加密
        String encodedInfo = encrypt(scanner.nextLine());
        System.out.println(encodedInfo);

        // 解密
//        String decodedInfo = decrypt(scanner.nextLine());
//        System.out.println(decodedInfo);
    }

    private static String encrypt(String code) {
        // 加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        // 加密配置
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        // 加密算法
        config.setAlgorithm("PBEWithMD5AndDes");
        // 加密密码公钥
        config.setPassword("记得输密码");
        encryptor.setConfig(config);
        String encodedCode = encryptor.encrypt(code);
        return encodedCode;
    }

    private static String decrypt(String code) {
        // 加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        // 加密配置
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        // 加密算法
        config.setAlgorithm("PBEWithMD5AndDes");
        // 加密密码公钥
        config.setPassword("记得输密码");
        encryptor.setConfig(config);
        String decodedCode = encryptor.decrypt(code);
        return decodedCode;
    }
}
