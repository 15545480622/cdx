package com.inspur.vista.labor.cp.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPBannerEntity;
import com.inspur.vista.labor.cp.entity.CPBannerListVO;
import com.inspur.vista.labor.cp.entity.CPBannerVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPBannerMapper
 * @Description: 文化宫banner
 * @authur: wangxueying01
 * @CreatDate: 2020/3/27 9:48
 */
public interface CPBannerMapper {

    /**
     * 获取banner
     *
     * @param id
     * @return
     */
    CPBannerVO selectByPrimaryKey(String id);

    /**
     * 新增banner
     *
     * @param cpBannerEntity
     * @return
     */
    int insertSelective(CPBannerEntity cpBannerEntity);

    /**
     * 更新banner
     *
     * @param cpBannerEntity
     * @return
     */
    int updateByPrimaryKeySelective(CPBannerEntity cpBannerEntity);

    /**
     * 分页查询banner
     *
     * @param page
     * @param parameters
     * @return
     */
    List<CPBannerListVO> listCPBanner(Page page, Map<String, Object> parameters);

    /**
     * 删除banner
     *
     * @param paramMap
     * @return
     */
    int deleteCPBanner(Map<String, Object> paramMap);

    /**
     * 批量删除banner
     *
     * @param paramMap
     * @return
     */
    int batchDeleteCPBanner(Map<String, Object> paramMap);

    /**
     * 切换是否展示
     *
     * @param paramMap
     * @return
     */
    int updateIsShow(Map<String, Object> paramMap);

    /**
     * 获取相邻banner
     *
     * @param paramMap
     * @return
     */
    CPBannerListVO getNextOrPre(Map<String, Object> paramMap);

    /**
     * 不分页查询banner
     *
     * @param cpId
     * @return
     */
    List<CPBannerListVO> listCPBannerInfo(String cpId);
}