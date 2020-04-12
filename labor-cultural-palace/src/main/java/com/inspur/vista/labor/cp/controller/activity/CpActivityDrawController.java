package com.inspur.vista.labor.cp.controller.activity;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.inspur.vista.labor.cp.controller.BaseController;
import com.inspur.vista.labor.cp.entity.UserAdminInfo;
import com.inspur.vista.labor.cp.entity.activity.CpActivityGoodsEntity;
import com.inspur.vista.labor.cp.service.activity.CpActivityFormService;
import com.inspur.vista.labor.cp.service.activity.CpActivityGoodsService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangzi
 * @version 1.0
 * @description 报名活动相关接口
 * @date 2020/03/19 13/31
 **/
@Api(tags = "文化宫活动抽奖接口")
@RestController
@RequestMapping("api/cp/activity/draw")
public class CpActivityDrawController extends BaseController {

    @Autowired
    private CpActivityFormService cpActivityFormService;
    @Autowired
    private CpActivityGoodsService cpActivityGoodsService;

    /**
     * 添加奖品
     */
    @PostMapping("/addGoods")
    @ApiOperation("添加奖品")
    public ResponseDTO addGoods(@RequestBody CpActivityGoodsEntity goods) {

        UserAdminInfo user = getUser();
        if (user == null) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR,"未获取到当前用户信息");
        }
        goods.setLaborId(user.getLaborId());
        goods.setLaborName(user.getLaborName());
        goods.setMargin(goods.getNum());
        cpActivityGoodsService.insert(goods);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS,null);

    }

    @PostMapping("/delGoods")
    @ApiOperation("删除奖品")
    public ResponseDTO delGoods(Integer id) {

        cpActivityGoodsService.deleteById(id);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS,null);
    }

    @GetMapping("/goodsList")
    @ApiOperation("奖品列表")
    public ResponseDTO goodsList(Integer activityId) {

        EntityWrapper<CpActivityGoodsEntity> goodsWrapper = new EntityWrapper<>();
        goodsWrapper.eq("activity_id", activityId);
        goodsWrapper.orderBy("create_time", false);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS,cpActivityGoodsService.selectList(goodsWrapper));
    }





















}
