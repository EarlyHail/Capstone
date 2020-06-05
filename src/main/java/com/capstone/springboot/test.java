package com.capstone.springboot;

import org.python.core.*;
import org.python.util.PythonInterpreter;

public class test {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        String comment = "댓글";
        PyString pyComment1 = Py.newStringOrUnicode(comment);
        interpreter.execfile("src/main/java/com/capstone/springboot/test.py");
        PyFunction getTagFunc = (PyFunction)interpreter.get("tagging", PyFunction.class);
        PyInteger pyTag = (PyInteger)(getTagFunc.__call__(pyComment1));
        int tag = (int)pyTag.getValue();
        System.out.println("Result : "+tag);
    }
}
