package com.yoona.community.mapper;

import com.yoona.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}