package com.gjs.fixedassets.service;

import com.gjs.fixedassets.entity.Job;

import java.util.List;

public interface JobService {

    List<Job> selectAllJobByCompanyId(Integer companyId);

    String selectJName(Integer jobId);
}
