package com.capstone.springboot;

import org.python.core.*;
import org.python.util.PythonInterpreter;

public class test {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        String comment = "안녕ㅎㅋㅌ촣ㅁ히;ㅏㅓㄴㅇㅅ";
        PyString pyComment = Py.newStringOrUnicode(comment);
        interpreter.execfile("src/main/java/com/capstone/springboot/test.py");
        PyFunction getTagFunc = (PyFunction)interpreter.get("tagging", PyFunction.class);
        PyInteger pyTag = (PyInteger)(getTagFunc.__call__(pyComment));
        int tag = (int)pyTag.getValue();
        System.out.println("Result : "+tag);
    }
}
