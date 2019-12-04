package com.cl.chatroom.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String encodeMsg(Message msg){
        return msg.getUsername()
                +"~"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(msg.getSentTime()) )
                + "~" + msg.getMsg();
    }

    public static String formatDateTime(Date time){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    public static Date parseDateTime(String time){
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        } catch (ParseException e) {
            return null;
        }
    }

    public static void printMsg(Message msg){
        System.out.println("=================================================================================================");
        System.out.println("          "+Utils.formatDateTime(msg.getSentTime()) + "        ");
        System.out.println(msg.getUsername() + ": " + msg.getMsg());
        System.out.println("=================================================================================================");
    }
}
