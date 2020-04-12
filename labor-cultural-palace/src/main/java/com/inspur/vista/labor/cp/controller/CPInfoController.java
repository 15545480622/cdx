package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPInfoService;
import com.inspur.vista.labor.cp.service.PubFilesService;
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
 * @Title: CPInfoController
 * @Description: 文化宫基本信息控制器
 * @Author: gengpeng
 * @CreateDate: 2019/12/5 16:02
 * @Version: 1.0
 */
@Api(value = "文化宫基本信息控制类", tags = {"文化宫基本信息接口"})
@RequestMapping("/api/cp/info")
@RestController
public class CPInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CPInfoController.class);

    @Autowired
    private CPInfoService cpInfoService;

    @Autowired
    private PubFilesService filesService;

    /**
     * 获取文化宫信息
     *
     * @param id 文化宫id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取文化宫信息", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPInfoVO> getCPInfo(@ApiParam(name = "id", value = "文化宫id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPInfoVO cpInfo = cpInfoService.getCPInfo(id);
        if (null != cpInfo) {
            responseDTO = WebUtils.createSuccessResponse(cpInfo);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到文化宫信息", "");
        }
        return responseDTO;
    }

    /**
     * 保存文化宫信息
     *
     * @param cpInfoEntity 文化宫信息
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存文化宫信息", notes = "")
    @PostMapping(value = "/save")
    public ResponseDTO save(@ApiParam(name = "cpInfoEntity", value = "文化宫信息", required = true) @RequestBody CPInfoEntity cpInfoEntity) {
        ResponseDTO responseDTO;
        try {
            responseDTO = cpInfoService.save(cpInfoEntity);
        } catch (Exception ex) {
            logger.error("保存文化宫信息失败", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "保存文化宫信息失败");
        }
        return responseDTO;
    }

    /**
     * 查询文化宫信息
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询文化宫信息", notes = "分页查询", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPInfoListVO> listCPInfo(@ApiParam(name = "parameters", value = "查询条件，page,pageSize", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            parameters.put("laborId", "O370000000000");
            Page<CPInfoListVO> cpInfoVOPage = cpInfoService.listCPInfo(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpInfoVOPage);
        } catch (Exception e) {
            logger.error("查询文化宫信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询文化宫信息失败");
        }
        return responseDTO;
    }

    /**
     * 申请信息变更
     *
     * @param applyAdd 文化宫信息变更信息
     * @return ResponseDTO
     */
    @ApiOperation(value = "申请信息变更", notes = "")
    @PostMapping(value = "/apply")
    public ResponseDTO apply(@ApiParam(name = "cpInfoEntity", value = "文化宫信息", required = true) @RequestBody CPInfoApplyAdd applyAdd) {
        ResponseDTO responseDTO;
        try {
            AtomicReference<String> msg = new AtomicReference<>("");

            if (StringUtils.isNotBlank(msg.get())) {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
            } else {
                applyAdd.setSubmitter(getUser().getUserId());
                cpInfoService.apply(applyAdd);
                responseDTO = WebUtils.createSuccessResponse("");
            }
        } catch (Exception e) {
            logger.error("新增/修改文化宫信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
        }
        return responseDTO;
    }

    /**
     * 审核
     *
     * @param applyCheck 审核信息
     * @return ResponseDTO
     */
    @ApiOperation(value = "审核", notes = "具有审核权限的人员进行审核", response = ResponseDTO.class)
    @PostMapping(value = "/check")
    public ResponseDTO check(@ApiParam(name = "applyCheck", value = "审核信息", required = true) @RequestBody CPApplyCheck applyCheck) {
        ResponseDTO responseDTO;
        try {
            applyCheck.setHandler(getUser().getUserId());
            applyCheck.setHandleLaborId(getUser().getLaborId());
            cpInfoService.check(applyCheck);
            responseDTO = WebUtils.createSuccessResponse("");
        } catch (Exception e) {
            logger.error("审核失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "审核失败");
        }
        return responseDTO;
    }

    /**
     * 获取文化宫附件列表
     *
     * @param id 驿站id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取文化宫附件", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/files")
    public ResponseDTO getCPFiles(@ApiParam(name = "id", value = "文化宫id", required = true) @RequestParam String id) {
        ResponseDTO responseDTO;
        List<PubFilesVO> cpInfoFiles = cpInfoService.getCPFiles(Long.parseLong(id));
        if (null != cpInfoFiles) {
            responseDTO = WebUtils.createSuccessResponse(cpInfoFiles);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到文化宫附件", "");
        }
        return responseDTO;
    }

    /**
     * 上传文化宫相关的附件
     *
     * @param file 文件
     * @param type 文件类型
     * @return ResponseDTO
     */
    @ApiOperation(value = "上传文化宫相关的附件", notes = "附件类型：A1:工会资产证明;A2:人员编制的批复;A3:工人文化宫标识;A0:其他", response = ResponseDTO.class)
    @PostMapping(value = "/upload")
    public ResponseDTO uploadFile(@RequestParam("file") MultipartFile[] file,
                                  @RequestParam("type") String type,
                                  @RequestParam(value = "cpId", required = false) String cpId) {
        ResponseDTO responseDTO;
        try {
            List<String> idList = cpInfoService.uploadFile(file, type, cpId);
            responseDTO = WebUtils.createSuccessResponse(idList);
        } catch (Exception ex) {
            logger.error("文件上传出现异常", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "文件上传出现异常");
        }
        return responseDTO;
    }


    /**
     * 获取文化宫申请信息
     *
     * @param applyId
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取文化宫申请信息", notes = "")
    @GetMapping(value = "/apply/{applyId}")
    public ResponseDTO<CPInfoVO> getCPInfoApply(@ApiParam(name = "applyId", value = "申请表id", required = true) @PathVariable(value = "applyId") String applyId) {
        ResponseDTO responseDTO;
        CPInfoApplyVO cpInfoApply = cpInfoService.getCPInfoApply(applyId);
        if (null != cpInfoApply) {
            responseDTO = WebUtils.createSuccessResponse(cpInfoApply);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到文化宫申请信息", "");
        }
        return responseDTO;
    }

    /**
     * 删除文化宫
     *
     * @param id 文化宫id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除文化宫", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/delete")
    public ResponseDTO deleteCPHonorInfo(@ApiParam(name = "id", value = "文化宫id", required = true) @RequestParam(value = "id") String id) {

        //执行删除操作
        int result = cpInfoService.removeCPInfoById(id);
        if (result > 0) {
            return WebUtils.createSuccessResponse("删除文化宫成功");
        } else {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "删除文化宫失败");
        }
    }
}
