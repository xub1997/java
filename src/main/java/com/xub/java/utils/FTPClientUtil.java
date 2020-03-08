package com.xub.java.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

@Slf4j
public class FTPClientUtil {

    private FTPClientUtil() {
    }


    /**
     * Description: 向FTP服务器上传文件
     *
     * @param url      FTP服务器hostname
     * @param port     FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param filePath FTP服务器保存目录
     * @param fileName 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String url, int port, String username, String password,
                                     String filePath, String fileName, InputStream input) throws IOException {
        boolean isSuccess = true;
        FTPClient ftpClient = new FTPClient();
        try {
            //连接ftp服务器
            ftpClient.connect(url, port);
            //登录ftp服务器
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode();
            //是否成功登录服务器
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                isSuccess = false;
                return isSuccess;
            }
            //转移到FTP服务器目录
            ftpClient.changeWorkingDirectory(filePath);
            //保存文件
            ftpClient.storeFile(fileName, input);
            //客户端登出
            ftpClient.logout();
        } catch (IOException e) {
            log.error("上传文件到 {} 失败", url, e);
        } finally {
            //关闭输入流
            if (input != null) {
                input.close();
            }
            //关闭连接
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }
        return isSuccess;
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     * @Version1.0
     */
    public static boolean downFile(String url, int port, String username, String password,
                                   String remotePath, String fileName, String localPath) throws IOException {
        boolean isSuccess = true;
        FTPClient ftpClient = new FTPClient();
        OutputStream output = null;
        try {
            //连接ftp服务器
            ftpClient.connect(url, port);
            //登录ftp服务器
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode();
            //是否成功登录服务器
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                isSuccess = false;
                return isSuccess;
            }
            //转移到FTP服务器目录
            ftpClient.changeWorkingDirectory(remotePath);
            //列举文件
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.getName().equals(fileName)) {
                    File localFile = new File(localPath + File.separator + ftpFile.getName());
                    output = new FileOutputStream(localFile);
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.retrieveFile(ftpFile.getName(), output);
                }
            }
            //客户端登出
            ftpClient.logout();
        } catch (IOException e) {
            log.error("从 {} 的{} 路径下载 {} 文件到 {} 失败", url, remotePath, fileName, localPath, e);
        } finally {
            //关闭输出流
            if (output != null) {
                output.close();
            }
            //关闭连接
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }
        return isSuccess;
    }

    /**
     * Description: 从FTP服务器删除文件
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要删除的文件名
     * @return
     * @Version1.0
     */
    public static boolean deleteFile(String url, int port, String username, String password,
                                     String remotePath, String fileName) throws IOException {
        boolean isSuccess = true;
        FTPClient ftpClient = new FTPClient();
        OutputStream output = null;
        try {
            //连接ftp服务器
            ftpClient.connect(url, port);
            //登录ftp服务器
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode();
            //是否成功登录服务器
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                isSuccess = false;
                return isSuccess;
            }
            //转移到FTP服务器目录
            ftpClient.changeWorkingDirectory(remotePath);
            //删除文件
            ftpClient.dele(fileName);
            //客户端登出
            ftpClient.logout();
        } catch (IOException e) {
            log.error("从 {} 的{} 路径删除 {} 文件失败", url, remotePath, fileName, e);
        } finally {
            //关闭输出流
            if (output != null) {
                output.close();
            }
            //关闭连接
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }
        return isSuccess;
    }

    /**
     * Description: 从FTP服务器创建文件夹
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param dirName    要创建的目录名
     * @return
     * @Version1.0
     */
    public static boolean makeDir(String url, int port, String username, String password,
                                  String remotePath, String dirName) throws IOException {
        boolean isSuccess = true;
        FTPClient ftpClient = new FTPClient();
        OutputStream output = null;
        try {
            //连接ftp服务器
            ftpClient.connect(url, port);
            //登录ftp服务器
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode();
            //是否成功登录服务器
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                isSuccess = false;
                return isSuccess;
            }
            //转移到FTP服务器目录
            ftpClient.changeWorkingDirectory(remotePath);
            //创建目录
            ftpClient.makeDirectory(dirName);
            //客户端登出
            ftpClient.logout();
        } catch (IOException e) {
            log.error("从 {} 的{} 路径创建 {} 文件夹失败", url, remotePath, dirName, e);
        } finally {
            //关闭输出流
            if (output != null) {
                output.close();
            }
            //关闭连接
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }
        return isSuccess;
    }

    /**
     * Description: 从FTP服务器删除文件夹
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param dirName    要创建的目录名
     * @return
     * @Version1.0
     */
    public static boolean deleteDir(String url, int port, String username, String password,
                                  String remotePath, String dirName) throws IOException {
        boolean isSuccess = true;
        FTPClient ftpClient = new FTPClient();
        OutputStream output = null;
        try {
            //连接ftp服务器
            ftpClient.connect(url, port);
            //登录ftp服务器
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode();
            //是否成功登录服务器
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                isSuccess = false;
                return isSuccess;
            }
            //转移到FTP服务器目录
            ftpClient.changeWorkingDirectory(remotePath);
            //创建目录
            ftpClient.makeDirectory(dirName);
            //客户端登出
            ftpClient.logout();
        } catch (IOException e) {
            log.error("从 {} 的{} 路径创建 {} 文件夹失败", url, remotePath, dirName, e);
        } finally {
            //关闭输出流
            if (output != null) {
                output.close();
            }
            //关闭连接
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }
        return isSuccess;
    }

}
