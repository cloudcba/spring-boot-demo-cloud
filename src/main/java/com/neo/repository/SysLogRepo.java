package com.neo.repository;

import com.neo.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysLogRepo extends JpaRepository<SysLog,Long> {
}