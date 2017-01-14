package com.whwr.domain;

public class ViladateResult {
	private int result = 0;
	private String msg = "";
	
	public ViladateResult(){
		result = 0;
	}
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
