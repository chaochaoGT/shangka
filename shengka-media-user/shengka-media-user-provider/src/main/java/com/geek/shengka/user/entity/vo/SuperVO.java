package com.geek.shengka.user.entity.vo;

/**
 * @Filename: SuperVO
 * @Description: 可以装载任意实体类之任意属性，可以用于装载多表连接查询返回的复合数据
 * @Version: 1.0
 * @Author:
 * @Email:
 * @History:<br> aaa
 * <li>Author: chao.wang</li>
 * <li>Date: 2018/11/19</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
import java.io.Serializable;
import java.util.TreeMap;

public class SuperVO extends TreeMap<String,Object> implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 设置实体类属性
     *
     * @param name
     *            属性名
     * @param value
     *            属性值
     * @return
     */
    public SuperVO setProperty(String name, Object value) {
        this.put(name, value);
        return this;
    }
}
