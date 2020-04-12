package com.inspur.vista.labor.cp.controller.activity;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.activity.CPActivityPollederEntity;
import com.inspur.vista.labor.cp.entity.activity.CPActivityVoteRecordsEntity;
import com.inspur.vista.labor.cp.entity.activity.CPCultureCouponEntity;
import com.inspur.vista.labor.cp.service.activity.CPActivityPollederService;
import com.inspur.vista.labor.cp.service.activity.CPActivityVoteRecordsService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @Title: CPActivityPollederController
 * @Description: 文化宫投票对象相关
 * @Author: ljs
 * @CreateDate: 2020/3/31 10:27
 * @Version: 1.0
 */
@Api(value = "文化宫活动投票对象控制类", tags = {"文化宫活动投票对象接口"})
@RestController
@RequestMapping("/api/cp/activity/polleder")
public class CPActivityPollederController {

    private static final Logger logger = LoggerFactory.getLogger(CPActivityPollederController.class);


    @Autowired
    private CPActivityPollederService activityPollederService;

    @Autowired
    private CPActivityVoteRecordsService activityVoteRecordsService;


    /**
     * 保存投票对象
     */
    @PostMapping(value = "/savePolleder")
    @ApiOperation(value = "保存投票对象", response = ResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="activityId", value = "活动id", paramType = "path", type = "int", required = true),
            @ApiImplicitParam(name="name", value = "活动对象名", paramType = "path", type = "string", required = true),
            @ApiImplicitParam(name="no", value = "活动对象编号", paramType = "path", type = "string", required = true),
            @ApiImplicitParam(name="headImg", value = "活动对象头像", paramType = "path", type = "string", required = true),
            @ApiImplicitParam(name="brief", value = "活动对象简介", paramType = "path", type = "string", required = true)
    })
    public Object savePolleder(@ApiIgnore @RequestBody CPActivityPollederEntity activityPollederEntity) {

        ResponseDTO responseDTO;
        try {
            if(activityPollederEntity != null){
                activityPollederEntity.setDeleted(0);
            }
            responseDTO = WebUtils.createSuccessResponse(activityPollederService.insert(activityPollederEntity) ? 1 : 0);
        } catch (Exception e) {
            logger.error("保存投票对象失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "保存投票对象失败");
        }
        return responseDTO;
    }

    /**
     * 删除投票对象
     */
    @PostMapping(value = "/deletePolleder")
    @ApiOperation(value = "删除投票对象", response = ResponseDTO.class)
    public Object deletePolleder(@ApiParam(name = "ids", value = "投票对象ID列表 ",required = true) @RequestBody Integer[] ids) {

        ResponseDTO responseDTO;
        try {
            responseDTO = WebUtils.createSuccessResponse(activityPollederService.deleteBatchIds(Arrays.asList(ids)) ? 1 : 0);
        } catch (Exception e) {
            logger.error("删除投票对象失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "删除投票对象失败");
        }
        return responseDTO;
    }


    /**
     * 修改投票对象
     */
    @PostMapping(value = "/updatePolleder")
    @ApiOperation(value = "修改投票对象", response = ResponseDTO.class)
    @ApiImplicitParams({
            /*@ApiImplicitParam(name="activityId", value = "活动id", paramType = "path", type = "int"),*/
            @ApiImplicitParam(name="id", value = "对象id", paramType = "path", type = "int", required = true),
            @ApiImplicitParam(name="name", value = "对象名", paramType = "path", type = "string", required = true),
            @ApiImplicitParam(name="no", value = "对象编号", paramType = "path", type = "int", required = true),
            @ApiImplicitParam(name="headImg", value = "对象头像", paramType = "path", type = "string", required = true),
            @ApiImplicitParam(name="brief", value = "对象简介", paramType = "path", type = "string", required = true)
    })
    public Object updatePolleder(@ApiIgnore @RequestBody CPActivityPollederEntity activityPollederEntity) {
        ResponseDTO responseDTO;
        try {
            responseDTO = WebUtils.createSuccessResponse(activityPollederService.updatePolleder(activityPollederEntity) ? 1 : 0);
        } catch (Exception e) {
            logger.error("修改投票对象失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "修改投票对象失败");
        }
        return responseDTO;
    }


    /**
     * 查询投票对象
     */
    @GetMapping(value = "/getPollederList")
    @ApiOperation(value = "查询投票对象", notes = "所有该活动下的投票对象", response = ResponseDTO.class)
    public Object getPollederList(@ApiParam(name = "activityId", value = "活动ID ",required = true) Integer activityId,
                                  @ApiParam(name = "noOrName", value = "投票对象的编号或者是名字 ",required = false) String noOrName,
                                  @ApiParam(name = "size", value = "每页多少条 默认10",required = false) Integer size,
                                  @ApiParam(name = "current", value = "第几页 默认1",required = false) Integer current) {

        ResponseDTO responseDTO;
        try {
            Page<CPActivityPollederEntity> page = new Page<>();
            if(current != null){
                page.setCurrent(current);
            }else{
                page.setCurrent(1);
            }
            if(size != null){
                page.setSize(size);
            }else{
                page.setSize(10);
            }

            EntityWrapper<CPActivityPollederEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("activity_id",activityId);
            if(noOrName != null && !noOrName.isEmpty()){
                wrapper.like("no",noOrName);
                wrapper.or();
                wrapper.like("name",noOrName);
            }
            responseDTO = WebUtils.createSuccessResponse(activityPollederService.selectPage(page,wrapper));
        } catch (Exception e) {
            logger.error("查询投票对象失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询投票对象失败");
        }
        return responseDTO;
    }




    /**
     * 查询投票对象的投票人列表
     */
    @GetMapping(value = "/getVoter")
    @ApiOperation(value = "查询投票对象的投票人列表", notes = "所有该活动下的投票对象的所有投票人", response = ResponseDTO.class)
    public Object getVoter(@ApiParam(name = "activityId", value = "活动ID ",required = true) Integer activityId,
                           @ApiParam(name = "pollederId", value = "投票对象的ID ",required = true) Integer pollederId,
                           @ApiParam(name = "size", value = "每页多少条 默认10",required = false) Integer size,
                           @ApiParam(name = "current", value = "第几页 默认1",required = false) Integer current) {
        ResponseDTO responseDTO;
        try {
            Page<CPActivityVoteRecordsEntity> page = new Page<>();
            if(current != null){
                page.setCurrent(current);
            }else{
                page.setCurrent(1);
            }
            if(size != null){
                page.setSize(size);
            }else{
                page.setSize(10);
            }

            EntityWrapper<CPActivityVoteRecordsEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("activity_id",activityId);
            wrapper.eq("polleder_id",pollederId);

            responseDTO = WebUtils.createSuccessResponse(activityVoteRecordsService.selectPage(page,wrapper));
        } catch (Exception e) {
            logger.error("查询投票对象失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询投票对象失败");
        }
        return responseDTO;
    }






}
