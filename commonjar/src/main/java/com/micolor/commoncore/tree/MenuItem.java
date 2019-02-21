package com.micolor.commoncore.tree;

import java.util.List;

/**
 * Created by Evangoe on 28/12/16.
 */
public class MenuItem {
    private String moduleId; //模块编号
    private String moduleName;//模块名称
    private String moduleSn;//模块标识
    private String moduleUrl;//模块地址
    private String icon;//模块图标
    private String cssId;//模块样式
    private String priority;//排序编号
    private String parentId;//父节点编号
    private String systemId;//系统编号
    private List<MenuItem> children;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleSn() {
        return moduleSn;
    }

    public void setModuleSn(String moduleSn) {
        this.moduleSn = moduleSn;
    }

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCssId() {
        return cssId;
    }

    public void setCssId(String cssId) {
        this.cssId = cssId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItem> children) {
        this.children = children;
    }
}
