/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.chujko.controller.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Artyom
 */
public class TagUtil {
    
    public static final String REGEX = "([#]{1})([a-zA-Zа-яА-Я0-9_-])+";
    
    public static String getFirstTag(String text){
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(text);
        while(m.find()){
            return m.group().replace("#", "");
        }
        return null;
    }
    
//    public static void main(String[] args){
//        String str = "Это пробный #текст, чтобы вот так fgf vot #su4ka idи ты #na_хуй сука!";
//        String tag;
//        tag = TagUtil.getFirstTag(str);
//        System.err.println(tag);
//    }
}
