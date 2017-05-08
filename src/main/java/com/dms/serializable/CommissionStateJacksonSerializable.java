package com.dms.serializable;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dms.enums.CommissionStateEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CommissionStateJacksonSerializable extends JsonSerializer<CommissionStateEnum> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommissionStateJacksonSerializable.class);

	@Override
	public void serialize(CommissionStateEnum state, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		if (state != null) {
			ObjectMapper om = new ObjectMapper();
			om.writeValue(jsonGenerator, state.getText());
		} else {
			LOGGER.info("commission state is null");
		}
	}
}
