package com.aluracursos.desafio.service;

import com.aluracursos.desafio.service.IConvierteDatos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {

    private final ObjectMapper mapper;

    public ConvierteDatos() {
        this.mapper = new ObjectMapper();
        // 👇 Muy importante: ignorar campos extra del JSON
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir JSON", e);
        }
    }
}
