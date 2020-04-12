package com.inspur.vista.labor.cp.controller.app;

import com.inspur.vista.labor.cp.entity.CPCourtInfoListVO;
import com.inspur.vista.labor.cp.service.CPCourtInfoService;
import com.inspur.vista.labor.cp.service.CPInfoService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Title: CPCourtAppController
 * @Description: 场地控制器
 * @Author: gengpeng
 * @CreateDate: 2020/3/12 9:39
 * @Version: 1.0
 */
@Api(value = "app端场地控制类", tags = {"app端场地接口"})
@RestController
@RequestMapping("/api/app/cp/court")
public class CPCourtAppController {

    private static final Logger logger = LoggerFactory.getLogger(CPCourtAppController.class);

    @Autowired
    private CPCourtInfoService cpCourtInfoService;

    @Autowired
    private CPInfoService cpInfoService;


    /**
     * 查询场地信息
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询场地信息", notes = "场地信息展示不采用分页的方式,加载已经启用的场地,查询条件：type(项目类型)；cpId(文化宫id)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO listCPCourtInfo(@ApiParam(name = "parameters", value = "查询条件：type(项目类型)；cpId(文化宫id)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            List<CPCourtInfoListVO> cpCourtInfoListVOS = cpCourtInfoService.listCanReserveCPCourtInfo(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpCourtInfoListVOS);
        } catch (Exception e) {
            logger.error("查询场地信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询场地信息失败");
        }
        return responseDTO;
    }

    /**
     * 根据文化宫id查询已有的项目类型
     *
     * @param id 文化宫id
     * @return ResponseDTO
     */
    @ApiOperation(value = "根据文化宫id查询已有的项目类型", notes = "")
    @GetMapping(value = "/getCPType/{id}")
    public ResponseDTO<List<Map<String, String>>> getCPType(@ApiParam(name = "id", value = "文化宫id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        List<Map<String, String>> typeList = cpInfoService.listTypeByCpId(id);
        if (null != typeList && (0 != typeList.size())) {
            responseDTO = WebUtils.createSuccessResponse(typeList);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到文化宫项目类型信息", "");
        }
        return responseDTO;
    }
}
