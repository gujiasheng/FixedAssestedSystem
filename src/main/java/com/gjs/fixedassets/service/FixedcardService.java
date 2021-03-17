package com.gjs.fixedassets.service;

import com.gjs.fixedassets.entity.Fixedcard;
import org.springframework.stereotype.Service;


public interface FixedcardService {

    //添加固资卡片
    void insertFixedAsssetCard(Fixedcard fixedcard);
}
