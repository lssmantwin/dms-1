package com.dms.serializable;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dms.exception.BadRequestException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class PasswordEncrypt extends JsonDeserializer<String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordEncrypt.class);

	@Override
	public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		String value = jsonParser.getText();
		if (StringUtils.isBlank(value)) {
			LOGGER.error("password must be not blank");
			throw new BadRequestException("password must be not blank");
		}
		return DigestUtils.sha512Hex(value);
	}
}
