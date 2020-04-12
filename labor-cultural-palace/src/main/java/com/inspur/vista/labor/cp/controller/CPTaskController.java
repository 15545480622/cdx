package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPTaskListVO;
import com.inspur.vista.labor.cp.service.CPTaskService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Title: CPTaskController
 * @Description: 任务控制器
 * @Author: gengpeng
 * @CreateDate: 2020/4/1 11:04
 * @Version: 1.0
 */
@Api(value = "任务控制类", tags = {"任务接口"})
@RequestMapping("/api/cp/task")
@RestController
public class CPTaskController extends BaseController {

    @Autowired
    private CPTaskService taskService;


    /**
     * 查询代办任务
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询代办任务", notes = "分页查询", response = ResponseDTO.class)
    @PostMapping(value = "/todo/list")
    public ResponseDTO listCPInfo(@ApiParam(name = "parameters", value = "查询条件，page,pageSize", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {

            parameters.put("handler", getUser().getUserId());
            parameters.put("handlerLaborId", getUser().getLaborId());
            Page<CPTaskListVO> cpInfoVOPage = taskService.listPubTaskTodo(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpInfoVOPage);
        } catch (Exception e) {
            logger.error("查询代办任务失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询代办任务失败");
        }
        return responseDTO;
    }
}
