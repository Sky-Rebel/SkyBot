package com.skybot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NapcatManage
{
	public static final Path NAPCAT_DIR = Path.of("Napcat");

	public static final Logger LOGGER = LoggerFactory.getLogger(NapcatManage.class);

	public static boolean isInstalled() {
		return Files.exists(NAPCAT_DIR);
	}

	public static void install()
	{
		     
	}
}
