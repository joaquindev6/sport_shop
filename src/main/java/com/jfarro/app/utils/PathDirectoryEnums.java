package com.jfarro.app.utils;

public enum PathDirectoryEnums {

    USER_FILE("photousers"),
    PRODUCT_FILE("photoproducts");

    public String directorys;

    private PathDirectoryEnums(String directorys) {
        this.directorys = directorys;
    }
}
