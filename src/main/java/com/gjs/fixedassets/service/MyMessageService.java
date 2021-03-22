package com.gjs.fixedassets.service;


import com.gjs.fixedassets.entity.Mymessage;

import java.util.List;

public interface MyMessageService {
    List<Mymessage> selectMessageLimit(Integer userId);
}
