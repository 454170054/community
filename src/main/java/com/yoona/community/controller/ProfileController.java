package com.yoona.community.controller;

import com.yoona.community.dto.NotificationDTO;
import com.yoona.community.dto.PaginationDTO;
import com.yoona.community.dto.QuestionDTO;
import com.yoona.community.mapper.NotificationMapper;
import com.yoona.community.model.User;
import com.yoona.community.service.NotificationService;
import com.yoona.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PaginationDTO<QuestionDTO> pagination = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination", pagination);
        } else if ("replies".equals(action)) {
            PaginationDTO<NotificationDTO> paginationDTO = notificationService.list(user.getId(), page, size);
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            model.addAttribute("pagination", paginationDTO);
        }
        return "profile";
    }
}
