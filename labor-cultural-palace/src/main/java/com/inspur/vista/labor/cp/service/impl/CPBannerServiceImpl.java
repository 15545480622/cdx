package com.inspur.vista.labor.cp.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.dao.CPBannerMapper;
import com.inspur.vista.labor.cp.entity.CPBannerEntity;
import com.inspur.vista.labor.cp.entity.CPBannerListVO;
import com.inspur.vista.labor.cp.entity.CPBannerVO;
import com.inspur.vista.labor.cp.service.CPBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPBannerServiceImpl
 * @Description: 文化宫banner
 * @authur: wangxueying01
 * @CreatDate: 2020/3/27 9:49
 */
@Service
public class CPBannerServiceImpl implements CPBannerService {

    @Autowired
    private CPBannerMapper cpBannerMapper;

    /**
     * 获取文化宫banner
     *
     * @param id
     * @return
     */
    @Override
    public CPBannerVO getCPBanner(String id) {
        return cpBannerMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询文化宫banner
     *
     * @param paramMap
     * @return
     */
    @Override
    public Page<CPBannerListVO> listCPBanner(Map<String, Object> paramMap) {
        int page = Convert.toInt(paramMap.get("page"));
        int pageSize = Convert.toInt(paramMap.get("pageSize"));
        Page<CPBannerListVO> p = new Page<>(page, pageSize);
        List<CPBannerListVO> cpBannerListVOList = cpBannerMapper.listCPBanner(p, paramMap);
        p.setRecords(cpBannerListVOList);
        return p;
    }

    /**
     * 保存文化宫banner
     *
     * @param cpBannerEntity
     * @return
     */
    @Override
    public CPBannerEntity saveCPBanner(CPBannerEntity cpBannerEntity) {
        if (null == cpBannerEntity.getId() || "".equals(cpBannerEntity.getId())) {
            cpBannerEntity.setId(IdUtil.fastSimpleUUID());
            cpBannerMapper.insertSelective(cpBannerEntity);
        } else {
            cpBannerMapper.updateByPrimaryKeySelective(cpBannerEntity);
        }
        return cpBannerEntity;
    }

    /**
     * 删除文化宫banner
     *
     * @param paramMap
     * @return
     */
    @Override
    public int removeCPBanner(Map<String, Object> paramMap) {
        String[] ids = (String[]) paramMap.get("ids");
        int result;
        if (ids.length == 1) {
            //单个删除
            paramMap.put("id", ids[0]);
            result = cpBannerMapper.deleteCPBanner(paramMap);
        } else {
            //批量删除
            result = cpBannerMapper.batchDeleteCPBanner(paramMap);
        }
        return result;
    }

    /**
     * 切换是否展示
     *
     * @param paramMap
     * @return
     */
    @Override
    public int changeIsShow(Map<String, Object> paramMap) {
        return cpBannerMapper.updateIsShow(paramMap);
    }

    /**
     * 排序功能
     *
     * @param paramMap
     * @return
     */
    @Override
    public void sort(Map<String, Object> paramMap) {
        CPBannerVO cpBannerVO = cpBannerMapper.selectByPrimaryKey((String) paramMap.get("id"));
        if (null != cpBannerVO) {
            paramMap.put("isShow", cpBannerVO.getIsShow());
            paramMap.put("bannerOrder", cpBannerVO.getBannerOrder());

            CPBannerListVO cpBannerListVO = cpBannerMapper.getNextOrPre(paramMap);

            if (null != cpBannerListVO) {
                int theCur = (int) paramMap.get("bannerOrder");
                int theAdjoin = cpBannerListVO.getBannerOrder();
                CPBannerEntity theCurBanner = new CPBannerEntity();
                CPBannerEntity theAdjoinBanner = new CPBannerEntity();

                theCurBanner.setId((String) paramMap.get("id"));
                theCurBanner.setBannerOrder(theAdjoin);
                theCurBanner.setModifier((String) paramMap.get("modifier"));
                cpBannerMapper.updateByPrimaryKeySelective(theCurBanner);

                theAdjoinBanner.setId(cpBannerListVO.getId());
                theAdjoinBanner.setBannerOrder(theCur);
                theAdjoinBanner.setModifier((String) paramMap.get("modifier"));
                cpBannerMapper.updateByPrimaryKeySelective(theAdjoinBanner);
            }
        }
    }

    /**
     * 不分页查询文化宫banner
     *
     * @param cpId
     * @return
     */
    @Override
    public List<CPBannerListVO> listCPBannerInfo(String cpId) {
        return cpBannerMapper.listCPBannerInfo(cpId);
    }

}
