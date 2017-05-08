package com.dms.serializable;

import java.io.IOException;

import com.dms.enums.CommissionStateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dms.enums.ContractStateEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CommissionStateJacksonDeSerializable extends JsonDeserializer<CommissionStateEnum> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommissionStateJacksonDeSerializable.class);

	@Override
	public CommissionStateEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		return CommissionStateEnum.fromText(jsonParser.getText());
	}
}
