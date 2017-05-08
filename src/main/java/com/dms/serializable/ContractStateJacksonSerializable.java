package com.dms.serializable;

import java.io.IOException;

import com.dms.enums.ContractStateEnum;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ContractStateJacksonSerializable extends JsonSerializer<ContractStateEnum> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContractStateJacksonSerializable.class);

	@Override
	public void serialize(ContractStateEnum state, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		if (state != null) {
			ObjectMapper om = new ObjectMapper();
			om.writeValue(jsonGenerator, state.getText());
		} else {
			LOGGER.info("contract state is null");
		}
	}
}
