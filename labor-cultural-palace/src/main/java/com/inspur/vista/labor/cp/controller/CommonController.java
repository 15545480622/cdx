package com.inspur.vista.labor.cp.controller;

import com.inspur.vista.labor.cp.entity.CantVO;
import com.inspur.vista.labor.cp.entity.DictItemVO;
import com.inspur.vista.labor.cp.entity.TreeDataVO;
import com.inspur.vista.labor.cp.service.CantService;
import com.inspur.vista.labor.cp.service.DictService;
import com.inspur.vista.labor.cp.service.OrganService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import com.inspur.vista.labor.uac.common.UacException;
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
 * @Title: CommonController
 * @Description: 公共接口控制器
 * @Author: gengpeng
 * @CreateDate: 2019/9/16 11:25
 * @Version: 1.0
 */
@Api(value = "公共接口控制类", tags = {"公共接口接口"})
@RestController
@RequestMapping("/api/common")
public class CommonController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CantService cantService;

    @Autowired
    private OrganService organService;

    @Autowired
    private DictService dictService;

    /**
     * @param superCode 区划编码
     * @Title:getCant
     * @Description: 根据区划码获取下级区划
     * @return：返回会员信息
     */
    @ApiOperation(value = "根据区划码获取下级区划", notes = "")
    @GetMapping(value = "/cant")
    public ResponseDTO getCant(@ApiParam(name = "superCode", value = "上级区划编码", required = true) @RequestParam("superCode") String superCode) {
        List<CantVO> resultMapList = cantService.selectDirectCant(superCode);
        return WebUtils.createSuccessResponse(resultMapList);
    }

    /**
     * @param cantName 区划名称
     * @Title:getCant
     * @Description: 根据区划名称获取区划编码
     * @return：返回区划编码
     */
    @ApiOperation(value = "根据区划名称获取区划编码", notes = "")
    @GetMapping(value = "/cant/cant_name")
    public ResponseDTO getCantCode(@ApiParam(name = "cantName", value = "区划名称", required = true) @RequestParam("cantName") String cantName) {
        String cantCode = cantService.selectCantCodeByCodeName(cantName);
        return WebUtils.createSuccessResponse(cantCode);
    }

    /**
     * 获取组织树
     *
     * @param organId
     * @return
     */
    @ApiOperation(value = "获取组织树", notes = "")
    @GetMapping(value = "/treeData")
    public ResponseDTO treeData(@ApiParam(name = "organId", value = "组织id", required = true) @RequestParam(value = "organId") String organId) {
        List<TreeDataVO> treeDataVOList = organService.getOrganTree(organId);
        return WebUtils.createSuccessResponse(treeDataVOList);
    }

    /**
     * 字典项获取接口
     *
     * @param body {"dict_codes": ["POST_TYPE","MEMBER_STATUS","SEX_TYPE"]}
     * @return
     */
    @ApiOperation(value = "获取字典项", notes = "")
    @PostMapping(path = "/dict/list")
    public ResponseDTO getDict(@ApiParam(name = "body", value = "形如{\"dict_codes\": [\"POST_TYPE\",\"MEMBER_STATUS\",\"SEX_TYPE\"]}", required = true) @RequestBody Map<String, Object> body) {
        ResponseDTO responseDTO;
        try {
            List<String> codes = (List<String>) body.get("dict_codes");
            List<DictItemVO> dictItemList = dictService.listDictItem(codes);
            responseDTO = WebUtils.createSuccessResponse(dictItemList);
        } catch (UacException e) {
            logger.error("getDictApi error", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_CODE_ERROR, e.getMessage());

        }
        return responseDTO;
    }
}
