package com.rhee.messagepush.support.dao;

import com.rhee.messagepush.support.domain.MessageTemplate;
import org.springframework.data.repository.CrudRepository;

/**
 * @author rhee
 * @date 2022/7/11 11:01 PM
 */
public interface MessageTemplateDao extends CrudRepository<MessageTemplate, Long> {
}
