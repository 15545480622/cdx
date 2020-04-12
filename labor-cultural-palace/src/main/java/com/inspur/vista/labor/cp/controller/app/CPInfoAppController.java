package com.inspur.vista.labor.cp.controller.app;

import com.inspur.vista.labor.cp.entity.CPInfoAppVO;
import com.inspur.vista.labor.cp.entity.CPInfoListVO;
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
 * @Title: CPInfoAppController
 * @Description: 文化宫信息控制器
 * @Author: gengpeng
 * @CreateDate: 2020/3/12 13:48
 * @Version: 1.0
 */
@Api(value = "app端文化宫信息控制类", tags = {"app端文化宫信息接口"})
@RestController
@RequestMapping("/api/app/cp/info")
public class CPInfoAppController {

    private static final Logger logger = LoggerFactory.getLogger(CPInfoAppController.class);

    @Autowired
    private CPInfoService cpInfoService;


    /**
     * 查询文化宫信息
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "根据区划、项目类型查询文化宫信息", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO listCPInfoByDistrict(@ApiParam(name = "parameters", value = "条件，district:区划编码;projectTypes:项目类型;order:排序方式，暂时默认距离（distance）;curLng:当前经度;curLat:当前纬度", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            List<CPInfoListVO> cpInfoVOPage = cpInfoService.listCPInfoByCantOrType(parameters);
            for (CPInfoListVO cpInfoListVO : cpInfoVOPage) {
                if (0 == cpInfoListVO.getIsAllDay()) {
                    String time = "";
                    String[] businessTime = cpInfoListVO.getBusinessTime().split(",");
                    for (String t : businessTime) {
                        if (!"".equals(t)) {
                            time += t + ",";
                        }
                    }
                    cpInfoListVO.setBusinessTime(time.substring(0, time.length() - 1));
                }
            }
            responseDTO = WebUtils.createSuccessResponse(cpInfoVOPage);
        } catch (Exception e) {
            logger.error("查询文化宫信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询文化宫信息失败");
        }
        return responseDTO;
    }

    /**
     * 获取文化宫信息
     *
     * @param id 文化宫id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取文化宫信息", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPInfoAppVO> getCPInfo(@ApiParam(name = "id", value = "文化宫id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPInfoAppVO cpInfo = cpInfoService.getAppCPInfo(id);
        if (null != cpInfo) {
            if (0 == cpInfo.getIsAllDay()) {
                String time = "";
                String[] businessTime = cpInfo.getBusinessTime().split(",");
                for (String t : businessTime) {
                    if (!"".equals(t)) {
                        time += t + ",";
                    }
                }
                cpInfo.setBusinessTime(time.substring(0, time.length() - 1));
            }
            responseDTO = WebUtils.createSuccessResponse(cpInfo);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到文化宫信息", "");
        }
        return responseDTO;
    }

}
