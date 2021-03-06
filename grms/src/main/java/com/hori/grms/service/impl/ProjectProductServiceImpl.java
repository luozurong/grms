/**
 * 
 */
package com.hori.grms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hori.grms.dao.ProjectProductMapper;
import com.hori.grms.dao.ProjectProductMenuMapper;
import com.hori.grms.model.ProjectProduct;
import com.hori.grms.model.ProjectProductExample;
import com.hori.grms.service.ProjectProductService;
import com.hori.grms.vo.ProjectProductVo;
import com.hori.grms.vo.project.ProductMenuVo;
import com.hori.grms.vo.project.ProjectMenuVo;

/** 
 * @ClassName: ProjectProductServiceImpl 
 * @Description: 项目清单表
 * @author zhuqiang
 * @date 2018年8月8日 下午7:05:55 
 */
@Service("projectProductService")
public class ProjectProductServiceImpl implements ProjectProductService{
	@Autowired
	private ProjectProductMapper projectProductMapper;
	@Autowired
	private ProjectProductMenuMapper projectProductMenuMapper;
	
	@Override
	public List<ProjectMenuVo> findVoByProjectCode(String projectCode) {
		List<ProjectMenuVo> projectProductList =  projectProductMapper.findVoByProjectCode(projectCode);
		if(projectProductList!=null&&projectProductList.size()>0) {
			for(ProjectMenuVo vo :projectProductList) {
				vo.setProjectMenus(projectProductMenuMapper.findVoByProductMenuId(vo.getId()));
				if(vo.getBusinessType().equals( "1")) {
					vo.setBusinessName("社区运营");
					if(vo.getProjectMenus()!=null&&vo.getProjectMenus().size()>0) {
						for(ProductMenuVo ivo : vo.getProjectMenus() ) {
							ivo.setAreaNames(vo.getAreaName());
						}
					}
				}else if(vo.getBusinessType().equals( "2")) {
					vo.setBusinessName("媒管");
				}else if(vo.getBusinessType().equals( "3")) {
					vo.setBusinessName("用户运营");
				}else if(vo.getBusinessType().equals( "4")) {
					vo.setBusinessName("电商运营");
				}
			}
		}
		return projectProductList;
	}

	@Override
	public ProjectProduct getById(String projectProductId) {
		return projectProductMapper.selectByPrimaryKey(projectProductId);
	}

	@Override
	public List<ProjectProduct> getListByProject(String projectCode) {
		Assert.notNull(projectCode, "projectCode 不能为空");
		ProjectProductExample example=new ProjectProductExample();
		example.or().andProjectCodeEqualTo(projectCode);//条件
		List<ProjectProduct> list=this.projectProductMapper.selectByExample(example);
		return list;
	}

	@Override
	public void update(ProjectProduct projectProduct) {
		projectProductMapper.updateByPrimaryKeySelective(projectProduct);
	}

	@Override
	public List<ProjectProductVo> getProductsOrderBy(String projectCode) {
		return projectProductMapper.selectByProjectCode(projectCode);
	}
}
