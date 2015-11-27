package com.mi.robodstar.Defaults;

public class Config{
    /* Singleton instance */
    private static Config config = new Config( );

    // Robot Field of View  (FOV)
    public static int FOV;

    // Config asset paths
    public static String C_HMAP_PATH;
    public static String C_MAP_PATH;

    /* Singleton constructor */
    private Config(){
        FOV = Reference.DEF_FOV;
        C_HMAP_PATH = Reference.HMAP_PATH;
        C_MAP_PATH = Reference.MAP_PATH;
    }

    /* Singleton getInstance */
    public static Config getInstance(){
        return config;
    }

    // TODO: after this load maps
    public static void setMapPaths(String pathM, String pathH){
        C_HMAP_PATH = pathH;
        C_MAP_PATH = pathM;
    }
}
