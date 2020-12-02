package com.haeni.di2;

public class Sawon {
	private Saram saram; // (자동주입) : (Saram)app.getBean("saram")을 스프링이 수행함
	private String dept="개발팀"; // 강제주입
	private String job="대리";

	public Saram getSaram() {
		return saram;
	}
	public void setSaram(Saram saram) {
		this.saram = saram;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
}
