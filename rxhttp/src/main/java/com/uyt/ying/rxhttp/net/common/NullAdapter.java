package com.uyt.ying.rxhttp.net.common;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * created  by xxxx on 2019/9/12.
 */
public class NullAdapter extends TypeAdapter<String> {

    @Override
    public void write(JsonWriter out, String value) throws IOException {
        if (null == value) {
            out.nullValue();
            return;
        }
        out.value(value);
    }

    @Override
    public String read(JsonReader in) throws IOException {
        if (JsonToken.NULL == in.peek()) {
            in.nextNull();
            return "";
        }
        return in.nextString();
    }
}
