package com.thizgroup.promotion.model.converter;

import javax.persistence.Converter;
import java.util.Map;

@Converter
public class JsonMapConverter extends AbstractJsonConverter<Map<String, Object>> {}
