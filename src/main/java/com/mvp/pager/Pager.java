package com.mvp.pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 分页
 * @author ht
 *
 */
public class Pager {
	
	//默认每页显示记录数
	private static final int DEFAULT_PAGE_SIZE = 10;
	
	//当前页数
	private int pageNo;
	//总页数
	private int totalPageSize;
	//结果集合
	private List<?> result;
	//是否启用分页
	private boolean isUsePage;
	//搜索条件
	private HashMap<String, String> searchInfo;
	
	/**
	 * 获取每页显示的记录数
	 * @return
	 */
	public int getPageSize(){
		return DEFAULT_PAGE_SIZE;
	}
	
	/**
	 * 获取当前页
	 * @return
	 */
	public int getPageNo(){
		return pageNo==0 ? 1 : pageNo;
	}
	
	/**
	 * 设置当前页
	 * @param pageNo
	 */
	public void setPageNo(int pageNo){
		if(pageNo < 1 || pageNo > getTotalPageSize()){
			this.pageNo = 1;
		}else{
			this.pageNo = pageNo;
		}
	}
	

	/**
	 * 获取总记录数
	 * @return
	 */
	public int getTotalItems() {
		return result==null ? 0 : result.size();
	}
	
	/**
	 * 获取总的页数
	 * @return
	 */
	public int getTotalPageSize() {
		if(getTotalItems() % DEFAULT_PAGE_SIZE == 0){
			this.totalPageSize = getTotalItems() / DEFAULT_PAGE_SIZE;
		}else{
			this.totalPageSize = getTotalItems() / DEFAULT_PAGE_SIZE + 1;
		}
		return totalPageSize;
	}
	
	/**
	 * 是否有下一页
	 * @param pageNo
	 * @return
	 */
	public boolean isHasNext(int pageNo){
		return pageNo >= getTotalPageSize() ? false : true;
	}
	
	/**
	 * 是否有上一页
	 * @return
	 */
	public boolean isHasPrev(int pageNo){
		return pageNo <= 1 ? false : true;
	}
	
	/**
	 * 下一页
	 * @param pageNo
	 * @return
	 */
	public int doNext(int pageNo){
		if(isHasNext(pageNo)){
			return pageNo + 1;
		}
		return pageNo;
	}
	
	/**
	 * 上一页
	 * @param pageNo
	 * @return
	 */
	public int doPrev(int pageNo){
		if(isHasPrev(pageNo)){
			return pageNo - 1;
		}
		return pageNo;
	}
	
	/**
	 * 获取结果
	 * @return
	 */
	public List<?> getResult() {
		return result;
	}
	
	/**
	 * 设置结果
	 * @param result
	 */
	public void setResult(List<?> result) {
		this.result = result;
	}
	
	/**
	 * 首页
	 * @return
	 */
	public int getFirstPage(){
		return 1;
	}
	
	/**
	 * 尾页
	 * @return
	 */
	public int getLastPage(){
		return getTotalPageSize();
	}
	
	/**
	 * 是否启用分页
	 * @return
	 */
	public boolean getIsUsePage() {
		return isUsePage;
	}
	
	/**
	 * 设置是否启用分页
	 * @param isUsePage
	 */
	public void setIsUsePage(boolean isUsePage){
		this.isUsePage = isUsePage;
	}
	
	public HashMap<String, String> getSearchInfo() {
		return searchInfo;
	}
	
	public void setSearchInfo(HashMap<String, String> searchInfo) {
		this.searchInfo = searchInfo;
	}
	
	//====================================================================================
	
	public Pager() {
		// TODO Auto-generated constructor stub
		this.pageNo = 1;
		this.isUsePage = true;
	}
	
	/**
	 * @param pageNo 当前页
	 */
	public Pager(int pageNo) {
		this.pageNo = pageNo;
		this.isUsePage = true;
	}
	
	//=======================================================================================
	
	public static void main(String[] args) {
		List<String> result = new ArrayList<>();
		result.add("abc1");
		result.add("abc2");
		result.add("abc3");
		result.add("abc4");
		result.add("abc5");
		result.add("abc6");
		result.add("abc7");
		result.add("abc8");
		result.add("abc9");
		result.add("abcA");
		result.add("abcB");
		Pager pager = new Pager();
		pager.setResult(result);
		System.out.println("当前页："+pager.getPageNo());
		System.out.println("总页数："+pager.getTotalPageSize());
		System.out.println("总记录数："+pager.getTotalItems());
		System.out.println("下一页："+pager.doNext(pager.getPageNo()));
		System.out.println("上一页："+pager.doPrev(pager.getPageNo()));
		System.out.println("首页："+pager.getFirstPage());
		System.out.println("尾页："+pager.getLastPage());
	}
}
