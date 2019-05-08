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
    set_float_value(_env, transport_bean_class, transport_bean, "resultFloat", 6.0f);
    const char* string_1 = "测试字符串===1";
    set_string_value_char(_env, transport_bean_class, transport_bean, "resultStringOne", string_1);

    jstring string_2 = _env ->NewStringUTF("测试字符串===2");
    set_string_value(_env, transport_bean_class, transport_bean, "resultStringTwo", string_2);

    return transport_bean;
}
}

