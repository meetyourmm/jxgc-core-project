package com.micolor.commoncore.file;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.file
 *
 * @Author: Evangoe
 * @Description: 文件通用属性
 * @Date:06/06/17
 * @Modified:
 */
public class FileProperty {
    private String fileName; //文件名称
    private String filePath; //文件路径
    private String fileSuffix; //文件后缀名称
    private String fileWebPath; //文件web访问路径
    private String fileSaveName;//文件保存名称
    private String fileSavePath;//文件保存路径
    private String fileSaveWebPath;//文件访问路径
    private Long fileSize;//文件大小
    private String fileContentType;//文件类型

    /**
     * Gets file size.
     *
     * @return the file size
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * Sets file size.
     *
     * @param fileSize the file size
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Gets file save web path.
     *
     * @return the file save web path
     */
    public String getFileSaveWebPath() {
        return fileSaveWebPath;
    }

    /**
     * Sets file save web path.
     *
     * @param fileSaveWebPath the file save web path
     */
    public void setFileSaveWebPath(String fileSaveWebPath) {
        this.fileSaveWebPath = fileSaveWebPath;
    }

    /**
     * Gets file save name.
     *
     * @return the file save name
     */
    public String getFileSaveName() {
        return fileSaveName;
    }

    /**
     * Sets file save name.
     *
     * @param fileSaveName the file save name
     */
    public void setFileSaveName(String fileSaveName) {
        this.fileSaveName = fileSaveName;
    }

    /**
     * Gets file save path.
     *
     * @return the file save path
     */
    public String getFileSavePath() {
        return fileSavePath;
    }

    /**
     * Sets file save path.
     *
     * @param fileSavePath the file save path
     */
    public void setFileSavePath(String fileSavePath) {
        this.fileSavePath = fileSavePath;
    }

    /**
     * Gets file web path.
     *
     * @return the file web path
     */
    public String getFileWebPath() {
        return fileWebPath;
    }

    /**
     * Sets file web path.
     *
     * @param fileWebPath the file web path
     */
    public void setFileWebPath(String fileWebPath) {
        this.fileWebPath = fileWebPath;
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets file name.
     *
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets file path.
     *
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets file path.
     *
     * @param filePath the file path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets file suffix.
     *
     * @return the file suffix
     */
    public String getFileSuffix() {
        return fileSuffix;
    }

    /**
     * Sets file suffix.
     *
     * @param fileSuffix the file suffix
     */
    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    /**
     * Gets file content type.
     *
     * @return the file content type
     */
    public String getFileContentType() {
        return fileContentType;
    }

    /**
     * Sets file content type.
     *
     * @param fileContentType the file content type
     */
    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
}
