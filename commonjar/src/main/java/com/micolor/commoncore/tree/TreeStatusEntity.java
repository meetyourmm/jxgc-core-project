package com.micolor.commoncore.tree;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.tree
 *
 * @Author: Evangoe
 * @Description: 树形结构的状态
 * @Date:22/6/17
 * @Modified:
 */
public class TreeStatusEntity {
    /**
     * The Opened.
     */
    private boolean opened;
    /**
     * The Disabled.
     */
    private boolean disabled;



    /**
     * The Selected.
     */
    private boolean selected;

    private boolean checked;

    public TreeStatusEntity() {
    }

    public TreeStatusEntity(boolean opened, boolean disabled, boolean selected, boolean checked) {
        this.opened = opened;
        this.disabled = disabled;
        this.selected = selected;
        this.checked =checked;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * Is opened boolean.
     *
     * @return the boolean
     */
    public boolean isOpened() {
        return opened;
    }

    /**
     * Sets opened.
     *
     * @param opened the opened
     */
    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    /**
     * Is disabled boolean.
     *
     * @return the boolean
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Sets disabled.
     *
     * @param disabled the disabled
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * Is selected boolean.
     *
     * @return the boolean
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets selected.
     *
     * @param selected the selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
