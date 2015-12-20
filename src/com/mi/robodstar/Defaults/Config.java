package com.mi.robodstar.Defaults;

public class Config{
    // Robot Field of View  (FOV)
    private static int FOV;

    // Config asset paths
    private static String C_HMAP_PATH;
    private static String C_MAP_PATH;


    public static void setDefaults(){
        FOV = Reference.DEF_FOV;
        C_HMAP_PATH = Reference.HMAP1_PATH;
        C_MAP_PATH = Reference.MAP1_PATH;
    }

    // TODO: after this reload maps
    public static void setMapPaths(String pathM, String pathH){
        C_HMAP_PATH = pathH;
        C_MAP_PATH = pathM;
    }

    public static String getHeuriticMapPath(int i){
        switch (i){
            case 1:
                return Reference.HMAP1_PATH;
            case 2:
                return Reference.HMAP2_PATH;
            case 3:
                return Reference.HMAP3_PATH;
            case 4:
                return Reference.HMAP4_PATH;
        }
        return C_HMAP_PATH;
    }

    public static String getMapPath(int i){
        switch (i){
            case 1:
                return Reference.MAP1_PATH;
            case 2:
                return Reference.MAP2_PATH;
        }
        return C_MAP_PATH;
    }

    public static int getFOV(){
        return FOV;
    }

    public static int getFOVSize(){
        return FOV * 2 + 1;
    }
}
