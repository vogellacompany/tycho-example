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

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Plugin1AddonTest {
	
	@Mock
	private InterfaceOfComplexFrameworkClass mockComplexFrameworkClass;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void verifyCallTest() {
		when(mockComplexFrameworkClass.getFoo()).thenReturn("Eclipse 4 RCP Application");
		
		Plugin1Addon plugin1Addon = new Plugin1Addon();
		plugin1Addon.applicationStarted(null, mockComplexFrameworkClass);
		
		// exactly once
		verify(mockComplexFrameworkClass).getFoo();
		
		// exactly once
		verify(mockComplexFrameworkClass, times(1)).getFoo();

		// at least once
		verify(mockComplexFrameworkClass, atLeastOnce()).getFoo();

		// at least once
		verify(mockComplexFrameworkClass, atLeast(1)).getFoo();

		// at most once
		verify(mockComplexFrameworkClass, atMost(1)).getFoo();
		
		// only this method is called
		verify(mockComplexFrameworkClass, only()).getFoo();
		
		// ensure method is never called
		verify(mockComplexFrameworkClass, never()).isBar();
	}
	
	@Test
	public void verifyOrderTest() {
		InterfaceOfComplexFrameworkClass secondComplexFrameworkClassMock = mock(InterfaceOfComplexFrameworkClass.class);
		
		Plugin1Addon plugin1Addon = new Plugin1Addon();
		plugin1Addon.applicationStarted(null, mockComplexFrameworkClass);
		plugin1Addon.applicationStarted(null, secondComplexFrameworkClassMock);
		
		// order of passed in varargs does not matter!
		InOrder order = inOrder(secondComplexFrameworkClassMock, mockComplexFrameworkClass);

		order.verify(mockComplexFrameworkClass).getFoo();
		order.verify(secondComplexFrameworkClassMock).getFoo();
		
	}

}
