package com.proj.poo.model;

public abstract class ZoneTuile implements Comparable<ZoneTuile> {

	protected int value;
	
	public ZoneTuile(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public int compareTo(ZoneTuile zt) {
		if (value < zt.value) return -1;
		else if (value == zt.value) return 0;
		else return 1;
		
	}
	
}
