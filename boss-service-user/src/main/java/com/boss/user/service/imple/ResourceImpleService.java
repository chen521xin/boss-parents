/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service.imple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.Policy;
import com.boss.core.db.Resource;
import com.boss.core.db.ResourceInfo;
import com.boss.core.pojo.InsurcompAndPolicyType;
import com.boss.user.mapper.ResourceMapper;
import com.boss.user.service.ResourceService;
import com.boss.utils.cons.CommonUtils;

/**
 * @description
 * @data 2018年2月4日下午5:05:35
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceImpleService extends BaseServiceImpl implements ResourceService {

	@Autowired
	private ResourceMapper resoruceMapper;

	@Autowired
	private UserServiceImpl userService;

	/**
	 * 根据角色id查询其赋予的所有权限
	 */
	@Override
	public List<Resource> findResourceByRole(Resource resource) {
		return resoruceMapper.findResourceByRole(resource);
	}

	/**
	 * 查询所有不分页
	 */
	@Override
	public List<Resource> findAllResource() {
		return resoruceMapper.findAllResource();
	}

	@Override
	public List<ResourceInfo> findMenuByRole(String roleCode) {
		if (roleCode == null) {
			roleCode = CommonUtils.ROLE_VISITOR;
		}
		List<Resource> resourceList = resoruceMapper.findMenuByRole(roleCode);
		return convertResource(resourceList);
	}

	public List<ResourceInfo> convertResource(List<Resource> resourceList) {
		if (resourceList == null) {
			return null;
		}
		boolean rb = true;
		boolean zgrs = true;
		boolean gsc = true;
		boolean cctb = true;
		if (!CommonUtils.ROLE_VISITOR.equals(getUserRole())) {
			rb = rb();
			zgrs = zgrs();
			gsc = gsc();
			cctb = cctb();
		}
		List<ResourceInfo> list = new ArrayList<ResourceInfo>();
		ResourceInfo info = null;
		List<Resource> childrenList = null;
		for (Resource parentResource : resourceList) {
			// 一级菜单控制
			if (parentResource.getId().equals("7f0828a449c311e8829218dbf239df73")) {
				// ecargo投保，当前用户配置了可投保才返回改菜单
				String flag = userService.selectUserIsAllowEcargo(getUserId());
				if (flag == null || CommonUtils.N.equals(flag)) {
					continue;
				}
			} else if (parentResource.getId().equals("bf7a65c43a3711e8913e18dbf239df73")) {
				// 保额投保一级菜单
				if (!zgrs && !rb && !gsc) {
					continue;
				}

			} else if (parentResource.getId().equals("4c19fd57623811e8adde18dbf239df73")) {
				// 车次投保一级菜单
				if (!cctb) {
					continue;
				}
			}else if(parentResource.getId().equals("768a4945470f11e89be818dbf239df73")){
				//产品管理
				if(!CommonUtils.ROLE_ADMIN.equals(getUserRole())){
					parentResource.setName("产品展示");
				}
			}
			if (parentResource.getParentId() == null) {
				// 二级菜单控制
				childrenList = new ArrayList<Resource>();
				info = new ResourceInfo();
				for (Resource resource : resourceList) {
					if (resource.getId().equals("a54c4fb7622511e8adde18dbf239df73")) {
						// 中国人寿保险
						if (!zgrs) {
							continue;
						}

					} else if (resource.getId().equals("aacdaf67622511e8adde18dbf239df73")) {
						// 人民保险
						if (!rb) {
							continue;
						}

					} else if (resource.getId().equals("aef248e1622511e8adde18dbf239df73")) {
						// 国寿财险
						if (!gsc) {
							continue;
						}

					}
					if (parentResource.getId().equals(resource.getParentId())) {
						childrenList.add(resource);
					}
				}
				info.setChildren(childrenList);
				info.setCurrent(parentResource);
				list.add(info);
			}
		}
		return list;
	}

	public boolean zgrs() {
		InsurcompAndPolicyType type = getType(CommonUtils.INSURCOMP_ID_RENSHOU, "2");
		List<Policy> list = resoruceMapper.getPolicyByUserId(type);
		if (list.size() <= 0) {
			return false;
		}
		return true;
	}

	public boolean gsc() {
		InsurcompAndPolicyType type = getType(CommonUtils.INSURCOMP_ID_GUOSHOU, "2");
		List<Policy> list = resoruceMapper.getPolicyByUserId(type);
		if (list.size() <= 0) {
			return false;
		}
		return true;
	}

	public boolean rb() {
		InsurcompAndPolicyType type = getType(CommonUtils.INSURCOMP_ID_RENBAO, "2");
		List<Policy> list = resoruceMapper.getPolicyByUserId(type);
		if (list.size() <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * 车次投保
	 * 
	 * @return
	 */
	public boolean cctb() {
		InsurcompAndPolicyType type = getType(CommonUtils.INSURCOMP_ID_RENSHOU, "1");
		List<Policy> list = resoruceMapper.getPolicyByUserId(type);
		if (list.size() <= 0) {
			return false;
		}
		return true;
	}

	public InsurcompAndPolicyType getType(String id, String isInsured) {
		InsurcompAndPolicyType type = new InsurcompAndPolicyType();
		type.setUserId(getUserId());
		type.setInsurcompId(id);
		type.setIsInsured(isInsured);
		return type;
	}

}
