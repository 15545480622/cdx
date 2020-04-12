package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPRegulationEntity;
import com.inspur.vista.labor.cp.entity.CPRegulationInfoVO;
import com.inspur.vista.labor.cp.entity.CPRegulationListVO;
import com.inspur.vista.labor.cp.service.CPRegulationService;
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
 * @ClassName: CPRegulationController
 * @Description: 文化宫制度管理控制器
 * @authur: wangxueying01
 * @CreatDate: 2020/1/3 10:14
 */
@Api(value = "制度管理控制器", tags = {"制度管理控制器"})
@RestController
@RequestMapping("/api/cp/regulation")
public class CPRegulationController {

    private static final Logger logger = LoggerFactory.getLogger(CPRegulationController.class);

    @Autowired
    private CPRegulationService cpRegulationService;

    /**
     * 获取文化宫制度信息
     *
     * @param id 文化宫制度信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取制度信息", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPRegulationInfoVO> getCPRegulation(@ApiParam(name = "id", value = "制度信息id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPRegulationInfoVO cpRegulationInfoVO = cpRegulationService.getCPRegulation(id);
        if (null != cpRegulationInfoVO) {
            responseDTO = WebUtils.createSuccessResponse(cpRegulationInfoVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到文化宫制度信息", "");
        }
        return responseDTO;
    }

    /**
     * 保存文化宫制度信息
     *
     * @param cpRegulationEntity 文化宫制度信息
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存文化宫制度信息", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveCPRegulation(@ApiParam(name = "cpRegulationEntity", value = "制度信息", required = true) @RequestBody CPRegulationEntity cpRegulationEntity) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (StringUtils.isBlank(cpRegulationEntity.getCpId())) {
            msg.set(msg.get() + "文化宫id不能为空" + ",");
        }
        if (StringUtils.isBlank(cpRegulationEntity.getName())) {
            msg.set(msg.get() + "制度名称不能为空" + ",");
        }
        if (null == cpRegulationEntity.getRegulationType()) {
            msg.set(msg.get() + "制度类型不能为空" + ",");
        }
        if (StringUtils.isBlank(cpRegulationEntity.getRegulationContent())) {
            msg.set(msg.get() + "制度内容不能为空" + ",");
        }

        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                cpRegulationService.saveCPRegulation(cpRegulationEntity);
                responseDTO = WebUtils.createSuccessResponse("");
            } catch (Exception e) {
                logger.error("新增/修改文化宫制度失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }

    /**
     * 查询文化宫制度信息
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询制度信息", notes = "查询条件，page,pageSize,cpId(文化宫id，必填),name(制度名称，模糊检索),regulationType(制度类型:1.文化宫制度;2.场所制度)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPRegulationListVO> listCPRegulation(@ApiParam(name = "parameters", value = "查询条件，page,pageSize,cpId(文化宫id，必填),name(制度名称，模糊检索),regulationType(制度类型:1.文化宫制度;2.场所制度)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPRegulationListVO> cpRegulationListVOPage = cpRegulationService.listCPRegulation(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpRegulationListVOPage);
        } catch (Exception e) {
            logger.error("查询文化宫制度信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询文化宫制度信息失败");
        }
        return responseDTO;
    }

    /**
     * 删除文化宫制度信息
     *
     * @param ids 文化宫制度信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除制度信息", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/delete")
    public ResponseDTO deleteCPRegulation(@ApiParam(name = "ids", value = "制度信息id", required = true) @RequestParam(value = "ids") String ids) {
        ResponseDTO responseDTO;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr.length > 0) {
                int result = cpRegulationService.removeCPRegulationById(idArr);
                if (result > 0) {
                    responseDTO = WebUtils.createSuccessResponse("文化宫制度删除成功");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "文化宫制度删除失败");
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
