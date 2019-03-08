package com.util.smb;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

import java.io.*;

/**
 * 类名称: SmbUtils
 * 类描述: Smb远程操作文件
 * @author squirrel
 * @date 2019-03-08
 */
public class SmbUtils {

    private SmbUtils(){}

    /**
     * 从共享目录拷贝文件到本地
     * @param domainip 共享文件所在机器IP
     * @param username 用户名
     * @param password 密码
     * @param remoteUrl 共享目录上的文件路径
     * @param localDir 本地目录
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public static void smbGet(String domainip, String username, String password, String remoteUrl, String localDir) throws IOException {
        // 先登录验证
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(domainip, username, password);
        SmbFile remoteFile = new SmbFile(remoteUrl, auth);
        if (remoteFile == null) {
            System.out.println("共享文件不存在 ！");
            return;
        }
        String fileName = remoteFile.getName();
        File localFile = new File(localDir + File.separator + fileName);
        try (InputStream in = new BufferedInputStream(new SmbFileInputStream(remoteFile)); OutputStream out = new BufferedOutputStream(new FileOutputStream(localFile))) {
            byte[] buffer = new byte[1024];
            while (in.read(buffer) != -1) {
                out.write(buffer);
                buffer = new byte[1024];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param domainip 共享文件所在机器IP
     * @param username 用户名
     * @param password 密码
     * @param remoteUrl 共享文件目录
     * @param localFilePath 本地文件绝对路径
     * @throws IOException
     */
    public static void smbPut(String domainip, String username, String password, String remoteUrl, String localFilePath) throws IOException {
        // 先登录验证
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(domainip, username, password);
        File localFile = new File(localFilePath);
        String fileName = localFile.getName();
        SmbFile remoteFileDir = new SmbFile(remoteUrl, auth);
        if (!remoteFileDir.exists()) {
            remoteFileDir.mkdir();
        }
        SmbFile remoteFile = new SmbFile(remoteUrl + "/" + fileName, auth);
        try (BufferedReader buffer = new BufferedReader(new FileReader(localFile)); OutputStream out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile))) {
            String line = null;
            while((line = buffer.readLine()) != null) {
                byte[] bytes = (line + "\n").getBytes();
                out.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
