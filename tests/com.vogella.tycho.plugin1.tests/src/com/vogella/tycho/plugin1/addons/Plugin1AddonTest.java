package com.vogella.tycho.plugin1.addons;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
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
	public void verifyCallTest() {
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

		// at most once
		verify(mockShell, atMost(1)).getText();
		
		// only this method is called
		verify(mockShell, only()).getText();
		
		// ensure method is never called
		verify(mockShell, never()).getBackground();
	}
	
	@Test
	public void verifyOrderTest() {
		Shell secondShellMock = mock(Shell.class);
		
		Plugin1Addon plugin1Addon = new Plugin1Addon();
		plugin1Addon.applicationStarted(null, mockShell);
		plugin1Addon.applicationStarted(null, secondShellMock);
		
		// order of passed in varargs does not matter!
		InOrder order = inOrder(secondShellMock, mockShell);

		order.verify(mockShell).getText();
		order.verify(secondShellMock).getText();
		
	}

}
