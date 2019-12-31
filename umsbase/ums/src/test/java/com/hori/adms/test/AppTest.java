package com.hori.adms.test;



import java.util.Date;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hori.db.support.Page;
import com.hori.service.UserService;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class AppTest {
	@Autowired
	private UserService userService;
	@Test
	public void testHibernate(){
		try {
			//List<User> list = userService.findUser();
			//User user = userService.getById("1");
			System.out.println("---------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	@Test
	public void testSave(){
		User user = new User();
		user.setId("1");
		user.setUserAccount("admin");
		user.setUserType(1);
		user.setCreateTime(new Date());
		userService.save(user);
		for (int i = 0; i < 5; i++) {
			user.setUserAccount("kinglian"+i);
			user.setUserType(i);
			user.setCreateTime(new Date());
			userService.save(user);
		}
	}
	
	@Test
	public void testDelete(){
		userService.delete("1483496647037aa20763b3304f4a8e72");
	}
	
	@Test
	public void testUpdate(){
		User user= userService.findUserById("1483496646691376aa6270614a319106");
		if(null!=user){
			user.setUserType(10);
			userService.update(user);
		}
	}
	
	@Test
	public void testPage(){
		Page page = userService.search();
		if(page!=null){
			List<User> list =(List<User>) page.getResult();
			long pageCount = page.getTotalPageCount();
			long totalCount = page.getTotalCount();	
			System.out.println("-----------"+pageCount+"============="+totalCount);
			for (User user : list) {
				
				System.out.println(user);
			}
		}
	}*/
}


