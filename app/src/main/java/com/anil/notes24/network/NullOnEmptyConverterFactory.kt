package com.anil.groceries.network


import android.annotation.SuppressLint
import androidx.annotation.Nullable
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


internal class NullOnEmptyConverterFactory private constructor() : Converter.Factory() {
    @SuppressLint("KotlinNullnessAnnotation")
    @Nullable
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, *> =
            retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter { body ->
            if (body.contentLength() == 0L) {
                "{}" // Empty JSON element
            } else delegate.convert(body)
        }
    }

    companion object {
        fun create(): Converter.Factory {
            return NullOnEmptyConverterFactory()
        }
    }
}