package com.dms.serializable;

import java.io.IOException;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ShortLocalDateTimeJacksonSerializable extends JsonSerializer<LocalDateTime> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShortLocalDateTimeJacksonSerializable.class);

	@Override
	public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		if (localDateTime != null) {
			ObjectMapper om = new ObjectMapper();
			om.writeValue(jsonGenerator, localDateTime.toString("yyyy-MM"));
		} else {
			LOGGER.info("localDateTime is null");
		}
	}
}
