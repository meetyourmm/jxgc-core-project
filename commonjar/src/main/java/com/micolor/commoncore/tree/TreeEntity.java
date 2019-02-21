package com.micolor.commoncore.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.tree
 *
 * @Author: Evangoe
 * @Description: 用于构架jstree的树形结构类
 * @Date:22/6/17
 * @Modified:
 */
public class TreeEntity {

    private String id;
    /**
     * The Text.
     */
    private String text;
    /**
     * The Parent id.
     */

    private String rootId;

    private String parentId;

    private String level;

    private HashMap attributes;

    public HashMap getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap attributes) {
        this.attributes = attributes;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    /**
     * The Icon.
     */
    public String icon;
    /**
     * The Status.
     */
    public TreeStatusEntity state;
    /**
     * The Children.
     */
    public List<TreeEntity> children;
    /**
     * The Li attr.
     */
    public Map li_attr;
    /**
     * The A attr.
     */
    public Map a_attr;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets icon.
     *
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets icon.
     *
     * @param icon the icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public TreeStatusEntity getState() {
        return state;
    }

    public void setState(TreeStatusEntity state) {
        this.state = state;
    }

    /**
     * Gets children.
     *
     * @return the children
     */
    public List<TreeEntity> getChildren() {
        return children;
    }

    /**
     * Sets children.
     *
     * @param children the children
     */
    public void setChildren(List<TreeEntity> children) {
        this.children = children;
    }

    /**
     * Gets li attr.
     *
     * @return the li attr
     */
    public Map getLi_attr() {
        return li_attr;
    }

    /**
     * Sets li attr.
     *
     * @param li_attr the li attr
     */
    public void setLi_attr(Map li_attr) {
        this.li_attr = li_attr;
    }

    /**
     * Gets a attr.
     *
     * @return the a attr
     */
    public Map getA_attr() {
        return a_attr;
    }

    /**
     * Sets a attr.
     *
     * @param a_attr the a attr
     */
    public void setA_attr(Map a_attr) {
        this.a_attr = a_attr;
    }
}
