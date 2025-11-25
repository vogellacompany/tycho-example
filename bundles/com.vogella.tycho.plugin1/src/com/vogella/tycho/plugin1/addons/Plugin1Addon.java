 
package com.vogella.tycho.plugin1.addons;


import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.osgi.service.event.Event;

import jakarta.inject.Inject;

@SuppressWarnings("restriction")
public class Plugin1Addon {

	@Inject
	@Optional
	public void applicationStarted(
			@EventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) Event event, @Optional InterfaceOfComplexFrameworkClass complexFrameworkClass) {
		if(complexFrameworkClass != null) {
			System.out.println("App startet: " + complexFrameworkClass.getFoo());
		}
	}
}
