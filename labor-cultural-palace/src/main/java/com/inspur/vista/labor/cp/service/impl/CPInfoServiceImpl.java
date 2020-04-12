package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.*;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPInfoService;
import com.inspur.vista.labor.cp.service.CommonService;
import com.inspur.vista.labor.cp.service.PubFilesService;
import com.inspur.vista.labor.cp.util.*;
import com.inspur.vista.tinyflow.core.util.HandleResultEnum;
import com.inspur.vista.tinyflow.core.util.TinyflowHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @Title: CulturePalaceInfoServiceimpl
 * @Description: 文化宫基本信息服务类
 * @Author: gengpeng
 * @CreateDate: 2019/12/5 16:01
 * @Version: 1.0
 */
@Service
public class CPInfoServiceImpl implements CPInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CPInfoServiceImpl.class);

    @Autowired
    private CPInfoMapper cpInfoMapper;

    @Autowired
    private CPInfoApplyMapper infoApplyMapper;

    @Autowired
    private CPApplyFileMapper applyFileMapper;

    @Autowired
    private CPAccountMapper cpAccountMapper;

    @Autowired
    private OrganMapper organMapper;

    @Autowired
    private PubFilesService filesService;

    @Autowired
    private PubFilesMapper filesMapper;

    @Autowired
    private PubFilesMapper pubFilesMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private CPCodeRuleMapper codeRuleMapper;

    @Autowired
    private OrganCantMapper organCantMapper;

    @Value("${bsp-gateway.url_add_organ}")
    private String addOrganUrl;

    @Value("${bsp-gateway.url_del_organ}")
    private String delOrganUrl;

    @Value("${bsp-gateway.url_add_user}")
    private String addUserUrl;

    @Value("${bsp-gateway.url_del_user}")
    private String delUserUrl;

    /**
     * 获取文化宫信息
     *
     * @param id 驿站id
     * @return CPInfoVO
     */
    @Override
    public CPInfoVO getCPInfo(String id) {
        return cpInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * app端获取文化宫信息
     *
     * @param id
     * @return
     */
    @Override
    public CPInfoAppVO getAppCPInfo(String id) {
        CPInfoVO cpInfoVO = cpInfoMapper.selectByPrimaryKey(id);
        CPInfoAppVO infoAppVO = new CPInfoAppVO();
        infoAppVO.setId(cpInfoVO.getId());
        infoAppVO.setName(cpInfoVO.getName());
        infoAppVO.setServiceFacilities(cpInfoVO.getServiceFacilities());
        infoAppVO.setOfficialsPhone(cpInfoVO.getOfficialsPhone());
        infoAppVO.setBusinessTime(cpInfoVO.getBusinessTime());
        infoAppVO.setContactPhone(cpInfoVO.getContactPhone());
        infoAppVO.setParkNum(cpInfoVO.getParkNum());
        infoAppVO.setDistrict(cpInfoVO.getDistrict());
        infoAppVO.setAddress(cpInfoVO.getAddress());
        infoAppVO.setIntroduction(cpInfoVO.getIntroduction());
        infoAppVO.setLng(cpInfoVO.getLng());
        infoAppVO.setLat(cpInfoVO.getLat());
        infoAppVO.setIsAllDay(cpInfoVO.getIsAllDay());
        String availablePayWay = cpAccountMapper.getAvailablePayWay(id);
        infoAppVO.setPayWay(availablePayWay);
        return infoAppVO;
    }

    /**
     * 获取文化宫申请信息
     *
     * @param applyId 申请表id
     * @return CPInfoVO
     */
    @Override
    public CPInfoApplyVO getCPInfoApply(String applyId) {
        CPInfoApplyVO applyVO = infoApplyMapper.selectByPrimaryKey(applyId);
        List<CPApplyFileVO> fileVOList = applyFileMapper.listApplyFile(applyId);
        applyVO.setAppFileList(fileVOList);
        return applyVO;
    }

    /**
     * 保存文化宫信息
     *
     * @param cpInfoEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO save(CPInfoEntity cpInfoEntity) throws Exception {
        if (StringUtils.isBlank(cpInfoEntity.getId())) {
            cpInfoEntity.setId(IdUtil.fastSimpleUUID());

            String cantCode;
            // 如果是省级
            if (1 == cpInfoEntity.getUnionLevel()) {
                cantCode = "370000";
            } else if (2 == cpInfoEntity.getUnionLevel()) {
                cantCode = cpInfoEntity.getDistrict().substring(0, 4) + "00";
            } else {
                cantCode = cpInfoEntity.getDistrict();
            }
            String code = getCPCode(cantCode);
            if (StringUtils.isBlank(code)) {
                return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "未找到编码规则");
            }
            OrganCantEntity organCant = organCantMapper.selectByCantCode(cantCode);
            if (null == organCant) {
                return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "未找到区县对应的组织");
            }
            Map resultMap = addOrgan(cpInfoEntity.getName(), code, organCant.getOrganId());
            if (null == resultMap) {
                return WebUtils.createFailureResponse(ErrorCodeEnum.P_SYS_ERROR, "新增组织失败");
            }
            String organId = Convert.toStr(resultMap.get("organId"));
            addUser(code, cpInfoEntity.getName(), organId, CPConstants.ROLE_SUPER_ADMIN);
            cpInfoEntity.setOrganCode(code);
            cpInfoEntity.setOrganId(organId);
            cpInfoMapper.insertSelective(cpInfoEntity);
        } else {
            cpInfoMapper.updateByPrimaryKeySelective(cpInfoEntity);
        }
        return WebUtils.createSuccessResponse(cpInfoEntity.getId());
    }

    /**
     * 获取文化宫编码
     *
     * @param cantCode
     * @return
     */
    private String getCPCode(String cantCode) {
        String code = "";
        int result = codeRuleMapper.updateCurrentValue(cantCode);
        if (result > 0) {
            CPCodeRuleEntity codeRuleEntity = codeRuleMapper.selectByPrimaryKey(cantCode);
            code = codeRuleEntity.getPrefixCpCode() + String.format("%02d", codeRuleEntity.getCurrentValue());
        }
        return code;
    }

    /**
     * 新增组织
     */
    private Map addOrgan(String organName, String organCode, String parentOrganId) throws Exception {

        Map resultMap;
        Map headerMap = new HashMap();
        headerMap.put("Authorization", "Bearer " + commonService.getToken());
        Map param = new HashMap();
        param.put("organName", organName);
        param.put("organCode", organCode);
        param.put("organType", "29");
        param.put("parentOrganId", parentOrganId);
        String paramJson = JSONObject.toJSONString(param);
        resultMap = HttpUtil.post(addOrganUrl, paramJson, headerMap);
        Map map = null;
        if (resultMap.size() > 0) {
            if (!"P00000".equals(resultMap.get("code").toString())) {
                logger.error("新增组织失败：{}", resultMap.get("msg"));
            } else {
                map = (Map) resultMap.get("data");
            }
        } else {
            logger.error("新增组织失");
        }

        return map;
    }

    /**
     * 删除组织
     *
     * @param organId
     */
    private boolean delOrgan(String organId) {
        boolean delResult;
        Map resultMap;
        Map headerMap = new HashMap();
        headerMap.put("Authorization", "Bearer " + commonService.getToken());
        Map param = new HashMap();
        param.put("organId", organId);
        resultMap = HttpUtil.post(delOrganUrl, param, headerMap);
        if (resultMap.size() > 0) {
            if (!"P00000".equals(resultMap.get("code").toString())) {
                logger.error("删除组织失败：{}", resultMap.get("msg"));
                delResult = false;
            } else {
                delResult = true;
            }
        } else {
            delResult = false;
        }

        return delResult;
    }

    /**
     * 用户新增
     *
     * @param userId
     * @param userName
     */
    private boolean addUser(String userId, String userName, String organId, String roleCodes) throws Exception {
        boolean addResult;
        Map resultMap;
        Map headerMap = new HashMap();
        headerMap.put("Authorization", "Bearer " + commonService.getToken());
        Map param = new HashMap();
        param.put("userId", userId);
        param.put("userName", userName);
        param.put("organId", organId);
        param.put("roleCodes", roleCodes);
        resultMap = HttpUtil.post(addUserUrl, param, headerMap);
        if (resultMap.size() > 0) {
            if (!"P00000".equals(resultMap.get("code").toString())) {
                logger.error("新增用户失败：{}", resultMap.get("msg"));
                addResult = false;
            } else {
                addResult = true;
            }
        } else {
            addResult = false;
        }

        return addResult;
    }

    /**
     * 驿站用户删除
     *
     * @param userId
     */
    private boolean delUser(String userId) {
        boolean delResult;
        Map resultMap;
        Map headerMap = new HashMap();
        headerMap.put("Authorization", "Bearer " + commonService.getToken());
        Map param = new HashMap();
        param.put("userId", userId);
        resultMap = HttpUtil.post(delUserUrl, param, headerMap);
        if (resultMap.size() > 0) {
            if (!"P00000".equals(resultMap.get("code").toString())) {
                logger.error("删除用户失败：{}", resultMap.get("msg"));
                delResult = false;
            } else {
                delResult = true;
            }
        } else {
            delResult = false;
        }

        return delResult;
    }

    /**
     * 保存文化宫信息
     *
     * @param applyAdd
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void apply(CPInfoApplyAdd applyAdd) {

        if (StringUtils.isBlank(applyAdd.getId())) {
            applyAdd.setId(IdUtil.fastSimpleUUID());
            //申请更改文化宫信息
            // 1.新增申请的信息
            CPInfoApplyEntity applyEntity = new CPInfoApplyEntity();
            applyEntity.setId(applyAdd.getId());
            applyEntity.setCpId(applyAdd.getCpId());
            applyEntity.setName(applyAdd.getName());
            applyEntity.setIsUnionAssets(applyAdd.getIsUnionAssets());
            applyEntity.setIsAloneAccount(applyAdd.getIsAloneAccount());
            applyEntity.setAloneAccount(applyAdd.getAloneAccount());
            applyEntity.setUnionLevel(applyAdd.getUnionLevel());
            applyEntity.setSocialCreditCode(applyAdd.getSocialCreditCode());
            applyEntity.setPublicWelfareType(applyAdd.getPublicWelfareType());
            applyEntity.setServiceFacilities(applyAdd.getServiceFacilities());
            applyEntity.setOfficialsName(applyAdd.getOfficialsName());
            applyEntity.setOfficialsPhone(applyAdd.getOfficialsPhone());
            applyEntity.setEstablishDate(applyAdd.getEstablishDate());
            applyEntity.setBusinessTime(applyAdd.getBusinessTime());
            applyEntity.setIsAllDay(applyAdd.getIsAllDay());
            applyEntity.setBusinessWeek(applyAdd.getBusinessWeek());
            applyEntity.setContactName(applyAdd.getContactName());
            applyEntity.setContactPhone(applyAdd.getContactPhone());
            applyEntity.setStaffingPeople(applyAdd.getStaffingPeople());
            applyEntity.setRealPeople(applyAdd.getRealPeople());
            applyEntity.setOnjobPeople(applyAdd.getOnjobPeople());
            applyEntity.setExternalPeople(applyAdd.getExternalPeople());
            applyEntity.setRetirePeople(applyAdd.getRetirePeople());
            applyEntity.setParkNum(applyAdd.getParkNum());
            applyEntity.setBusinessRange(applyAdd.getBusinessRange());
            applyEntity.setDistrict(applyAdd.getDistrict());
            applyEntity.setAddress(applyAdd.getAddress());
            applyEntity.setIntroduction(applyAdd.getIntroduction());
            applyEntity.setLng(applyAdd.getLng());
            applyEntity.setLat(applyAdd.getLat());
            applyEntity.setArea(applyAdd.getArea());
            applyEntity.setConstructionArea(applyAdd.getConstructionArea());
            applyEntity.setLeasedConstructionArea(applyAdd.getLeasedConstructionArea());
            applyEntity.setCapacity(applyAdd.getCapacity());
            applyEntity.setLaborId(applyAdd.getLaborId());
            infoApplyMapper.insertSelective(applyEntity);
            // 把文件信息复制到申请表附件里
            String[] fileIds = applyAdd.getApplyFileIds().split(",");
            List<PubFilesVO> pubFilesVOList = pubFilesMapper.listByIds(fileIds);
            List<CPApplyFileEntity> dataList = new ArrayList<>();
            for (PubFilesVO filesVO : pubFilesVOList) {
                CPApplyFileEntity applyFileEntity = new CPApplyFileEntity();
                applyFileEntity.setId(IdUtil.fastSimpleUUID());
                applyFileEntity.setApplyId(applyEntity.getId());
                applyFileEntity.setType(filesVO.getType());
                applyFileEntity.setFileName(filesVO.getFileName());
                applyFileEntity.setFileExt(filesVO.getFileExt());
                applyFileEntity.setFileSize(filesVO.getFileSize());
                applyFileEntity.setFilePath(filesVO.getFilePath());
                dataList.add(applyFileEntity);
            }
            if (!dataList.isEmpty()) {
                applyFileMapper.batchInsert(dataList);
            }

            applyFileMapper.batchUpdate(applyAdd.getId(), fileIds);
            // 3.发起流程
            TinyflowHelper.buildInstance()
                    .bsnInfo(CPConstants.APPLY_TYPE_CP_CHANGE, applyAdd.getId(), "文化宫信息修改")
                    .processor(applyAdd.getSubmitter(), "", "")
                    .validAndStart("whgxxxg");
        } else {
            CPInfoApplyEntity applyEntity = new CPInfoApplyEntity();
            applyEntity.setId(applyAdd.getId());
            applyEntity.setCpId(applyAdd.getCpId());
            applyEntity.setName(applyAdd.getName());
            applyEntity.setIsUnionAssets(applyAdd.getIsUnionAssets());
            applyEntity.setIsAloneAccount(applyAdd.getIsAloneAccount());
            applyEntity.setAloneAccount(applyAdd.getAloneAccount());
            applyEntity.setUnionLevel(applyAdd.getUnionLevel());
            applyEntity.setSocialCreditCode(applyAdd.getSocialCreditCode());
            applyEntity.setPublicWelfareType(applyAdd.getPublicWelfareType());
            applyEntity.setServiceFacilities(applyAdd.getServiceFacilities());
            applyEntity.setOfficialsName(applyAdd.getOfficialsName());
            applyEntity.setOfficialsPhone(applyAdd.getOfficialsPhone());
            applyEntity.setEstablishDate(applyAdd.getEstablishDate());
            applyEntity.setBusinessTime(applyAdd.getBusinessTime());
            applyEntity.setIsAllDay(applyAdd.getIsAllDay());
            applyEntity.setBusinessWeek(applyAdd.getBusinessWeek());
            applyEntity.setContactName(applyAdd.getContactName());
            applyEntity.setContactPhone(applyAdd.getContactPhone());
            applyEntity.setStaffingPeople(applyAdd.getStaffingPeople());
            applyEntity.setRealPeople(applyAdd.getRealPeople());
            applyEntity.setOnjobPeople(applyAdd.getOnjobPeople());
            applyEntity.setExternalPeople(applyAdd.getExternalPeople());
            applyEntity.setRetirePeople(applyAdd.getRetirePeople());
            applyEntity.setParkNum(applyAdd.getParkNum());
            applyEntity.setBusinessRange(applyAdd.getBusinessRange());
            applyEntity.setDistrict(applyAdd.getDistrict());
            applyEntity.setAddress(applyAdd.getAddress());
            applyEntity.setIntroduction(applyAdd.getIntroduction());
            applyEntity.setLng(applyAdd.getLng());
            applyEntity.setLat(applyAdd.getLat());
            applyEntity.setArea(applyAdd.getArea());
            applyEntity.setConstructionArea(applyAdd.getConstructionArea());
            applyEntity.setLeasedConstructionArea(applyAdd.getLeasedConstructionArea());
            applyEntity.setCapacity(applyAdd.getCapacity());
            applyEntity.setLaborId(applyAdd.getLaborId());
            infoApplyMapper.updateByPrimaryKeySelective(applyEntity);
            // 2.更新附件信息，增加申请表id
            List<CPApplyFileVO> applyFileVOList = applyFileMapper.listApplyFile(applyAdd.getId());
            String[] applyFileIdArray = applyAdd.getApplyFileIds().split(",");
            for (CPApplyFileVO cpApplyFileVO : applyFileVOList) {
                if (!Arrays.asList(applyFileIdArray).contains(cpApplyFileVO.getId())) {
                    applyFileMapper.deleteById(cpApplyFileVO.getId());
                }

            }
            applyFileMapper.batchUpdate(applyAdd.getId(), applyFileIdArray);
            // 3.发起流程
            TinyflowHelper.buildTask().bsn(CPConstants.APPLY_TYPE_CP_CHANGE, applyAdd.getId())
                    .process(applyAdd.getSubmitter(), "", HandleResultEnum.AGREE, "重新提交")
                    .next("", "")
                    .validAndFinish();
        }

    }

    /**
     * 审核
     *
     * @param applyCheck
     */
    @Override
    public void check(CPApplyCheck applyCheck) {
        // 通过
        if (applyCheck.getHandleResult() == CPConstants.APPLY_CHECK_PASS) {
            TinyflowHelper.buildTask().bsn(CPConstants.APPLY_TYPE_CP_CHANGE, applyCheck.getApplyId())
                    .process(applyCheck.getHandler(), applyCheck.getHandleLaborId(), HandleResultEnum.AGREE, "同意")
                    .next("", "")
                    .validAndFinish();
            // 1.更新文化宫信息
            CPInfoApplyVO applyVO = infoApplyMapper.selectByPrimaryKey(applyCheck.getApplyId());
            CPInfoEntity cpInfoEntity = new CPInfoEntity();
            cpInfoEntity.setId(applyVO.getCpId());
            cpInfoEntity.setName(applyVO.getName());
            cpInfoEntity.setIsUnionAssets(applyVO.getIsUnionAssets());
            cpInfoEntity.setIsAloneAccount(applyVO.getIsAloneAccount());
            cpInfoEntity.setAloneAccount(applyVO.getAloneAccount());
            cpInfoEntity.setUnionLevel(applyVO.getUnionLevel());
            cpInfoEntity.setSocialCreditCode(applyVO.getSocialCreditCode());
            cpInfoEntity.setUnitNature(applyVO.getPublicWelfareType());
            cpInfoEntity.setServiceFacilities(applyVO.getServiceFacilities());
            cpInfoEntity.setOfficialsName(applyVO.getOfficialsName());
            cpInfoEntity.setOfficialsPhone(applyVO.getOfficialsPhone());
            cpInfoEntity.setEstablishDate(applyVO.getEstablishDate());
            cpInfoEntity.setBusinessTime(applyVO.getBusinessTime());
            cpInfoEntity.setIsAllDay(applyVO.getIsAllDay());
            cpInfoEntity.setBusinessWeek(applyVO.getBusinessWeek());
            cpInfoEntity.setContactName(applyVO.getContactName());
            cpInfoEntity.setContactPhone(applyVO.getContactPhone());
            cpInfoEntity.setStaffingPeople(applyVO.getStaffingPeople());
            cpInfoEntity.setRealPeople(applyVO.getRealPeople());
            cpInfoEntity.setOnjobPeople(applyVO.getOnjobPeople());
            cpInfoEntity.setExternalPeople(applyVO.getExternalPeople());
            cpInfoEntity.setRetirePeople(applyVO.getRetirePeople());
            cpInfoEntity.setParkNum(applyVO.getParkNum());
            cpInfoEntity.setBusinessRange(applyVO.getBusinessRange());
            cpInfoEntity.setDistrict(applyVO.getDistrict());
            cpInfoEntity.setAddress(applyVO.getAddress());
            cpInfoEntity.setIntroduction(applyVO.getIntroduction());
            cpInfoEntity.setLng(applyVO.getLng());
            cpInfoEntity.setLat(applyVO.getLat());
            cpInfoEntity.setArea(applyVO.getArea());
            cpInfoEntity.setConstructionArea(applyVO.getConstructionArea());
            cpInfoEntity.setLeasedConstructionArea(applyVO.getLeasedConstructionArea());
            cpInfoEntity.setCapacity(applyVO.getCapacity());
            cpInfoMapper.updateByPrimaryKeySelective(cpInfoEntity);
            // 2.替换文件
            pubFilesMapper.deleteFilesByBsnId(applyCheck.getApplyId(), applyCheck.getHandler());

            List<CPApplyFileVO> applyFileVOList = applyFileMapper.listApplyFile(applyCheck.getApplyId());
            List<PubFilesEntity> dataList = new ArrayList<>();
            for (CPApplyFileVO applyFileVO : applyFileVOList) {
                PubFilesEntity filesEntity = new PubFilesEntity();
                filesEntity.setId(IdUtil.fastSimpleUUID());
                filesEntity.setBsnId(cpInfoEntity.getId());
                filesEntity.setType(applyFileVO.getType());
                filesEntity.setFilePath(applyFileVO.getFilePath());
                filesEntity.setFileExt(applyFileVO.getFileExt());
                filesEntity.setFileSize(applyFileVO.getFileSize());
                filesEntity.setFileName(applyFileVO.getFileName());
                dataList.add(filesEntity);
            }
            pubFilesMapper.batchInsert(dataList);
        } else {
            TinyflowHelper.buildTask().bsn(CPConstants.APPLY_TYPE_CP_CHANGE, applyCheck.getApplyId())
                    .process(applyCheck.getHandler(), applyCheck.getHandleLaborId(), HandleResultEnum.DISAGREE, "不同意")
                    .next("", "")
                    .validAndFinish();
        }

    }

    /**
     * 查询文化宫信息
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPInfoListVO> listCPInfo(Map<String, Object> parameters) {
        OrganEntity organ = organMapper.selectByOrganId(parameters.get("laborId").toString());
        parameters.put("struPath", organ.getStruPath());
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPInfoListVO> p = new Page<>(page, pageSize);
        List<CPInfoListVO> cpInfoList = cpInfoMapper.listCPInfo(p, parameters);

        for (CPInfoListVO cpInfoListVO : cpInfoList) {
            //如果tag字段没有维护，展示场地的项目类别
            if (StringUtils.isBlank(cpInfoListVO.getTag())) {
                List<Map<String, String>> typeList = listTypeByCpId(cpInfoListVO.getId());
                Map<String, String> typeMap = new HashMap<>();
                for (Map<String, String> map : typeList) {
                    typeMap.putAll(map);
                }
                List<String> valuesList = new ArrayList<>(typeMap.values());
                String typeString = ArrayUtil.join(valuesList.toArray(), ",");
                cpInfoListVO.setTag(typeString);
            }
        }

        p.setRecords(cpInfoList);
        return p;
    }

    /**
     * 根据区划、项目类型查询文化宫
     *
     * @param parameters
     * @return
     */
    @Override
    public List<CPInfoListVO> listCPInfoByCantOrType(Map<String, Object> parameters) {

        //高德坐标系转百度坐标系
        if (parameters.get("curLng") != null && StringUtils.isNotBlank(parameters.get("curLng").toString())
                && parameters.get("curLat") != null && StringUtils.isNotBlank(parameters.get("curLat").toString())) {
            double[] cur = ECGeoCoordinateTransformUtil.gcj02tobd09(Convert.toDouble(parameters.get("curLng")), Convert.toDouble(parameters.get("curLat")));
            double curLng = cur[0];
            double curLat = cur[1];
            parameters.put("curLng", String.valueOf(curLng));
            parameters.put("curLat", String.valueOf(curLat));
        }
        if (StringUtils.isNotBlank(parameters.get("projectTypes").toString())) {
            String[] projectTypeArray = parameters.get("projectTypes").toString().split(",");
            parameters.put("projectTypeArray", projectTypeArray);
        }
        List<CPInfoListVO> cpInfoListVOS = cpInfoMapper.listCPInfoByCantOrType(parameters);
        for (CPInfoListVO cpInfoListVO : cpInfoListVOS) {
            cpInfoListVO.setDistance(cpInfoListVO.getDistance() + "km");
            //1、如果tag字段没有维护，展示场地的项目类别
            if (StringUtils.isBlank(cpInfoListVO.getTag())) {
                List<Map<String, String>> typeList = listTypeByCpId(cpInfoListVO.getId());
                List<String> valuesList = new ArrayList<>();
                for (Map<String, String> map : typeList) {
                    valuesList.add(map.get("itemValue"));
                }

                String typeString = ArrayUtil.join(valuesList.toArray(), ",");
                cpInfoListVO.setTag(typeString);
            }
            //2、将营业星期字段的数字转化为星期
            if (StringUtils.isNotBlank(cpInfoListVO.getBusinessWeek())) {
                String[] businessWeek = cpInfoListVO.getBusinessWeek().split(",");
                List<Integer> businessWeekList = new ArrayList<>();
                for (String week : businessWeek) {
                    businessWeekList.add(Integer.valueOf(week));
                }
                cpInfoListVO.setBusinessWeek(transformWeek(businessWeekList));
            }

        }
        return cpInfoListVOS;
    }

    /**
     * 查询文化宫的附件
     *
     * @param cpId
     * @return
     */
    @Override
    public List<PubFilesVO> getCPFiles(Long cpId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("bsnId", cpId);
        return filesService.listPubFiles(parameters);
    }

    /**
     * 根据文化宫id查询已有的项目类型
     *
     * @param cpId
     * @return
     */
    @Override
    public List<Map<String, String>> listTypeByCpId(String cpId) {
        List<Map<String, String>> typeList = cpInfoMapper.listTypeByCpId(cpId);
        return typeList;
    }

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param cpId
     * @return
     */
    @Override
    public List<String> uploadFile(MultipartFile[] file, String type, String cpId) throws Exception {
        FileBsnInfo bsnInfo = createBsnInfo(cpId, type);
        List<UploadFileResponse> uploadFileResponseList = filesService.updateFile(bsnInfo, file);
        List<PubFilesEntity> fileList = new ArrayList<>();
        List<String> fileIdList = new ArrayList<>();
        for (UploadFileResponse fileResponse : uploadFileResponseList) {
            PubFilesEntity filesEntity = new PubFilesEntity();
            filesEntity.setId(IdUtil.fastSimpleUUID());
            filesEntity.setType(type);
            filesEntity.setBsnId(cpId);
            filesEntity.setFileName(fileResponse.getFileName());
            filesEntity.setFileExt(fileResponse.getFileExt());
            filesEntity.setFileSize(fileResponse.getFileSize());
            filesEntity.setFilePath(fileResponse.getFilePath());
            fileList.add(filesEntity);
            fileIdList.add(filesEntity.getId());
        }
        filesMapper.batchInsert(fileList);
        return fileIdList;
    }


    /**
     * 生成文件业务信息
     *
     * @param cpId
     * @param type
     * @return
     */
    private FileBsnInfo createBsnInfo(String cpId, String type) {
        FileBsnInfo bsnInfo = new FileBsnInfo();
        if (CPConstants.FILE_TYPE_CP_ASSET_CERTIFICATION.equals(type)) {
            bsnInfo.setBsnId(cpId);
            bsnInfo.setBsnType(type);
            bsnInfo.setBsnDesc("文化宫的工会资产证明");
        } else if (CPConstants.FILE_TYPE_CP_STAFFING_REPLY.equals(type)) {
            bsnInfo.setBsnId(cpId);
            bsnInfo.setBsnType(type);
            bsnInfo.setBsnDesc("文化宫的人员编制的批复");
        } else if (CPConstants.FILE_TYPE_CP_IDENTITY.equals(type)) {
            bsnInfo.setBsnId(cpId);
            bsnInfo.setBsnType(type);
            bsnInfo.setBsnDesc("文化宫的工人文化宫标识");
        } else if (CPConstants.FILE_TYPE_CP_OTHER.equals(type)) {
            bsnInfo.setBsnId(cpId);
            bsnInfo.setBsnType(type);
            bsnInfo.setBsnDesc("文化宫的其他文件");
        }
        return bsnInfo;
    }

    /**
     * 将数字集合转换为星期
     *
     * @param NoNum
     * @return
     */
    private String transformWeek(List<Integer> NoNum) {
        int state = 0;
        String result = "";
        for (int i = 0; i < NoNum.size(); i++) {
            if (i == NoNum.size() - 1) {
                state = 2;
            }
            if (state == 0) {
                if (NoNum.get(i + 1) == NoNum.get(i) + 1) {
                    result += Integer.toString(NoNum.get(i));
                    result += "-";
                    state = 1;
                } else {
                    result += Integer.toString(NoNum.get(i));
                    result += ",";
                }
            } else if (state == 1) {
                if (NoNum.get(i + 1) != NoNum.get(i) + 1) {
                    result += Integer.toString(NoNum.get(i));
                    result += ",";
                    state = 0;
                } else {
                    result += NoNum.get(i) + "-";
                }
            } else {
                result += Integer.toString(NoNum.get(i));
            }
        }

        String[] str = result.split(",");
        String week = "";
        for (int stritem = 0; stritem < str.length; stritem++) {
            String[] sp = str[stritem].split("-");
            week = week + getWeek(Integer.parseInt(sp[0])) + "——" + getWeek(Integer.parseInt(sp[sp.length - 1])) + ",";
        }
        return week.substring(0, week.length() - 1);
    }

    /**
     * 数字转星期
     *
     * @param week
     * @return
     */
    private String getWeek(int week) {
        switch (week) {
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            case 7:
                return "星期日";
            default:
                return "";
        }
    }

    /**
     * 根据id删除文化宫
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeCPInfoById(String id) {
        Map<String, Object> paramMap = new HashMap<>();
        int result;
        paramMap.put("modifier", "");
        // 单个删除
        paramMap.put("id", id);
        CPInfoVO cpInfoVO = cpInfoMapper.selectByPrimaryKey(id);
        result = cpInfoMapper.deleteCPInfoById(paramMap);
        if (result > 0) {
            delOrgan(cpInfoVO.getOrganId());
            delUser(cpInfoVO.getOrganCode());
        }
        return result;
    }
}
