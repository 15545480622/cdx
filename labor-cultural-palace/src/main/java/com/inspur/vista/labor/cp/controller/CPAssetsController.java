package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAssetsInfoEntity;
import com.inspur.vista.labor.cp.entity.CPAssetsInfoListVO;
import com.inspur.vista.labor.cp.entity.CPAssetsInfoVO;
import com.inspur.vista.labor.cp.service.CPAssetsService;
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

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title: CPAssetsController
 * @Description: 文化宫资产控制器
 * @Author: liuzq
 * @CreateDate: 2019/12/10 10:20
 * @Version: 1.0
 */
@Api(value = "资产控制器", tags = {"文化宫资产接口"})
@RestController
@RequestMapping("/api/cp/assets")
public class CPAssetsController {

    private static final Logger logger = LoggerFactory.getLogger(CPAssetsController.class);

    @Autowired
    private CPAssetsService cpAssetsService;

    /**
     * 获取文化宫资产
     *
     * @param id 文化宫资产id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取文化宫资产", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPAssetsInfoVO> getCPAssets(@ApiParam(name = "id", value = "文化宫资产id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPAssetsInfoVO cpAssetsInfoVO = cpAssetsService.getCPAssets(id);
        if (null != cpAssetsInfoVO) {
            responseDTO = WebUtils.createSuccessResponse(cpAssetsInfoVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到文化宫资产", "");
        }
        return responseDTO;
    }

    /**
     * 保存文化宫资产
     *
     * @param cpAssets 文化宫资产
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存文化宫资产", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveCPAssets(@ApiParam(name = "cpAssets", value = "文化宫资产", required = true) @RequestBody CPAssetsInfoEntity cpAssets) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (StringUtils.isBlank(cpAssets.getCpId())) {
            msg.set(msg.get() + "文化宫id不能为空" + ",");
        }
        if (StringUtils.isBlank(cpAssets.getYear())) {
            msg.set(msg.get() + "年度不能为空" + ",");
        }
        if (StringUtils.isBlank(cpAssets.getId())) {
            CPAssetsInfoVO assetsInfoVO = cpAssetsService.getCPAssetsByYearAndCPId(cpAssets.getCpId(), cpAssets.getYear());
            if (null != assetsInfoVO) {
                msg.set(msg.get() + cpAssets.getYear() + "年度已添加，请勿重复添加" + ",");
            }
        }
        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                cpAssetsService.saveCPAssets(cpAssets);
                responseDTO = WebUtils.createSuccessResponse("");
            } catch (Exception e) {
                logger.error("新增/修改文化宫资产失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }

    /**
     * 查询文化宫资产
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询文化宫资产", notes = "资产信息展示采用分页的方式,查询条件：assetsName(资产名称)；cpId(文化宫id)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPAssetsInfoListVO> listCPAssets(@ApiParam(name = "parameters", value = "查询条件：assetsName(资产名称)；cpId(文化宫id)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPAssetsInfoListVO> cpAssetsListVOPage = cpAssetsService.listCPAssets(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpAssetsListVOPage);
        } catch (Exception e) {
            logger.error("查询文化宫资产失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询文化宫资产失败");
        }
        return responseDTO;
    }

}
