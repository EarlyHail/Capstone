package com.capstone.springboot.web.dto;

import com.capstone.springboot.domain.comments.Comments;
import com.capstone.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

@Getter
@NoArgsConstructor
public class CommentsSaveRequestDto {
    private static PythonInterpreter interpreter;
    private String content;
    private String author;
    private Long pid;
    private Long tag;
    private Long report;
    @Builder
    public CommentsSaveRequestDto(String content, String author, Long pid) {
        this.content = content;
        this.author = author;
        this.pid = pid;
    }

    public Comments toEntity(){
        interpreter = new PythonInterpreter();
        String comment = this.content;
        PyString pyComment = Py.newStringOrUnicode(comment);
        interpreter.execfile("src/main/java/com/capstone/springboot/test.py");
        PyFunction getTagFunc = (PyFunction)interpreter.get("tagging", PyFunction.class);
        PyInteger pyTag = (PyInteger)(getTagFunc.__call__(pyComment));
        this.tag = (long)pyTag.getValue();
        if(tag == 1)
            report = tag;
        return Comments.builder()
                .content(content)
                .author(author)
                .pid(pid)
                .tag(tag)
                .report(report)
                .build();
    }
}
