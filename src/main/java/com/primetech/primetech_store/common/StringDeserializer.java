package com.primetech.primetech_store.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StringDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (!p.getCurrentToken().isScalarValue() || !p.getText().matches(".*\\D.*")) {
            throw new IOException("La descripción debe ser un texto y no puede ser un número.");
        }
        return p.getText();
    }
}
