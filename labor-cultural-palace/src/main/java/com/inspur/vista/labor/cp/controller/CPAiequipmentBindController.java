package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAiequipmentBindEntity;
import com.inspur.vista.labor.cp.entity.CPAiequipmentBindListVO;
import com.inspur.vista.labor.cp.entity.CPAiequipmentBindVO;
import com.inspur.vista.labor.cp.service.CPAiequipmentBindService;
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
 * @Title: CPAiequipmentBindController
 * @Description: 智能设备绑定信息控制器
 * @Author: liuzq
 * @CreateDate: 2020/03/07 15:54
 * @Version: 1.0
 */
@Api(value = "智能设备绑定信息控制类", tags = {"智能设备绑定接口"})
@RestController
@RequestMapping("/api/cp/aiequipment/bind")
public class CPAiequipmentBindController {

    private static final Logger logger = LoggerFactory.getLogger(CPAiequipmentBindController.class);

    @Autowired
    private CPAiequipmentBindService cpAiequipmentBindService;

    /**
     * 获取智能设备绑定信息
     *
     * @param id 智能设备绑定信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取智能设备绑定信息", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPAiequipmentBindVO> getCPAiequipmentBind(@ApiParam(name = "id", value = "智能设备绑定信息id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPAiequipmentBindVO cpAiequipmentBind = cpAiequipmentBindService.getCPAiequipmentBind(id);
        if (null != cpAiequipmentBind) {
            responseDTO = WebUtils.createSuccessResponse(cpAiequipmentBind);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到智能设备绑定信息", "");
        }
        return responseDTO;
    }

    /**
     * 保存智能设备绑定信息
     *
     * @param cpAiequipmentBind 智能设备绑定信息
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存智能设备绑定信息", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveCPAiequipmentBind(@ApiParam(name = "cpAiequipmentBind", value = "智能设备绑定信息", required = true) @RequestBody CPAiequipmentBindEntity cpAiequipmentBind) {
        ResponseDTO responseDTO;
        try {
            AtomicReference<String> msg = new AtomicReference<>("");


            if (StringUtils.isNotBlank(msg.get())) {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
            } else {
                CPAiequipmentBindEntity cpAiequipmentBindEntity = cpAiequipmentBindService.saveCPAiequipmentBind(cpAiequipmentBind);
                responseDTO = WebUtils.createSuccessResponse(cpAiequipmentBindEntity.getId());
            }
        } catch (Exception e) {
            logger.error("新增/修改智能设备绑定信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
        }
        return responseDTO;
    }

    /**
     * 查询智能设备绑定信息
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询智能设备绑定信息", notes = "分页查询", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO listCPAiequipmentBind(@ApiParam(name = "parameters", value = "查询条件，page,pageSize", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPAiequipmentBindListVO> cpAiequipmentBindListVOPage = cpAiequipmentBindService.listCPAiequipmentBind(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpAiequipmentBindListVOPage);
        } catch (Exception e) {
            logger.error("查询智能设备绑定信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询智能设备绑定信息失败");
        }
        return responseDTO;
    }

    /**
     * 删除智能设备绑定信息
     *
     * @param ids 智能设备绑定信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除智能设备绑定信息", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/delete")
    public ResponseDTO deleteCPAiequipmentBind(@ApiParam(name = "ids", value = "智能设备绑定信息id", required = true) @RequestParam(value = "ids") String ids) {
        ResponseDTO responseDTO;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr.length > 0) {
                int result = cpAiequipmentBindService.removeCPAiequipmentBindById(idArr);
                if (result > 0) {
                    responseDTO = WebUtils.createSuccessResponse("智能设备绑定信息删除成功");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "智能设备绑定信息删除失败");
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
