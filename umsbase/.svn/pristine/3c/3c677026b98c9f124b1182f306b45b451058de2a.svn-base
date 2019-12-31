package com.hori.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hori.comparator.AuthComparator;
import com.hori.dao.AuthDao;
import com.hori.dao.BaseDaoI;
import com.hori.model.Auth;
import com.hori.pageModel.TreeNode;
import com.hori.service.AuthService;
import com.hori.vo.AuthVo;

/**
 * 权限Service
 * 
 * @author 
 * 
 */
@Service
public class AuthServiceImpl extends BaseServiceImpl implements AuthService {
	@Autowired
	private AuthDao authDao;

	/**
	 * 获得权限treegrid
	 */
	/*@Transactional(propagation = Propagation.SUPPORTS)*/
	public List<AuthVo> treegrid(AuthVo authVo) {
		List<Auth> auths = authDao.getListFortreegrid(authVo);
		return getAuthsFromTauths(auths);
	}

	private List<AuthVo> getAuthsFromTauths(List<Auth> auths) {
		List<AuthVo> authVos = new ArrayList<AuthVo>();
		if (auths != null && auths.size() > 0) {
			for (Auth t : auths) {
				AuthVo a = new AuthVo();
				BeanUtils.copyProperties(t, a);
				if (t.getAuth() != null) {
					a.setCpid(t.getAuth().getId());
					a.setCpname(t.getAuth().getCname());
				}
				if (countChildren(t.getId()) > 0) {
					a.setState("closed");
				}
				authVos.add(a);
			}
		}
		return authVos;
	}

	private Long countChildren(String pid) {
		return authDao.countChildren(pid);
	}

	public void delete(AuthVo authVo) {
		del(authVo.getId());
	}

	private void del(String id) {
		Auth t = authDao.get(Auth.class, id);
		if (t != null) {
			authDao.delTroletauth(t);
			Set<Auth> auths = t.getAuths();
			if (auths != null && !auths.isEmpty()) {
				// 说明当前权限下面还有子权限
				for (Auth auth : auths) {
					del(auth.getId());
				}
			}
			authDao.delete(t);
		}
	}

	/*@Transactional(propagation = Propagation.SUPPORTS)*/
	public List<TreeNode> tree(AuthVo authVo, boolean b) {
		List<Auth> l = authDao.getListFortree(authVo, b);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (Auth t : l) {
			tree.add(tree(t, b));
		}
		return tree;
	}

	private TreeNode tree(Auth t, boolean recursive) {
		TreeNode node = new TreeNode();
		node.setId(t.getId());
		node.setText(t.getCname());
		Map<String, Object> attributes = new HashMap<String, Object>();
		node.setAttributes(attributes);
		if (t.getAuths() != null && t.getAuths().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Auth> l = new ArrayList<Auth>(t.getAuths());
				Collections.sort(l, new AuthComparator());// 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (Auth r : l) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
			}
		}
		return node;
	}

	public void add(AuthVo authVo) {
		Auth t = new Auth();
		BeanUtils.copyProperties(authVo, t);
		if (authVo.getCpid() != null && !authVo.getCpid().equals(authVo.getId())) {
			t.setAuth(authDao.get(Auth.class, authVo.getCpid()));
		}
		authDao.save(t);
	}

	public void edit(AuthVo authVo) {
		Auth t = authDao.get(Auth.class, authVo.getId());// 要修改的权限
		BeanUtils.copyProperties(authVo, t);
		if (authVo.getCpid() != null && !authVo.getCpid().equals(authVo.getId())) {
			Auth pAuth = authDao.get(Auth.class, authVo.getCpid());// 要设置的上级权限
			if (pAuth != null) {
				if (isDown(t, pAuth)) {// 要将当前节点修改到当前节点的子节点中
					Set<Auth> auths = t.getAuths();// 当前要修改的权限的所有下级权限
					if (auths != null && auths.size() > 0) {
						for (Auth auth : auths) {
							if (auth != null) {
								auth.setAuth(null);
							}
						}
					}
				}
				t.setAuth(pAuth);
			}
		}
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点
	 * 
	 * @param t
	 * @param pt
	 * @return
	 */
	private boolean isDown(Auth t, Auth pt) {
		if (pt.getAuth() != null) {
			if (pt.getAuth().getId().equals(t.getId())) {
				return true;
			} else {
				return isDown(t, pt.getAuth());
			}
		}
		return false;
	}

	
	/**
	 * 根据请求参数,去权限表查询看是否存在配置
	 * @param auth
	 * @return
	 */
	public boolean findAuthExist(String curl) {
		return authDao.findAuthExist(curl);
	}

	/**
	 * 追加参数：权限类型
	 */
	public List<TreeNode> treeByCtype(AuthVo authVo, boolean b) {
		List<Auth> l = authDao.getListForTreeByCtype(authVo, b);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (Auth t : l) {
			tree.add(tree(t, b));
		}
		return tree;
	}

	@Override
	public List<Auth> getTopMenu() {
		// TODO Auto-generated method stub
		return authDao.getTopMenu();
	}

	@Override
	public List<AuthVo> getOneLevelMenu(String roleId) {
		// TODO Auto-generated method stub
		return authDao.getOneLevelMenu(roleId);
	}

	@Override
	public List<Auth> getAuthByPid(String pid) {
		// TODO Auto-generated method stub
		return this.getAuthByPid(pid);
	}

}
