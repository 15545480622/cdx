package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPAiequipmentInfoEntity;
import com.inspur.vista.labor.cp.entity.CPAiequipmentInfoListVO;
import com.inspur.vista.labor.cp.entity.CPAiequipmentInfoVO;
import com.inspur.vista.labor.cp.service.CPAiequipmentInfoService;
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
 * @Title: CPAiequipmentInfoController
 * @Description: 智能设备信息控制器
 * @Author: liuzq
 * @CreateDate: 2020/03/07 14:37
 * @Version: 1.0
 */
@Api(value = "智能设备信息控制类", tags = {"智能设备信息接口"})
@RestController
@RequestMapping("/api/cp/aiequipment/info")
public class CPAiequipmentInfoController {

	private static final Logger logger = LoggerFactory.getLogger(CPAiequipmentInfoController.class);

	@Autowired
    private CPAiequipmentInfoService cpAiequipmentInfoService;

    /**
     * 获取智能设备信息
     *
     * @param id 智能设备信息id
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取智能设备信息", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPAiequipmentInfoVO> getCPAiequipmentInfo(@ApiParam(name = "id", value = "智能设备信息id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPAiequipmentInfoVO cpAiequipmentInfo = cpAiequipmentInfoService.getCPAiequipmentInfo(id);
        if (null != cpAiequipmentInfo) {
            responseDTO = WebUtils.createSuccessResponse(cpAiequipmentInfo);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到智能设备信息", "");
        }
        return responseDTO;
    }

	/**
     * 保存智能设备信息
     *
     * @param cpAiequipmentInfo 智能设备信息
     * @return ResponseDTO
     */
	@ApiOperation(value = "保存智能设备信息", notes = "", response = ResponseDTO.class)
	@PostMapping(value = "/save")
	public ResponseDTO saveCPAiequipmentInfo(@ApiParam(name = "cpAiequipmentInfo", value = "智能设备信息", required = true) @RequestBody CPAiequipmentInfoEntity cpAiequipmentInfo) {
        ResponseDTO responseDTO;
		try {
			AtomicReference<String> msg = new AtomicReference<>("");


			if (StringUtils.isNotBlank(msg.get())) {
				responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
			} else {
				CPAiequipmentInfoEntity cpAiequipmentInfoEntity = cpAiequipmentInfoService.saveCPAiequipmentInfo(cpAiequipmentInfo);
				responseDTO = WebUtils.createSuccessResponse(cpAiequipmentInfoEntity.getId());
			}
		} catch (Exception e) {
			logger.error("新增/修改智能设备信息失败", e);
			responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
		}
		return responseDTO;
	}

    /**
     * 查询智能设备信息
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
	@ApiOperation(value = "查询智能设备信息", notes = "分页查询", response = ResponseDTO.class)
	@PostMapping(value = "/list")
	public ResponseDTO listCPAiequipmentInfo(@ApiParam(name = "parameters", value = "查询条件，page,pageSize,cpId(文化宫id)", required = true) @RequestBody Map<String, Object> parameters) {
		ResponseDTO responseDTO;
		try {
			Page<CPAiequipmentInfoListVO> cpAiequipmentInfoListVOPage = cpAiequipmentInfoService.listCPAiequipmentInfo(parameters);
			responseDTO = WebUtils.createSuccessResponse(cpAiequipmentInfoListVOPage);
		} catch (Exception e) {
			logger.error("查询智能设备信息失败", e);
			responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询智能设备信息失败");
		}
		return responseDTO;
	}

	/**
	 * 删除智能设备信息
	 *
	 * @param ids 智能设备信息id
	 * @return ResponseDTO
	 */
	@ApiOperation(value = "删除智能设备信息", notes = "", response = ResponseDTO.class)
	@GetMapping(value = "/delete")
	public ResponseDTO deleteCPAiequipmentInfo(@ApiParam(name = "ids", value = "智能设备信息id", required = true) @RequestParam(value = "ids") String ids) {
		ResponseDTO responseDTO;
		if (StringUtils.isNotBlank(ids)) {
			String[] idArr = ids.split(",");
			if (idArr.length > 0) {
				int result = cpAiequipmentInfoService.removeCPAiequipmentInfoById(idArr);
				if (result > 0) {
					responseDTO = WebUtils.createSuccessResponse("智能设备信息删除成功");
				} else {
					responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "智能设备信息删除失败");
				}
			} else {
				responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
			}
		} else {
			responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
		}
		return responseDTO;
	}
}
