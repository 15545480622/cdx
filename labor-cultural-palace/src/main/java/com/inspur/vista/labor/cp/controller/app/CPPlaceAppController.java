package com.inspur.vista.labor.cp.controller.app;

import com.inspur.vista.labor.cp.entity.CPInfoVO;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoVO;
import com.inspur.vista.labor.cp.entity.DictItemVO;
import com.inspur.vista.labor.cp.service.CPPlaceInfoService;
import com.inspur.vista.labor.cp.service.DictService;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: CPPlaceAppController
 * @Description: app端场所
 * @Author: gengpeng
 * @CreateDate: 2020/4/2 20:29
 * @Version: 1.0
 */
@Api(value = "app端场所控制类", tags = {"app端场所接口"})
@RestController
@RequestMapping("/api/app/cp/place")
public class CPPlaceAppController {

    private static final Logger logger = LoggerFactory.getLogger(CPPlaceAppController.class);

    @Autowired
    private CPPlaceInfoService placeInfoService;

    @Autowired
    private DictService dictService;


    /**
     * 获取场所信息
     *
     * @param id 场所id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取场所信息", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPInfoVO> getPlaceInfo(@ApiParam(name = "id", value = "场所id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPPlaceInfoVO placeInfoVO = placeInfoService.getCPPlaceInfo(id);
        if (null != placeInfoVO) {
            DictItemVO dictItemVO = dictService.getDictItem(placeInfoVO.getType(), CPConstants.DICT_PROJECT_TYPE);
            placeInfoVO.setTypeName(dictItemVO.getItemValue());
            responseDTO = WebUtils.createSuccessResponse(placeInfoVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到场所信息", "");
        }
        return responseDTO;
    }
}
