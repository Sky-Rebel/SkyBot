package com.skybot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class NapcatInstall
{
	public static final Logger LOGGER = LoggerFactory.getLogger(NapcatInstall.class);

	public static final Path NAPCAT_DIR = Path.of("Napcat");

	public static final String INSTALL_VERSION = "v4.9.98";

	public static final String INSTALL_FILE = "NapCat.Shell.zip";

	public static final String INSTALL_ADDRESS = "https://github.com/NapNeko/NapCatQQ/releases/download/";

	public static final String INSTALL_URl = INSTALL_ADDRESS + INSTALL_VERSION + "/" + INSTALL_FILE;

	private static boolean isInstalled()
	{
		if (Files.exists(NAPCAT_DIR)) {
			LOGGER.info("检测到Napcat既已下载，将跳过下载！");
			return true;
		} else {
			LOGGER.info("检测到Napcat尚未下载，将自动下载！");
			return false;
		}
	}

	public static void checkInstall()
	{
		try
		{
			if (!isInstalled())
			{
				File tempFile;
				try (HttpClient httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build())
				{
					HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(INSTALL_URl)).GET().build();
					HttpResponse<byte[]> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray());
					System.out.println(httpResponse.statusCode());
					if (httpResponse.statusCode() != 200) LOGGER.warn("非预期之内的HTTP响应码 -> {}", httpResponse.statusCode());
					byte[] zipBytes = httpResponse.body();
					tempFile = File.createTempFile("Napcat.zip", "zip");
					try (FileOutputStream fileOutputStream = new FileOutputStream(tempFile))
					{
						fileOutputStream.write(zipBytes);
					}
				}
				File workDir = new File("Napcat"); workDir.mkdir();
				try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(tempFile)))
				{
					ZipEntry zipEntry;
					while ((zipEntry = zipInputStream.getNextEntry()) != null)
					{
						String filePath = workDir.getAbsolutePath() + File.separator + zipEntry.getName();
						if (!zipEntry.isDirectory())
						{
							File file = new File(filePath);
							File parentDir = file.getParentFile();
							if (!parentDir.exists()) parentDir.mkdirs();
							try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file)))
							{
								byte[] buffer = new byte[4092];
								int len;
								while ((len = zipInputStream.read(buffer)) != -1)
								{
									bufferedOutputStream.write(buffer, 0, len);
								}
							}
						}
						else new File(filePath).mkdirs();
					}
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error("Napcat在下载过程中出现异常", e);
		}
	}
}
