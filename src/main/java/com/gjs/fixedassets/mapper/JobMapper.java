package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.List;

@Mapper
public interface JobMapper {
    Job selectByPrimaryKey(Integer jobId);

    List<Job> selectAllJobByCompanyId(Integer companyId);

    String selectJName(Integer jobId);

    int insert(Job job);

    void update(Job job);
}