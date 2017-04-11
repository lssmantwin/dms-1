package com.system.security;

import org.junit.Assert;
import org.junit.Test;

public class AesUtilsTest {

	@Test
	public void test() {
		String content = "admin";
		String encryptContent = AesUtils.encrypt(content);
		String decryptContent = AesUtils.decrypt(encryptContent);
		System.out.println(content);
		System.out.println(encryptContent);
		System.out.println(decryptContent);
		Assert.assertEquals("encrypt/decrypt failure", content, decryptContent);
	}

}
