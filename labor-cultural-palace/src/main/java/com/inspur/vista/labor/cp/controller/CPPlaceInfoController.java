package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoEntity;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoListVO;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoVO;
import com.inspur.vista.labor.cp.service.CPPlaceInfoService;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title: CPPlaceInfoController
 * @Description: 场所信息控制器
 * @Author: wangxueying01
 * @CreateDate: 2020/03/04 16:35
 * @Version: 1.0
 */
@Api(value = "场所信息控制类", tags = {"场所信息接口"})
@RestController
@RequestMapping("/api/cp/place/info")
public class CPPlaceInfoController {

    private static final Logger logger = LoggerFactory.getLogger(CPPlaceInfoController.class);

    @Autowired
    private CPPlaceInfoService cpPlaceInfoService;

    /**
     * 获取场所信息
     *
     * @param id 场所信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取场所信息", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPPlaceInfoVO> getCPPlaceInfo(@ApiParam(name = "id", value = "场所信息id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPPlaceInfoVO cpPlaceInfo = cpPlaceInfoService.getCPPlaceInfo(id);
        if (null != cpPlaceInfo) {
            responseDTO = WebUtils.createSuccessResponse(cpPlaceInfo);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到场所信息", "");
        }
        return responseDTO;
    }

    /**
     * 保存场所信息
     *
     * @param cpPlaceInfo 场所信息
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存场所信息", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveCPPlaceInfo(@ApiParam(name = "cpPlaceInfo", value = "场所信息", required = true) @RequestBody CPPlaceInfoEntity cpPlaceInfo) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (StringUtils.isBlank(cpPlaceInfo.getCpId())) {
            msg.set("文化宫id不能为空" + ",");
        }
        if (StringUtils.isBlank(cpPlaceInfo.getName())) {
            msg.set("场所名称不能为空" + ",");
        }

        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                CPPlaceInfoEntity cpPlaceInfoEntity = cpPlaceInfoService.saveCPPlaceInfo(cpPlaceInfo);
                responseDTO = WebUtils.createSuccessResponse(cpPlaceInfoEntity.getId());
            } catch (Exception e) {
                logger.error("新增/修改场所信息失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }

    /**
     * 查询场所信息
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询场所信息", notes = "查询条件，page,pageSize,cpId(文化宫id，必填),name(场所名称，模糊检索),type(类型)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPPlaceInfoListVO> listCPPlaceInfo(@ApiParam(name = "parameters", value = "查询条件，详情见接口描述", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPPlaceInfoListVO> cpPlaceInfoListVOPage = cpPlaceInfoService.listCPPlaceInfo(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpPlaceInfoListVOPage);
        } catch (Exception e) {
            logger.error("查询场所信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询场所信息失败");
        }
        return responseDTO;
    }

    /**
     * 删除场所信息
     *
     * @param ids 场所信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除场所信息", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/delete")
    public ResponseDTO deleteCPPlaceInfo(@ApiParam(name = "ids", value = "场所信息id", required = true) @RequestParam(value = "ids") String ids) {
        ResponseDTO responseDTO;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr.length > 0) {
                String modifier = "";
                int result = cpPlaceInfoService.removeCPPlaceInfoById(idArr, modifier);
                if (result > 0) {
                    responseDTO = WebUtils.createSuccessResponse("场所信息删除成功");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "场所信息删除失败");
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
     * 生成场所二维码
     *
     * @param placeId 场所Id
     * @return ResponseDTO
     */
    @ApiOperation(value = "生成场所二维码", notes = "返回二维码信息包含二维码类型、场所id、场所名称")
    @GetMapping(value = "/qrcode")
    public void generateQRCode(@ApiParam(name = "placeId", value = "场所id", required = true) @RequestParam(value = "placeId") String placeId,
                               HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setHeader("Content-Disposition", "attachment;filename=场所二维码" + placeId + ".jpg");
        response.setContentType("image/jpeg");
        try {
            cpPlaceInfoService.generateQRCode(placeId, response);
        } catch (IOException e) {
            logger.error("生成二维码失败", e);
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
                                  @ApiParam(name = "placeId", value = "场所id", required = true) @RequestParam(value = "cpId") String placeId
    ) {
        ResponseDTO responseDTO;
        try {
            List<String> fileIdList = cpPlaceInfoService.uploadFile(file, type, placeId);
            responseDTO = WebUtils.createSuccessResponse(fileIdList);
        } catch (Exception ex) {
            logger.error("文件上传出现异常", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "文件上传出现异常");
        }
        return responseDTO;
    }
}
