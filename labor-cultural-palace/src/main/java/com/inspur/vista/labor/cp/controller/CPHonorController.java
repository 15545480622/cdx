package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPHonorEntity;
import com.inspur.vista.labor.cp.entity.CPHonorInfoVO;
import com.inspur.vista.labor.cp.entity.CPHonorListVO;
import com.inspur.vista.labor.cp.service.CPHonorService;
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
 * @ClassName: CPHonorController
 * @Description: 文化宫荣誉信息控制器
 * @authur: wangxueying01
 * @CreatDate: 2019/12/23 8:48
 */
@Api(value = "荣誉信息控制器", tags = {"荣誉信息接口"})
@RestController
@RequestMapping("/api/cp/honor")
public class CPHonorController {

    private static final Logger logger = LoggerFactory.getLogger(CPHonorController.class);

    @Autowired
    private CPHonorService cpHonorService;

    /**
     * 获取荣誉信息
     *
     * @param id 荣誉信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取荣誉信息", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPHonorInfoVO> getCPHonorInfo(@ApiParam(name = "id", value = "荣誉信息id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPHonorInfoVO cpHonorVO = cpHonorService.getCPHonorInfo(id);
        if (null != cpHonorVO) {
            responseDTO = WebUtils.createSuccessResponse(cpHonorVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到荣誉信息", "");
        }
        return responseDTO;
    }

    /**
     * 保存荣誉信息
     *
     * @param cpHonorEntity 荣誉信息
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存荣誉信息", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveCPHonorInfo(@ApiParam(name = "cpHonorEntity", value = "荣誉信息", required = true) @RequestBody CPHonorEntity cpHonorEntity) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (StringUtils.isBlank(cpHonorEntity.getCpId())) {
            msg.set(msg.get() + "文化宫id不能为空" + ",");
        }
        if (StringUtils.isBlank(cpHonorEntity.getHonorName())) {
            msg.set(msg.get() + "荣誉获得时间不能为空" + ",");
        }
        if (null == cpHonorEntity.getHonorLevel()) {
            msg.set(msg.get() + "荣誉级别不能为空" + ",");
        }
        if (StringUtils.isBlank(cpHonorEntity.getHonorName())) {
            msg.set(msg.get() + "荣誉名称不能为空" + ",");
        }

        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                CPHonorEntity cpHonorInfo = cpHonorService.saveHonorInfo(cpHonorEntity);
                responseDTO = WebUtils.createSuccessResponse(cpHonorInfo.getId());
            } catch (Exception e) {
                logger.error("新增/修改荣誉信息失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }

    /**
     * 查询荣誉信息
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询荣誉信息", notes = "荣誉信息展示采用分页的方式,查询条件：page;pageSize;cpId(文化宫id);honorName(荣誉名称)；honorTime(荣誉获得年份)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPHonorListVO> listCPHonorInfo(@ApiParam(name = "parameters", value = "查询条件：cpId(文化宫id);honorName(荣誉名称)；honorTime(荣誉获得年份)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPHonorListVO> cpHonorListVOPage = cpHonorService.listCPHonorInfo(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpHonorListVOPage);
        } catch (Exception e) {
            logger.error("查询荣誉信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询荣誉信息失败");
        }
        return responseDTO;
    }

    /**
     * 删除荣誉信息
     *
     * @param ids 荣誉信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除荣誉信息", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/delete")
    public ResponseDTO deleteCPHonorInfo(@ApiParam(name = "ids", value = "荣誉信息id", required = true) @RequestParam(value = "ids") String ids) {

        //验证输入参数
        if (StringUtils.isBlank((ids))) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
        }
        String[] idArr = ids.split(",");
        if (idArr.length <= 0) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
        }

        //执行删除操作
        int result = cpHonorService.removeCPHonorInfoById(idArr);
        if (result > 0) {
            return WebUtils.createSuccessResponse("荣誉信息删除成功");
        } else {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "荣誉信息删除失败");
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
                                  @ApiParam(name = "honorId", value = "荣誉id", required = true) @RequestParam(value = "cpId") String honorId
    ) {
        ResponseDTO responseDTO;
        try {
            List<String> fileIdList = cpHonorService.uploadFile(file, type, honorId);
            responseDTO = WebUtils.createSuccessResponse(fileIdList);
        } catch (Exception ex) {
            logger.error("文件上传出现异常", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "文件上传出现异常");
        }
        return responseDTO;
    }
}
