package com.rhee.messagepush.support.dao;

import com.rhee.messagepush.support.domain.MessageTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author rhee
 * @date 2022/7/17 4:54 PM
 */
@SpringBootTest
public class MessageTemplateDaoTest {

    @Autowired
    private MessageTemplateDao messageTemplateDao;

    @Test
    public void testQuery(){
        Iterable<MessageTemplate> all = messageTemplateDao.findAll();
        System.out.println(all);
    }


}
