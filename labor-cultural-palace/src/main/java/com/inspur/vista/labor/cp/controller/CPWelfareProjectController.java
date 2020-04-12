package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectEntity;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectListVO;
import com.inspur.vista.labor.cp.entity.CPWelfareProjectVO;
import com.inspur.vista.labor.cp.service.CPWelfareProjectService;
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
 * @ClassName: CPWelfareProjectController
 * @Description: 公益性服务项目清单控制器
 * @authur: wangxueying01
 * @CreatDate: 2020/4/8 10:59
 */
@Api(value = "公益性服务项目清单控制器", tags = {"公益性服务项目清单控制器"})
@RestController
@RequestMapping("/api/cp/welfare/project")
public class CPWelfareProjectController {

    private static final Logger logger = LoggerFactory.getLogger(CPWelfareProjectController.class);

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
     * 保存公益性服务项目清单
     *
     * @param cpWelfareProjectEntity 公益性服务项目清单
     * @return ResponseDTO
     */
    @ApiOperation(value = "保存公益性服务项目清单", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveCPWelfareProject(@ApiParam(name = "cpWelfareProjectEntity", value = "公益性服务项目清单", required = true) @RequestBody CPWelfareProjectEntity cpWelfareProjectEntity) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (StringUtils.isBlank(cpWelfareProjectEntity.getCpId())) {
            msg.set(msg.get() + "文化宫id不能为空" + ",");
        }
        if (StringUtils.isBlank(cpWelfareProjectEntity.getName())) {
            msg.set(msg.get() + "名称不能为空" + ",");
        }
        if (null == cpWelfareProjectEntity.getType()) {
            msg.set(msg.get() + "类型不能为空" + ",");
        }
        if (StringUtils.isBlank(cpWelfareProjectEntity.getContent())) {
            msg.set(msg.get() + "内容不能为空" + ",");
        }

        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                cpWelfareProjectService.saveCPWelfareProject(cpWelfareProjectEntity);
                responseDTO = WebUtils.createSuccessResponse("");
            } catch (Exception e) {
                logger.error("新增/修改公益性服务项目清单失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }

    /**
     * 查询公益性服务项目清单
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询公益性服务项目清单", notes = "查询条件，page,pageSize,cpId(文化宫id，必填),name(名称，模糊检索),type(类型：1.月度；2.年度)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPWelfareProjectListVO> listCPWelfareProject(@ApiParam(name = "parameters", value = "查询条件，page,pageSize,cpId(文化宫id，必填),name(名称，模糊检索),type(类型：1.月度；2.年度)", required = true) @RequestBody Map<String, Object> parameters) {
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

    /**
     * 删除公益性服务项目清单
     *
     * @param ids 公益性服务项目清单id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除公益性服务项目清单", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/delete")
    public ResponseDTO deleteCPRegulation(@ApiParam(name = "ids", value = "公益性服务项目清单id", required = true) @RequestParam(value = "ids") String ids) {
        ResponseDTO responseDTO;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr.length > 0) {
                int result = cpWelfareProjectService.removeCPWelfareProjectById(idArr);
                if (result > 0) {
                    responseDTO = WebUtils.createSuccessResponse("公益性服务项目清单删除成功");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "公益性服务项目清单删除失败");
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
