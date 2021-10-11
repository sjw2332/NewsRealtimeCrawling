package com.cos.realtime;

import org.junit.jupiter.api.Test;

public class test {
	
	private int a= 1;
	
	@Test
	public void test2() {
		for (int i = a; i < 20; i++) {
			System.out.println(i);
			this.a = i;
		}
		System.out.println(a);
	}
}
