package com.inspur.vista.labor.cp.controller.activity;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.controller.BaseController;
import com.inspur.vista.labor.cp.dao.CPPlaceInfoMapper;
import com.inspur.vista.labor.cp.entity.CPPlaceInfoVO;
import com.inspur.vista.labor.cp.entity.FileBsnInfo;
import com.inspur.vista.labor.cp.entity.UploadFileResponse;
import com.inspur.vista.labor.cp.entity.UserAdminInfo;
import com.inspur.vista.labor.cp.entity.activity.CpActivityBaseEntity;
import com.inspur.vista.labor.cp.entity.activity.CpActivityFormEntity;
import com.inspur.vista.labor.cp.entity.activity.CpActivitySpecifyOrganEntity;
import com.inspur.vista.labor.cp.service.PubFilesService;
import com.inspur.vista.labor.cp.service.activity.*;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.ErrorCodeEnum;
import com.inspur.vista.labor.cp.util.ResponseDTO;
import com.inspur.vista.labor.cp.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 活动
 * 
 * @author 
 * @email 
 * @date 2020-03-18 14:28:24
 */
@Api(tags = "文化宫活动基础接口")
@RestController
@RequestMapping("api/cp/activity/base")
public class CpActivityBaseController extends BaseController {

	@Autowired
	private CpActivityBaseService cpActivityBaseService;
    @Autowired
	private CpActivityFormService cpActivityFormService;
    @Autowired
    private CpActivityJoinRecordsService cpActivityJoinRecordsService;
    @Autowired
    private CpActivityCouponRecordsService cpActivityCouponRecordsService;
    @Autowired
    private CPPlaceInfoMapper cpPlaceInfoMapper;
    @Autowired
    private CpActivityDrawRecordsService cpActivityDrawRecordsService;
    @Autowired
    private PubFilesService filesService;
    @Value("${oss.download_host}")
    private String filePrefix;
    @Value("${cp.app-activity-page}")
    private String activityPage;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
    @ApiOperation("活动列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value =  "页码", paramType = "query", type = "string"),
            @ApiImplicitParam(name="pageSize", value =  "条数", paramType = "query", type = "string"),
            @ApiImplicitParam(name="title", value =  "标题", paramType = "query", type = "string"),
            @ApiImplicitParam(name="status", value =  "状态 0关闭，1开启", paramType = "query", type = "string"),
            @ApiImplicitParam(name="startTime", value =  "开始时间", paramType = "query", type = "string"),
            @ApiImplicitParam(name="endTime", value =  "结束时间", paramType = "query", type = "string"),
            @ApiImplicitParam(name="type", value =  "活动类型：投票:vote，抽奖:draw，报名join，领券tickets", paramType = "query", type = "string"),
            @ApiImplicitParam(name="releaseStatus", value =  "审核状态", paramType = "query", type = "string"),
            @ApiImplicitParam(name="laborId", value =  "创建工会id", paramType = "query", type = "string"),
    })
	public ResponseDTO list(@RequestParam @ApiIgnore Map<String, Object> params){
		Page page = cpActivityBaseService.queryPage(params);
		return WebUtils.createSuccessResponse(page);
	}

    /**
     * 基本信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("基本信息详情(编辑回显)")
    public ResponseDTO info(@PathVariable("id") Integer id){
        CpActivityBaseEntity cpActivityBaseEntity = cpActivityBaseService.selectById(id);
        if (cpActivityBaseEntity == null) {
            return WebUtils.createSuccessResponse(null);
        }
        //图片地址处理
        if (StringUtils.isNotBlank(cpActivityBaseEntity.getCoverImg())) {
            cpActivityBaseEntity.setCoverImg(cpActivityBaseService.activityUrlReduction(cpActivityBaseEntity.getCoverImg()));
        }
        if (StringUtils.isNotBlank(cpActivityBaseEntity.getContent())) {
            cpActivityBaseEntity.setContent(cpActivityBaseService.activityUrlReduction(cpActivityBaseEntity.getContent()));
        }
        //获取指定工会列表
        List<CpActivitySpecifyOrganEntity> list = cpActivityBaseService.querySpecifyOrgansByActivityId(id);
        cpActivityBaseEntity.setSpecifyList(list);
        //场所名称
        CPPlaceInfoVO cpPlaceInfoVO = cpPlaceInfoMapper.selectByPrimaryKey(cpActivityBaseEntity.getPlaceId());
        if (cpPlaceInfoVO != null) {
            cpActivityBaseEntity.setPlaceName(cpPlaceInfoVO.getName());
        }
        return WebUtils.createSuccessResponse(cpActivityBaseEntity);
    }

    /**
     * 活动详情
     */
    @GetMapping("/detils/{id}")
    @ApiOperation("活动详情")
    public ResponseDTO detils(@PathVariable("id") Integer id){
        Map detils = cpActivityBaseService.detils(id);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, detils);
    }


    /**
     * 创建活动
     */
    @PostMapping("/save")
    @ApiOperation("创建活动")
    public ResponseDTO save(@RequestBody CpActivityBaseEntity cpActivityBaseEntity){

        UserAdminInfo user = getUser();
        cpActivityBaseEntity.setCpId("a28f691d470441f88bb1f2ec2ecb965c");

        StringBuilder sb = new StringBuilder();
        sb.append("<span class=\"blue\">创建</span>");
        sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        sb.append("  【").append(user.getLaborName()).append("(")
                .append(user.getUserName()).append(")").append("】").append("创建活动");
        cpActivityBaseEntity.setRemark(sb.toString());

        cpActivityBaseEntity.setLaborId(user.getLaborId());
        cpActivityBaseEntity.setLaborCode(user.getLaborCode());
        cpActivityBaseEntity.setLaborType(user.getLaborType());
        cpActivityBaseEntity.setLaborName(user.getLaborName());
        cpActivityBaseEntity.setOperatorId(user.getUserId());
        cpActivityBaseEntity.setOperatorName(user.getUserName());

        cpActivityBaseService.insertActivity(cpActivityBaseEntity);
        cpActivityBaseEntity.setLink(activityPage + "/#/signUpCp/" + cpActivityBaseEntity.getId());
        cpActivityBaseService.updateById(cpActivityBaseEntity);

        return WebUtils.createSuccessResponse(cpActivityBaseEntity);
    }

    /**
     * 编辑
     */
    @PostMapping("/update")
    @ApiOperation("编辑活动")
    public ResponseDTO update(@RequestBody CpActivityBaseEntity cpActivityBaseEntity){
        cpActivityBaseService.updateActivityById(cpActivityBaseEntity);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS,"");
    }



    /**
     * 开启关闭活动
     * @param id
     * @param status
     */
    @PostMapping("/updateStatus/{id}")
    @ApiOperation("开启关闭活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value = "活动id", paramType = "path", type = "int"),
            @ApiImplicitParam(name="status", value = "开启/关闭(1开启，0关闭)", paramType = "query", type = "string")
    })
    public ResponseDTO updateStatus(@PathVariable("id") int id, @RequestParam("status") int status) {
        cpActivityBaseService.updateStatus(id, status);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS,"");
    }

    /**
     * 置顶活动
     * @param id
     * @param isTop
     */
    @PutMapping("/updateIsTop/{id}/{isTop}")
    @ApiOperation("置顶，取消置顶")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value = "活动id", paramType = "path", type = "int"),
            @ApiImplicitParam(name="isTop", value = "置顶/取消（0取消，1置顶）", paramType = "path", type = "string")
    })
    public ResponseDTO setTop(@PathVariable("id") int id, @PathVariable("isTop")int isTop) {
        CpActivityBaseEntity entity = new CpActivityBaseEntity();
        entity.setId(id);
        entity.setIsTop(isTop);
        cpActivityBaseService.updateById(entity);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS,null);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{activityId}")
    @ApiOperation("删除活动")
    public ResponseDTO delete(@PathVariable int activityId) {
        cpActivityBaseService.deleteActivityById(activityId);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS,null);
    }

    /**
     * 根据文化宫id获取场所列表
     */
    @PostMapping("/getPlaceList/{cpId}")
    @ApiOperation("根据文化宫id获取场所列表")
    public ResponseDTO getPlaceListByCpId(@PathVariable String cpId) {
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS,cpActivityBaseService.getPlaceListByCpId(cpId));
    }

    /**
     * 报名活动参与情况
     */
    @PostMapping("/join/recordPage")
    @ApiOperation("报名活动参与情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name="laborId", value = "机构id", required = false, paramType = "path", type = "int"),
            @ApiImplicitParam(name="userName", value = "用户姓名", required = false, paramType = "path", type = "int"),
            @ApiImplicitParam(name="id", value = "活动id", required = true, paramType = "path", type = "int"),
            @ApiImplicitParam(name="page", value =  "页码", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="pageSize", value =  "条数", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO joinRecordPage(@ApiIgnore @RequestParam Map params) {

        Map<String, Object> result = new HashMap<>();
        //获取活动id
        if (params.get("id") == null) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "活动id不可为空");
        }
        Integer id = Integer.valueOf(Objects.toString(params.get("id")));
        //获取活动详情
        CpActivityBaseEntity cpActivityBaseEntity = cpActivityBaseService.selectById(id);
        //报名活动
        if ("join".equals(cpActivityBaseEntity.getType())) {
            //活动表单
            CpActivityFormEntity form = cpActivityFormService.selectByActivityid(id);
            //获取参与情况分页信息
            Page page = cpActivityJoinRecordsService.selectPageByActivityId(params);
            result.put("form", form);
            result.put("records", page);
        }
        return WebUtils.createSuccessResponse(result);
    }

    /**
     * 领券活动参与情况
     */
    @PostMapping("/coupon/recordPage")
    @ApiOperation("领券活动参与情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name="laborId", value = "机构id", required = false, paramType = "path", type = "int"),
            @ApiImplicitParam(name="userName", value = "用户姓名", required = false, paramType = "path", type = "int"),
            @ApiImplicitParam(name="id", value = "活动id", required = true, paramType = "path", type = "int"),
            @ApiImplicitParam(name="page", value =  "页码", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="pageSize", value =  "条数", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO couponRecordPage(@ApiIgnore @RequestParam Map params) {

        //获取活动id
        if (params.get("id") == null) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "活动id不可为空");
        }
        Page<Map> mapPage = cpActivityCouponRecordsService.couponRecordPage(params);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, mapPage);
    }

    /**
     * 抽奖活动参与情况
     */
    @PostMapping("/draw/recordPage")
    @ApiOperation("抽奖活动参与情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name="laborId", value = "机构id", required = false, paramType = "path", type = "int"),
            @ApiImplicitParam(name="userName", value = "用户姓名", required = false, paramType = "path", type = "int"),
            @ApiImplicitParam(name="id", value = "活动id", required = true, paramType = "path", type = "int"),
            @ApiImplicitParam(name="page", value =  "页码", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="pageSize", value =  "条数", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO drawRecordPage(@ApiIgnore @RequestParam Map params) {

        //获取活动id
        if (params.get("id") == null) {
            return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "活动id不可为空");
        }
        Page<Map> mapPage = cpActivityDrawRecordsService.drawRecordPage(params);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, mapPage);
    }



    /**
     * 领券活动添加优惠券
     */
    @PostMapping("/addCoupon")
    @ApiOperation("领券活动添加优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value = "活动id", required = true, paramType = "path", type = "int"),
            @ApiImplicitParam(name="couponId", value =  "优惠券id", required = true, paramType = "query", type = "string"),
            @ApiImplicitParam(name="count", value =  "优惠券数量", required = true, paramType = "query", type = "string"),
    })
    public ResponseDTO addCoupon(@ApiIgnore @RequestParam Map params) {
        cpActivityBaseService.addCoupon(params);
        return WebUtils.createSuccessResponse(ErrorCodeEnum.P_SUCCESS, null);
    }

    @ApiOperation(value = "活动文件上传", notes = "", response = ResponseDTO.class)
    @PostMapping(value = "/upload")
    public ResponseDTO uploadFile(@RequestParam("file") MultipartFile[] file) {
        ResponseDTO responseDTO;
        try {
            FileBsnInfo bsnInfo = new FileBsnInfo();
            bsnInfo.setBsnType(CPConstants.FILE_TYPE_ACTIVITY);
            bsnInfo.setBsnDesc("活动图片");
            List<UploadFileResponse> uploadFileResponseList = filesService.updateFile(bsnInfo, file);
            uploadFileResponseList.forEach(r -> {
                if (StringUtils.isNotBlank(r.getFilePath())) {
                    r.setFilePath(filePrefix + r.getFilePath());
                }
            });
            responseDTO = WebUtils.createSuccessResponse(uploadFileResponseList);
        } catch (Exception ex) {
            logger.error("文件上传出现异常", ex);
            responseDTO = WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "文件上传出现异常");
        }
        return responseDTO;
    }




	
}
