package com.vogella.logging.config;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		configureLogbackInBundle(bundleContext.getBundle());
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
	}

	private void configureLogbackInBundle(Bundle bundle) throws JoranException, IOException {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator jc = new JoranConfigurator();
		jc.setContext(context);
		context.reset();

		// this assumes that the logback.xml file is in the root of the bundle.
		URL logbackConfigFileUrl = FileLocator.find(bundle, new Path("logback.xml"),null);
		jc.doConfigure(logbackConfigFileUrl.openStream());
	}

}
