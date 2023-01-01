package com.microservice3.notificationservice.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Map;

public class StudentSerDes implements Serializer<Student>, Deserializer<Student> {
    public static final ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, Student student) {


        try {
            return mapper.writeValueAsBytes(student);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e);
        }

    }

    @Override
    public Student deserialize(String topic, byte[] data) {
        try {
            return mapper.readValue(data, Student.class);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }


    @Override
    public void close() {
        Serializer.super.close();
    }
}
