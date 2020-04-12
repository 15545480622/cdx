package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPAccountMapper;
import com.inspur.vista.labor.cp.entity.CPAccountEntity;
import com.inspur.vista.labor.cp.entity.CPAccountVO;
import com.inspur.vista.labor.cp.service.CPAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Title: CPAccountServiceImpl
 * @Description: 文化宫收款账号服务类
 * @Author: gengpeng
 * @CreateDate: 2019/12/6 16:59
 * @Version: 1.0
 */
@Service
public class CPAccountServiceImpl implements CPAccountService {

    @Autowired
    private CPAccountMapper cpAccountMapper;

    /**
     * 获取有效的账号
     *
     * @param id
     * @param type
     * @return
     */
    @Override
    public CPAccountVO getActiveAccount(String cpId, int type) {
        CPAccountVO cpAccountVO = new CPAccountVO();
        CPAccountEntity cpAccountEntity = cpAccountMapper.selectEffectiveAccountByType(cpId, type);
        cpAccountVO.setType(cpAccountEntity.getType());
        cpAccountVO.setAccount(cpAccountEntity.getAccount());
        cpAccountVO.setActiveTime(cpAccountEntity.getActiveTime());
        cpAccountVO.setFailureTime(cpAccountEntity.getFailureTime());
        return cpAccountVO;
    }


    /**
     * 加载账号列表
     *
     * @return
     */
    @Override
    public Page<CPAccountVO> listAccount(Map<String, Object> parameters) {
        int page = Integer.parseInt(Objects.toString(parameters.get("page")));
        int pageSize = Integer.parseInt(Objects.toString(parameters.get("pageSize")));
        Page<CPAccountVO> p = new Page<>(page, pageSize);
        List<CPAccountVO> schoolInfoListVOList;
        schoolInfoListVOList = cpAccountMapper.listAccount(p, parameters);
        p.setRecords(schoolInfoListVOList);
        return p;
    }

    /**
     * 保存文化宫收款账号
     *
     * @param cpAccountEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CPAccountEntity cpAccountEntity) {

        // 先查询文化宫下有没有这个类型的账号
        CPAccountEntity accountEntityInDb = cpAccountMapper.selectLastAccountByType(cpAccountEntity.getCpId(), cpAccountEntity.getType());
        // 如果有
        if (null != accountEntityInDb && !accountEntityInDb.getAccount().equals(cpAccountEntity.getAccount())) {
            // 旧账号的失效时间是新账号启用的前一天
            accountEntityInDb.setFailureTime(DateUtil.offsetDay(cpAccountEntity.getActiveTime(), -1));
            cpAccountMapper.updateFailureTime(accountEntityInDb);
            cpAccountMapper.insertSelective(cpAccountEntity);
        } else if (null == accountEntityInDb) {
            cpAccountMapper.insertSelective(cpAccountEntity);
        }
    }
}
