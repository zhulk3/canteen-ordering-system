package com.longkai.canteenorderingsystem.pojo;

import java.util.HashSet;
import java.util.Set;

public class Functions implements Comparable<Functions> {
    private int id;
    private String name;
    private int parentId;
    private boolean isLeaf;
    private Set ais = new HashSet();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getParentId() {
        return parentId;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public Set getAis() {
        return ais;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public void setAis(Set ais) {
        this.ais = ais;
    }

    @Override
    public int compareTo(Functions o) {
        return ((Integer) this.getId()).compareTo((Integer) (o.getId()));
    }
}

