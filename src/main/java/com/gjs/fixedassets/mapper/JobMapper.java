package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Fixedcard;
import com.gjs.fixedassets.entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.List;

@Mapper
public interface JobMapper {
    Job selectByPrimaryKey(Integer jobId);

    List<Job> selectAllJobByCompanyId(Integer companyId);

    String selectJName(Integer jobId);

    int insert(Job job);

    void update(Job job);

    List<Job> selectJobByCompanyId(@Param("companyId") Integer companyId, @Param("page") Integer page, @Param("limit") Integer limit, @Param("jobName") String jobName);

    List<Job> selectJobCount(@Param("companyId") Integer companyId, @Param("jobName") String jobName);

    void deleteJob(Integer jobId);

    void updateJob(Job job);
}