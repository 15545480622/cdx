package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPEvaluationListVO;
import com.inspur.vista.labor.cp.entity.CPPlaceStarListVO;
import com.inspur.vista.labor.cp.service.CPEvaluationService;
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

import java.util.Map;

/**
 * @Title: CPEvaluationController
 * @Description: 评价记录控制器
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:31
 * @Version: 1.0
 */
@Api(value = "评价记录控制类", tags = {"评价记录接口"})
@RestController
@RequestMapping("/api/cp/evaluation")
public class CPEvaluationController {

    private static final Logger logger = LoggerFactory.getLogger(CPEvaluationController.class);

    @Autowired
    private CPEvaluationService cpEvaluationService;


    /**
     * 查询评价记录
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询文化宫评价记录", notes = "分页查询", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPEvaluationListVO> listCPPlaceEvaluation(@ApiParam(name = "parameters", value = "查询条件，page,pageSize,cpId(文化宫id)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPEvaluationListVO> cpPlaceEvaluationListVOPage = cpEvaluationService.listCPEvaluation(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpPlaceEvaluationListVOPage);
        } catch (Exception e) {
            logger.error("查询文化宫评价记录失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询文化宫评价记录失败");
        }
        return responseDTO;
    }

    /**
     * 评价星级排名
     *
     * @param parameters
     * @return
     */
    @ApiOperation(value = "查询评价星级排名", notes = "分页查询，此功能为管理单位使用,排名种类:", response = ResponseDTO.class)
    @PostMapping(value = "/star/list")
    public ResponseDTO<CPPlaceStarListVO> listCPPlaceStar(@ApiParam(name = "parameters", value = "查询条件，page,pageSize,starSpecies(排名种类)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPPlaceStarListVO> cpPlaceStarListVOPage = cpEvaluationService.listCPStar(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpPlaceStarListVOPage);
        } catch (Exception e) {
            logger.error("查询文化宫评价星级排名失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询评价星级排名失败");
        }
        return responseDTO;
    }
}
