package com.ecpss.templet.infrastructure.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

public class MyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	
	public Properties getProperties() throws IOException {
		return mergeProperties();
	}
	
}
