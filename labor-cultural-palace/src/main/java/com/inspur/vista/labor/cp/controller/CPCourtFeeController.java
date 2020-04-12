package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPCourtFeeEntity;
import com.inspur.vista.labor.cp.entity.CPCourtFeeListVO;
import com.inspur.vista.labor.cp.entity.CPCourtFeeVO;
import com.inspur.vista.labor.cp.service.CPCourtFeeService;
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
 * @Title: CPCourtFeeController
 * @Description: 场地费用标准控制器
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
@Api(value = "场地费用控制类", tags = {"场地费用接口"})
@RestController
@RequestMapping("/api/cp/court/fee")
public class CPCourtFeeController {

    private static final Logger logger = LoggerFactory.getLogger(CPCourtFeeController.class);

    @Autowired
    private CPCourtFeeService cpCourtFeeService;

    /**
     * 获取场地费用标准
     *
     * @param id 场地费用标准id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取场地费用标准", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseDTO<CPCourtFeeVO> getCPCourtFee(@ApiParam(name = "id", value = "费用标准id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPCourtFeeVO cpArenaFee = cpCourtFeeService.getCPCourtFee(id);
        if (null != cpArenaFee) {
            responseDTO = WebUtils.createSuccessResponse(cpArenaFee);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到场地费用标准", "");
        }
        return responseDTO;
    }

    /**
     * 保存场地费用标准
     *
     * @param cpArenaFee 场地费用标准
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存场地费用标准", notes = "")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseDTO saveCPCourtFee(@ApiParam(name = "cpArenaFee", value = "费用标准", required = true) @RequestBody CPCourtFeeEntity cpArenaFee) {
        ResponseDTO responseDTO;
        try {
            AtomicReference<String> msg = new AtomicReference<>("");
            if (StringUtils.isNotBlank(msg.get())) {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
            } else {
                responseDTO = cpCourtFeeService.saveCPCourtFee(cpArenaFee);
            }
        } catch (Exception e) {
            logger.error("新增场地费用标准失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
        }
        return responseDTO;
    }

    /**
     * 查询场地费用标准
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询场地费用标准", notes = "查询条件：courtId(场地id)；page,pageSize", response = ResponseDTO.class)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Page<CPCourtFeeListVO> listCPCourtFee(@ApiParam(name = "parameters", value = "查询条件：courtId(场地id)；page,pageSize", required = true) @RequestBody Map<String, Object> parameters) {
        return cpCourtFeeService.listCPCourtFee(parameters);
    }

    /**
     * 删除场地费用标准
     *
     * @param id 场地费用标准id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除场地费用标准", notes = "")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseDTO deleteCPCourtFee(@ApiParam(name = "id", value = "费用标准id", required = true) @RequestParam(value = "id") String id) {
        ResponseDTO responseDTO;
        String modifier = "";
        int result = cpCourtFeeService.removeCPCourtFeeById(id, modifier);
        if (result > 0) {
            responseDTO = WebUtils.createSuccessResponse("场地费用标准删除成功");
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "场地费用标准删除失败");
        }

        return responseDTO;
    }
}
