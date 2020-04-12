package com.inspur.vista.labor.cp.controller.app.activity;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.AppUserInfo;
import com.inspur.vista.labor.cp.entity.activity.CPActivityPollederEntity;
import com.inspur.vista.labor.cp.entity.activity.CPActivityVoteRecordsEntity;
import com.inspur.vista.labor.cp.exception.RRException;
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

import java.util.Arrays;

/**
 * @Title: CPActivityVoteRecordsController
 * @Description: 文化宫APP投票相关
 * @Author: ljs
 * @CreateDate: 2020-03-31 14:31:09
 * @Version: 1.0
 */
@Api(value = "文化宫APP投票相关", tags = {"文化宫APP投票相关"})
@RestController
@RequestMapping("/api/app/cp/activity/vote")
public class CPActivityVoteRecordsController {

    private static final Logger logger = LoggerFactory.getLogger(CPActivityVoteRecordsController.class);

    @Autowired
    private CPActivityVoteRecordsService activityVoteRecordsService;



    /**
     * 添加一次投票活动投票
     */
    @PostMapping(value = "/addVotes")
    @ApiOperation(value = "添加一次投票活动投票", response = ResponseDTO.class)
    public Object addVotes(@ApiIgnore @RequestAttribute("userInfo") AppUserInfo appUserInfo,
                           @ApiParam(name = "activityId", value = "活动ID",required = true) Integer activityId,
                           @ApiParam(name = "pollederId", value = "被投票对象ID",required = true) Integer pollederId) {
        ResponseDTO responseDTO;
        try {
            if(appUserInfo == null || activityId == null || pollederId == null){
                throw new NullPointerException("参数错误");
            }
            responseDTO = WebUtils.createSuccessResponse(activityVoteRecordsService.addVotes(appUserInfo, activityId, pollederId) ? 1 : 0);
        } catch (RRException e) {
            logger.error("添加一次投票活动投票失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, e.getMsg());
        } catch (Exception e) {
            logger.error("添加一次投票活动投票失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "添加一次投票活动投票失败");
        }
        return responseDTO;
    }

}
