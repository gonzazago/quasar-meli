package com.meli.quasar.constants;

public class StringContstants {

    public static final String SATELLITE_KENOBI = "kenobi";
    public static final String SATELLITE_SATO = "sato";
    public static final String SATELLITE_SKYWALKER = "skywalker";
    public static final String PATH_VARIABLE_NAME = "satellite_name";
    public static String BASE_PATH="/api/v1/";

    public static final String TOP_SECRET_PATH = BASE_PATH.concat("topsecret");
    public static final String TOP_SECRET_SPLIT_GET =BASE_PATH.concat("topsecret_split");



    public static final String TOP_SECRET_SPLIT_ID = "/{".concat(PATH_VARIABLE_NAME).concat("}");
    public static final String TOP_SECRET_SPLIT_POST = TOP_SECRET_SPLIT_GET.concat(TOP_SECRET_SPLIT_ID);



}
