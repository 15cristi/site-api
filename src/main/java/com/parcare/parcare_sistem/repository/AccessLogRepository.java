package com.parcare.parcare_sistem.repository;

import com.parcare.parcare_sistem.model.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    List<AccessLog> findAllByOrderByAccessTimeDesc();
}


