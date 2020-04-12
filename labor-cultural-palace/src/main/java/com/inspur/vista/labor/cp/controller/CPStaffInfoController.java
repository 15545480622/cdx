package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPStaffInfoEntity;
import com.inspur.vista.labor.cp.entity.CPStaffInfoListVO;
import com.inspur.vista.labor.cp.entity.CPStaffInfoVO;
import com.inspur.vista.labor.cp.service.CPStaffInfoService;
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

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: CPStaffInfoController
 * @Description: 文化宫人员信息控制器
 * @authur: wangxueying01
 * @CreatDate: 2019/12/5 19:33
 */
@Api(value = "人员管理控制类", tags = {"人员管理接口"})
@RestController
@RequestMapping("/api/cp/staff")
public class CPStaffInfoController {

    private static final Logger logger = LoggerFactory.getLogger(CPStaffInfoController.class);

    @Autowired
    private CPStaffInfoService cpStaffInfoService;

    /**
     * 获取人员信息
     *
     * @param id 人员信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取人员信息", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPStaffInfoVO> getCPStaffInfo(@ApiParam(name = "id", value = "人员信息id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPStaffInfoVO cpStaffInfoVO = cpStaffInfoService.getCPStaffInfo(id);
        if (null != cpStaffInfoVO) {
            responseDTO = WebUtils.createSuccessResponse(cpStaffInfoVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到人员信息", "");
        }
        return responseDTO;
    }

    /**
     * 查询人员信息
     *
     * @param parameters 查询参数
     * @return responseDTO
     */
    @ApiOperation(value = "查询人员信息", notes = "人员信息展示采用分页的方式,查询条件：name(姓名);idcard(身份证号);employmentType(用工类别：1.编制、2.外聘、3.合同制);havingSWNum(是否在编:1.是 2.否)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPStaffInfoListVO> listCPStaffInfo(@ApiParam(name = "parameters", value = "查询条件：name(姓名);idcard(身份证号);employmentType(用工类别：1.编制、2.外聘、3.合同制);havingSWNum(是否在编:1.是 2.否)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPStaffInfoListVO> cpStaffInfoListVOPage = cpStaffInfoService.listCPStaffInfo(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpStaffInfoListVOPage);
        } catch (Exception e) {
            logger.error("查询人员信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询人员信息失败");
        }
        return responseDTO;
    }

    /**
     * 保存人员信息
     *
     * @param cpStaffInfoEntity 人员信息
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存人员信息", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveCPStaffInfo(@ApiParam(name = "cpStaffInfoEntity", value = "人员信息", required = true) @RequestBody CPStaffInfoEntity cpStaffInfoEntity) {
        ResponseDTO responseDTO;
        try {
            AtomicReference<String> msg = new AtomicReference<>("");

            if (StringUtils.isBlank(cpStaffInfoEntity.getId())) {
                CPStaffInfoVO staffInfoVO = cpStaffInfoService.getCPStaffInfoByIdcard(cpStaffInfoEntity.getIdcard());
                if (null != staffInfoVO) {
                    msg.set(msg.get() + "该人员已存在请勿重复添加" + ",");
                }
            }
            if (StringUtils.isNotBlank(msg.get())) {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
            } else {
                cpStaffInfoService.saveCPStaffInfo(cpStaffInfoEntity);
                responseDTO = WebUtils.createSuccessResponse(cpStaffInfoEntity.getId());
            }
        } catch (Exception e) {
            logger.error("新增/修改人员信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
        }
        return responseDTO;
    }

    /**
     * 删除人员信息
     *
     * @param ids 人员信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除人员信息", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/delete")
    public ResponseDTO deleteCPStaffInfo(@ApiParam(name = "ids", value = "人员信息id", required = true) @RequestParam(value = "ids") String ids) {
        ResponseDTO responseDTO;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr.length > 0) {
                int result = cpStaffInfoService.removeCPStaffInfoById(idArr);
                if (result > 0) {
                    responseDTO = WebUtils.createSuccessResponse("人员信息删除成功");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "人员信息删除失败");
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
    @ApiOperation(value = "上传附件", notes = "类型：D1:照片;D2:聘用证书;D3:获奖证明;D4:资质照片", response = ResponseDTO.class)
    @PostMapping(value = "/upload")
    public ResponseDTO uploadFile(@ApiParam(name = "file", value = "附件", required = true) @RequestParam("file") MultipartFile[] file,
                                  @ApiParam(name = "type", value = "附件类型", required = true) @RequestParam("type") String type,
                                  @ApiParam(name = "staffId", value = "人员id", required = true) @RequestParam(value = "staffId") String staffId) {
        ResponseDTO responseDTO;
        try {
            List<String> fileIdList = cpStaffInfoService.uploadFile(file, type, staffId);
            responseDTO = WebUtils.createSuccessResponse(fileIdList);
        } catch (Exception ex) {
            logger.error("文件上传出现异常", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "文件上传出现异常");
        }
        return responseDTO;
    }

}
