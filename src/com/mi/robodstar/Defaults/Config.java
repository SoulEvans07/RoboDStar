package com.mi.robodstar.Defaults;

public class Config{
    // Robot Field of View  (FOV)
    private static int FOV;

    // Config asset paths
    private static String C_HMAP_PATH;
    private static String C_MAP_PATH;


    public static void setDefaults(){
        FOV = Reference.DEF_FOV;
        C_HMAP_PATH = Reference.HMAP_PATH;
        C_MAP_PATH = Reference.MAP_PATH;
    }

    // TODO: after this reload maps
    public static void setMapPaths(String pathM, String pathH){
        C_HMAP_PATH = pathH;
        C_MAP_PATH = pathM;
    }

    public static String getHeuriticMapPath(){
        return C_HMAP_PATH;
    }

    public static String getMapPath(){
        return C_MAP_PATH;
    }

    public static int getFOV(){
        return FOV;
    }
}
