package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkspScCited;
import com.geek.shengka.backend.mapper.SkspScCitedDAO;
import com.geek.shengka.backend.params.req.SkspScCitedAddReqParam;
import com.geek.shengka.backend.params.req.SkspScCitedDTO;
import com.geek.shengka.backend.params.req.SkspScCitedQueryDTO;
import com.geek.shengka.backend.service.SkspScCitedService;
import com.geek.shengka.backend.util.BeanMapperUtils;
import com.geek.shengka.backend.util.PageDataUtils;
import com.geek.shengka.backend.util.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 分包业务处理
 *
 * @author: yunfei
 * @create: 2019-08-26 10:39
 **/
@Service
public class SkspScCitedServiceImpl implements SkspScCitedService {

    @Autowired
    SkspScCitedDAO llspScCitedDAO;


    @Override
    public int add(SkspScCitedDTO skspScCitedDTO) {
        SkspScCited llspScCited = new SkspScCited();
        BeanUtils.copyProperties(skspScCitedDTO, llspScCited);
        return llspScCitedDAO.insertSelective(llspScCited);
    }

    @Override
    public int update(SkspScCitedDTO skspScCitedDTO) {
        SkspScCited llspScCited = new SkspScCited();
        BeanUtils.copyProperties(skspScCitedDTO, llspScCited);
        return llspScCitedDAO.updateByPrimaryKeySelective(llspScCited);
    }

    @Override
    public SkspScCitedDTO get(Long id) {
        SkspScCited llspScCited = llspScCitedDAO.selectByPrimaryKey(id);
        return BeanMapperUtils.map(llspScCited, SkspScCitedDTO.class);
    }

    @Override
    public PageVO<SkspScCited> getList(SkspScCitedQueryDTO param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkspScCited> list = llspScCitedDAO.getList(param);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 批量新增
     *
     * @param addReqParamList
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 12:37 2019/8/29
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean uploadData(List<SkspScCitedAddReqParam> addReqParamList) {
        llspScCitedDAO.truncate();
        List<SkspScCited> scCitedList = BeanMapperUtils.mapList(addReqParamList, SkspScCited.class);
        return llspScCitedDAO.insertBatch(scCitedList);
    }


}
