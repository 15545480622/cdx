package com.inspur.vista.labor.cp.service.impl;

import com.inspur.vista.labor.cp.dao.OrganMapper;
import com.inspur.vista.labor.cp.entity.OrganEntity;
import com.inspur.vista.labor.cp.entity.TreeDataVO;
import com.inspur.vista.labor.cp.service.OrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Title: OrganServiceImpl
 * @Description: 工会组织服务类
 * @Author: gengpeng
 * @CreateDate: 2019/9/20 19:41
 * @Version: 1.0
 */
@Service
public class OrganServiceImpl implements OrganService {

    @Autowired
    private OrganMapper organMapper;

    @Override
    public List<TreeDataVO> getOrganTree(String organId) {
        List<TreeDataVO> treeDataVOList = new LinkedList<>();
        if ("rootId".equals(organId)) {
            TreeDataVO treeDataVO = new TreeDataVO();
            OrganEntity organ = organMapper.selectByOrganId("O370000000000");
            treeDataVO.setOrganId("O370000000000");
            treeDataVO.setOrganName(organ.getOrganName());
            treeDataVO.setOrganType(organ.getType());
            treeDataVO.setParentOrganId("rootId");
            treeDataVO.setStruId(organ.getStruId());
            treeDataVO.setStruOrder(organ.getStruOrder());
            treeDataVO.setStruLevel(organ.getStruLevel());
            treeDataVO.setIsLeaf("1");
            treeDataVOList.add(treeDataVO);
        } else {
            OrganEntity parentOrgan = organMapper.selectByOrganId(organId);
            Map param = new LinkedHashMap();
            if (null != parentOrgan) {
                param.put("struPath", parentOrgan.getStruPath());
                param.put("level", parentOrgan.getStruLevel() + 1);
                List<OrganEntity> organList = organMapper.listByStruPathLike(param);
                for (OrganEntity organ : organList) {
                    if (!"2".equals(organ.getType()) && !"28".equals(organ.getType())) {
                        TreeDataVO treeDataVO = new TreeDataVO();
                        treeDataVO.setOrganId(organ.getOrganId());
                        treeDataVO.setOrganName(organ.getOrganName());
                        treeDataVO.setOrganType(organ.getType());
                        treeDataVO.setParentOrganId(organId);
                        treeDataVO.setStruId(organ.getStruId());
                        treeDataVO.setStruOrder(organ.getStruOrder());
                        treeDataVO.setStruLevel(organ.getStruLevel());
                        if ("24".equals(organ.getType()) || "2288".equals(organ.getType()) || "22991".equals(organ.getType()) || "22992".equals(organ.getType())
                                || "22993".equals(organ.getType()) || "21991".equals(organ.getType()) || "21992".equals(organ.getType())
                                || "21993".equals(organ.getType())) {
                            treeDataVO.setIsLeaf("0");
                        }
                        treeDataVOList.add(treeDataVO);
                    }
                }
            }

        }
        return treeDataVOList;
    }

    /**
     * 通过organId获取组织信息
     *
     * @param organId
     * @return
     */
    @Override
    public OrganEntity getOrganByOrganId(String organId) {
        return organMapper.selectByOrganId(organId);
    }
}
