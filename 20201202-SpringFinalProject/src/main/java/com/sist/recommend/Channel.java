package com.sist.recommend;

import java.util.ArrayList;
import java.util.List;

public class Channel {
	private List<Item> item = new ArrayList<Item>();

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
	
}
