package com.inspur.vista.labor.cp.controller.app;

import com.inspur.vista.labor.cp.entity.CPUsageInfoEntity;
import com.inspur.vista.labor.cp.entity.CPUsageInfoListVO;
import com.inspur.vista.labor.cp.service.CPUsageInfoService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: CPUsageAppController
 * @Description: 场所使用记录控制器
 * @authur: wangxueying01
 * @CreatDate: 2020/3/13 10:12
 */
@Api(value = "app端场所使用记录控制类", tags = {"app端场所使用记录接口"})
@RestController
@RequestMapping("/api/app/cp/usage")
public class CPUsageAppController {

    private static final Logger logger = LoggerFactory.getLogger(CPUsageAppController.class);

    @Autowired
    private CPUsageInfoService cpUsageInfoService;

    /**
     * 查询场所使用记录
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询场所使用记录", notes = "场地信息展示不采用分页的方式,查询条件：place_id(场所id)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO listCPUsageInfo(@ApiParam(name = "parameters", value = "查询条件：place_id(场所id)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            List<CPUsageInfoListVO> cpUsageInfoListVOList = cpUsageInfoService.listCPUsageInfo(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpUsageInfoListVOList);
        } catch (Exception e) {
            logger.error("查询场所使用记录失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询场所使用记录失败");
        }
        return responseDTO;
    }

    /**
     * 新增场所使用记录
     *
     * @param cpUsageInfoEntity 场所使用记录
     * @return ResponseDTO
     */
    @ApiIgnore
    @ApiOperation(value = "保存场所使用记录", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveCPUsageInfo(@ApiParam(name = "cpUsageInfoEntity", value = "场所使用记录", required = true) @RequestBody CPUsageInfoEntity cpUsageInfoEntity) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (StringUtils.isBlank(cpUsageInfoEntity.getPlaceId())) {
            msg.set("场所id不能为空" + ",");
        }

        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                CPUsageInfoEntity cpUsageInfo = cpUsageInfoService.saveCPUsageInfo(cpUsageInfoEntity);
                responseDTO = WebUtils.createSuccessResponse(cpUsageInfo.getId());
            } catch (Exception e) {
                logger.error("新增/修改场所使用记录", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }
}
