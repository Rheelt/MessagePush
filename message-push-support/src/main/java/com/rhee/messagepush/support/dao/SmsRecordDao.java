package com.rhee.messagepush.support.dao;

import com.rhee.messagepush.support.domain.SmsRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rhee
 * @date 2022/7/11 11:01 PM
 */
@Repository
public interface SmsRecordDao extends JpaRepository<SmsRecord, Long> {
}
