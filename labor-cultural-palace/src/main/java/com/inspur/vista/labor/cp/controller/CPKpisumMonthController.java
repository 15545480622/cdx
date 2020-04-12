package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPKpisumMonthDetailVO;
import com.inspur.vista.labor.cp.entity.CPKpisumMonthEntity;
import com.inspur.vista.labor.cp.entity.CPKpisumMonthListVO;
import com.inspur.vista.labor.cp.service.CPKpisumMonthService;
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
 * @ClassName: CPKpisumMonthController
 * @Description: 文化宫月度统计接口类
 * @authur: wangxueying01
 * @CreatDate: 2020/1/8 11:31
 */
@RestController
@RequestMapping("/api/cp/kpisum/month")
public class CPKpisumMonthController {

    private static final Logger logger = LoggerFactory.getLogger(CPKpisumMonthController.class);

    @Autowired
    private CPKpisumMonthService cpKpisumMonthService;

    /**
     * 获取月度统计
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseDTO getCPKpisumMonth(@PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPKpisumMonthDetailVO cpKpisumMonthDetailVO = cpKpisumMonthService.getCPKpisumMonth(Long.valueOf(id));
        if (null != cpKpisumMonthDetailVO) {
            responseDTO = WebUtils.createSuccessResponse(cpKpisumMonthDetailVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到月度统计", "");
        }
        return responseDTO;
    }

    /**
     * 查询月度统计
     *
     * @param parameters
     * @return
     */
    @PostMapping(value = "/list")
    public Page<CPKpisumMonthListVO> listCPKpisumMonth(@RequestBody Map<String, Object> parameters) {
        return cpKpisumMonthService.listCPKpisumMonth(parameters);
    }

    /**
     * 新增月度统计
     *
     * @param cpKpisumMonthEntity
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseDTO saveCPKpisumMonth(@RequestBody CPKpisumMonthEntity cpKpisumMonthEntity) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (null == cpKpisumMonthEntity.getCpId()) {
            msg.set("文化宫id不能为空" + ",");
        }
        if (StringUtils.isBlank(cpKpisumMonthEntity.getStatTime())) {
            msg.set("统计时间不能为空" + ",");
        }

        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                if (null == cpKpisumMonthEntity.getId() && 6 == cpKpisumMonthEntity.getStatTime().length()) {
                    cpKpisumMonthService.saveKpisumMonth(cpKpisumMonthEntity);
                    responseDTO = WebUtils.createSuccessResponse("");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "请求参数不正确");
                }
            } catch (Exception e) {
                logger.error("新增月度统计失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }
}
