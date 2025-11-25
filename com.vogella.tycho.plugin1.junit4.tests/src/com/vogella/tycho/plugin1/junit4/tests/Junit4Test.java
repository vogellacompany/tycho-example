package com.vogella.tycho.plugin1.junit4.tests;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.vogella.tycho.plugin1.addons.Plugin1Addon;

public class Junit4Test {


	@Test
	public void verifyCallTest() {
		Plugin1Addon plugin1Addon = new Plugin1Addon();

		assertNotNull(plugin1Addon);
	}
}
