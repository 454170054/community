package com.yoona.community.mapper;

import com.yoona.community.model.Question;

public interface QuestionExtMapper {
    int incView(Question question);
    int incCommentCount(Question question);
}
