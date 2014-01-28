package com.careerlog.entity;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class Page<T> {
	//page number, default page number is 1
	private int start=1;
	//how many records shown on page, default is 20
	private int pageSize = 20;
	//total number of records
	private int totalRecord;
	//total pages
	private int totalPage;
	//records for current page
	private List<T> results;
	//all other parameters
	private Map<String,Object> params = new HashMap<String, Object>();

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
		this.setTotalPage(totalPage);
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
	
}
