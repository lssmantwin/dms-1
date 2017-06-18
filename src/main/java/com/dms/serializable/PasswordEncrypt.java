package com.dms.serializable;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dms.exception.BadRequestException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import sun.misc.BASE64Encoder;

public class PasswordEncrypt extends JsonDeserializer<String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordEncrypt.class);

	@Override
	public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		String value = jsonParser.getText();
		if (StringUtils.isBlank(value)) {
			LOGGER.error("password must be not blank");
			throw new BadRequestException("password must be not blank");
		}
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			return base64en.encode(md5.digest(jsonParser.getText().getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("no such algorithm exception", e);
		}
		return StringUtils.EMPTY;
	}
}
