package com.hori.service.impl;

import com.hori.service.BaseServiceI;

/**
 * 基础Service,所有ServiceImpl需要extends此Service来获得默认事务的控制
 * 
 * @author 
 * 
 */
/*@Service("baseService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)*/
public class BaseServiceImpl  implements BaseServiceI {
}
