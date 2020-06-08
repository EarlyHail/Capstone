package com.capstone.springboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RuntimeTest {
    public static void main(String[] args){
        Long result=21412l;
        try{
            Runtime r = Runtime.getRuntime();
            String comment = "시발";
            long beforeTime = System.currentTimeMillis();

            Process p = r.exec("python C:/Users/user/IdeaProjects/Capstone/src/main/java/com/capstone/springboot/python/final.py 씨발");
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            System.out.println("loop starting....");
            String s = null;
            while ((s = stdError.readLine()) != null);
            while((s = stdInput.readLine()) != null) {
                System.out.println(s);
                if(s.length() <= 1)
                    result = (long)Integer.parseInt(s);
            }
            System.out.println("loop ending......");
            long afterTime = System.currentTimeMillis();
            long secDiffTime = (afterTime - beforeTime)/1000;
            System.out.println("time(m) : "+secDiffTime);
            System.out.println(result);
            System.out.println(result+1);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
