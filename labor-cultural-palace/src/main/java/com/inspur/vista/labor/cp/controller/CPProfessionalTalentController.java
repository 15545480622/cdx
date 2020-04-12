package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPProfessionalTalentAdd;
import com.inspur.vista.labor.cp.entity.CPProfessionalTalentListVO;
import com.inspur.vista.labor.cp.entity.CPProfessionalTalentVO;
import com.inspur.vista.labor.cp.service.CPProfessionalTalentService;
import com.inspur.vista.labor.cp.service.CPStaffInfoService;
import com.inspur.vista.labor.cp.service.PubFilesService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title: CPProfessionalTalentController
 * @Description: 专业人才库控制器
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:20
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/cp/professional/talent")
public class CPProfessionalTalentController {

    private static final Logger logger = LoggerFactory.getLogger(CPProfessionalTalentController.class);

    @Autowired
    private CPProfessionalTalentService cpProfessionalTalentService;

    @Autowired
    private CPStaffInfoService cpStaffInfoService;

    @Autowired
    private PubFilesService filesService;

    /**
     * 获取专业人才信息
     *
     * @param id id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取专业人才信息", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPProfessionalTalentVO> getCPProfessionalTalent(@ApiParam(name = "staffId", value = "工作人员id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPProfessionalTalentVO cpProfessionalTalentVO = cpProfessionalTalentService.getCPProfessionalTalent(id);
        if (null != cpProfessionalTalentVO) {
            responseDTO = WebUtils.createSuccessResponse(cpProfessionalTalentVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到专业人才信息", "");
        }
        return responseDTO;
    }

    /**
     * 保存专业人才信息
     *
     * @param professionalTalentAdd 专业人才
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存专业人才信息", notes = "")
    @PostMapping(value = "/save")
    public ResponseDTO saveCPProfessionalTalent(@ApiParam(name = "cpProfessionalTalent", value = "专业人才信息", required = true) @RequestBody CPProfessionalTalentAdd professionalTalentAdd) {
        ResponseDTO responseDTO;
        try {
            AtomicReference<String> msg = new AtomicReference<>("");


            if (StringUtils.isNotBlank(msg.get())) {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
            } else {
                cpProfessionalTalentService.saveCPProfessionalTalent(professionalTalentAdd);
                responseDTO = WebUtils.createSuccessResponse("");
            }
        } catch (Exception e) {
            logger.error("新增/修改专业人才信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
        }
        return responseDTO;
    }

    /**
     * 查询专业人才信息
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询专业人才信息", notes = "查询条件：page，pageSize，cpId(文化宫id),name(姓名);idcard(身份证号);employmentType(用工类别：1.编制、2.外聘、3.合同制);isStaffing(是否在编:1.是 2.否)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public Page<CPProfessionalTalentListVO> listCPProfessionalTalent(@ApiParam(name = "parameters", value = "参数，见描述", required = true) @RequestBody Map<String, Object> parameters) {
        return cpProfessionalTalentService.listCPProfessionalTalent(parameters);
    }

    /**
     * 删除专业人才信息
     *
     * @param ids 专业人才id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除专业人才信息", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/delete")
    public ResponseDTO deleteCPProfessionalTalent(@ApiParam(name = "ids", value = "专业人才id", required = true) @RequestParam(value = "ids") String ids) {
        ResponseDTO responseDTO;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr.length > 0) {
                int result = cpProfessionalTalentService.removeCPProfessionalTalentById(idArr);
                if (result > 0) {
                    responseDTO = WebUtils.createSuccessResponse("专业人才信息删除成功");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "专业人才信息删除失败");
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
                                  @ApiParam(name = "cpId", value = "文化宫id", required = true) @RequestParam(value = "cpId") String cpId) {
        ResponseDTO responseDTO;
        try {
            List<String> fileIdList = cpProfessionalTalentService.uploadFile(file, type, cpId);
            responseDTO = WebUtils.createSuccessResponse(fileIdList);
        } catch (Exception ex) {
            logger.error("文件上传出现异常", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "文件上传出现异常");
        }
        return responseDTO;
    }


}
