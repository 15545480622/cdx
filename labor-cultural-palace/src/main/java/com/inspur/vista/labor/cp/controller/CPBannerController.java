package com.inspur.vista.labor.cp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPBannerService;
import com.inspur.vista.labor.cp.service.PubFilesService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.inspur.vista.labor.cp.util.CPConstants.FILE_TYPE_BANNER;

/**
 * @ClassName: CPBannerController
 * @Description: 文化宫bannner控制器
 * @authur: wangxueying01
 * @CreatDate: 2020/3/27 9:48
 */
@Api(value = "文化宫bannner控制器", tags = {"bannner图接口"})
@RestController
@RequestMapping("/api/cp/banner")
public class CPBannerController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CPBannerController.class);

    @Autowired
    private CPBannerService cpBannerService;

    @Autowired
    private PubFilesService filesService;

    /**
     * 获取banner图
     *
     * @param id banner图id
     * @return responseDTO
     */
    @ApiOperation(value = "获取banner图", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CPBannerVO> getCPBanner(@ApiParam(name = "id", value = "banner图id", required = true) @PathVariable(value = "id") String id) {
        ResponseDTO responseDTO;
        CPBannerVO cpBannerVO = cpBannerService.getCPBanner(id);
        if (null != cpBannerVO) {
            responseDTO = WebUtils.createSuccessResponse(cpBannerVO);
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_RES_NOT_FOUND, "未找到bannner图", "");
        }
        return responseDTO;
    }

    /**
     * 保存banner图
     *
     * @param cpBannerInfo banner图
     * @return responseDTO
     */
    @ApiOperation(value = "保存banner图", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/save")
    public ResponseDTO saveCPBanner(@ApiParam(name = "cpBannerInfo", value = "banner图", required = true) @RequestBody CPBannerEntity cpBannerInfo) {
        ResponseDTO responseDTO;
        AtomicReference<String> msg = new AtomicReference<>("");
        if (StringUtils.isBlank(cpBannerInfo.getCpId())) {
            msg.set(msg.get() + "文化宫id不能为空" + ",");
        }
        if (StringUtils.isBlank(cpBannerInfo.getBannerName())) {
            msg.set(msg.get() + "banner名称不能为空" + ",");
        }

        if (StringUtils.isNotBlank(msg.get())) {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.C_VALUE_NULL, msg.get().substring(0, msg.get().length() - 1));
        } else {
            try {
                cpBannerInfo.setCreator(getUser().getUserId());
                cpBannerInfo.setModifier(getUser().getUserId());
                CPBannerEntity cpBannerEntity = cpBannerService.saveCPBanner(cpBannerInfo);
                responseDTO = WebUtils.createSuccessResponse(cpBannerEntity.getId());
            } catch (Exception e) {
                logger.error("新增/修改banner失败", e);
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "");
            }
        }
        return responseDTO;
    }

    /**
     * 查询banner图
     *
     * @param parameters 查询参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询banner图", notes = "banner图展示采用分页的方式,查询条件：page;pageSize;cpId(文化宫id);bannerName(banner名称);bannerLink(banner外链地址);isShow(是否展示);remark(备注)", response = ResponseDTO.class)
    @PostMapping(value = "/list")
    public ResponseDTO<CPBannerListVO> listCPBanner(@ApiParam(name = "parameters", value = "查询条件：page;pageSize;cpId(文化宫id);bannerName(banner名称);bannerLink(banner外链地址);isShow(是否展示);remark(备注)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            Page<CPBannerListVO> cpBannerListVOPage = cpBannerService.listCPBanner(parameters);
            responseDTO = WebUtils.createSuccessResponse(cpBannerListVOPage);
        } catch (Exception e) {
            logger.error("查询banner图失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "查询banner图失败");
        }
        return responseDTO;
    }

    /**
     * 删除banner图
     *
     * @param ids banner图id
     * @return ResponseDTO
     */
    @ApiOperation(value = "删除banner图", notes = "", response = ResponseDTO.class)
    @GetMapping(value = "/delete")
    public ResponseDTO deleteCPCourtInfo(@ApiParam(name = "ids", value = "banner图id", required = true) @RequestParam(value = "ids") String ids) {
        ResponseDTO responseDTO;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr.length > 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("modifier", getUser().getUserId());
                map.put("ids", idArr);
                int result = cpBannerService.removeCPBanner(map);
                if (result > 0) {
                    responseDTO = WebUtils.createSuccessResponse("banner图删除成功");
                } else {
                    responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "banner图删除失败");
                }
            } else {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
            }
        } else {
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.S_REQ_PARAM_ERROR, "请求参数不正确");
        }
        return responseDTO;
    }

    /**
     * 切换banner图是否展示
     *
     * @param parameters 参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "切换banner图是否展示", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/changeIsShow")
    public ResponseDTO changeIsShow(@ApiParam(name = "parameters", value = "id(banner图id);isShow(是否展示：1 展示 0 不展示)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            parameters.put("modifier", getUser().getUserId());
            int result = cpBannerService.changeIsShow(parameters);
            if (result == 1) {
                responseDTO = WebUtils.createSuccessResponse("banner图展示状态修改成功");
            } else {
                responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "banner图展示状态修改失败");
            }
        } catch (Exception e) {
            logger.error("banner图展示状态修改失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "banner图展示状态修改失败");
        }
        return responseDTO;
    }

    /**
     * 排序
     *
     * @param parameters 参数
     * @return ResponseDTO
     */
    @ApiOperation(value = "banner图排序", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/sort")
    public ResponseDTO sort(@ApiParam(name = "parameters", value = "id(banner图id);moveType(上移下移:上移 1，下移 -1)", required = true) @RequestBody Map<String, Object> parameters) {
        ResponseDTO responseDTO;
        try {
            parameters.put("modifier", getUser().getUserId());
            cpBannerService.sort(parameters);
            responseDTO = WebUtils.createSuccessResponse("banner图排序修改成功");
        } catch (Exception e) {
            logger.error("banner图排序修改失败", e);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "banner图排序修改失败");
        }
        return responseDTO;
    }

    /**
     * 上传banner图
     *
     * @param file 文件
     * @return ResponseDTO
     */
    @ApiOperation(value = "上传banner图", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/upload")
    public ResponseDTO uploadFile(@RequestParam("file") MultipartFile[] file,
                                  @RequestParam("cpId") String cpId) {
        ResponseDTO responseDTO;
        try {
            FileBsnInfo bsnInfo = new FileBsnInfo();
            bsnInfo.setBsnId(cpId);
            bsnInfo.setBsnType(FILE_TYPE_BANNER);
            bsnInfo.setBsnDesc("文化宫的banner图，bsnId是文化宫id");
            List<UploadFileResponse> uploadFileResponseList = filesService.updateFile(bsnInfo, file);
            responseDTO = WebUtils.createSuccessResponse(uploadFileResponseList);
        } catch (Exception ex) {
            logger.error("文件上传出现异常", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "文件上传出现异常");
        }
        return responseDTO;
    }
}