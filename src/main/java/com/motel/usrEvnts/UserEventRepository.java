package com.motel.usrEvnts;

import com.motel.usrEvnts.entity.UserEvents;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface UserEventRepository extends CrudRepository<UserEvents, Long> {

    @Query("select u from UserEvents u where u.timestamp <= :timestamp")
    public List<UserEvents> findByTimestamp(@Param("timestamp") Timestamp timestamp);

    public List<UserEvents> findByUserId(Long userId);
}
