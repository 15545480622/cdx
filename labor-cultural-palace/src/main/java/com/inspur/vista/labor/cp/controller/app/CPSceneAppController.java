package com.inspur.vista.labor.cp.controller.app;

import com.inspur.vista.labor.cp.entity.CPSceneInfoVO;
import com.inspur.vista.labor.cp.service.CPSceneInfoService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Title: CPSceneAppController
 * @Description: 针对App端的场次定义控制器
 * @Author: gengpeng
 * @CreateDate: 2020/3/12 9:16
 * @Version: 1.0
 */
@Api(value = "app端场次控制类", tags = {"app端场次接口"})
@RestController
@RequestMapping("/api/app/cp/scene/")
public class CPSceneAppController {

    private static final Logger logger = LoggerFactory.getLogger(CPSceneAppController.class);

    @Autowired
    private CPSceneInfoService cpSceneInfoService;

    /**
     * 获取场次定义列表
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取场次定义列表", notes = "场次展示不采用分页的方式,通过时间区间展示，查询条件，" +
            "courtId：场地id（必填）；cxBeginDate：开始时间（到天）；cxEndDate结束时间（到天）", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPSceneInfoVO> listCPSceneDefinition(@ApiParam(name = "parameters", value = "查询条件，courtId：场地id（必填）;cxBeginDate：开始时间（到天）；cxEndDate结束时间（到天）", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            List<CPSceneInfoVO> sceneDefinitionList = cpSceneInfoService.listCPScene(parameters);
            responseDTO = WebUtils.createSuccessResponse(sceneDefinitionList);
        } catch (Exception e) {
            logger.error("获取场次定义出现异常", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "获取场次定义出现异常");
        }
        return responseDTO;
    }
}
