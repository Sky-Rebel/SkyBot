package com.skybot.bot.util;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class NapcatInstall
{
	public static final Logger LOGGER = LoggerFactory.getLogger(NapcatInstall.class);

	public static final Path NAPCAT_DIR = Paths.get("napcat");

	public static final String NAPCAT_DEFAULT_VERSION = "v4.9.98";

	public static final String NAPCAT_INSTALL_FILE = "NapCat.Shell.zip";

	public static final String NAPCAT_RELEASE_DOWNLOAD_URL = "https://github.com/NapNeko/NapCatQQ/releases/download/";

	public static final String NAPCAT_LATEST_RELEASE_URL = "https://api.github.com/repos/NapNeko/NapCatQQ/releases/latest";

	private static boolean isNapcatInstalled()
	{
		try
		{
			if (!Files.exists(NAPCAT_DIR)) return false;
			return Files.list(NAPCAT_DIR).findFirst().isPresent();
		}
		catch (IOException e)
		{
			LOGGER.error("无法获取目录列表 -> {}", NAPCAT_DIR, e);
			return false;
		}
	}

	public static boolean checkAndInstallNapcat()
	{
		if (isNapcatInstalled()) return true;
		LOGGER.info("检测到Napcat尚未下载，将自动下载！");
		File tempZipFile = null;
		try
		{
			Files.createDirectories(NAPCAT_DIR);
			String latestVersion = getLatestNapcatVersion();
			String downloadUrl = NAPCAT_RELEASE_DOWNLOAD_URL + latestVersion + "/" + NAPCAT_INSTALL_FILE;
			System.out.println(downloadUrl);
			LOGGER.info("Napcat开始下载，版本号为 -> {}", latestVersion);
			tempZipFile = downloadNapcatToTempFile(downloadUrl);
			if (tempZipFile == null) return false;
			LOGGER.info("Napcat下载完成，开始解压文件！");
			unzipNapcatArchiveToDir(tempZipFile, NAPCAT_DIR.toFile());
			LOGGER.info("文件解压完成，欢迎使用Skybot！");
			return true;
		}
		catch (Exception e)
		{
			LOGGER.error("Napcat根目录创建失败！", e);
			return false;
		}
		finally
		{
			if (tempZipFile != null && tempZipFile.exists())
			{
				try
				{
					Files.deleteIfExists(tempZipFile.toPath());
				}
				catch (IOException e)
				{
					LOGGER.warn("Napcat临时文件清理失败！", e);
				}
			}
		}
	}

	private static String getLatestNapcatVersion()
	{
		try
		{
			HttpClient httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
			HttpRequest httpRequest = HttpRequest.newBuilder()
				.uri(URI.create(NAPCAT_LATEST_RELEASE_URL))
				.header("Accept", "application/json")
				.GET()
				.build();
			HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() != 200) return NAPCAT_DEFAULT_VERSION;
			JSONObject jsonObject = new JSONObject(response.body());
			return jsonObject.optString("tag_name", NAPCAT_DEFAULT_VERSION);
		}
		catch (Exception e)
		{
			return NAPCAT_DEFAULT_VERSION;
		}
	}

	private static File downloadNapcatToTempFile(String downloadUrl)
	{
		try
		{
			File tempFile;
			HttpClient httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
			HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(NAPCAT_RELEASE_DOWNLOAD_URL)).GET().build();
			HttpResponse<byte[]> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray());
			if (httpResponse.statusCode() != HttpURLConnection.HTTP_OK)
			{
				LOGGER.error("Napcat下载失败，预期之外的Http状态码 -> {}", httpResponse.statusCode());
				return null;
			}
			tempFile = File.createTempFile("Napcat", ".zip");
			Files.write(tempFile.toPath(), httpResponse.body());
			return tempFile;
		}
		catch (IOException e)
		{
			LOGGER.error("Napcat下载失败！", e);
			return null;
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
			LOGGER.error("Napcat下载中断！", e);
			return null;
		}
	}

	private static void unzipNapcatArchiveToDir(File zipFile, File targetDir)
	{
		try
		{
			FileInputStream fileInputStream = new FileInputStream(zipFile);
			ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
			ZipEntry zipEntry;
			while ((zipEntry = zipInputStream.getNextEntry()) != null)
			{
				File entryFile = new File(targetDir, zipEntry.getName());
				if (zipEntry.isDirectory())
				{
					Files.createDirectories(entryFile.toPath());
					continue;
				}
				Files.createDirectories(entryFile.getParentFile().toPath());
				FileOutputStream fileOutputStream = new FileOutputStream(entryFile);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
				bufferedOutputStream.write(zipInputStream.readAllBytes());
				zipInputStream.closeEntry();
			}
		}
		catch (IOException e)
		{
			LOGGER.error("Napcat解压失败！", e);
		}
	}

	public static void main()
	{
		checkAndInstallNapcat();
	}
}