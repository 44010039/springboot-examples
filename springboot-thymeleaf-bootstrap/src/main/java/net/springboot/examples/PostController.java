package net.springboot.examples;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = "/posts/page")
    public String getPage(Pageable pageable, Model model) {
		model.addAttribute("posts", postRepository.findAll(pageable));
        return "posts";
	}
}
