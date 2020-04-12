package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.inspur.vista.labor.cp.dao.CPPlaceInfoMapper;
import com.inspur.vista.labor.cp.dao.PubFilesMapper;
import com.inspur.vista.labor.cp.entity.*;
import com.inspur.vista.labor.cp.service.CPPlaceInfoService;
import com.inspur.vista.labor.cp.service.PubFilesService;
import com.inspur.vista.labor.cp.util.CPConstants;
import com.inspur.vista.labor.cp.util.CPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: CPPlaceInfoServiceImpl
 * @Description: 场所信息服务类
 * @Author: wangxueying01
 * @CreateDate: 2020/03/04 16:35
 * @Version: 1.0
 */
@Service
public class CPPlaceInfoServiceImpl implements CPPlaceInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CPPlaceInfoServiceImpl.class);

    @Autowired
    private CPPlaceInfoMapper cpPlaceInfoMapper;

    @Autowired
    private PubFilesService pubFilesService;

    @Autowired
    private PubFilesMapper filesMapper;

    @Value("${cp.gateway}")
    private String gatewayUrl;

    /**
     * 获取场所信息
     *
     * @param id 场所信息id
     * @return
     */
    @Override
    public CPPlaceInfoVO getCPPlaceInfo(String id) {
        CPPlaceInfoVO cpPlaceInfoVO = cpPlaceInfoMapper.selectByPrimaryKey(id);
        return cpPlaceInfoVO;
    }

    /**
     * 查询场所信息
     *
     * @param parameters
     * @return
     */
    @Override
    public Page<CPPlaceInfoListVO> listCPPlaceInfo(Map<String, Object> parameters) {
        int page = Convert.toInt(parameters.get("page"));
        int pageSize = Convert.toInt(parameters.get("pageSize"));
        Page<CPPlaceInfoListVO> p = new Page<>(page, pageSize);
        List<CPPlaceInfoListVO> cpPlaceInfoList = cpPlaceInfoMapper.listCPPlaceInfo(p, parameters);
        p.setRecords(cpPlaceInfoList);
        return p;
    }

    /**
     * 保存场所信息
     *
     * @param cpPlaceInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CPPlaceInfoEntity saveCPPlaceInfo(CPPlaceInfoEntity cpPlaceInfo) {
        if (null == cpPlaceInfo.getId() || "".equals(cpPlaceInfo.getId())) {
            cpPlaceInfo.setId(IdUtil.fastSimpleUUID());
            cpPlaceInfo.setState(CPConstants.INFO_VALID);
            cpPlaceInfoMapper.insertSelective(cpPlaceInfo);

            //新增1.会议室
            //    2.培训室
            //    3.阅览室 场所时默认生成一个场地
//            if (CPConstants.PLACE_TYPE_MEETING.equals(cpPlaceInfo.getType())
//                    || CPConstants.PLACE_TYPE_TRAINING.equals(cpPlaceInfo.getType())
//                    || CPConstants.PLACE_TYPE_READING.equals(cpPlaceInfo.getType())) {
//
//                CPCourtInfoEntity cpCourtInfo = new CPCourtInfoEntity();
//                cpCourtInfo.setPlaceId(cpPlaceInfo.getId());
//                switch (cpPlaceInfo.getType()) {
//                    case CPConstants.PLACE_TYPE_MEETING:
//                        cpCourtInfo.setName("会议室1区");
//                        cpCourtInfo.setType(CPConstants.PLACE_TYPE_MEETING + "1");
//                        break;
//                    case CPConstants.PLACE_TYPE_TRAINING:
//                        cpCourtInfo.setName("培训室1区");
//                        cpCourtInfo.setType(CPConstants.PLACE_TYPE_TRAINING + "1");
//                        break;
//                    case CPConstants.PLACE_TYPE_READING:
//                        cpCourtInfo.setName("阅览室1区");
//                        cpCourtInfo.setType(CPConstants.PLACE_TYPE_READING + "1");
//                        break;
//                    default:
//                        break;
//                }
//                cpCourtInfoService.saveCPCourtInfo(cpCourtInfo);
//            }
        } else {
            cpPlaceInfoMapper.updateByPrimaryKeySelective(cpPlaceInfo);
        }
        return cpPlaceInfo;
    }

    /**
     * 通过id删除场所信息
     *
     * @param ids 场所信息id的字符串数组
     * @return
     */
    @Override
    public int removeCPPlaceInfoById(String[] ids, String modifier) {
        Map<String, Object> paramMap = new HashMap<>();
        int result;
        paramMap.put("modifier", modifier);
        if (ids.length == 1) {
            // 单个删除
            paramMap.put("id", ids[0]);
            result = cpPlaceInfoMapper.deleteCPPlaceInfoById(paramMap);
        } else {
            // 批量删除
            paramMap.put("ids", ids);
            result = cpPlaceInfoMapper.batchDeleteCPPlaceInfoById(paramMap);
        }
        return result;
    }

    /**
     * 生成场所二维码
     *
     * @param placeId  场所id
     * @param response
     * @throws IOException
     */
    @Override
    public void generateQRCode(String placeId, HttpServletResponse response) throws IOException {

        // 生成二维码
        QrConfig config = new QrConfig(400, 400);
        config.setMargin(1);
        config.setRatio(3);
        // 高纠错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);

        Map<String, Object> map = new HashMap<>();
        map.put("type", CPConstants.URL_ROUTER_PLACE);
        map.put("id", placeId);
        String url = gatewayUrl + "&param=" + CPUtils.encryptQRCodeParam(map);
        BufferedImage image = QrCodeUtil.generate(url, config);

        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    /**
     * 上传文件
     *
     * @param file
     * @param type
     * @param placeId
     * @return
     */
    @Override
    public List<String> uploadFile(MultipartFile[] file, String type, String placeId) throws Exception {
        FileBsnInfo bsnInfo = new FileBsnInfo();
        bsnInfo.setBsnId(placeId);
        bsnInfo.setBsnType(type);
        bsnInfo.setBsnDesc("文化宫场所规章制度照片");
        List<UploadFileResponse> uploadFileResponseList = pubFilesService.updateFile(bsnInfo, file);
        // 2.新增附件信息
        List<PubFilesEntity> fileList = new ArrayList<>();
        List<String> fileIdList = new ArrayList<>();
        for (UploadFileResponse fileResponse : uploadFileResponseList) {
            PubFilesEntity filesEntity = new PubFilesEntity();
            filesEntity.setId(IdUtil.fastSimpleUUID());
            filesEntity.setType(type);
            filesEntity.setBsnId(placeId);
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
}
