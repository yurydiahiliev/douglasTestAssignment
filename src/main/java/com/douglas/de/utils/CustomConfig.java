package com.douglas.de.utils;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:application.properties")
public interface CustomConfig extends Config {

    String baseUrl();

    String browser();

    Long browserTimeout();
}
