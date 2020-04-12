package com.inspur.vista.labor.cp.controller.app;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPProfessionalTalentListVO;
import com.inspur.vista.labor.cp.entity.CPStaffInfoListVO;
import com.inspur.vista.labor.cp.service.CPStaffInfoService;
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
 * @ClassName: CPTalentAppController
 * @Description: app端专业人才
 * @authur: wangxueying01
 * @CreatDate: 2020/4/4 10:36
 */
@Api(value = "app端专业人才控制类", tags = {"app端专业人才接口"})
@RestController
@RequestMapping("/api/app/cp/talent")
public class CPTalentAppController {

    private static final Logger logger = LoggerFactory.getLogger(CPTalentAppController.class);

    @Autowired
    private CPStaffInfoService staffInfoService;

    /**
     * 查询文化宫下专业人才信息
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询文化宫下专业人才信息", notes = "查询条件：page，pageSize，cpId(文化宫id),name(姓名);idcard(身份证号);employmentType(用工类别：1.编制、2.外聘、3.合同制);isStaffing(是否在编:1.是 2.否)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPStaffInfoListVO> listCPProfessionalTalent(@ApiParam(name = "parameters", value = "参数，见描述", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            parameters.put("isProfessional", 1);
            Page<CPStaffInfoListVO> cpProfessionalTalentListVOPage = staffInfoService.listCPStaffInfo(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpProfessionalTalentListVOPage);
        } catch (Exception e) {
            logger.error("查询专业人才信息失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询专业人才信息失败");
        }
        return responseDTO;
    }

    /**
     * 查询场地下专业人才信息
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询场地下专业人才信息", notes = "查询条件：page，pageSize，cpId(文化宫id),courtId(场地id)", response = ResponseDTO.class)
    @PostMapping(value = "/listByCourtId")
    public ResponseDTO<CPProfessionalTalentListVO> listCPProfessionalTalentByCourtId(@ApiParam(name = "parameters", value = "参数，见描述", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPStaffInfoListVO> cpProfessionalTalentListVOPage = staffInfoService.listCPTalentByCourtId(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpProfessionalTalentListVOPage);
        } catch (Exception e) {
            logger.error("查询场地下专业人才信息", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询场地下专业人才信息");
        }
        return responseDTO;
    }

}
