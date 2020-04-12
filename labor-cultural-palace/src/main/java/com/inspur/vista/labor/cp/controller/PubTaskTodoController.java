package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPTaskEntity;
import com.inspur.vista.labor.cp.entity.CPTaskListVO;
import com.inspur.vista.labor.cp.entity.CPTaskVO;
import com.inspur.vista.labor.cp.service.CPTaskService;
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
 * @Title: PubTaskTodoController
 * @Description: 待办任务控制器
 * @Author: liuzq
 * @CreateDate: 2019/12/17 14:32
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/pub/task/todo")
public class PubTaskTodoController {

    private static final Logger logger = LoggerFactory.getLogger(PubTaskTodoController.class);

    @Autowired
    private CPTaskService CPTaskService;

    /**
     * 获取待办任务
     *
     * @param id 待办任务id
     * @return ResponseDTO
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseDTO getPubTaskTodo(@PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPTaskVO pubTaskTodo = CPTaskService.getPubTaskTodo(id);
        if (null != pubTaskTodo) {
            responseDTO = WebUtils.createSuccessResponse(pubTaskTodo);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到待办任务", "");
        }
        return responseDTO;
    }

    /**
     * 保存待办任务
     *
     * @param pubTaskTodo 待办任务
     * @return ResponseDTO
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseDTO savePubTaskTodo(@Valid @RequestBody CPTaskEntity pubTaskTodo, BindingResult errors) {
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
                CPTaskEntity CPTaskEntity = CPTaskService.savePubTaskTodo(pubTaskTodo);
                responseDTO = WebUtils.createSuccessResponse("");
            }
        } catch (Exception e) {
            logger.error("新增/修改待办任务失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
        }
        return responseDTO;
    }

    /**
     * 查询待办任务
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Page<CPTaskListVO> listPubTaskTodo(@RequestBody Map<String, Object> parameters) {
        return CPTaskService.listPubTaskTodo(parameters);
    }

}
