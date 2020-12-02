package com.sist.di3;

public class MemberList {
	// 생성된 객체도 사용가능하다.
	private Member mem;

	public Member getMem() {
		return mem;
	}

	public void setMem(Member mem) {
		this.mem = mem;
	}
	
	public void print()
	{
		System.out.println("회원번호:"+mem.getNo());
		System.out.println("이름:"+mem.getName());
		System.out.println("성별:"+mem.getSex());
		System.out.println("주소:"+mem.getAddr());
		System.out.println("전화:"+mem.getTel());
	}
	

}
