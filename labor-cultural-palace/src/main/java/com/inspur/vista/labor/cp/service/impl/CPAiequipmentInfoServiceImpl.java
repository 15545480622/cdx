package com.inspur.vista.labor.cp.service.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPAiequipmentInfoMapper;
import com.inspur.vista.labor.cp.entity.CPAiequipmentInfoEntity;
import com.inspur.vista.labor.cp.entity.CPAiequipmentInfoListVO;
import com.inspur.vista.labor.cp.entity.CPAiequipmentInfoVO;
import com.inspur.vista.labor.cp.service.CPAiequipmentInfoService;
import com.inspur.vista.labor.cp.util.CPConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: CPAiequipmentInfoServiceImpl
 * @Description: 智能设备信息服务类
 * @Author: liuzq
 * @CreateDate: 2020/03/07 14:37
 * @Version: 1.0
 */
@Service
public class CPAiequipmentInfoServiceImpl implements CPAiequipmentInfoService {

		private static final Logger logger = LoggerFactory.getLogger(CPAiequipmentInfoServiceImpl.class);

		@Autowired
    	private CPAiequipmentInfoMapper cpAiequipmentInfoMapper;

    /**
     * 获取智能设备信息
     *
     * @param id 智能设备信息id
     * @return
     */
    @Override
    public CPAiequipmentInfoVO getCPAiequipmentInfo(String id) {
        CPAiequipmentInfoVO cpAiequipmentInfoVO = cpAiequipmentInfoMapper.selectByPrimaryKey(id);
        return cpAiequipmentInfoVO;
    }

		/**
		 * 查询智能设备信息
		 *
		 * @param parameters
		 * @return
		 */
		@Override
		public Page<CPAiequipmentInfoListVO> listCPAiequipmentInfo(Map<String, Object> parameters) {
			int page = Convert.toInt(parameters.get("page"));
			int pageSize = Convert.toInt(parameters.get("pageSize"));
			Page<CPAiequipmentInfoListVO> p = new Page<>(page, pageSize);
			List<CPAiequipmentInfoListVO> cpAiequipmentInfoList = cpAiequipmentInfoMapper.listCPAiequipmentInfo(p, parameters);
			p.setRecords(cpAiequipmentInfoList);
			return p;
		}

		/**
		 * 保存智能设备信息
		 *
		 * @param cpAiequipmentInfo
		 * @return
		 */
		@Override
		@Transactional(rollbackFor = Exception.class)
		public CPAiequipmentInfoEntity saveCPAiequipmentInfo(CPAiequipmentInfoEntity cpAiequipmentInfo) {
            if (null == cpAiequipmentInfo.getId() || "".equals(cpAiequipmentInfo.getId())) {
			cpAiequipmentInfo.setId(IdUtil.fastSimpleUUID());
				cpAiequipmentInfo.setState(CPConstants.INFO_VALID);
				cpAiequipmentInfoMapper.insertSelective(cpAiequipmentInfo);
			} else {
				cpAiequipmentInfoMapper.updateByPrimaryKeySelective(cpAiequipmentInfo);
			}
			return cpAiequipmentInfo;
		}

		/**
		 * 通过id删除智能设备信息
		 *
		 * @param ids 智能设备信息id的字符串数组
		 * @return
		 */
		@Override
		public int removeCPAiequipmentInfoById(String[] ids) {
			Map<String, Object> paramMap = new HashMap<>();
			int result;
			paramMap.put("modifier", "");
			if (ids.length == 1) {
				// 单个删除
				paramMap.put("id", ids[0]);
				result = cpAiequipmentInfoMapper.deleteCPAiequipmentInfoById(paramMap);
			} else {
				// 批量删除
				paramMap.put("ids", ids);
				result = cpAiequipmentInfoMapper.batchDeleteCPAiequipmentInfoById(paramMap);
			}
			return result;
		}
}
