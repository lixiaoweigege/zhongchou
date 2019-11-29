package com.java2019.test;

import org.junit.Assert;
import org.junit.Test;

public class DaacTest {
	int i = 1, j = 8;
	@Test
	public void test3() {
		Assert.assertEquals(7, i*j+1);
	}
}
