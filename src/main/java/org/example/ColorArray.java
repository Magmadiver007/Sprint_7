package org.example;

import java.util.ArrayList;
import java.util.List;

public class ColorArray {
    public static List<String>  getListBlack (){
        List<String> colour = new ArrayList<>();
        colour.add("BLACK");
        return colour;
    }
    public static List<String>  getListGrey(){
        List<String> colour = new ArrayList<>();
        colour.add("GREY");
        return colour;
    }
    public static List<String>  getListBlackAndGrey(){
        List<String> colour = new ArrayList<>();
        colour.add("BLACK");
        colour.add("GRAY");
        return colour;
    }
}
