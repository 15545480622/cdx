package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPSecurityInfoEntity;
import com.inspur.vista.labor.cp.entity.CPSecurityInfoListVO;
import com.inspur.vista.labor.cp.entity.CPSecurityInfoVO;
import com.inspur.vista.labor.cp.service.CPSecurityInfoService;
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
 * @ClassName: CPSecurityInfoController
 * @Description: 安全管理控制器
 * @authur: wangxueying01
 * @CreatDate: 2020/4/7 19:52
 */
@Api(value = "安全管理控制器", tags = {"安全管理接口"})
@RestController
@RequestMapping("/api/cp/security")
public class CPSecurityInfoController {

    private static final Logger logger = LoggerFactory.getLogger(CPSecurityInfoController.class);

    @Autowired
    private CPSecurityInfoService cpSecurityInfoService;

    /**
     * 获取安全管理
     *
     * @param id 安全管理id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取安全管理", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPSecurityInfoVO> getCPSecurityInfo(@ApiParam(name = "id", value = "安全管理id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPSecurityInfoVO cpSecurityInfoVO = cpSecurityInfoService.getCPSecurityInfo(id);
        if (null != cpSecurityInfoVO) {
            responseDTO = WebUtils.createSuccessResponse(cpSecurityInfoVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到安全管理信息", "");
        }
        return responseDTO;
    }

    /**
     * 保存安全管理
     *
     * @param cpSecurityInfoEntity 安全管理
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存安全管理", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveCPSecurityInfo(@ApiParam(name = "cpSecurityInfoEntity", value = "安全管理", required = true) @RequestBody CPSecurityInfoEntity cpSecurityInfoEntity) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (StringUtils.isBlank(cpSecurityInfoEntity.getCpId())) {
            msg.set(msg.get() + "文化宫id不能为空" + ",");
        }
        if (StringUtils.isBlank(cpSecurityInfoEntity.getSecurityOrgan())) {
            msg.set(msg.get() + "安全管理机构不能为空" + ",");
        }
        if (StringUtils.isBlank(cpSecurityInfoEntity.getSecurityUser())) {
            msg.set(msg.get() + "安全管理人员不能为空" + ",");
        }

        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                CPSecurityInfoEntity cpSecurityInfo = cpSecurityInfoService.saveCPSecurityInfo(cpSecurityInfoEntity);
                responseDTO = WebUtils.createSuccessResponse(cpSecurityInfo.getId());
            } catch (Exception e) {
                logger.error("新增/修改安全管理失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }

    /**
     * 查询安全管理
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询安全管理", notes = "安全管理展示采用分页的方式,查询条件：page;pageSize;cpId(文化宫id)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPSecurityInfoListVO> listCPSecurityInfo(@ApiParam(name = "parameters", value = "查询条件：cpId(文化宫id)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPSecurityInfoListVO> cpSecurityInfoListVOPage = cpSecurityInfoService.listCPSecurityInfo(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpSecurityInfoListVOPage);
        } catch (Exception e) {
            logger.error("查询安全管理失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询安全管理失败");
        }
        return responseDTO;
    }

    /**
     * 删除安全管理
     *
     * @param ids 安全管理id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除安全管理", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/delete")
    public ResponseDTO deleteCPSecurityInfo(@ApiParam(name = "ids", value = "安全管理id", required = true) @RequestParam(value = "ids") String ids) {

        //验证输入参数
        if (StringUtils.isBlank((ids))) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
        }
        String[] idArr = ids.split(",");
        if (idArr.length <= 0) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("modifier", "");
        paramMap.put("ids", idArr);
        //执行删除操作
        int result = cpSecurityInfoService.removeCPSecurityInfo(paramMap);
        if (result > 0) {
            return WebUtils.createSuccessResponse("安全管理删除成功");
        } else {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "安全管理删除失败");
        }
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
                                  @ApiParam(name = "securityId", value = "安全管理id", required = true) @RequestParam(value = "cpId") String securityId
    ) {
        ResponseDTO responseDTO;
        try {
            List<String> fileIdList = cpSecurityInfoService.uploadFile(file, type, securityId);
            responseDTO = WebUtils.createSuccessResponse(fileIdList);
        } catch (Exception ex) {
            logger.error("文件上传出现异常", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "文件上传出现异常");
        }
        return responseDTO;
    }
}
