package com.meli.quasar.constants;

public class StringContstants {

    public static final String KENOBI = "kenobi";
    public static final String SATELLITE_SATO = "sato";
    public static final String SATELLITE_SKYWALKER = "skywalker";
    public static final String SATELLITES_MAP = "satellites";
    public static final String SATELLITES_LIST = "satellite-list";

    public static final String TOP_SECRET_PATH = "/topsecret";
    public static final String TOP_SECRET_SPLIT_GET = "/topsecret_split";
    public static final String PATH_VARIABLE_NAME = "satellite_name";
    public static final String TOP_SECRET_SPLIT_ID = "/{".concat(PATH_VARIABLE_NAME).concat("}");
    public static final String TOP_SECRET_SPLIT_POST = TOP_SECRET_SPLIT_GET.concat(TOP_SECRET_SPLIT_ID);

}
