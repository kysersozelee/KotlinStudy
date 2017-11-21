package com.git.kysersozelee.part1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class test1 {
    public static void main(String[] args) {


        Thread thread = new Thread(() -> log.error("Hi"));


        thread.run();


    }
}
