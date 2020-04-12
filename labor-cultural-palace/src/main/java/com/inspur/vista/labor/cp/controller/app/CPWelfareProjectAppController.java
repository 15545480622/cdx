package com.inspur.vista.labor.cp.controller.app;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectListVO;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectVO;
import com.inspur.vista.labor.cp.service.CPWelfareProjectService;
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

import java.util.Map;

/**
 * @ClassName: CPWelfareProjectAppController
 * @Description: 公益性服务项目清单控制器
 * @authur: wangxueying01
 * @CreatDate: 2020/4/8 14:35
 */
@Api(value = "app端公益性服务项目清单控制器", tags = {"app端公益性服务项目清单控制器"})
@RestController
@RequestMapping("/api/app/cp/welfare/project")
public class CPWelfareProjectAppController {

    private static final Logger logger = LoggerFactory.getLogger(CPWelfareProjectAppController.class);

    @Autowired
    private CPWelfareProjectService cpWelfareProjectService;

    /**
     * 获取公益性服务项目清单
     *
     * @param id 公益性服务项目清单id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取公益性服务项目清单", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPWelfareProjectVO> getCPWelfareProject(@ApiParam(name = "id", value = "公益性服务项目清单id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPWelfareProjectVO cpWelfareProjectVO = cpWelfareProjectService.getCPWelfareProject(id);
        if (null != cpWelfareProjectVO) {
            responseDTO = WebUtils.createSuccessResponse(cpWelfareProjectVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到公益性服务项目清单", "");
        }
        return responseDTO;
    }

    /**
     * 查询公益性服务项目清单
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询公益性服务项目清单", notes = "查询条件，page,pageSize,cpId(文化宫id)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPWelfareProjectListVO> listCPWelfareProject(@ApiParam(name = "parameters", value = "查询条件，page,pageSize,cpId(文化宫id)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPWelfareProjectListVO> cpWelfareProjectListVOPage = cpWelfareProjectService.listCPWelfareProject(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpWelfareProjectListVOPage);
        } catch (Exception e) {
            logger.error("查询公益性服务项目清单失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询公益性服务项目清单失败");
        }
        return responseDTO;
    }
}
