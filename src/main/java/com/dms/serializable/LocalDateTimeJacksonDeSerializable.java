package com.dms.serializable;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeJacksonDeSerializable extends JsonDeserializer<LocalDateTime> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateTimeJacksonDeSerializable.class);

	@Override
	public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		String value = StringUtils.left(jsonParser.getValueAsString(), 10);
		if (StringUtils.isNotBlank(value)) {
			return LocalDateTime.parse(value, DateTimeFormat.forPattern("yyyy-MM-dd"));
		}
		return null;
	}
}
