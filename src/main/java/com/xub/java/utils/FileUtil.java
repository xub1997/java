package com.xub.java.utils;

import com.google.common.collect.Lists;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xub
 * @Name: FileUtil
 * @Description: TODO
 * @date 2020/2/7  16:16
 */
public class FileUtil {

    private FileUtil() {
    }

    private static List<File> files = Lists.newArrayList();

    /**
     * 获取指定目录的所有文件
     *
     * @param dir
     * @return
     */
    public static List<File> listFiles(String dir) {
        File target = new File(dir);
        listFiles(target);
        return files;
    }

    /**
     * 获取指定目录的所有文件
     *
     * @param dir
     * @return
     */
    public static List<File> listFiles(File dir) {
        if (dir.exists()) {
            File[] listFiles = dir.listFiles();
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    listFiles(file);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

    /**
     * 复制文件（字节流形式）
     *
     * @param sourceName
     * @param targetName
     */
    public static void copyFile(String sourceName, String targetName) throws IOException {
        File sourceFile = new File(sourceName);
        File targetFile = new File(targetName);
        copyFile(sourceFile, targetFile);
    }

    /**
     * 复制文件（字节流形式）
     *
     * @param sourceFile
     * @param targetFile
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        //未使用Buffer
//        InputStream input = new FileInputStream(sourceFile);
//        OutputStream out = new FileOutputStream(targetFile);
        //使用Buffer
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFile));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(targetFile));
        //建立缓冲字节数组读存文件
        byte[] buf = new byte[1024 * 3];
        int length = 0;
        while ((length = input.read(buf)) != -1) {
            out.write(buf, 0, length);
        }
        out.close();
        input.close();
    }

    /**
     * 剪切文件（字节流形式）
     *
     * @param sourceName
     * @param targetName
     */
    public static void cutFile(String sourceName, String targetName) throws IOException {
        File sourceFile = new File(sourceName);
        File targetFile = new File(targetName);
        cutFile(sourceFile, targetFile);
    }

    /**
     * 剪切文件（字节流形式）
     *
     * @param sourceFile
     * @param targetFile
     */
    public static void cutFile(File sourceFile, File targetFile) throws IOException {
        if (sourceFile.exists()) {
            copyFile(sourceFile, targetFile);
            sourceFile.delete();
        }
    }

    /**
     * 复制文件（字符流形式）
     *
     * @param sourceName
     * @param targetName
     */
    public static void copyFileByChar(String sourceName, String targetName) throws IOException  {
        File sourceFile = new File(sourceName);
        File targetFile = new File(targetName);
        copyFileByChar(sourceFile, targetFile);
    }

    /**
     * 复制文件（字符流形式）
     *
     * @param sourceFile
     * @param targetFile
     */
    public static void copyFileByChar(File sourceFile, File targetFile) throws IOException {

        //未使用Buffer
//        FileReader reader = new FileReader(sourceFile);
//        FileWriter writer = new FileWriter(targetFile);
        //使用Buffer
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));
        int length = 0;
        char[] chars = new char[1024];
        while ((length = reader.read()) != -1) {
            writer.write(chars, 0, length);
        }
        writer.close();
        reader.close();

    }

    /**
     * 剪切文件（字符流形式）
     *
     * @param sourceName
     * @param targetName
     */
    public static void cutFileByChar(String sourceName, String targetName)  throws IOException {
        File sourceFile = new File(sourceName);
        File targetFile = new File(targetName);
        cutFileByChar(sourceFile, targetFile);
    }

    /**
     * 剪切文件（字符流形式）
     *
     * @param sourceFile
     * @param targetFile
     */
    public static void cutFileByChar(File sourceFile, File targetFile) throws IOException  {
        if (sourceFile.exists()) {
            copyFileByChar(sourceFile, targetFile);
            sourceFile.delete();
        }
    }

    /**
     * 根据url拿取file
     *
     * @param url
     * @param suffix 文件后缀名
     */
    public static File createFileByUrl(String url, String suffix) {
        byte[] byteFile = getImageFromNetByUrl(url);
        if (byteFile != null) {
            File file = getFileFromBytes(byteFile, suffix);
            return file;
        } else {
            return null;
        }
    }

    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl 网络连接地址
     * @return
     */
    private static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     *
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    // 创建临时文件
    private static File getFileFromBytes(byte[] b, String suffix) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = File.createTempFile("pattern", "." + suffix);
            System.out.println("临时文件位置：" + file.getCanonicalPath());
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public static MultipartFile createImg(String url) {
        try {
            // File转换成MutipartFile
            File file = FileUtil.createFileByUrl(url, "jpg");
            FileInputStream inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
            return multipartFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MultipartFile fileToMultipart(String filePath) {
        try {
            // File转换成MutipartFile
            File file = new File(filePath);
            FileInputStream inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), "png", "image/png", inputStream);
            return multipartFile;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private static final int BASE64_SPLIT_LENGTH = 2;

    public static boolean base64ToFile(String filePath, String base64Data) throws Exception {
        String dataPrix = "";
        String data = "";

        if (base64Data == null || "".equals(base64Data)) {
            return false;
        } else {
            String[] d = base64Data.split("base64,");
            if (d != null && d.length == BASE64_SPLIT_LENGTH) {
                dataPrix = d[0];
                data = d[1];
            } else {
                return false;
            }
        }

        // 因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
        byte[] bs = Base64Utils.decodeFromString(data);
        // 使用apache提供的工具类操作流
        org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(filePath), bs);

        return true;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(listFiles("D://upload/"));
        copyFile("C:\\Users\\xub\\Documents\\HBuilderProjects\\travel\\img\\1.png",
                "C:\\Users\\xub\\Documents\\HBuilderProjects\\travel\\img\\11111111.png");
    }
}
