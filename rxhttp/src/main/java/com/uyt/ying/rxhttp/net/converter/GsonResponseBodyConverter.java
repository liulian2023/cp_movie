/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uyt.ying.rxhttp.net.converter;

import com.uyt.ying.rxhttp.net.exception.RefreshTokenExpiredException;
import com.uyt.ying.rxhttp.net.exception.ServerResponseException;
import com.uyt.ying.rxhttp.net.exception.TokenExpiredException;
import com.uyt.ying.rxhttp.net.model.BaseEntity;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static com.uyt.ying.rxhttp.net.common.ErrorCode.REFRESH_TOKEN_EXPIRED;
import static com.uyt.ying.rxhttp.net.common.ErrorCode.SUCCESS;
import static com.uyt.ying.rxhttp.net.common.ErrorCode.TOKEN_EXPIRED;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, Object> {

    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {

        this.adapter = adapter;
    }

    @Override public Object convert(ResponseBody value) throws IOException {

        try {
            BaseEntity response = (BaseEntity) adapter.fromJson(value.charStream());
            if (response.getErrorCode() == SUCCESS) {
                return response;
            }else if (response.getErrorCode() == TOKEN_EXPIRED) {
                throw new TokenExpiredException(response.getErrorCode(), response.getErrorMsg());
            } else if (response.getErrorCode() == REFRESH_TOKEN_EXPIRED) {
                throw new RefreshTokenExpiredException(response.getErrorCode(), response.getErrorMsg());
            }
            else  {
                throw new ServerResponseException(response.getErrorCode(), response.getErrorMsg());
            }
        } finally {
            value.close();
        }

    }
}
