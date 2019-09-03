package com.magic.magicprojectbaseapp.core.http.converter.gson;


import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * <pre>
 *     author :
 *     time   : 2019/06/05
 *     desc   :Gson解析相关
 * </pre>
 */
 final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String s=value.string();
            return adapter.fromJson(s);
        } finally {
            value.close();
        }
    }
}