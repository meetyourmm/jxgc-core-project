package com.micolor.commoncore.file;

import com.micolor.commoncore.file.service.FileOperationService;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import io.swagger.annotations.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.file
 *
 * @Author: Ann
 * @Description:
 * @Date:2017/7/1
 * @Modified:
 */
@Controller
@EnableAutoConfiguration
@Api(value = "富文本上传图片接口", tags = "富文本上传图片接口")
public class FroalaEditorController {

    private static Logger logger = LoggerFactory.getLogger(FroalaEditorController.class);
    @Autowired
    private FileOperationService fileOperationService;

    /**
     * 富文本上传图片
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/file/froala/editor", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "富文本上传图片并返回图片路径", tags = "富文本上传图片并返回图片路径")
    public String saveEditorPic(@ApiParam(value = "上传的文件", required = true, name = "myFileName") @RequestParam("myFileName") MultipartFile[] file, HttpServletRequest request) throws IOException {
        MsgBean msgBean;
        String imgUrl = null;
        String basePath = request.getContextPath();
        msgBean = fileOperationService.writeFile(file[0], request, "froala");
        if (msgBean.getStatus().equals(EnumUtil.MessageStatus.OK)) {
            FileProperty fileProperty = (FileProperty) msgBean.getContent();
            imgUrl = basePath + fileProperty.getFileWebPath();
        }
        return imgUrl;
    }

    /**
     * 富文本上传图片
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/file/wang/editor", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "富文本上传图片并返回图片路径", tags = "富文本上传图片并返回图片路径形如{errno:0/1,data:文件路径}的JSON串")
    public JSONObject saveEditorPics(@ApiParam(value = "上传的文件", required = true, name = "myFileName") @RequestParam("myFileName") MultipartFile[] file, HttpServletRequest request) throws IOException {
        MsgBean msgBean;
        String imgUrl = "";
        String basePath = request.getContextPath();
        msgBean = fileOperationService.writeFile(file[0], request, "froala");
        JSONObject json = new JSONObject();
        JSONArray data = new JSONArray();
        if (msgBean.getStatus().equals(EnumUtil.MessageStatus.OK)) {
            FileProperty fileProperty = (FileProperty) msgBean.getContent();
            imgUrl = basePath + fileProperty.getFileWebPath();
            json.put("errno", "0");
            data.add(imgUrl);
            json.put("data", data);
        } else {
            json.put("errno", "1");
            json.put("data", "");
        }
        return json;
    }

    /**
     * 上传图片返会图片url
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @ApiIgnore
    @RequestMapping(value = "/file/fileinput/pic", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String saveFileInputPic(@RequestParam("myFileName") MultipartFile[] file, HttpServletRequest request) throws IOException {
        MsgBean msgBean;
        String imgUrl = null;
        String dir = request.getParameter("fileDir");
        FileOperation fileOperation = new FileOperation();
        msgBean = fileOperationService.writeFile(file[0], request, dir);
        if (msgBean.getStatus().equals(EnumUtil.MessageStatus.OK)) {
            FileProperty fileProperty = (FileProperty) msgBean.getContent();
            imgUrl = fileProperty.getFileWebPath();
        }
        return imgUrl;
    }

    /**
     * 上传附件返回附件信息
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @ApiIgnore
    @RequestMapping(value = "/file/fileinput/file", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public MsgBean saveFileInputFiles(@RequestParam("myFileName") MultipartFile[] file, HttpServletRequest request) throws IOException {
        MsgBean msgBean;
        String dir = request.getParameter("fileDir");
        FileOperation fileOperation = new FileOperation();
        msgBean = msgBean = fileOperationService.writeFile(file[0], request, dir);
        return msgBean;
    }

    /**
     * webuploader上传附件单张
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @ApiIgnore
    @RequestMapping(value = "/file/webuploader/file2single", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody()
    public MsgBean saveFileInputFiles(MultipartFile file, HttpServletRequest request) throws IOException {
        MsgBean msgBean;
        String dir = request.getParameter("fileDir");
        msgBean = fileOperationService.writeFile(file, request, dir);
        return msgBean;
    }

    @ApiIgnore
    @PostMapping(value = "/file/webuploader/upload")
    @ResponseBody()
    public MsgBean fileUpload4WebUpLoader(@RequestParam("file") MultipartFile file, @RequestParam("dir") String dir) {
        return fileOperationService.fileUpload4WebUpLoader(file, dir);
    }
}
