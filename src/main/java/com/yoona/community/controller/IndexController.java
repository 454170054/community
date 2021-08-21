package com.yoona.community.controller;

import com.yoona.community.dto.PaginationDTO;
import com.yoona.community.dto.QuestionDTO;
import com.yoona.community.mapper.QuestionMapper;
import com.yoona.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "9") Integer size,
                        @RequestParam(name = "search", required = false) String search){
        PaginationDTO<QuestionDTO> pagination = questionService.list(search, page, size);
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        return "index";
    }
}
