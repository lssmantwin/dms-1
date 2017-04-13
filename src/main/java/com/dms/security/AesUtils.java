package com.dms.security;

import java.io.UnsupportedEncodingException;
import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AesUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(AesUtils.class);

	private static final String KEY_ALGORITHM = "AES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	private static Key KEY;
	private static IvParameterSpec IV;

	static {
		try {
			IV = new IvParameterSpec(SECURE_RANDOM.generateSeed(16));
			KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
			keyGenerator.init(128, new SecureRandom(SECURE_RANDOM.generateSeed(32)));
			KEY = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("no such algorithm", e);
		}
	}

	private AesUtils() {
		throw new InstantiationError("can not be instantiated");
	}

	public static String encrypt(String content) {
		try {
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, KEY, IV);
			return parseByte2HexStr(cipher.doFinal(content.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("no such algorithm", e);
		} catch (NoSuchPaddingException e) {
			LOGGER.error("no such padding", e);
		} catch (BadPaddingException e) {
			LOGGER.error("bad padding", e);
		} catch (IllegalBlockSizeException e) {
			LOGGER.error("illegal block size", e);
		} catch (InvalidKeyException e) {
			LOGGER.error("invalid key", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("unsupported encoding", e);
		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.error("invalid algorithm parameter", e);
		}
		return StringUtils.EMPTY;
	}

	public static String decrypt(String content) {
		try {
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, KEY, IV);
			return new String(cipher.doFinal(parseHexStr2Byte(content)), "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("no such algorithm", e);
		} catch (NoSuchPaddingException e) {
			LOGGER.error("no such padding", e);
		} catch (BadPaddingException e) {
			LOGGER.error("bad padding", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("unsupported encoding", e);
		} catch (IllegalBlockSizeException e) {
			LOGGER.error("illegal block size", e);
		} catch (InvalidKeyException e) {
			LOGGER.error("invalid key", e);
		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.error("invalid algorithm parameter", e);
		}
		return StringUtils.EMPTY;
	}

	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}