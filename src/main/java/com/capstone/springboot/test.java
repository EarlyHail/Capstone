package com.capstone.springboot;

import org.python.util.PythonInterpreter;

public class test {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src/main/java/com/capstone/springboot/test.py");
        interpreter.exec("print(sum(5,10))");
    }
}
