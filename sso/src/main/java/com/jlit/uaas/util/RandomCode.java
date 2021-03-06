package com.jlit.uaas.util;

/**
 * 因为手机不能存储第一位为0的随机码,因此在随机码生成时，限制第一位不能为0
 * @author damon
 *
 */
public class RandomCode {
	public static char[] generateCode4() {
		// 定义验证码的字符表
		String chars = "0123456789";

		char[] rands = new char[4];
		
		//生成随机数第一位不能为0
		int rand = (int) (Math.random() * 10);
		while(rand==0)
			rand = (int) (Math.random() * 10);
		rands[0] = chars.charAt(rand);
		
		for (int i = 1; i < 4; i++)
		{
			rand = (int) (Math.random() * 10);
			rands[i] = chars.charAt(rand);
		}

		return rands;
	}

	public static char[] generateCode8() {
		// 定义验证码的字符表
		String chars = "0123456789";

		char[] rands = new char[8];
		
		//生成随机数第一位不能为0
		int rand = (int) (Math.random() * 10);
		while(rand==0)
			rand = (int) (Math.random() * 10);
		rands[0] = chars.charAt(rand);
		
		for (int i = 1; i < 8; i++)
		{
			rand = (int) (Math.random() * 10);
			rands[i] = chars.charAt(rand);
		}

		return rands;
	}
	
	public static char[] generateCode16() {
		// 定义验证码的字符表
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		char[] rands = new char[16];
		//生成随机数第一位不能为0
		int rand = (int) (Math.random() * 36);
		while(rand==0)
			rand = (int) (Math.random() * 36);
		rands[0] = chars.charAt(rand);
		
		for (int i = 1; i < 16; i++)
		{
			rand = (int) (Math.random() * 36);
			rands[i] = chars.charAt(rand);
		}

		return rands;
	}
	
	public static void main(String[] args){
		System.out.println(RandomCode.generateCode4());
	}
}
