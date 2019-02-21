package com.micolor.commoncore.file.service.impl;

import com.micolor.commoncore.file.FileProperty;
import com.micolor.commoncore.file.service.FileOperationService;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.StringStatics;
import com.micolor.commoncore.string.StringUtil;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("fileOperationService")
public class FileOperationImpl implements FileOperationService {
    @Value("${evnsign}")
    private String evnsign;
    @Autowired
    private HttpServletRequest request;
    private static Logger logger = LoggerFactory.getLogger(FileOperationImpl.class);

    public MsgBean writeFile(MultipartFile file, HttpServletRequest request, String classType) {
        String baseWebPath = StringStatics.SLASH + "webapp" + StringStatics.SLASH + "uploadfiles";
        int pre = (int) System.currentTimeMillis();
        MsgBean msgBean = new MsgBean();
        String fileName = file.getOriginalFilename(); //文件名称
        int fileSuffixPointIndex = fileName.lastIndexOf('.');
        String suffix = fileName.substring(fileSuffixPointIndex + 1);
        String fileTypeFolder = "/" + classType;//文件类型区分目录
        String fileDateFolder = "/" + StringUtil.getCurrDate(); //文件日期区分目录
        String baseDirPath = checkBaseDirPathByEvnsign();
        String savePath = baseDirPath + fileTypeFolder + fileDateFolder;
        String saveWebPath = baseWebPath + fileTypeFolder + fileDateFolder;
        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdirs();
            logger.info("创建目录【{}】成功！", savePath);
        }
        //处理分片时上传文件
        if (("blob").equals(suffix)) {
            suffix = "jpg";
        }
        String transFileName = pre + "." + suffix;
        String filePath = savePath + StringStatics.SLASH + transFileName;
        String fileWebPath = saveWebPath + StringStatics.SLASH + transFileName;
        try (
                FileOutputStream os = new FileOutputStream(filePath);
                FileInputStream in = (FileInputStream) file.getInputStream();
        ) {
            //拿到输出流，同时重命名上传的文件

            //拿到上传文件的输入流

            //以写字节的方式写文件
            int b = 0;
            while ((b = in.read()) != -1) {
                os.write(b);
            }
            os.flush();
            os.close();
            in.close();
            logger.info("保存文件【{}】成功！", filePath);
            FileProperty fileProperty = new FileProperty();
            fileProperty.setFileName(fileName);
            fileProperty.setFilePath(filePath);
            fileProperty.setFileWebPath(fileWebPath);
            fileProperty.setFileSuffix(suffix);
            fileProperty.setFileContentType(file.getContentType());
            fileProperty.setFileSize(file.getSize());
            fileProperty.setFileSaveName(transFileName);
            fileProperty.setFileSavePath(savePath);
            fileProperty.setFileSaveWebPath(saveWebPath);
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
            msgBean.setMsg("保存文件【" + filePath + "】成功！");
            msgBean.setContent(fileProperty);
        } catch (Exception e) {
            logger.error(e.getMessage());
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
            msgBean.setMsg("保存文件失败！");
        }
        return msgBean;
    }

    /**
     * 生成缩略图
     *
     * @param fileProperty
     * @param width
     * @param height
     * @return
     */
    public String minThumb(FileProperty fileProperty, Integer width, Integer height) {
        String rePath = null;
        String fileName = width + "x" + height + fileProperty.getFileSaveName();//文件名称
        String saveFile = fileProperty.getFilePath();//文件路径
        String savePath = fileProperty.getFileSavePath() + "/" + fileName;//保存目录
        try {
            Thumbnails.of(saveFile)
                    .size(width, height)
                    .toFile(savePath);
            rePath = fileProperty.getFileSaveWebPath() + "/" + fileName;
        } catch (Exception e) {
            logger.error(StringStatics.INNERERROR, e);
        }
        return rePath;
    }

    /**
     * 将二进制流保存成图片
     *
     * @param instreams
     * @param fileName
     * @param classType
     * @return
     */
    public MsgBean saveToImgByInputStream(InputStream instreams, String fileName, String classType) {
        MsgBean msgBean = new MsgBean();
        String baseWebPath = StringStatics.SLASH + "webapp" + StringStatics.SLASH + "uploadfiles";
        String basePath = checkBaseDirPathByEvnsign();
        String fileTypeFolder = "/" + classType;//文件类型区分目录
        String savePath = basePath + fileTypeFolder;
        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdirs();
            logger.info("创建目录【{}】成功！", savePath);
        }
        String transFileName = fileName + ".jpg";
        String filePath = savePath + StringStatics.SLASH + transFileName;
        String saveWebPath = baseWebPath + fileTypeFolder + StringStatics.SLASH + transFileName;
        try {
            File file = new File(filePath);//可以是任何图片格式.jpg,.png等
            FileOutputStream fos = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int nRead;
            while ((nRead = instreams.read(b)) != -1) {
                fos.write(b, 0, nRead);
            }
            fos.flush();
            fos.close();
            logger.info("保存文件【{}】成功！", filePath);
            FileProperty fileProperty = new FileProperty();
            fileProperty.setFileSaveName(transFileName);
            fileProperty.setFileSaveWebPath(saveWebPath);
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
            msgBean.setMsg("保存文件【" + filePath + "】成功！");
            msgBean.setContent(fileProperty);
        } catch (Exception e) {
            logger.error(e.getMessage());
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
            msgBean.setMsg("保存文件失败！");
        }
        return msgBean;
    }

    /**
     * 根据配置环境获取保存路径
     *
     * @return
     */
    public String checkBaseDirPathByEvnsign() {
        String resPath;
        String projectBasePath = request.getSession().getServletContext().getRealPath("/");
        String baseDirPath = projectBasePath + "webapp/uploadfiles";
        if (!"dev".equals(evnsign)) {
            resPath = "/mnt/uploadfiles";
        } else {
            resPath = baseDirPath;
        }
        return resPath;
    }

    /**
     * webupload文件上传
     *
     * @param file
     * @param dir
     * @return
     * @throws IOException
     */
    public MsgBean fileUpload4WebUpLoader(MultipartFile file, String dir) {
        MsgBean msgBean = new MsgBean();
        try {
            String baseWebPath = StringStatics.SLASH + "webapp" + StringStatics.SLASH + "uploadfiles";
            String basePath = checkBaseDirPathByEvnsign();
            String fileDateFolder = StringStatics.SLASH + StringUtil.getCurrDate(); //文件日期区分目录
            String dirPath = StringStatics.SLASH + dir;
            String savePath = basePath + dirPath + fileDateFolder;
            String fileName = file.getOriginalFilename();
            Long size = file.getSize();
            int pre = (int) System.currentTimeMillis();
            int fileSuffixPointIndex = fileName.lastIndexOf('.');
            String suffix = fileName.substring(fileSuffixPointIndex + 1);
            String transFileName = pre + "." + suffix;
            String fileWebPath = baseWebPath + dirPath + fileDateFolder + StringStatics.SLASH + transFileName;
            File dirs = new File(savePath);
            if (!dirs.exists()) {
                dirs.mkdirs();
                logger.info("创建目录【{}】成功！", savePath);
            }
            if (size < 5242880) {
                File targetFile = new File(savePath, transFileName);
                file.transferTo(targetFile); // 小文件，直接拷贝
            } else {
                File tempFile = new File(savePath, transFileName);
                OutputStream outputStream = new FileOutputStream(tempFile, true);
                InputStream inputStream = file.getInputStream();
                byte buffer[] = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                }
                inputStream.close();
                outputStream.close();
            }
            FileProperty fileProperty = new FileProperty();
            fileProperty.setFileName(fileName);
            fileProperty.setFilePath(savePath);
            fileProperty.setFileWebPath(fileWebPath);
            fileProperty.setFileSuffix(suffix);
            fileProperty.setFileContentType(file.getContentType());
            fileProperty.setFileSize(file.getSize());
            fileProperty.setFileSaveName(transFileName);
            fileProperty.setFileSavePath(savePath);
            fileProperty.setFileSaveWebPath(fileWebPath);
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
            msgBean.setContent(fileProperty);
        } catch (Exception e) {
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
            msgBean.setMsg("操作失败");
        }
        return msgBean;
    }
}
