package com.mvp.common;

import java.io.Serializable;

public class TaskStatus implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4543369694369431785L;

	public enum Result{
		/** 失败  */
		NG,
		/** 成功  */
		OK
	}
	private Result result;
	private String message;
	
	/** 处理成功 **/
	public static TaskStatus SUCCESS(){
		return get(Result.OK);
	}
	
	/** 处理成功 **/
	public static TaskStatus SUCCESS(String message){
		return get(Result.OK,message);
	}
	
	/** 处理失败 **/
	public static TaskStatus WARNING(){
		return get(Result.NG);
	}
	
	public static TaskStatus WARNING(String message){
		return get(Result.NG,message);
	}
	
	public static TaskStatus get(Result result){
		return new TaskStatus(result);
	}
	
	public static TaskStatus get(Result result,String message){
		return new TaskStatus(result,message);
	}
	
	public TaskStatus(){
		this.result = Result.OK;
		this.message = "处理成功";
	}
	
	public TaskStatus(Result result){
		this.result = result;
		if(result == Result.OK){
			this.message = "处理成功";
		}else{
			this.message = "处理失败";
		}
	}
	
	public boolean isSuccess(){
		return this.result == Result.OK;
	}
	
	public TaskStatus(Result result,String message){
		this.result = result;
		this.message = message;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
