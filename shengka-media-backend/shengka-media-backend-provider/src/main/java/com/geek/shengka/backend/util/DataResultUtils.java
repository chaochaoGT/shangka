package com.geek.shengka.backend.util;


import com.geek.shengka.backend.constant.CommonConstant;

/**
 * @author qubianzhong
 * @date 2019/6/24 14:12
 */
public class DataResultUtils {
    private DataResultUtils() {
    }

    public static DataResult<Boolean> add(Boolean added) {
        if (added) {
            return DataResult.ok(Boolean.TRUE);
        } else {
            return DataResult.fail(CommonConstant.SYS_ERROR, CommonConstant.ADD_FAIL);
        }
    }

    public static DataResult<Boolean> update(Boolean added) {
        if (added) {
            return DataResult.ok(Boolean.TRUE);
        } else {
            return DataResult.fail(CommonConstant.SYS_ERROR, CommonConstant.UPDATE_FAIL);
        }
    }

    public static DataResult<Boolean> update(Boolean added, String[] redisKey, RedisService redisService) {
        if (added) {
            if (redisKey != null) {
                for (String key : redisKey) {
                    redisService.deleteKey(key);
                }
            }
            return DataResult.ok(Boolean.TRUE);
        } else {
            return DataResult.fail(CommonConstant.SYS_ERROR, CommonConstant.UPDATE_FAIL);
        }
    }

    public static DataResult<Boolean> delete(Boolean deleted) {
        if (deleted) {
            return DataResult.ok(Boolean.TRUE);
        } else {
            return DataResult.fail(CommonConstant.SYS_ERROR, CommonConstant.DELETE_FAIL);
        }
    }

    public static DataResult<Boolean> delete(Boolean deleted, String[] redisKey, RedisService redisService) {
        if (deleted) {
            if (redisKey != null) {
                for (String key : redisKey) {
                    redisService.deleteKey(key);
                }
            }
            return DataResult.ok(Boolean.TRUE);
        } else {
            return DataResult.fail(CommonConstant.SYS_ERROR, CommonConstant.DELETE_FAIL);
        }
    }
}
