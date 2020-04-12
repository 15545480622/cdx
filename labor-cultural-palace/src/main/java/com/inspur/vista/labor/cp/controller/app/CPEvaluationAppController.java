package com.inspur.vista.labor.cp.controller.app;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.controller.BaseController;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPEvaluationService;
import com.inspur.vista.labor.cp.service.CPReserveInfoService;
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
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: CPEvaluationAppController
 * @Description: 评价记录控制器
 * @authur: wangxueying01
 * @CreatDate: 2019/12/23 16:36
 */
@Api(value = "app评价记录控制类", tags = {"app端评价记录接口"})
@RestController
@RequestMapping("/api/app/cp/evaluation")
public class CPEvaluationAppController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CPEvaluationAppController.class);

    @Autowired
    private CPEvaluationService cpEvaluationService;

    @Autowired
    private CPReserveInfoService reserveInfoService;

    /**
     * 新增评价记录
     *
     * @param cpPlaceEvaluation 文化宫评价记录
     * @return ResponseDTO
     */
    @ApiOperation(value = "新增文化宫评价记录", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveCPPlaceEvaluation(@ApiParam(name = "cpPlaceEvaluation", value = "文化宫评价记录", required = true) @RequestBody CPEvaluationEntity cpPlaceEvaluation,
                                             @ApiIgnore @RequestAttribute("userInfo") AppUserInfo userInfo) {
        ResponseDTO responseDTO;
        cpPlaceEvaluation.setUserCode(userInfo.getUserCode());
        AtomicReference<String> msg = new AtomicReference<>("");
        if (StringUtils.isAllBlank(cpPlaceEvaluation.getCpId(), cpPlaceEvaluation.getReserveId())) {
            msg.set("文化宫id和预约记录id不能同时为空" + ",");
        }
        if (null == cpPlaceEvaluation.getStar()) {
            msg.set("整体星级不能为空" + ",");
        }
        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                if (StringUtils.isNotBlank(cpPlaceEvaluation.getReserveId())) {
                    CPReserveInfoVO reserveInfoVO = reserveInfoService.getCPReserveInfo(cpPlaceEvaluation.getReserveId());
                    if (null != reserveInfoVO) {
                        cpPlaceEvaluation.setCpId(reserveInfoVO.getCpId());
                    }
                }
                CPEvaluationEntity cpEvaluationEntity = cpEvaluationService.saveCPEvaluation(cpPlaceEvaluation);
                responseDTO = WebUtils.createSuccessResponse(cpEvaluationEntity.getId());
            } catch (Exception e) {
                logger.error("新增/修改文化宫评价记录失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }

    /**
     * 查询评价记录
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询文化宫评价记录", notes = "分页查询", response = ResponseDTO.class)
    @PostMapping(value = "/place/list")
    public ResponseDTO listCPPlaceEvaluation(@ApiParam(name = "parameters", value = "查询条件，page,pageSize,cpId(文化宫id)", required = true) @RequestBody Map<String, Object> parameters) {
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
     * 获取文化宫平均星级
     *
     * @param cpId 文化宫id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取文化宫平均星级", notes = "")
    @GetMapping(value = "/{cpId}")
    public ResponseDTO<CPStarVO> getCPCourtInfo(@ApiParam(name = "cpId", value = "文化宫id", required = true) @PathVariable(value = "cpId") String cpId) {
        ResponseDTO responseDTO;
        CPStarVO cpStarVO = cpEvaluationService.getCPStar(cpId);
        if (null != cpStarVO) {
            responseDTO = WebUtils.createSuccessResponse(cpStarVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到文化宫星级信息", "");
        }
        return responseDTO;
    }
}
