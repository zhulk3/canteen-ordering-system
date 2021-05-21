package com.longkai.canteenorderingsystem.util;


import com.longkai.canteenorderingsystem.pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class JsonFactory {
    public static List<TreeNode> buildTree(List<TreeNode> nodes, int id) {
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
        for (TreeNode treeNode : nodes) {
            TreeNode tn = new TreeNode();
            tn.setId(treeNode.getId());
            tn.setText(treeNode.getText());
            if (id == treeNode.getFid()) {
                tn.setChildren(buildTree(nodes, tn.getId()));
                treeNodeList.add(tn);
            }
        }
        return treeNodeList;
    }


}

