package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPFundsMonthDetailVO;
import com.inspur.vista.labor.cp.entity.CPFundsMonthEntity;
import com.inspur.vista.labor.cp.entity.CPFundsMonthListVO;
import com.inspur.vista.labor.cp.service.CPFundsMonthService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: CPFundsMonthController
 * @Description: 月度经费收支控制器
 * @authur: wangxueying01
 * @CreatDate: 2020/1/13 8:46
 */
@RestController
@RequestMapping("/api/cp/funds/month")
public class CPFundsMonthController {

    private static final Logger logger = LoggerFactory.getLogger(CPFundsMonthController.class);

    @Autowired
    private CPFundsMonthService cpFundsMonthService;

    /**
     * 获取月度经费收支
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPFundsMonthDetailVO> getCPFundsMonth(@PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPFundsMonthDetailVO cpFundsMonthDetailVO = cpFundsMonthService.getCPFundsMonth(Long.valueOf(id));
        if (null != cpFundsMonthDetailVO) {
            responseDTO = WebUtils.createSuccessResponse(cpFundsMonthDetailVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到月度经费收支", "");
        }
        return responseDTO;
    }

    /**
     * 查询月度经费收支
     *
     * @param parameters
     * @return
     */
    @PostMapping(value = "/list")
    public Page<CPFundsMonthListVO> listCPFundsMonth(@RequestBody Map<String, Object> parameters) {
        return cpFundsMonthService.listCPFundsMonth(parameters);
    }

    /**
     * 保存月度经费收支
     *
     * @param cpFundsMonthInfo
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseDTO saveCPFundsMonthInfo(@RequestBody CPFundsMonthEntity cpFundsMonthInfo) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (null == cpFundsMonthInfo.getCpId()) {
            msg.set("文化宫id不能为空" + ",");
        }

        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                cpFundsMonthService.saveCPFundsMonth(cpFundsMonthInfo);
                responseDTO = WebUtils.createSuccessResponse("");
            } catch (Exception e) {
                logger.error("新增/修改月度经费收支失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }
}
