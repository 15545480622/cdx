package com.inspur.vista.labor.cp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.inspur.vista.labor.cp.entity.CPBannerEntity;
import com.inspur.vista.labor.cp.entity.CPBannerListVO;
import com.inspur.vista.labor.cp.entity.CPBannerVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CPBannerService
 * @Description: 文化宫banner
 * @authur: wangxueying01
 * @CreatDate: 2020/3/27 9:49
 */
public interface CPBannerService {

    /**
     * 获取文化宫banner
     *
     * @param id
     * @return
     */
    CPBannerVO getCPBanner(String id);

    /**
     * 分页查询文化宫banner
     *
     * @param paramMap
     * @return
     */
    Page<CPBannerListVO> listCPBanner(Map<String, Object> paramMap);

    /**
     * 保存文化宫banner
     *
     * @param cpBannerEntity
     * @return
     */
    CPBannerEntity saveCPBanner(CPBannerEntity cpBannerEntity);

    /**
     * 删除文化宫banner
     *
     * @param paramMap
     * @return
     */
    int removeCPBanner(Map<String, Object> paramMap);

    /**
     * 切换是否展示
     *
     * @param paramMap
     * @return
     */
    int changeIsShow(Map<String, Object> paramMap);

    /**
     * 排序功能
     *
     * @param paramMap
     * @return
     */
    void sort(Map<String, Object> paramMap);

    /**
     * 不分页查询文化宫banner
     *
     * @param cpId
     * @return
     */
    List<CPBannerListVO> listCPBannerInfo(String cpId);
}