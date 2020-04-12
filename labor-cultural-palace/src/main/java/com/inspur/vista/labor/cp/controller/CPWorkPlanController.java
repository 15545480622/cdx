package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPWorkPlanDetailVO;
import com.inspur.vista.labor.cp.entity.CPWorkPlanEntity;
import com.inspur.vista.labor.cp.entity.CPWorkPlanListVO;
import com.inspur.vista.labor.cp.service.CPWorkPlanService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: CPWorkPlanController
 * @Description: 文化宫工作计划控制器
 * @authur: wangxueying01
 * @CreatDate: 2020/1/3 15:41
 */
@RestController
@RequestMapping("/api/cp/plan/working")
public class CPWorkPlanController {

    private static final Logger logger = LoggerFactory.getLogger(CPWorkPlanController.class);

    @Autowired
    private CPWorkPlanService cpWorkPlanService;

    /**
     * 获取工作计划
     *
     * @param id 工作计划id
     * @return ResponseDTO
     */
    @GetMapping(value = "/{id}")
    public ResponseDTO getCPWorkPlan(@PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPWorkPlanDetailVO cpWorkPlanDetailVO = cpWorkPlanService.getCPWorkPlan(Long.valueOf(id));
        if (null != cpWorkPlanDetailVO) {
            responseDTO = WebUtils.createSuccessResponse(cpWorkPlanDetailVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到工作计划", "");
        }
        return responseDTO;
    }

    /**
     * 查询工作计划
     *
     * @param parameters 查询参数
     * @return responseDTO
     */
    @PostMapping(value = "/list")
    public Page<CPWorkPlanListVO> listCPWorkPlan(@RequestBody Map<String, Object> parameters) {
        return cpWorkPlanService.listCPWorkPlan(parameters);
    }

    /**
     * 保存工作计划
     *
     * @param cpWorkPlanEntity 工作计划
     * @return ResponseDTO
     */
    @PostMapping(value = "/save")
    public ResponseDTO saveCPWorkPlan(@Valid @RequestBody CPWorkPlanEntity cpWorkPlanEntity, BindingResult errors) {
        ResponseDTO responseDTO;
        try {
            AtomicReference<String> msg = new AtomicReference<>("");
            if (errors.hasErrors()) {
                List<FieldError> fieldErrorList = errors.getFieldErrors();
                for (FieldError fieldError : fieldErrorList) {
                    msg.set(msg.get() + fieldError.getDefaultMessage() + ",");
                }
            }

            if (StringUtils.isNotBlank(msg.get())) {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
            } else {
                cpWorkPlanService.saveCPWorkPlan(cpWorkPlanEntity);
                responseDTO = WebUtils.createSuccessResponse("");
            }
        } catch (Exception e) {
            logger.error("新增/修改工作计划失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
        }
        return responseDTO;
    }

    /**
     * 删除工作计划
     *
     * @param ids 工作计划id
     * @return ResponseDTO
     */
    @GetMapping(value = "/delete")
    public ResponseDTO deleteCPWorkPlan(@RequestParam(value = "ids") String ids) {
        ResponseDTO responseDTO;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr.length > 0) {
                int result = cpWorkPlanService.removeCPWorkPlanById(idArr);
                if (result > 0) {
                    responseDTO = WebUtils.createSuccessResponse("工作计划删除成功");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "工作计划删除失败");
                }
            } else {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
            }
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
        }
        return responseDTO;
    }
}
