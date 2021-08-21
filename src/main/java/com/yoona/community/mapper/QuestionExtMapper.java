package com.yoona.community.mapper;

import com.yoona.community.dto.QueryDTO;
import com.yoona.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question question);

    int incCommentCount(Question question);

    List<Question> selectRelated(Question question);

    Integer countBySearch(QueryDTO queryDTO);

    List<Question> selectBySearch(QueryDTO queryDTO);
}
