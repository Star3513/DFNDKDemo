LOCAL_PATH := $(call my-dir)


include $(CLEAR_VARS)

LOCAL_MODULE  := df_demo_jni

LOCAL_C_INCLUDES := $(LOCAL_PATH)../ \
                    $(LOCAL_PATH)../utils
LOCAL_SRC_FILES := ../df_transport_demo_jni.cpp \
                   ../utils/df_jni_set_value_util.cpp

LOCAL_LDLIBS += -llog -lz -Wl
LOCAL_LDLIBS += $(static_libs)
LOCAL_CXXFLAGS += -Os -DHAVE_PTHREADS -fPIC -fno-rtti -fvisibility=hidden -ffunction-sections -fdata-sections -fvisibility-inlines-hidden
LOCAL_LDFLAGS += -Wl,--gc-sections -s



include $(BUILD_SHARED_LIBRARY)