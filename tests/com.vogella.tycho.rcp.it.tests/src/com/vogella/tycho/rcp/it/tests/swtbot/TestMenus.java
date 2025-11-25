package com.vogella.tycho.rcp.it.tests.swtbot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestMenus {

	private static SWTBot bot;

	@BeforeAll
	public static void beforeClass() throws Exception {
		// don't use SWTWorkbenchBot here which relies on Platform 3.x
		bot = new SWTBot();
	}

	@Test
	public void ensureSaveIsDisabledWhenNothingIsDirty() {
		SWTBotMenu menu = bot.menu("File").menu("Save");
		
		assertThat("Save command in menu is not enabled", not(menu.isEnabled()));
	}

}