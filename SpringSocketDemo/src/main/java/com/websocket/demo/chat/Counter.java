package com.example.Chatdemo.chat;

import org.springframework.stereotype.Service;

@Service//for use in every class if you instantiate counter in that class
public class Counter {
    private int count = 0;
    public int countup(){
        return ++count;
    }
    public int countdown(){
       return --count;
    }
//    public int getCount(){
//        return count;
//    }

}
