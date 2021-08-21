package com.yoona.community.service;

import com.yoona.community.Exception.CustomizeErrorCode;
import com.yoona.community.Exception.CustomizeException;
import com.yoona.community.dto.NotificationDTO;
import com.yoona.community.dto.PaginationDTO;
import com.yoona.community.enums.NotificationStatusEnum;
import com.yoona.community.enums.NotificationTypeEnum;
import com.yoona.community.mapper.NotificationMapper;
import com.yoona.community.mapper.UserMapper;
import com.yoona.community.model.Notification;
import com.yoona.community.model.NotificationExample;
import com.yoona.community.model.User;
import com.yoona.community.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO<NotificationDTO> list(Long id, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(id);
        Integer totalCount = (int)notificationMapper.countByExample(new NotificationExample());
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size * (page - 1);
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(id);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        if(notifications.size() == 0){
            return paginationDTO;
        }
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification: notifications){
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId).andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification == null){
            throw  new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(!notification.getReceiver().equals(user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
