package com.micolor.commoncore.file;

import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.StringStatics;
import com.micolor.commoncore.string.StringUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.file
 *
 * @Author: Evangoe
 * @Description:
 * @Date:06/06/17
 * @Modified:
 */
@Deprecated
public class FileOperation {
    private static Logger logger = LoggerFactory.getLogger(FileOperation.class);

    /**
     * @param file      文件
     * @param request
     * @param classType
     * @return
     */
    @Deprecated
    public MsgBean writeFile(MultipartFile file, HttpServletRequest request, String classType) {
        //String projectBasePath = request.getSession().getServletContext().getRealPath("/");
        String projectBasePath = "/mnt/";
        String baseDirPath = projectBasePath + "webapp/uploadfiles";
        String baseWebPath = StringStatics.SLASH+"webapp"+StringStatics.SLASH+"uploadfiles";
        int pre = (int) System.currentTimeMillis();
        MsgBean msgBean = new MsgBean();
        String fileName = file.getOriginalFilename(); //文件名称
        int fileSuffixPointIndex = fileName.lastIndexOf('.');
        String suffix = fileName.substring(fileSuffixPointIndex + 1);
        String fileTypeFolder = "/" + classType;//文件类型区分目录
        String fileDateFolder = "/" + StringUtil.getCurrDate(); //文件日期区分目录
        String savePath = baseDirPath + fileTypeFolder + fileDateFolder;
        String saveWebPath = baseWebPath + fileTypeFolder + fileDateFolder;
        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdirs();
            logger.info("创建目录【{}】成功！",savePath);
        }

        //处理分片时上传文件
        if (("blob").equals(suffix)) {
            suffix = "jpg";
        }

        String transFileName = pre + "." + suffix;
        String filePath = savePath + StringStatics.SLASH + transFileName;
        String fileWebPath = saveWebPath + StringStatics.SLASH + transFileName;

        try(
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
            logger.info("保存文件【{}】成功！",filePath);
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
     * @param fileProperty
     * @param width
     * @param height
     * @return
     */
    @Deprecated
    public String minThumb(FileProperty fileProperty,Integer width,Integer height){
        String rePath = null;
        String fileName = width + "x" + height + fileProperty.getFileSaveName();//文件名称
        String saveFile = fileProperty.getFilePath();//文件路径
        String savePath = fileProperty.getFileSavePath() + "/" + fileName;//保存目录
        try {
            Thumbnails.of(saveFile)
                    .size(width, height)
                    .toFile(savePath);
            rePath = fileProperty.getFileSaveWebPath()  + "/" +  fileName;
        }catch (Exception e){
            logger.error(StringStatics.INNERERROR,e);
        }
        return rePath;
    }

    /**
     * 删除单个文件
     * @param fileName
     * @return
     */
    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            try{
                file.delete();
            }catch (Exception e){
                logger.error(StringStatics.INNERERROR,e);
            }
        }
    }

    public static String ReadFile2String(String filePath){
        StringBuilder lines = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.append(line);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();;
        }
        return lines.toString();
    }
}