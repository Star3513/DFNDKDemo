//
// Created by hanlz on 2019/4/22.
//
#include <jni.h>
#include <cstring>

#include <stdlib.h>
#include <stdio.h>
#include "utils/df_jni_set_value_util.h"

#define jni_package(x) Java_com_deepfinch_ndk_dfjnilib_jni_DFTransportDemo_##x

extern "C" {
JNIEXPORT jint
JNICALL
jni_package(getCurrentTimeHour)(JNIEnv *_env, jclass _this) {
    jint result_int = 3;
    return result_int;
}

JNIEXPORT jobject
JNICALL
jni_package(getTransportBean)(JNIEnv *_env, jclass _this) {
    jclass transport_bean_class = _env->FindClass("com/deepfinch/ndk/dfjnilib/jni/DFTransportBean");
    jobject transport_bean = _env->AllocObject(transport_bean_class);

    set_int_value(_env, transport_bean_class, transport_bean, "resultInt", 5);

    int result_int_array_length = 4;
    int *result_int_array = new int[result_int_array_length];
    for (int i = 0; i < result_int_array_length; ++i) {
        result_int_array[i] = i;
    }
    set_int_array_value(_env, transport_bean_class, transport_bean, "resultIntArray", result_int_array, result_int_array_length);
    free(result_int_array);

    int result_float_array_length = 5;
    float *result_float_array = new float[result_float_array_length];
    for (int i = 0; i < result_float_array_length; ++i) {
        result_float_array[i] = i * 0.8f;
    }
    set_float_array_value(_env, transport_bean_class, transport_bean, "resultFloatArray", result_float_array, result_float_array_length);
    free(result_float_array);

    set_float_value(_env, transport_bean_class, transport_bean, "resultFloat", 6.0f);
    const char* string_1 = "测试字符串===1";
    set_string_value_char(_env, transport_bean_class, transport_bean, "resultStringOne", string_1);

    jstring string_2 = _env ->NewStringUTF("测试字符串===2");
    set_string_value(_env, transport_bean_class, transport_bean, "resultStringTwo", string_2);

    const char* transport_model_class_path = "com/deepfinch/ndk/dfjnilib/jni/DFTransportModel";
    const char* transport_model_class_signature = "Lcom/deepfinch/ndk/dfjnilib/jni/DFTransportModel;";
    jclass transport_model_class = _env->FindClass(transport_model_class_path);
    jobject transport_model = _env->AllocObject(transport_model_class);
    set_string_value_char(_env, transport_model_class, transport_model, "description", "字段的一个类");
    set_object_value(_env, transport_bean_class, transport_bean, "transportModel", transport_model, transport_model_class_signature);

    return transport_bean;
}
}

