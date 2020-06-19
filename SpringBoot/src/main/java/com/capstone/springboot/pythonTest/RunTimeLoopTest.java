package com.capstone.springboot;

import java.io.*;

public class RunTimeLoopTest {
    public static void main(String[] args){
        Long result=21412l;
        try{
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("python C:/Users/HojinLee/IdeaProjects/Capstone/src/main/java/com/capstone/springboot/python/loopTest.py");
            BufferedWriter stdOutput = new BufferedWriter(new
                    OutputStreamWriter(p.getOutputStream()));
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));
            String s = null;
            while((s = stdInput.readLine()) != null) {
                System.out.print("result: ");
                System.out.println(s);
                System.out.print("user input: ");
                stdOutput.write("testing....");
                stdOutput.flush();
                System.out.print("flushed: ");
            }
            while ((s = stdError.readLine()) != null);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
