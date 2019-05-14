//
// Created by hanlz on 2019/4/22.
//

#ifndef DFJNIDEMO_DF_JNI_SET_VALUE_UTIL_H
#define DFJNIDEMO_DF_JNI_SET_VALUE_UTIL_H

#include <jni.h>
#include <cstring>
#include <string>

extern "C" {

jobject
set_int_value(JNIEnv *env, jclass return_class, jobject return_object, std::string field_name,
        jint int_value);

jobject
set_int_array_value(JNIEnv *env, jclass return_class, jobject return_object, std::string field_name,
                    int* int_array_value, int int_array_length);

jobject
set_float_value(JNIEnv *env, jclass return_class, jobject return_object, std::string field_name,
                jfloat float_value);

jobject
set_float_array_value(JNIEnv *env, jclass return_class, jobject return_object,
                      std::string field_name, float *float_array_value, int float_array_length);

jobject
set_string_value_char(JNIEnv *env, jclass return_class, jobject return_object, std::string field_name,
                 const char* char_array_value);

jobject
set_string_value(JNIEnv *env, jclass return_class, jobject return_object, std::string field_name,
                 jstring string_value);

jobject
set_object_value(JNIEnv *env, jclass return_class, jobject return_object, std::string field_name,
                 jobject object_value, std::string class_path_value);

};

#endif //DFJNIDEMO_DF_JNI_SET_VALUE_UTIL_H
