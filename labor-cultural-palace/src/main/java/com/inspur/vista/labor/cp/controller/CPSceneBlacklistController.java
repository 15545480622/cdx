package com.inspur.vista.labor.cp.controller;

import com.inspur.vista.labor.cp.entity.CPSceneBlacklistVO;
import com.inspur.vista.labor.cp.service.CPSceneBlacklistService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: CPSceneBlacklistController
 * @Description: 场次黑名单接口
 * @Author: gengpeng
 * @CreateDate: 2020/3/17 11:02
 * @Version: 1.0
 */
@Api(value = "场次黑名单接口", tags = {"场次黑名单接口"})
@RestController
@RequestMapping("/api/cp/scene/blacklist")
public class CPSceneBlacklistController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CPSceneBlacklistController.class);

    @Autowired
    private CPSceneBlacklistService sceneBlacklistService;

    /**
     * 新增黑名单记录
     *
     * @param sceneBlacklistVO
     * @return ResponseDTO
     */
    @ApiOperation(value = "新增黑名单记录", notes = "")
    @PostMapping(value = "/add")
    public ResponseDTO addBlacklist(@ApiParam(name = "sceneBlacklistVO", required = true) @RequestBody CPSceneBlacklistVO sceneBlacklistVO) {
        ResponseDTO responseDTO;
        try {
            String modifier = getUser().getUserId();
            sceneBlacklistService.addToBlacklist(sceneBlacklistVO, modifier);
            responseDTO = WebUtils.createSuccessResponse("");
        } catch (Exception ex) {
            logger.error("新增黑名单记录失败", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "新增黑名单记录失败");
        }
        return responseDTO;
    }

    /**
     * 删除黑名单记录
     *
     * @param sceneBlacklistVO
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除黑名单记录", notes = "删除黑名单只允许场次删除，传courtId(场地id);type(类型:1.场地黑名单 2.场次黑名单);beginTime(场次开始时间);endTime(场次结束时间)")
    @PostMapping(value = "/remove")
    public ResponseDTO removeBlacklist(@ApiParam(name = "sceneBlacklistVO", required = true) @RequestBody CPSceneBlacklistVO sceneBlacklistVO) {
        ResponseDTO responseDTO;
        try {
            sceneBlacklistService.removeFromBlacklist(sceneBlacklistVO);
            responseDTO = WebUtils.createSuccessResponse("");
        } catch (Exception ex) {
            logger.error("删除黑名单记录失败", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "删除黑名单记录失败");
        }
        return responseDTO;
    }
}
