package com.inspur.vista.labor.cp.controller;

import com.inspur.vista.labor.cp.entity.PubFilesVO;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: PubFilesController
 * @Description: 附件控制器
 * @Author: liuzq
 * @CreateDate: 2019/12/11 15:04
 * @Version: 1.0
 */
@Api(value = "附件控制类", tags = {"附件接口"})
@RestController
@RequestMapping("/api/pub/files")
public class PubFilesController {

    private static final Logger logger = LoggerFactory.getLogger(PubFilesController.class);

    @Autowired
    private PubFilesService pubFilesService;


    /**
     * 获取附件
     *
     * @param id 附件id
     * @return ResponseDTO
     */
    @ApiOperation(value = "通过附件id获取附件信息", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseDTO getPubFiles(@ApiParam(name = "id", value = "附件id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        PubFilesVO pubFiles = pubFilesService.getPubFiles(id);
        if (null != pubFiles) {
            responseDTO = WebUtils.createSuccessResponse(pubFiles);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到附件", "");
        }
        return responseDTO;
    }

    /**
     * 通过类型获取附件信息
     *
     * @param bsnId 业务id
     * @param type  类型
     * @return ResponseDTO
     */
    @ApiOperation(value = "通过类型获取附件信息", notes = "")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseDTO getPubFiles(@RequestParam String bsnId, @RequestParam Integer type) {
        ResponseDTO responseDTO;
        Map param = new HashMap();
        param.put("bsnId", bsnId);
        param.put("type", type);
        List<PubFilesVO> pubFiles = pubFilesService.listPubFiles(param);
        if (null != pubFiles) {
            responseDTO = WebUtils.createSuccessResponse(pubFiles);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到附件", "");
        }
        return responseDTO;
    }

    /**
     * 查询附件
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取附件列表", notes = "参数：bsnId（业务id）；type(附件类型)")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<PubFilesVO> listPubFiles(@ApiParam(name = "parameters", value = "参数见描述", required = true) @RequestBody Map<String, Object> parameters) {
        return pubFilesService.listPubFiles(parameters);
    }

    /**
     * 删除附件
     *
     * @param ids 附件id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除附件", notes = "")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseDTO deletePubFiles(@ApiParam(name = "ids", value = "附件id，多个用逗号分隔", required = true) @RequestParam(value = "ids") String ids) {
        ResponseDTO responseDTO;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr.length > 0) {
                int result = pubFilesService.removePubFilesById(idArr);
                if (result > 0) {
                    responseDTO = WebUtils.createSuccessResponse("附件删除成功");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "附件删除失败");
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
