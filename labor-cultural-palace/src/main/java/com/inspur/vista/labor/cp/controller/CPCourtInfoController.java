package com.inspur.vista.labor.cp.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPCourtInfoService;
import com.inspur.vista.labor.cp.service.CPCourtTalentService;
import com.inspur.vista.labor.cp.service.CPReserveInfoService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title: CPCourtInfoController
 * @Description: 场地信息控制器
 * @Author: wangxueying01
 * @CreateDate: 2020/03/04 16:35
 * @Version: 1.0
 */
@Api(value = "场地信息控制类", tags = {"场地信息接口"})
@RestController
@RequestMapping("/api/cp/court/info")
public class CPCourtInfoController {

    private static final Logger logger = LoggerFactory.getLogger(CPCourtInfoController.class);

    @Autowired
    private CPCourtInfoService cpCourtInfoService;

    @Autowired
    private CPReserveInfoService reserveInfoService;

    @Autowired
    private CPCourtTalentService cpCourtTalentService;

    /**
     * 获取场地信息
     *
     * @param id 场地信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取场地信息", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPCourtInfoVO> getCPCourtInfo(@ApiParam(name = "id", value = "场地信息id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPCourtInfoVO cpCourtInfo = cpCourtInfoService.getCPCourtInfo(id);
        if (null != cpCourtInfo) {
            responseDTO = WebUtils.createSuccessResponse(cpCourtInfo);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到场地信息", "");
        }
        return responseDTO;
    }

    /**
     * 保存场地信息
     *
     * @param cpCourtInfo 场地信息
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存场地信息", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveCPCourtInfo(@ApiParam(name = "cpCourtInfo", value = "场地信息", required = true) @RequestBody CPCourtInfoEntity cpCourtInfo) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (StringUtils.isBlank(cpCourtInfo.getId())) {
            if (StringUtils.isBlank(cpCourtInfo.getPlaceId())) {
                msg.set(msg.get() + "场所id不能为空" + ",");
            }
            if (StringUtils.isBlank(cpCourtInfo.getName())) {
                msg.set(msg.get() + "场地名称不能为空" + ",");
            }
            if (StringUtils.isBlank(cpCourtInfo.getType())) {
                msg.set(msg.get() + "场地的项目类别不能为空" + ",");
            }
            if (null == cpCourtInfo.getIncludeHolidays()) {
                msg.set(msg.get() + "是否包含法定节假日不能为空" + ",");
            }
            if (null == cpCourtInfo.getIncludeWeekend()) {
                msg.set(msg.get() + "是否包含周末不能为空" + ",");
            }
            if (null == cpCourtInfo.getMinimumHireHour()) {
                msg.set(msg.get() + "最小起租小时不能为空" + ",");
            }
        }

        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                CPCourtInfoEntity cpCourtInfoEntity = cpCourtInfoService.saveCPCourtInfo(cpCourtInfo);
                responseDTO = WebUtils.createSuccessResponse(cpCourtInfoEntity.getId());
            } catch (Exception e) {
                logger.error("新增/修改场地信息失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }

    /**
     * 查询场地信息
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询场地信息", notes = "场地信息展示不采用分页的方式,加载已经启用的场地,查询条件：type(项目类型)；cpId(文化宫id);placeId(场所id)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPCourtInfoListVO> listCPCourtInfo(@ApiParam(name = "parameters", value = "查询条件：type(项目类型)；cpId(文化宫id);placeId(场所id)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
//            parameters.put("isEnable", CPConstants.ENABLED);
            List<CPCourtInfoListVO> cpCourtInfoListVOS = cpCourtInfoService.listCPCourtInfo(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpCourtInfoListVOS);
        } catch (Exception e) {
            logger.error("查询场地信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询场地信息失败");
        }
        return responseDTO;
    }

    /**
     * 删除场地信息
     *
     * @param ids 场地信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除场地信息", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/delete")
    public ResponseDTO deleteCPCourtInfo(@ApiParam(name = "ids", value = "场地id", required = true) @RequestParam(value = "ids") String ids) {
        ResponseDTO responseDTO;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr.length > 0) {
                String modifier = "";
                int result = cpCourtInfoService.removeCPCourtInfoById(idArr, modifier);
                if (result > 0) {
                    responseDTO = WebUtils.createSuccessResponse("场地信息删除成功");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "场地信息删除失败");
                }

            } else {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
            }
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
        }
        return responseDTO;
    }

    /**
     * 启用场地
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "启用场地", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/enable")
    public ResponseDTO enableCourt(@ApiParam(name = "id", value = "场地id", required = true) @RequestParam(value = "id") String id) {

        ResponseDTO responseDTO = WebUtils.createSuccessResponse("");
        CPCourtInfoVO cpCourtInfoVO = cpCourtInfoService.getCPCourtInfo(id);
        if (null != cpCourtInfoVO) {
            // 如果场地不可以预约
            if (0 == cpCourtInfoVO.getCanReserve()) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("id", id);
                paramMap.put("modifier", "");
                int result = cpCourtInfoService.enableCourt(paramMap);
                if (result == 1) {
                    responseDTO = WebUtils.createSuccessResponse("场地启用状态修改成功");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "场地启用状态修改失败");
                }
            } else {
                int minimumHireMinute = (int) ((cpCourtInfoVO.getMinimumHireHour()) * 60);

                if (null != cpCourtInfoVO.getEveningStartTime() && null != cpCourtInfoVO.getEveningEndTime()) {
                    DateTime eveningStart = DateUtil.parse(DateUtil.today() + " " + cpCourtInfoVO.getEveningStartTime(), "yyyy-MM-dd HH:mm:ss");
                    DateTime eveningEnd = DateUtil.parse(DateUtil.today() + " " + cpCourtInfoVO.getEveningEndTime(), "yyyy-MM-dd HH:mm:ss");
                    long eveningTime = DateUtil.between(eveningStart, eveningEnd, DateUnit.MINUTE);
                    if (eveningTime % minimumHireMinute != 0) {
                        responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "服务时间段不能被最小起租时间等分");
                    }
                }
                if (responseDTO.getCode().equals(ErrorCodeEnum.P_SUCCESS.getCode())) {
                    DateTime morningStart = DateUtil.parse(DateUtil.today() + " " + cpCourtInfoVO.getMorningStartTime(), "yyyy-MM-dd HH:mm:ss");
                    DateTime morningEnd = DateUtil.parse(DateUtil.today() + " " + cpCourtInfoVO.getMorningEndTime(), "yyyy-MM-dd HH:mm:ss");
                    DateTime afternoonStart = DateUtil.parse(DateUtil.today() + " " + cpCourtInfoVO.getAfternoonStartTime(), "yyyy-MM-dd HH:mm:ss");
                    DateTime afternoonEnd = DateUtil.parse(DateUtil.today() + " " + cpCourtInfoVO.getAfternoonEndTime(), "yyyy-MM-dd HH:mm:ss");
                    long morningTime = DateUtil.between(morningStart, morningEnd, DateUnit.MINUTE);
                    long afternoonTime = DateUtil.between(afternoonStart, afternoonEnd, DateUnit.MINUTE);

                    if ((morningTime % minimumHireMinute != 0) || (afternoonTime % minimumHireMinute != 0)) {
                        responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "服务时间段不能被最小起租时间等分");
                    } else {
                        Map<String, Object> paramMap = new HashMap<>();
                        paramMap.put("id", id);
                        paramMap.put("modifier", "");
                        int result = cpCourtInfoService.enableCourt(paramMap);
                        if (result == 1) {
                            responseDTO = WebUtils.createSuccessResponse("场地启用状态修改成功");
                        } else {
                            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "场地启用状态修改失败");
                        }
                    }
                }
            }
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "未找到该启用场地信息");
        }
        return responseDTO;
    }

    /**
     * 关联专业人才
     *
     * @param cpCourtTalentEntity 关联专业人才
     * @return ResponseDTO
     */
    @ApiOperation(value = "关联专业人才", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/saveCourtTalent")
    public ResponseDTO saveCPCourtTalent(@ApiParam(name = "cpCourtTalentEntity", value = "关联专业人才", required = true) @RequestBody CPCourtTalentEntity cpCourtTalentEntity) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (StringUtils.isBlank(cpCourtTalentEntity.getCourtId())) {
            msg.set(msg.get() + "场地id不能为空" + ",");
        }
        if (StringUtils.isBlank(cpCourtTalentEntity.getTalentId())) {
            msg.set(msg.get() + "专业人才id不能为空" + ",");
        }

        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                cpCourtTalentService.saveCourtTalent(cpCourtTalentEntity);
                responseDTO = WebUtils.createSuccessResponse("");
            } catch (Exception e) {
                logger.error("关联专业人才信息失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }

    /**
     * 取消关联专业人才
     *
     * @param parameters
     * @return
     */
    @ApiOperation(value = "取消关联专业人才", notes = "取消关联专业人才,必传参数：courtId(场地id);talentId(专业人才id)", response = ResponseDTO.class)
    @PostMapping(value = "/cancelCPCourtTalent")
    public ResponseDTO cancelCPCourtTalent(@ApiParam(name = "parameters", value = "取消关联专业人才,必传参数：courtId(场地id);talentId(专业人才id)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            int result = cpCourtTalentService.cancelCourtTalent(parameters);
            if (1 == result) {
                responseDTO = WebUtils.createSuccessResponse("");
            } else {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "取消关联专业人才失败");
            }
        } catch (Exception e) {
            logger.error("取消关联专业人才失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "取消关联专业人才失败");
        }
        return responseDTO;
    }

    /**
     * 查询专业人才及其关联状态
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询专业人才及其关联状态", notes = "分页查询,查询条件：page,pageSize,courtId(场地id),cpId(文化宫id)", response = ResponseDTO.class)
    @PostMapping(value = "/listCPCourtTalent")
    public ResponseDTO<CPCourtTalentListVO> listCPCourtTalent(@ApiParam(name = "parameters", value = "查询条件：page,pageSize,courtId(场地id),cpId(文化宫id)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPCourtTalentListVO> cpCourtTalentListVOS = cpCourtTalentService.listCourtTalent(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpCourtTalentListVOS);
        } catch (Exception e) {
            logger.error("查询专业人才及其关联状态失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询专业人才及其关联状态失败");
        }
        return responseDTO;
    }

    /**
     * 上传附件
     *
     * @param file 文件
     * @param type 文件类型
     * @return ResponseDTO
     */
    @ApiOperation(value = "上传附件", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/upload")
    public ResponseDTO uploadFile(@ApiParam(name = "file", value = "附件", required = true) @RequestParam("file") MultipartFile[] file,
                                  @ApiParam(name = "type", value = "附件类型", required = true) @RequestParam("type") String type,
                                  @ApiParam(name = "courtId", value = "场所id", required = true) @RequestParam(value = "cpId") String courtId
    ) {
        ResponseDTO responseDTO;
        try {
            List<String> fileIdList = cpCourtInfoService.uploadFile(file, type, courtId);
            responseDTO = WebUtils.createSuccessResponse(fileIdList);
        } catch (Exception ex) {
            logger.error("文件上传出现异常", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "文件上传出现异常");
        }
        return responseDTO;
    }
}
