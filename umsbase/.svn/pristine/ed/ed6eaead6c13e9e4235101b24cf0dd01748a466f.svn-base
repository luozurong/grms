package com.hori.utils;

import java.math.BigInteger;

/**
 * 对字节数组进行各种处理的工具类
 * 
 * @author 佘晓彬、暨文醒
 * 
 */
public class ByteOps {

	/**
	 * 将分别存储在2个字节中的数值转换为int类型
	 * 
	 * @param b1
	 *            高位字节
	 * @param b0
	 *            低位字节
	 * @return int类型的数值
	 */
	public static int makeInt(byte b1, byte b0) {
		return ((b1 << 8) | (b0 & 0xff)) & 0xffff;
	}

	/**
	 * 将分别存储在2个字节中的数值转换为String类型
	 * 
	 * @param b1
	 *            高位字节
	 * @param b0
	 *            低位字节
	 * @return 以String类型表示的数值
	 */
	public static String makeInt2String(byte b1, byte b0) {
		int i = (((b1 << 8) | (b0 & 0xff)) & 0xffff);
		return Integer.toString(i);
	}
	
	/**
	 * 将一个字节长度的数值转换为short类型
	 * 
	 * @param b0
	 *            以一个字节形式存放的数值
	 * @return short类型的数值
	 */
	public static short makeShort(byte b0) {
		return (short) ((0 << 8) | (b0 & 0xff));
	}

	/**
	 * 将一个字节长度的数值转换为String类型
	 * 
	 * @param b0
	 *            以一个字节形式存放的数值
	 * @return int类型的数值
	 */
	public static String makeShort2String(byte b0) {
		short i = (short) ((0 << 8) | (b0 & 0xff));
		return Short.toString(i);
	}

	/**
	 * 将分别存储在4个字节中的数值转换为String类型
	 * 
	 * @param b3
	 *            最高位字节
	 * @param b2
	 *            第2位字节
	 * @param b1
	 *            第3位字节
	 * @param b0
	 *            最低位字节
	 * @return 以String类型表示的数值
	 */
	public static String makeLong2String(byte b3, byte b2, byte b1, byte b0) {
		long i = ((((long) 0 & 0xff) << 56) | (((long) 0 & 0xff) << 48)
				| (((long) 0 & 0xff) << 40) | (((long) 0 & 0xff) << 32)
				| (((long) b3 & 0xff) << 24) | (((long) b2 & 0xff) << 16)
				| (((long) b1 & 0xff) << 8) | (((long) b0 & 0xff) << 0));

		return Long.toString(i);
	}

	/**
	 * 将分别存储在4个字节中的数值转换为long类型
	 * 
	 * @param b3
	 *            最高位字节
	 * @param b2
	 *            第2位字节
	 * @param b1
	 *            第3位字节
	 * @param b0
	 *            最低位字节
	 * @return 以long类型表示的数值
	 */
	public static long makeLong(byte b3, byte b2, byte b1, byte b0) {
		long i = ((((long) 0 & 0xff) << 56) | (((long) 0 & 0xff) << 48)
				| (((long) 0 & 0xff) << 40) | (((long) 0 & 0xff) << 32)
				| (((long) b3 & 0xff) << 24) | (((long) b2 & 0xff) << 16)
				| (((long) b1 & 0xff) << 8) | (((long) b0 & 0xff) << 0));

		return i;
	}

	/**
	 * 将二进制字节数组表示的数值转换为String类型输出
	 * 
	 * @param magnitude
	 *            以二进制字节数组表示的数值，为无符号正数
	 * @return 以String类型表示的数值
	 */
	public static String makeBigInteger2String(byte[] magnitude) {
		BigInteger bigInteger = new BigInteger(1, magnitude);
		return bigInteger.toString();
	}

	/**
	 * 以字节数组中指定部分生成新的字节数组
	 * 
	 * @param pdu
	 *            需要从中复制数据的字节数组
	 * @param off
	 *            开始位置
	 * @param len
	 *            需要复制的长度
	 * @return 生成的新字节数组
	 */
	public static byte[] cpByteArray(byte[] pdu, int off, int len) {
		byte[] tempByte = new byte[len];
		int end = off + len;
		for (int i = off, j = 0; i < end; i++, j++)
			tempByte[j] = pdu[i];
		return tempByte;
	}

	private final static byte[] hex = "0123456789ABCDEF".getBytes();

	/**
	 * 从字节数组到十六进制字符串转换
	 * 
	 * @param b
	 *            需要转换成十六进制字符串显示的字节数组
	 * @return 转换后的十六进制字符串
	 */
	public static String bytes2HexString(byte[] b) {
		byte[] buff = new byte[3 * b.length];
		for (int i = 0; i < b.length; i++) {
			buff[3 * i] = hex[(b[i] >> 4) & 0x0f];
			buff[3 * i + 1] = hex[b[i] & 0x0f];
			buff[3 * i + 2] = 32;
		}
		return new String(buff);
	}
	
	
	public static byte[] int2ByteArray(int number, int bytes) {
		int temp = number;
		byte[] b = new byte[bytes];
		for (int i = b.length - 1; i > -1; i--) {
			b[i] = new Integer(temp & 0xff).byteValue();
			temp = temp >> 8;
		}
		return b;
	}
	
   
	public static byte[] long2FourByte(long v) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (v >> 8 * (3 - i) & 0xFF);
		}
		return b;
	}
	
	
	public static byte[] bigInteger2EightBytes(String v){
		byte[] b = null;
		BigInteger bi = new BigInteger(v);
		byte[] tempByte = bi.toByteArray();
		if (tempByte.length <= 8) {
			b = new byte[8];	
			for (int i =0 ; i< 8-tempByte.length; i++) 
				b[i] = 0;
			for (int i =0 ; i< tempByte.length; i++)
				b[8-tempByte.length+i] = tempByte[i];
		}
		return b;
	}


}
