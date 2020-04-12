package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPTaskHistoryEntity;
import com.inspur.vista.labor.cp.entity.PubTaskHistoryDetailVO;
import com.inspur.vista.labor.cp.entity.PubTaskHistoryListVO;
import com.inspur.vista.labor.cp.service.PubTaskHistoryService;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title: PubTaskHistoryController
 * @Description: 历史任务控制器
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:32
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/pub/task/history")
public class PubTaskHistoryController {

    private static final Logger logger = LoggerFactory.getLogger(PubTaskHistoryController.class);

    @Autowired
    private PubTaskHistoryService pubTaskHistoryService;

    /**
     * 获取历史任务
     *
     * @param id 历史任务id
     * @return ResponseDTO
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseDTO getPubTaskHistory(@PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        PubTaskHistoryDetailVO pubTaskHistory = pubTaskHistoryService.getPubTaskHistory(Long.parseLong(id));
        if (null != pubTaskHistory) {
            responseDTO = WebUtils.createSuccessResponse(pubTaskHistory);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到历史任务", "");
        }
        return responseDTO;
    }

    /**
     * 保存历史任务
     *
     * @param pubTaskHistory 历史任务
     * @return ResponseDTO
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseDTO savePubTaskHistory(@Valid @RequestBody CPTaskHistoryEntity pubTaskHistory, BindingResult errors) {
        ResponseDTO responseDTO;
        try {
            AtomicReference<String> msg = new AtomicReference<>("");
            if (errors.hasErrors()) {
                List<FieldError> fieldErrorList = errors.getFieldErrors();
                for (FieldError fieldError : fieldErrorList) {
                    msg.set(msg.get() + fieldError.getDefaultMessage() + ",");
                }
            }

            if (StringUtils.isNotBlank(msg.get())) {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
            } else {
                CPTaskHistoryEntity CPTaskHistoryEntity = pubTaskHistoryService.savePubTaskHistory(pubTaskHistory);
                responseDTO = WebUtils.createSuccessResponse(CPTaskHistoryEntity.getId());
            }
        } catch (Exception e) {
            logger.error("新增/修改历史任务失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
        }
        return responseDTO;
    }

    /**
     * 查询历史任务
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Page<PubTaskHistoryListVO> listPubTaskHistory(@RequestBody Map<String, Object> parameters) {
        return pubTaskHistoryService.listPubTaskHistory(parameters);
    }

}
