package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPVisitorsFlowrateEntity;
import com.inspur.vista.labor.cp.entity.CPVisitorsFlowrateListVO;
import com.inspur.vista.labor.cp.service.CPVisitorsFlowrateService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title: CPVisitorsFlowrateController
 * @Description: 访问记录控制器
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:47
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/cp/visitors/flowrate")
public class CPVisitorsFlowrateController {

    private static final Logger logger = LoggerFactory.getLogger(CPVisitorsFlowrateController.class);

    @Autowired
    private CPVisitorsFlowrateService cpVisitorsFlowrateService;

    /**
     * 查询访问记录
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @PostMapping(value = "/list")
    public Page<CPVisitorsFlowrateListVO> listCPVisitorsFlowrate(@RequestBody Map<String, Object> parameters) {
        return cpVisitorsFlowrateService.listCPVisitorsFlowrate(parameters);
    }

    /**
     * 新增访问记录
     *
     * @param cpVisitorsFlowrate 访问记录
     * @return ResponseDTO
     */
    @PostMapping(value = "/save")
    public ResponseDTO saveCPVisitorsFlowrate(@Valid @RequestBody CPVisitorsFlowrateEntity cpVisitorsFlowrate, BindingResult errors) {
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
                if (cpVisitorsFlowrate.getId() == null) {
                    cpVisitorsFlowrateService.saveCPVisitorsFlowrate(cpVisitorsFlowrate);
                    responseDTO = WebUtils.createSuccessResponse("");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "访问参数不正确");
                }
            }
        } catch (Exception e) {
            logger.error("新增访问记录失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
        }
        return responseDTO;
    }
}
