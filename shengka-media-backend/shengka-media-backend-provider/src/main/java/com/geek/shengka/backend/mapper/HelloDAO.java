package com.geek.shengka.backend.mapper;

import org.springframework.stereotype.Repository;

/**
 * @author qubianzhong
 * @date 2019/7/31 16:24
 */
@Repository
public interface HelloDAO {

    /**
     * hello world
     *
     * @param
     * @return java.lang.String
     * @author qubianzhong
     * @Date 16:26 2019/7/31
     */
    String hello();
}
