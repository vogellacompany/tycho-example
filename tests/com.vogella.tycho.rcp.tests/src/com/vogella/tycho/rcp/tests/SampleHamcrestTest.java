package com.vogella.tycho.rcp.tests;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SampleHamcrestTest {

	@Test
	public void hasSizeOf3() {
		List<Integer> list = Arrays.asList(5, 2, 4);
		
		assertThat(list, hasSize(3));
	}
	
	@Test
	public void containsNumbersInAnyOrder() {
		List<Integer> list = Arrays.asList(5, 2, 4);
		
		assertThat(list, containsInAnyOrder(2, 4, 5));
	}
	
	@Test
	public void everyItemGreaterThan1() {
		List<Integer> list = Arrays.asList(5, 2, 4);
		
		assertThat(list, everyItem(greaterThan(1)));
	}
	
	@Test
	public void test() {
		assertThat("Hamcrest", is("Hamcrest"));
	}

}
