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
            Process p = r.exec("python C:/Users/HojinLee/IdeaProjects/Capstone/src/main/java/com/capstone/springboot/python/final.py 씨발");
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));
            String s = null;
            while ((s = stdError.readLine()) != null);
            while((s = stdInput.readLine()) != null) {
                System.out.println(s);
                if(s.length() <= 1)
                    result = (long)Integer.parseInt(s);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
