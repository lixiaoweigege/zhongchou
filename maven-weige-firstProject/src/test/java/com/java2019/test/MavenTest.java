package com.java2019.test;

import org.junit.Assert;
import org.junit.Test;

public class MavenTest {
	int i = 1, j = 8;
	@Test
	public void test1() {
		Assert.assertEquals(7, i-j);
	}
	@Test
	public void test2() {
		Assert.assertEquals(7, i+j);
	}
}
