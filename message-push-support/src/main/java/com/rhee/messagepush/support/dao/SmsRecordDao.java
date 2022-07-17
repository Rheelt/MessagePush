package com.rhee.messagepush.support.dao;

import com.rhee.messagepush.support.domain.SmsRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rhee
 * @date 2022/7/11 11:01 PM
 */
public interface SmsRecordDao extends JpaRepository<SmsRecord, Long> {
}
