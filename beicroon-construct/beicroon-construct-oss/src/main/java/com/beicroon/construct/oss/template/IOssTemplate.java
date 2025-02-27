package com.beicroon.construct.oss.template;

public interface IOssTemplate {

    String getName();

    String upload(byte[] bytes, String filename, String ext);

}
