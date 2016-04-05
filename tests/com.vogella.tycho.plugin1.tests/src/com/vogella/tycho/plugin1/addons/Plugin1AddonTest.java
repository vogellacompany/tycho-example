package com.vogella.tycho.plugin1.addons;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Plugin1AddonTest {
	
	@Mock
	private Shell mockShell;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		when(mockShell.getText()).thenReturn("Eclipse 4 RCP Application");
		
		Plugin1Addon plugin1Addon = new Plugin1Addon();
		plugin1Addon.applicationStarted(null, mockShell);
		
		// exactly once
		verify(mockShell).getText();
		
		// exactly once
		verify(mockShell, times(1)).getText();

		// at least once
		verify(mockShell, atLeastOnce()).getText();

		// at least once
		verify(mockShell, atLeast(1)).getText();
	}

}
