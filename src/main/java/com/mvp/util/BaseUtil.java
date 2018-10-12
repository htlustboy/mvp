package com.mvp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

import com.mvp.model.User;



/**
 * 字符串工具类
 * @author hutao
 *
 */
public class BaseUtil {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	
	/**
	 * 判断一个字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str){
		if(str==null || str.length()==0){
			return false;
		}
		return true;
	}
	
	/**
	 * 字符串链接
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String stringConcat(String str1,String str2){
		if(str1==null && str2==null){
			return "";
		}
		if(str1==null && str2!=null){
			return str2;
		}
		if(str2==null && str1!=null){
			return str1;
		}
		return str1.concat(str2);
	}
	
	/**
	 * 字符串转数组
	 * @param str
	 * @return
	 */
	public static char[] string2Array(String str){
		if(isNotBlank(str)){
			return str.toCharArray();
		}
		return new char[0];
	} 
	
	/**
	 * 字符串反转
	 * @param str
	 * @return
	 */
	public static String string2Reverse(String str){
		if(isNotBlank(str)){
			return new StringBuilder(str).reverse().toString();
		}
		return "";
	}
	
	
	/**
	 * 两个数组相加，返回新的数组
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static String[] array2arrays(String[] array1,String[] array2){
		if(array1==null && array2==null){
			return null;
		}
		if(array1==null || array1.length==0){
			return array2;
		}
		if(array2==null || array2.length==0){
			return array1;
		}else{
			String[] result = new String[array1.length+array2.length];
			for (int i = 0; i < array1.length; i++) {
				result[i] = array1[i];
			}
			for (int i = 0; i < array2.length; i++) {
				result[array1.length+i]=array2[i];
			}
			return result;
		}
	}
	
	/**
	 * 判断数组中是否包含某个元素
	 * @param array
	 * @param obj
	 * @return
	 */
	public static boolean array2contain(String[] array,String obj){
		if(array==null || array.length==0){
			return false;
		}
		for (int i = 0; i < array.length; i++) {
			if(array[i].equals(obj)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 字符串转时间
	 * @param str
	 * @return
	 */
	public static Date string2Date(String str){
		if(isNotBlank(str)){
			try {
				return sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 时间转字符串
	 * @param date
	 * @return
	 */
	public static String date2String(Date date){
		return sdf.format(date);
	}
	
	/**
	 * 时间戳转时间字符串
	 * @param date
	 * @return
	 */
	public static String timeStamp2Date(long date){
		return sdf.format(new Date(date));
	}
	
	/**
	 * 时间转换成时间戳
	 * @param date
	 * @return
	 */
	public static long date2timeStamp(Date date){
		return date.getTime();
	}
	
	/**
	 * 字符串转Long型
	 * @param str
	 */
	public static long string2long(String str){
		return Long.parseLong(str);
	}
	
	/**
	 * long类型转字符串
	 * @param number
	 * @return
	 */
	public static String long2string(long number){
		return String.valueOf(number);
	}
	
	/**
	 * 字符串转int型
	 * @param str
	 * @return
	 */
	public static Integer string2int(String str){
		return Integer.parseInt(str);
	}
	
	/**
	 * int型转字符串
	 * @param number
	 * @return
	 */
	public static String int2string(int number){
		return String.valueOf(number);
	}
	
	/**
	 * 获取文件后缀名
	 * @param filepath
	 * @return
	 */
	public static String getSuffix(String filepath){
		if(isNotBlank(filepath)){
			if(filepath.lastIndexOf(".")==-1){
				return "";
			}else{
				return filepath.substring(filepath.lastIndexOf(".")+1);
			}
		}
		return "";
	}
	
	/**
	 * 文件路径转换，将'\'转换成'\\'
	 * @return
	 */
	public static String pathReplace(String path){
		if(path!=null && path.length()>0){
			return path.replaceAll("\\\\", "\\\\\\\\");
		}
		return path;
	}
	
	/**
	 * 文件路径转换，将'\\'转换成'/'
	 * @param path
	 * @return
	 */
	public static String pathReplace2(String path){
		if(path!=null && path.length()>0)
			return path.replaceAll("\\\\\\\\", "/");
		return path;
	}	
	
	/**
	 * 密码加密
	 * @param password
	 */
	public static String password2Hex(String userName,String password){
		String hashAlgorithmName = "MD5"; //加密方式
		int hashIterations = 1024; //加密次数
		Object salt = userName;
		return new SimpleHash(hashAlgorithmName, password, salt,hashIterations).toHex();
	}
	
	/**
	 * 注销当前用户
	 */
	public static void logout(){
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			currentUser.logout();
		}
	}
	
	/**
	 * 判断是否登陆
	 * @return
	 */
	public static boolean isLogin(){
		return SecurityUtils.getSubject().isAuthenticated();
	}
	
	/**
	 * @return 获取当前登陆的用户
	 */
	public static User getCurrentUser(){
		User user = null;
		try {
			user = (User) SecurityUtils.getSubject().getPrincipal();
		} catch (UnavailableSecurityManagerException e) {
		}
		return user;
	}
	
	/**
	 * 生成UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
	/**
	 * 获取指定长度的UUID字符串
	 * @param length
	 * @return
	 */
	public static String getRamdomUUID(int length){
		if(length<=0 || length>=32){
			length = 16;
		}
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, length);
	}
	
//	public static void main(String[] args) {
//		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
//		System.out.println(password2Hex("lustboy", "123456"));//f7c3aea7e18076502c9e29fc4d4d16f9
//		System.out.println(getCurrentUser());
//	}
	
}
