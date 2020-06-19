package com.capstone.springboot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaCsvTest {
    public static void main(String[] args){
        int resultCount = 0;
        String[] data = {"abc", "def", "efg"};

        List<List<String>> csvData = new ArrayList<List<String>>();
        BufferedReader br = null;
        BufferedWriter bw = null;
        try{
            String line = "";
            br = Files.newBufferedReader(Paths.get("C:\\Users\\HojinLee\\IdeaProjects\\Capstone\\src\\main\\java\\com\\capstone\\springboot\\python\\keyword.csv"));
            while((line = br.readLine()) != null){
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                tmpList = Arrays.asList(array);
                csvData.add(tmpList);
            }
            br.close();

            bw = Files.newBufferedWriter(Paths.get("C:\\Users\\HojinLee\\IdeaProjects\\Capstone\\src\\main\\java\\com\\capstone\\springboot\\python\\keyword.csv"));
            int count =0;
            for(List<String> newLine : csvData){
                List<String> list = newLine;
                for(String str: list){
                    bw.write(str);
                    bw.write(",");
                    count++;
                }
                bw.newLine();
            }
            for(String dom: data){
                bw.write(String.valueOf(count));
                bw.write(",");
                bw.write(dom);
                bw.newLine();
                count++;
            }
            bw.flush();
            bw.close();
            System.out.println(csvData.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
