package com.yoona.community.controller;

import com.yoona.community.dto.CommentDTO;
import com.yoona.community.mapper.COMMENTMapper;
import com.yoona.community.model.COMMENT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CommentController {

    @Autowired
    private COMMENTMapper commentMapper;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO){
        COMMENT record = new COMMENT();
        commentMapper.insert(record);
    }
}
