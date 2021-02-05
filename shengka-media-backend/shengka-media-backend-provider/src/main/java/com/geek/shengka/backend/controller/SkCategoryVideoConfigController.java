package com.geek.shengka.backend.controller;

import com.geek.shengka.backend.entity.SkPublishVideo;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.mapper.SkPublishVideoDAO;
import com.geek.shengka.backend.params.req.SkCategoryVideoConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkCategoryVideoConfigListReqParam;
import com.geek.shengka.backend.params.req.SkCategoryVideoConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkCategoryVideoConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkCategoryVideoConfigListResParam;
import com.geek.shengka.backend.service.SkCategoryVideoConfigService;
import com.geek.shengka.backend.util.DataResult;
import com.geek.shengka.backend.util.DataResultUtils;
import com.geek.shengka.backend.util.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author qubianzhong
 * @date 2019/8/22 13:42
 */
@RestController
@Api(tags = "频道顶部视频配置")
@RequestMapping(value = "/categoryVideoConfig")
@Validated
public class SkCategoryVideoConfigController extends BaseController {

    @Autowired
    private SkCategoryVideoConfigService skCategoryVideoConfigService;
    @Autowired
    private SkPublishVideoDAO skPublishVideoDAO;

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表")
    public DataResult<PageVO<SkCategoryVideoConfigListResParam>> list(SkCategoryVideoConfigListReqParam param) {
        return DataResult.ok(skCategoryVideoConfigService.list(param));
    }

    @GetMapping(value = "/info/{id}")
    @ApiOperation(value = "详情")
    public DataResult<SkCategoryVideoConfigInfoResParam> info(@PathVariable(value = "id") Long id) {
        return DataResult.ok(skCategoryVideoConfigService.info(id));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public DataResult<Boolean> add(@RequestBody SkCategoryVideoConfigAddReqParam addReqParam) throws BaseException {
        addReqParam.setCreateBy(getUserId());
        checkVideoId(addReqParam.getVideoId());
        return DataResultUtils.add(skCategoryVideoConfigService.add(addReqParam));
    }

    /**
     * 校验视频ID是否有效
     *
     * @param videoId
     * @return void
     * @author qubianzhong
     * @Date 14:17 2019/8/22
     */
    private void checkVideoId(String videoId) throws BaseException {
        if (StringUtils.isEmpty(videoId)) {
            throw new BaseException("视频ID不能为空！");
        }
        SkPublishVideo video = skPublishVideoDAO.selectByPrimaryKey(videoId);
        if (video == null) {
            throw new BaseException("视频ID:" + videoId + "不存在！");
        }
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public DataResult<Boolean> add(@RequestBody SkCategoryVideoConfigUpdateReqParam updateReqParam) throws BaseException {
        if (updateReqParam.getId() == null) {
            throw new BaseException("ID不能为空！");
        }
        updateReqParam.setUpdateBy(getUserId());
        checkVideoId(updateReqParam.getVideoId());
        return DataResultUtils.update(skCategoryVideoConfigService.update(updateReqParam));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除")
    public DataResult<Boolean> delete(@PathVariable(value = "id") Long id) {
        return DataResultUtils.update(skCategoryVideoConfigService.delete(id));
    }

}
