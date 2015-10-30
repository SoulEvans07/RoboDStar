package com.mi.robodstar.Utility;

import com.mi.robodstar.Defaults.Reference;

import java.io.PrintStream;

public class LogHelper {
    private static PrintStream printStream = System.out;
    private static int rec = 0;
    private static int startParagraph = 0;
    private static int com = 0;
    private static int paragraph = startParagraph;

    private static void log(String log){
        if(rec == 0)
            printStream.print(log);
    }

    public static void clear(){
        if(rec == 0) {
            paragraph = startParagraph;
            rec = 0;
        }
    }

    public static void lift(){
        lift(1);
    }

    public static void lift(int num){
        paragraph += num;
    }

    public static void close(){
        close(1);
    }

    public static void close(int num){
        paragraph -= num;
        if(paragraph < 0)
            error("paragraph depth below zero: " + paragraph);
    }

    public static void pauseRec(){
        rec++;
    }

    public static void ignoreComments(){
        com++;
    }

    public static void unPauseRec(){
        rec--;
        if(rec < 0)
            error("record depth below zero: " + rec);
    }

    public static void showComments(){
        com--;
        if(com < 0)
            error("comment depth below zero: " + com);
    }

    private static String pareagrapher(){
        String tab = "";
        for(int i = 1; i < paragraph; i++){
            tab = tab.concat(LogHelper.tabber());
        }
        return tab;
    }

    public static void breakLine(int breaks){
        for(int i = 0; i < breaks; i++)
            log("\n");
    }

    public static void inline(Object line){
        String raw = String.valueOf(line);
        raw = raw.replace("\n", " ");
        String fin = pareagrapher();
        fin = fin.concat(raw) + "\n";
        log(fin);
    }

    public static void line(Object line){
        String raw = String.valueOf(line);
        raw = raw.replace("\n", " ");
        log(raw + "\n");
    }

    public static void comment(Object comment){
        String raw = String.valueOf(comment);
        raw = raw.replace("\n", " ");
        if(com == 0)
            log("COMMENT:\t" + raw + "\n");
    }

    public static void error(Object object){
        String raw = String.valueOf(object);
        String fin = "ERROR:\t" + pareagrapher();
        fin = fin.concat("\"" + raw + "\"\n");
        log(fin);
    }

    public static String tabber(int tabSize){
        String tab = "";
        for(int i = 0; i < tabSize; i++){
            tab = tab.concat(" ");
        }

        return tab;
    }

    public static String tabber(){
        return tabber(Reference.TAB_SIZE);
    }
}

