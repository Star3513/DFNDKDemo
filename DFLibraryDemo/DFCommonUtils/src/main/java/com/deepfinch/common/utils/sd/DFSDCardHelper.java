package com.deepfinch.common.utils.sd;

import android.os.Environment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by DEEPFINCH on 2017/2/24.
 */

public class DFSDCardHelper {
    public static final String TAG = "DFSDCardHelper";

    public static final String DF_XIAOTONG_DIRECTORY = "xiaotong";
    public static final String DF_XIAOTONG_DIRECTORY_TEMP = "temp";
    public static final String DF_XIAOTONG_IMAGE_CACHE = "image";
    public static final String DF_XIAOTONG_DIRECTORY_BACKGROUND = "background";
    public static final String DF_XIAOTONG_BACKGROUND_IMAGE_NAME = "xiaotong-attendance-back-image";

    public static String getSDCardPath() {
        String sdCardPath = null;
        File sdFile = Environment.getExternalStorageDirectory();
        if (sdFile != null) {
            sdCardPath = sdFile.getAbsolutePath();
        }
        return sdCardPath;
    }

    public static boolean saveFile(String path, String imageName, byte[] sourceFile) {
        boolean isSuccess = true;
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File imageFile = new File(path.concat(File.separator).concat(imageName));
        try {
            copyFileUsingFileStreams(sourceFile, imageFile);
        } catch (IOException e) {
            isSuccess = false;
            e.printStackTrace();
        }
        return isSuccess;
    }

    private static void copyFileUsingFileStreams(byte[] source, File dest)
            throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(source);
        OutputStream output = null;
        try {
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }

    public static void copyFileUsingFilePath(String sourceFilePath, String destPath, String imageName) {
        File directory = new File(destPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String destFilePath = destPath.concat(File.separator).concat(imageName);

        FileInputStream inputStream = null;
        OutputStream output = null;
        try {
            inputStream = new FileInputStream(sourceFilePath);
            output = new FileOutputStream(new File(destFilePath));
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getDownloadTempPath() {
        String sdPath = getSDCardPath();
        String tempImageDirectory = sdPath.concat(File.separator)
                .concat(DF_XIAOTONG_DIRECTORY_TEMP)
                .concat(File.separator)
                .concat(DF_XIAOTONG_IMAGE_CACHE)
                .concat(File.separator);
        File imageTempFile = new File(tempImageDirectory);
        if (!imageTempFile.exists()) {
            imageTempFile.mkdirs();
        }
        return tempImageDirectory;
    }

    public static byte[] getByteByPath(String path) {
        FileInputStream inputStream = null;
        ByteArrayOutputStream out = null;
        byte[] result = null;
        try {
            inputStream = new FileInputStream(path);
            out = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                out.write(buf, 0, bytesRead);
            }

            result = out.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return result;
    }

    public static boolean deleteFileByPath(String filePath) {
        boolean delete = false;
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            delete = file.delete();
        }
        return delete;
    }

    public static boolean deleteDirectionByPath(String directionPath) {
        boolean delete = false;
        File direction = new File(directionPath);
        if (direction.isDirectory() && direction.exists()) {
            File[] files = direction.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                deleteFileByPath(file.getAbsolutePath());
            }
            delete = direction.delete();
        }

        return delete;
    }
}
