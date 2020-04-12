package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPLiabilitiesMonthDetailVO;
import com.inspur.vista.labor.cp.entity.CPLiabilitiesMonthEntity;
import com.inspur.vista.labor.cp.entity.CPLiabilitiesMonthListVO;
import com.inspur.vista.labor.cp.service.CPLiabilitiesMonthService;
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
 * @ClassName: CPLiabilitiesMonthController
 * @Description: 月度资产负债控制器
 * @authur: wangxueying01
 * @CreatDate: 2020/1/9 15:55
 */
@RestController
@RequestMapping("/api/cp/liabilities/month")
public class CPLiabilitiesMonthController {

    private static final Logger logger = LoggerFactory.getLogger(CPLiabilitiesMonthController.class);

    @Autowired
    private CPLiabilitiesMonthService cpLiabilitiesMonthService;

    /**
     * 获取月度资产负债
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseDTO getCPLiabilitiesMonth(@PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPLiabilitiesMonthDetailVO cpLiabilitiesMonthDetailVO = cpLiabilitiesMonthService.getCPLiabilitiesMonth(Long.valueOf(id));
        if (null != cpLiabilitiesMonthDetailVO) {
            responseDTO = WebUtils.createSuccessResponse(cpLiabilitiesMonthDetailVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到月度资产负债", "");
        }
        return responseDTO;
    }

    /**
     * 查询月度资产负债
     *
     * @param parameters
     * @return
     */
    @PostMapping(value = "/list")
    public Page<CPLiabilitiesMonthListVO> listCPLiabilitiesMonth(@RequestBody Map<String, Object> parameters) {
        return cpLiabilitiesMonthService.listCPLiabilitiesMonth(parameters);
    }

    /**
     * 保存月度资产负债
     *
     * @param cpLiabilitiesMonthInfo
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseDTO saveCPLiabilitiesMonthInfo(@RequestBody CPLiabilitiesMonthEntity cpLiabilitiesMonthInfo) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (null == cpLiabilitiesMonthInfo.getCpId()) {
            msg.set("文化宫id不能为空" + ",");
        }
        if (StringUtils.isBlank(cpLiabilitiesMonthInfo.getMonth())) {
            msg.set("月份不能为空" + ",");
        }
        if (null == cpLiabilitiesMonthInfo.getItem()) {
            msg.set("资产填报项不能为空" + ",");
        }
        if (null == cpLiabilitiesMonthInfo.getEarlyAmount()) {
            cpLiabilitiesMonthInfo.setEarlyAmount(0.00);
        }
        if (null == cpLiabilitiesMonthInfo.getEndAmount()) {
            cpLiabilitiesMonthInfo.setEndAmount(0.00);
        }


        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                cpLiabilitiesMonthService.saveCPLiabilitiesMonth(cpLiabilitiesMonthInfo);
                responseDTO = WebUtils.createSuccessResponse("");
            } catch (Exception e) {
                logger.error("新增/修改月度资产负债失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }
}
