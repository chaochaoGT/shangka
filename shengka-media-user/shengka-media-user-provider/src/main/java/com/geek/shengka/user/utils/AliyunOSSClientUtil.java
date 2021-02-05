package com.geek.shengka.user.utils;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.user.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
public class AliyunOSSClientUtil {

    /**
     * 阿里云API的内或外网域名
     */
    private static final String ENDPOINT = OSSConfig.endpoint;
    /**
     * 阿里云API的密钥Access Key ID
     */
    private static final String ACCESS_KEY_ID = OSSConfig.accesskeyid;
    /**
     * 阿里云API的密钥Access Key Secret
     */
    private static final String ACCESS_KEY_SECRET = OSSConfig.accesskeysecret;
    /**
     * 阿里云API的bucket名称
     */
    private static final String BACKET_NAME = OSSConfig.backetname;
    /**
     * 阿里云API的文件夹名称
     */
    private static final String FOLDER = OSSConfig.folder;
    /**
     * 阿里云API的文件夹名称
     */
    private static final String HTTP_TITLE = OSSConfig.httpTitle;

    /**
     * 获取阿里云OSS客户端对象
     *
     * @return ossClient
     */
    public static OSSClient getOSSClient() {
        return new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 创建存储空间
     *
     * @param ossClient  OSS连接
     * @param bucketName 存储空间
     * @return
     */
    public static String createBucketName(OSSClient ossClient, String bucketName) {
        //存储空间
        final String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            //创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     *
     * @param ossClient  oss对象
     * @param bucketName 存储空间
     */
    public static void deleteBucket(OSSClient ossClient, String bucketName) {
        ossClient.deleteBucket(bucketName);
    }

    /**
     * 创建模拟文件夹
     *
     * @param ossClient  oss连接
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名如"qj_nanjing/"
     * @return 文件夹名
     */
    public static String createFolder(OSSClient ossClient, String bucketName, String folder) {
        //文件夹名
        final String keySuffixWithSlash = folder;
        //判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            //创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            //得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            return object.getKey();

        }
        return keySuffixWithSlash;
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param ossClient  oss连接
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"qj_nanjing/"
     * @param key        Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key) {
        ossClient.deleteObject(bucketName, folder + key);
    }

    /**
     * 公有OSS
     * 上传图片至OSS
     *
     * @param multipartFile multipartFile文件
     * @return String 返回图片上传地址
     */
    public static BaseResponse<String> uploadPub(MultipartFile multipartFile) {
        try {
            return BaseResponse.newSuccess(ossUpload(multipartFile, getOSSClient()));
        } catch (Exception e) {
            log.error(e.toString());
            return BaseResponse.newFailure(CommonConstant.SYS_ERROR, e.getMessage());
        }
    }

    private static String ossUpload(MultipartFile multipartFile, OSSClient ossClient) {
        String fileName = null;
        String fileFolder = null;

        try {
            //以输入流的形式上传文件
            InputStream is = multipartFile.getInputStream();
            //文件名
            fileName = multipartFile.getOriginalFilename();
            String name = fileName.substring(fileName.lastIndexOf(CommonConstant.POINT));
            fileName = CommonUtil.getRandomStr(6) + name;
            //文件大小
            Long fileSize = multipartFile.getSize();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl(CommonConstant.NO_CACHE);
            //指定该Object下设置Header
            metadata.setHeader(CommonConstant.PRAGMA, CommonConstant.NO_CACHE);
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding(CommonConstant.UTF8);
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition(CommonConstant.FILE_PREFIX + fileName
                    + CommonConstant.SLASH + fileSize + CommonConstant.BYPE_WITH_POINT);
            Date currentDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = format.format(currentDate);
            if (FOLDER.length() > 1) {
                fileFolder = FOLDER + dateStr + CommonConstant.SLASH;
            } else {
                fileFolder = dateStr + CommonConstant.SLASH;
            }
            boolean found = ossClient.doesObjectExist(BACKET_NAME, fileFolder);
            if (!found) {
                createFolder(ossClient, BACKET_NAME, fileFolder);
            }
            //上传文件   (上传文件流的形式)
            ossClient.putObject(BACKET_NAME, fileFolder + fileName, is, metadata);
        } catch (IOException e) {
            log.error(e.toString());
        }
        return HTTP_TITLE + BACKET_NAME + CommonConstant.POINT + ENDPOINT + CommonConstant.SLASH + fileFolder + fileName;
    }

    public static String ossUpload(InputStream is, String fileName, Long fileSize) {
        String fileFolder = null;
        try {
            OSSClient ossClient = getOSSClient();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition(CommonConstant.FILE_PREFIX + fileName
                    + CommonConstant.SLASH + fileSize + CommonConstant.BYPE_WITH_POINT);
            //上传文件   (上传文件流的形式)
            Date currentDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = format.format(currentDate);
            if (FOLDER.length() >= 2) {
                fileFolder = FOLDER + dateStr + CommonConstant.SLASH;
            } else {
                fileFolder = dateStr + CommonConstant.SLASH;
            }
            boolean found = ossClient.doesObjectExist(BACKET_NAME, fileFolder);
            if (!found) {
                createFolder(ossClient, BACKET_NAME, fileFolder);
            }
            //上传文件   (上传文件流的形式)
            ossClient.putObject(BACKET_NAME, fileFolder + fileName, is, metadata);
        } catch (IOException | OSSException | ClientException e) {
            log.error(e.toString());
        }
        return HTTP_TITLE + BACKET_NAME + CommonConstant.POINT + ENDPOINT + CommonConstant.SLASH + fileFolder + fileName;
    }

    public static BaseResponse<List<String>> uploadPubs(MultipartFile[] multipartFiles) {
        try {
            List<String> result = new ArrayList<>(multipartFiles.length);
            List<MultipartFile> list = Arrays.asList(multipartFiles);
            list.parallelStream().forEach(file -> {
                result.add(ossUpload(file, getOSSClient()));
            });
            return BaseResponse.newSuccess(result);
        } catch (Exception e) {
            return BaseResponse.newFailure(CommonConstant.SYS_ERROR, e.getMessage());
        }
    }


    /**
     * 获取图片路径
     *
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split(CommonConstant.SLASH);
            return this.getUrl(AliyunOSSClientUtil.FOLDER + split[split.length - 1]);
        }
        return null;
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        //文件的后缀名
        return fileName.substring(fileName.lastIndexOf(CommonConstant.POINT));
//        if (CommonConstant.BMP.equalsIgnoreCase(fileExtension)) {
//            return CommonConstant.BMP_FILE_FORMAT;
//        }
//        if (CommonConstant.JPEG.equalsIgnoreCase(fileExtension)
//                || CommonConstant.JPG.equalsIgnoreCase(fileExtension)
//                || CommonConstant.PNG.equalsIgnoreCase(fileExtension)) {
//            return CommonConstant.IMAGE_FILE_FORMAT;
//        }
//        if (CommonConstant.GIF.equalsIgnoreCase(fileExtension)) {
//            return CommonConstant.IMAGE_GIF_FILE_FORMAT;
//        } else {
//            throw new RuntimeException("不是图片格式，请上传图片");
//        }
    }


    /**
     * fileName 文件名
     *
     * @return String 返回图片上传地址
     */
    public static String getDefaultImg(String fileName) {
        return BACKET_NAME + CommonConstant.POINT + ENDPOINT + CommonConstant.SLASH + FOLDER + fileName;
    }


    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
        URL url = ossClient.generatePresignedUrl(BACKET_NAME, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}
