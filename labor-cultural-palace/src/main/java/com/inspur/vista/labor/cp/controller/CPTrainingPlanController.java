package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPTrainingPlanEntity;
import com.inspur.vista.labor.cp.entity.CPTrainingPlanListVO;
import com.inspur.vista.labor.cp.entity.CPTrainingPlanVO;
import com.inspur.vista.labor.cp.service.CPTrainingPlanService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title: CPTrainingPlanController
 * @Description: 人才培养计划
 * @Author: gengpeng
 * @CreateDate: 2019/12/31 8:35
 * @Version: 1.0
 */
@Api(value = "人才培养计划控制器", tags = {"人才培养计划接口"})
@RestController
@RequestMapping("/api/cp/plan/training")
public class CPTrainingPlanController {

    private static final Logger logger = LoggerFactory.getLogger(CPAssetsController.class);

    @Autowired
    private CPTrainingPlanService trainingPlanService;

    /**
     * 获取人才培养计划
     *
     * @param id 计划id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取人才培养计划", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPTrainingPlanVO> getTrainingPlan(@ApiParam(name = "id", value = "计划id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPTrainingPlanVO trainingPlan = trainingPlanService.getCPTrainingPlan(id);
        if (null != trainingPlan) {
            responseDTO = WebUtils.createSuccessResponse(trainingPlan);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到人才培养计划", "");
        }
        return responseDTO;
    }

    /**
     * 保存人才培养计划
     *
     * @param trainingPlan 人才培养计划
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存人才培养计划", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveTrainingPlan(@ApiParam(name = "trainingPlan", value = "人才培养计划", required = true) @RequestBody CPTrainingPlanEntity trainingPlan) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (StringUtils.isBlank(trainingPlan.getYear())) {
            msg.set(msg.get() + "培养年度不能为空" + ",");
        }
        if (StringUtils.isBlank(trainingPlan.getTarget())) {
            msg.set(msg.get() + "培养目标不能为空" + ",");
        }
        if (StringUtils.isBlank(trainingPlan.getContent())) {
            msg.set(msg.get() + "计划内容不能为空" + ",");
        }

        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                if (null == trainingPlan.getId()) {
                    CPTrainingPlanEntity trainingPlanInDb = trainingPlanService.getCPTrainingPlanByYear(trainingPlan.getYear());
                    if (null == trainingPlanInDb) {
                        String creator = "";
                        trainingPlan.setCreator(creator);
                        trainingPlanService.saveCPTrainingPlan(trainingPlan);
                        responseDTO = WebUtils.createSuccessResponse("");
                    } else {
                        responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_EXIST, "已存在" + trainingPlan.getYear() + "年计划");
                    }
                } else {
                    trainingPlanService.saveCPTrainingPlan(trainingPlan);
                    responseDTO = WebUtils.createSuccessResponse("");
                }
            } catch (Exception e) {
                logger.error("新增/修改人才培养计划失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }

    /**
     * 查询人才培养计划
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询人才培养计划", notes = "人才培养计划展示采用分页的方式,查询条件：page;pageSize;year(培养年度);target(培养目标);content(计划内容)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPTrainingPlanListVO> listTrainingPlan(@ApiParam(name = "parameters", value = "查询条件：page;pageSize;year(培养年度);target(培养目标);content(计划内容)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPTrainingPlanListVO> cpTrainingPlanListVOPage = trainingPlanService.listCPTrainingPlan(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpTrainingPlanListVOPage);
        } catch (Exception e) {
            logger.error("查询人才培养计划失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询人才培养计划失败");
        }
        return responseDTO;
    }

    /**
     * 删除人才培养计划
     *
     * @param id id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除人才培养计划", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/delete")
    public ResponseDTO deleteTrainingPlan(@ApiParam(name = "id", value = "人才培养计划id", required = true) @RequestParam(value = "id") String id) {
        ResponseDTO responseDTO;
        int result = trainingPlanService.removeCPTrainingPlanById(id);
        if (result > 0) {
            responseDTO = WebUtils.createSuccessResponse("人才培养计划删除成功");
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "人才培养计划删除失败");
        }
        return responseDTO;
    }

}