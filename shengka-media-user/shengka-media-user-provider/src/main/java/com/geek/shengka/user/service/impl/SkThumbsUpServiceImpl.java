package com.geek.shengka.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geek.shengka.user.entity.SkThumbsUp;
import com.geek.shengka.user.mapper.SkThumbsUpDAO;

@Service
public class SkThumbsUpServiceImpl {

    @Autowired
    private SkThumbsUpDAO skThumbsUpDAO;
    
    public SkThumbsUp findById(Long id) {
    	return skThumbsUpDAO.selectByPrimaryKey(id);
    }
}
