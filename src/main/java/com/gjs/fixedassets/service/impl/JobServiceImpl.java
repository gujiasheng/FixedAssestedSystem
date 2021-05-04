package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.Fixedcard;
import com.gjs.fixedassets.entity.Job;
import com.gjs.fixedassets.mapper.JobMapper;
import com.gjs.fixedassets.service.JobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Resource
    private JobMapper jobMapper;

    @Override
    public List<Job> selectAllJobByCompanyId(Integer companyId) {
        return jobMapper.selectAllJobByCompanyId(companyId);
    }

    @Override
    public String selectJName(Integer jobId) {
        return jobMapper.selectJName(jobId);
    }

    @Override
    public List<Job> selectJobByCompanyId(Integer companyId, Integer page, Integer limit, String jobName) {
        int startNum = (page - 1) * limit;
        return jobMapper.selectJobByCompanyId(companyId, startNum, limit, jobName);
    }

    @Override
    public List<Job> selectJobCount(Integer companyId, String jobName) {
        return jobMapper.selectJobCount(companyId, jobName);
    }

    @Override
    public void addJob(Job job) {
        jobMapper.insert(job);
    }
}
