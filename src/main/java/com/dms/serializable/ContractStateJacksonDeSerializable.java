package com.dms.serializable;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dms.enums.ContractStateEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ContractStateJacksonDeSerializable extends JsonDeserializer<ContractStateEnum> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContractStateJacksonDeSerializable.class);

	@Override
	public ContractStateEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		return ContractStateEnum.fromText(jsonParser.getText());
	}
}
