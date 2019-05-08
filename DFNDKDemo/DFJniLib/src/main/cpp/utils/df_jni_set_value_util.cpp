//
// Created by hanlz on 2019/4/22.
//
#include "df_jni_set_value_util.h"

extern "C" {

jobject
set_int_value(JNIEnv *env, jclass return_class, jobject return_object, std::string field_name,
              jint int_value){
    jfieldID field_id = env->GetFieldID(return_class, field_name.c_str(), "I");
    env->SetIntField(return_object, field_id, int_value);
    return return_object;
}

jobject
set_int_array_value(JNIEnv *env, jclass return_class, jobject return_object, std::string field_name,
                    int* int_array_value, int int_array_length){
    jfieldID field_id = env->GetFieldID(return_class, field_name.c_str(), "I");
    jintArray return_int_array = env->NewIntArray(int_array_length);
    env->SetIntArrayRegion(return_int_array, 0, int_array_length, int_array_value);
    env->SetObjectField(return_object, field_id, return_int_array);
    return return_object;
}

jobject
set_float_value(JNIEnv *env, jclass return_class, jobject return_object, std::string field_name,
                jfloat float_value) {
    jfieldID field_id = env->GetFieldID(return_class, field_name.c_str(), "F");
    env->SetFloatField(return_object, field_id, float_value);
    return return_object;
}

jobject
set_string_value_char(JNIEnv *env, jclass return_class, jobject return_object, std::string field_name,
                 const char* char_array_value) {
    jfieldID field_id = env->GetFieldID(return_class, field_name.c_str(), "Ljava/lang/String;");
    jstring tempStringValue = env->NewStringUTF(char_array_value);
    env->SetObjectField(return_object, field_id, tempStringValue);
    return return_object;
}

jobject
set_string_value(JNIEnv *env, jclass return_class, jobject return_object, std::string field_name,
                 jstring string_value) {
    jfieldID field_id = env->GetFieldID(return_class, field_name.c_str(), "Ljava/lang/String;");

    env->SetObjectField(return_object, field_id, string_value);
    return return_object;
}

}
