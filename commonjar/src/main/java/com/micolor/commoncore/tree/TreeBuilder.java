package com.micolor.commoncore.tree;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.tree
 *
 * @Author: Evangoe
 * @Description:
 * @Date:22/6/17
 * @Modified:
 */
public class TreeBuilder {

    @SuppressWarnings("unchecked")
    public List<TreeEntity> buildListToTree(List<TreeEntity> dirs) {
        List<TreeEntity> roots = findRoots(dirs);
        List<TreeEntity> notRoots = (List<TreeEntity>) CollectionUtils
                .subtract(dirs, roots);
        for (TreeEntity root : roots) {
            root.setChildren(findChildren(root, notRoots));
        }
        return roots;
    }


    public List<TreeEntity> findRoots(List<TreeEntity> allNodes) {
        List<TreeEntity> results = new ArrayList<TreeEntity>();
        for (TreeEntity node : allNodes) {
            boolean isRoot = true;
            for (TreeEntity comparedOne : allNodes) {
                if (node.getParentId() .equals(comparedOne.getId()) ) {
                    isRoot = false;
                    break;
                }
            }
            if (isRoot) {
                node.setLevel("0");
                results.add(node);
                node.setRootId(node.getId());
            }
        }
        return results;
    }


    @SuppressWarnings("unchecked")
    private List<TreeEntity> findChildren(TreeEntity root, List<TreeEntity> allNodes) {
        List<TreeEntity> children = new ArrayList<TreeEntity>();

        for (TreeEntity comparedOne : allNodes) {
            if (comparedOne.getParentId() .equals(root.getId()) ) {
//                comparedOne.setParent(root);
                children.add(comparedOne);
            }
        }
        List<TreeEntity> notChildren = (List<TreeEntity>) CollectionUtils.subtract(allNodes, children);
        for (TreeEntity child : children) {
            List<TreeEntity> tmpChildren = findChildren(child, notChildren);
            HashMap map = new HashMap();
            if (tmpChildren == null || tmpChildren.size() < 1) {
                map.put("leaf","true");
            } else {
                map.put("leaf","false");
            }
            child.setLi_attr(map);
            child.setChildren(tmpChildren);
        }
        return children;
    }
}
