package com.zeta.practice;

import java.util.List;
import java.util.Vector;

public class Temp {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new Vector<>();

        Thread t = new Thread(()->{
            list.add("D");
        });
        t.start();


        list.add("B");
        list.add("A");
        list.add("C");

//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).equals("B")){
//                list.remove(list.get(i));
//            }
//        }

        t.join();
        for (String s:list){
            if (s.equals("B")){
                list.remove(s);
            }
        }
        System.out.println(list);


    }
}
