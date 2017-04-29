package com.dms.security;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.UUID;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] bytes = new SecureRandom().generateSeed(16);
		System.out.println(UUID.randomUUID());

	}
//0508e740-4c15-42d7-b787-85baa71f3a2d
//c842df95-ad6f-41a2-b625-ccac180e7b25
}
