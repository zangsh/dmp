package com.mw.dmp;

import org.junit.Test;
import java.util.Random;

public class RandomTest {

    @Test
    public void test(){
//        System.out.println((int)(1 + Math.random()*50));
        for (int i = 0 ;i< 100;i++){
            System.out.println((int)(40*60 + 600 + Math.random()*20*60));
        }
    }
}
