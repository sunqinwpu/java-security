/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package org.dolphin.study.java.security.key;

import org.junit.Test;

/**
 *
 * @author sunqi
 * @version $Id: KeyGenerateTest.java, v 0.1 2015年12月9日 下午11:47:36 sunqi Exp $
 */
public class KeyGenerateTest {
    @Test
    public void testGeneAesHexKey() throws Exception {
        System.out.println("aes 128 hex key :" + KeyGenerate.geneAesHexKey(128));
    }

    @Test
    public void testGeneAesBase64Key() throws Exception {
        System.out.println("aes 128 base64 key :" + KeyGenerate.geneAesBase64Key(128));
    }
}
