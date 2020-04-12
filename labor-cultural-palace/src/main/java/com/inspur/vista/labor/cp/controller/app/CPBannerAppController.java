package com.inspur.vista.labor.cp.controller.app;

import com.inspur.vista.labor.cp.entity.CPBannerListVO;
import com.inspur.vista.labor.cp.service.CPBannerService;
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

import java.util.List;

/**
 * @ClassName: CPBannerAppController
 * @Description: banner控制器
 * @authur: wangxueying01
 * @CreatDate: 2020/4/2 14:41
 */
@Api(value = "app端banner图控制类", tags = {"app端banner图接口"})
@RestController
@RequestMapping("/api/app/cp/banner")
public class CPBannerAppController {

    private static final Logger logger = LoggerFactory.getLogger(CPBannerAppController.class);

    @Autowired
    private CPBannerService cpBannerService;

    /**
     * 根据文化宫id查询banner图
     *
     * @param id 文化宫id
     * @return ResponseDTO
     */
    @ApiOperation(value = "根据文化宫id查询banner图", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/getBanner/{id}")
    public ResponseDTO listCPBanner(@ApiParam(name = "id", value = "文化宫id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        try {
           List<CPBannerListVO> cpBannerListVOList = cpBannerService.listCPBannerInfo(id);
            responseDTO = WebUtils.createSuccessResponse(cpBannerListVOList);
        } catch (Exception e) {
            logger.error("查询banner图失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询banner图失败");
        }
        return responseDTO;
    }
}
