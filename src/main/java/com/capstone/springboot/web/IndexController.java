package com.capstone.springboot.web;

import com.capstone.springboot.service.CommentsService;
import com.capstone.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final CommentsService commentsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/show/{id}")
    public String postsShow(@PathVariable Long id, Model model) {
        model.addAttribute("post", postsService.findById(id));
        return "posts-show";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";

    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("post", postsService.findById(id));
        return "posts-update";
    }

    @GetMapping("/comments/save/{id}")
    public String commentsSave(@PathVariable Long id, Model model){
        model.addAttribute("post", postsService.findById(id));
        return "comments-save";
    }

    @GetMapping("/comments/show/{id}")
    public String commentsShow(@PathVariable Long id, Model model){
        model.addAttribute("post", postsService.findById(id));
        model.addAttribute("comment", commentsService.findByPid(id));
        return "comments-show";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin-index";
    }

    @GetMapping("/admin/comments")
    public String adminComments(Model model){
        model.addAttribute("comment", commentsService.findAllTag());
        return "admin-comments";
    }
}
