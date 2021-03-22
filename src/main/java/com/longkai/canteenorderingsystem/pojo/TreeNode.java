package com.longkai.canteenorderingsystem.pojo;


import java.util.List;

public class TreeNode {
    private int id;
    private String text;
    private int fid;
    private List<TreeNode> children;

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getFid() {
        return fid;
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}

