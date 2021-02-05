package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.mapper.HelloDAO;
import com.geek.shengka.backend.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qubianzhong
 * @date 2019/7/31 16:19
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private HelloDAO helloDao;

    @Override
    public String hello() {
        return helloDao.hello();
    }
}
