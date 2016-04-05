package com.vogella.tycho.rcp.tests;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class SampleHamcrestTest {

	@Test
	public void test() {
		assertThat("Hamcrest", is("Hamcrest"));
	}

}
