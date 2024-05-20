package com.example.springsecuritydemo.result.response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ResultDataSerializer<T> extends StdSerializer<T> {

    public ResultDataSerializer() {
        this(null);
    }

    public ResultDataSerializer(Class<T> t) {
        super(t);
    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeObject(new Object());
    }
}
