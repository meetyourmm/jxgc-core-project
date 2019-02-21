package com.micolor.commoncore.file.service;

import com.micolor.commoncore.file.FileProperty;
import com.micolor.commoncore.message.domain.MsgBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

public interface FileOperationService {
    public String minThumb(FileProperty fileProperty, Integer width, Integer height);

    public MsgBean writeFile(MultipartFile file, HttpServletRequest request, String classType);

    public MsgBean saveToImgByInputStream(InputStream instreams, String fileName, String classType);

    public MsgBean fileUpload4WebUpLoader( MultipartFile file,String dir);
}
