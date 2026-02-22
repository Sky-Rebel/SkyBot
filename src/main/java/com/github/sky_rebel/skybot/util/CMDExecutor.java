package com.github.sky_rebel.skybot.util;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CMDExecutor
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CMDExecutor.class);

	private static final Path RECORD_FILE = Path.of("bat_process_record.json");

	private static final int TASK_SUBMITTED = 0;

	private static final int ERROR_FILE_NOT_EXIST = -1;

	private static final int ERROR_PROCESS_START_EXCEPTION = -2;

	private static final int ERROR_PROCESS_INTERRUPTED_EXCEPTION = -3;

	private static final Map<String, Long> processAndPidMap = new ConcurrentHashMap<>();

	private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

	static
	{
		BatProcessManage.loadRecord();
	}

	public static void startBatProcess(String workDir, String batFile, String... args)
	{
		EXECUTOR_SERVICE.submit(() ->
		{
			Process process = null;
			try
			{
				Path workDirPath = Path.of(workDir);
				if (!Files.exists(workDirPath))
				{
					LOGGER.error("BAT文件工作目录不存在 -> {}", workDir);
					return;
				}
				Path batFilePath = Path.of(workDir, batFile).toAbsolutePath();
				if (!Files.exists(batFilePath))
				{
					LOGGER.error("BAT文件不存在 -> {}", batFilePath);
					return;
				}

				String argsStr = args == null || args.length == 0 ? "" : ":" + String.join(",", args);
				String processKey = batFilePath.toFile().getCanonicalPath() + argsStr;

				if (BatProcessManage.isRunning(processKey))
				{
					LOGGER.error("BAT进程已启动，无需重复启动 -> {}", processKey);
					return;
				}
				List<String> command = new ArrayList<>();
				command.add("cmd.exe");
				command.add("/c");
				String argsJoin = args == null || args.length == 0 ? "" :
				Arrays.stream(args).map(arg -> "\"" + arg + "\"").collect(Collectors.joining(" "));
				String batAbsolutePath = batFilePath.toString();
				String startCmd = String.format
				(
					"start \"SkyBot-%s\" /wait cmd.exe /k \"%s %s\"",
					batFile,
					batAbsolutePath,
					argsJoin
				);
				startCmd = startCmd.replace("  ", " ").trim();
				command.add(startCmd);
				ProcessBuilder processBuilder = new ProcessBuilder(command);
				processBuilder.directory(workDirPath.toFile());
				process = processBuilder.start();
				long pid = process.pid();
				processAndPidMap.put(processKey, pid);
				LOGGER.info("BAT进程启动成功 -> PID: {}, 路径: {}, 参数: {}", pid, batFilePath, Arrays.toString(args));
				BatProcessManage.saveRecord();
				Process finalProcess = process;
				new Thread(() ->
				{
					try
					{
						finalProcess.waitFor();
						processAndPidMap.remove(processKey);
						BatProcessManage.saveRecord();
					}
					catch (InterruptedException e)
					{
						Thread.currentThread().interrupt();
						processAndPidMap.remove(processKey);
						BatProcessManage.saveRecord();
					}
				},
				"BatExitMonitor-" + pid).start();

			}
			catch (Exception e)
			{
				LOGGER.error("BAT文件启动失败 -> workDir: {}, batFile: {}, args: {}", workDir, batFile, Arrays.toString(args), e);
			}
			finally
			{
				if (process != null && process.isAlive() && Thread.currentThread().isInterrupted())
				{
					process.destroyForcibly();
				}
			}
		});
	}

	public static int execute(String name, boolean removeEmpty, String workDir, String command, String... args)
	{
		EXECUTOR_SERVICE.submit(() ->
		{
			Process process = null;
			BufferedReader bufferedReader = null;
			try
			{
				File file = new File(workDir);
				if (!file.exists())
				{
					LOGGER.error("CMD命令工作目录无存！");
					return ERROR_FILE_NOT_EXIST;
				}
				List<String> arrayList = new ArrayList<>();
				arrayList.add("cmd.exe");
				arrayList.add("/c");
				if (command != null)
					arrayList.add(command);
				if (args != null)
					arrayList.addAll(Arrays.stream(args).toList());
				ProcessBuilder processBuilder = new ProcessBuilder(arrayList);
				processBuilder.directory(file);
				processBuilder.redirectErrorStream(true);
				process = processBuilder.start();
				InputStream inputStream = process.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
				bufferedReader = new BufferedReader(inputStreamReader);
				String line;
				while ((line = bufferedReader.readLine()) != null)
				{
					if (removeEmpty)
					{
						if (line.isEmpty())
							continue;
						line = line.replaceAll("\n", "");
						line = line.trim();
					}
					System.out.println(name == null ? "" : (name + " -> ") + line);
				}
				return process.waitFor();
			}
			catch (IOException e)
			{
				LOGGER.error("CMD命令进程启动失败！", e);
				return ERROR_PROCESS_START_EXCEPTION;
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				LOGGER.error("CMD命令进程异常中断！");
				return ERROR_PROCESS_INTERRUPTED_EXCEPTION;
			}
			finally
			{
				if (bufferedReader != null)
				{
					try
					{
						bufferedReader.close();
					}
					catch (IOException e)
					{
						LOGGER.error("CMD命令进程字符输入流关闭失败！");
					}
				}
				if (process != null)
					process.destroy();
			}
		});
		return TASK_SUBMITTED;
	}

	/**
	 * @deprecated 由 {@link CMDExecutor#startBatProcess(String, String, String...)} 替代
	 */
	@Deprecated
	public static void startBat(String name, boolean removeEmpty, String workDir, String... args)
	{
		execute(name, removeEmpty, workDir, "start", args);
	}

	public static void shutdownExecutor()
	{
		EXECUTOR_SERVICE.shutdown();
		try
		{
			if (!EXECUTOR_SERVICE.awaitTermination(5, TimeUnit.SECONDS))
			{
				EXECUTOR_SERVICE.shutdownNow();
			}
		}
		catch (InterruptedException e)
		{
			EXECUTOR_SERVICE.shutdownNow();
		}
	}

	public static void forceCleanInvalidProcess(String workDir, String batFile, String... args)
	{
		try
		{
			Path batFilePath = Path.of(workDir, batFile).toAbsolutePath();
			String argsStr = args == null || args.length == 0 ? "" : ":" + String.join(",", args);
			String processKey = batFilePath.toFile().getCanonicalPath() + argsStr;
			Long pid = processAndPidMap.remove(processKey);
			if (pid == null)
			{
				return;
			}
			ProcessBuilder processBuilder = new ProcessBuilder("taskkill", "/f", "/t", "/pid", String.valueOf(pid));
			processBuilder.redirectErrorStream(true);
			Process killProcess = processBuilder.start();
			if (!killProcess.waitFor(3, TimeUnit.SECONDS))
			{
				killProcess.destroyForcibly();
			}
			BatProcessManage.saveRecord();
		}
		catch (Exception e)
		{
			LOGGER.error("强制清理无效进程失败！", e);
		}
	}

	private static class BatProcessManage
	{
		public static void loadRecord()
		{
			try
			{
				if (!Files.exists(RECORD_FILE))
				{
					return;
				}
				String content = Files.readString(RECORD_FILE);
				if (content.isBlank() || !content.startsWith("{"))
				{
					Files.writeString(RECORD_FILE, "{}");
					return;
				}
				JSONObject data = new JSONObject(content);
				data.keySet().forEach(key ->
				{
					long pid = data.getLong(key);
					if (isProcessAlive(pid))
					{
						processAndPidMap.put(key, pid);
					}
				});
			}
			catch (Exception e)
			{
				LOGGER.error("BAT进程数据加载失败！", e);
			}
		}

		public static void saveRecord()
		{
			try
			{
				Path parentPath = RECORD_FILE.getParent();
				if (parentPath != null && !Files.exists(parentPath))
				{
					Files.createDirectories(parentPath);
				}
				if (!Files.exists(RECORD_FILE))
				{
					Files.createFile(RECORD_FILE);
					Files.writeString(RECORD_FILE, "{}");
				}
				JSONObject data = new JSONObject(processAndPidMap);
				Files.writeString(RECORD_FILE, data.toString(4), StandardCharsets.UTF_8);
			}
			catch (Exception e)
			{
				LOGGER.error("BAT进程数据保存失败！", e);
			}
		}

		public static boolean isRunning(String key)
		{
			if (!processAndPidMap.containsKey(key))
			{
				return false;
			}
			long pid = processAndPidMap.get(key);
			if (pid <= 0)
			{
				processAndPidMap.remove(key);
				saveRecord();
				return false;
			}
			if (isProcessAlive(pid) && isCmdProcess(pid))
			{
				return true;
			}
			processAndPidMap.remove(key);
			saveRecord();
			return false;
		}

		private static boolean isProcessAlive(long pid)
		{
			if (pid <= 0)
			{
				return false;
			}
			Process process = null;
			BufferedReader bufferedReader = null;
			try
			{
				ProcessBuilder processBuilder = new ProcessBuilder("tasklist", "/fi", "PID eq " + pid, "/fo", "csv", "/nh");
				processBuilder.redirectErrorStream(true);
				process = processBuilder.start();
				if (!process.waitFor(3, TimeUnit.SECONDS))
				{
					process.destroyForcibly();
					return false;
				}
				bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
				String line = bufferedReader.readLine();
				return line != null && line.contains(String.valueOf(pid));
			}
			catch (Exception e)
			{
				LOGGER.warn("Tasklist检测PID失败 -> PID: {}", pid, e);
				return false;
			}
			finally
			{
				closeResource(bufferedReader, process);
			}
		}

		private static boolean isCmdProcess(long pid)
		{
			if (pid <= 0)
			{
				return false;
			}
			Process process = null;
			BufferedReader reader = null;
			try
			{
				ProcessBuilder processBuilder = new ProcessBuilder("tasklist", "/fi", "PID eq " + pid, "/fo", "csv", "/nh");
				processBuilder.redirectErrorStream(true);
				process = processBuilder.start();
				process.waitFor(3, TimeUnit.SECONDS);
				reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
				String line = reader.readLine();
				return line != null && line.contains("\"cmd.exe\"");
			}
			catch (Exception e)
			{
				LOGGER.warn("检测是否为cmd进程失败 -> PID: {}", pid, e);
				return false;
			}
			finally
			{
				closeResource(reader, process);
			}
		}

		private static void closeResource(BufferedReader bufferedReader, Process process)
		{
			try
			{
				if (bufferedReader != null)
				{
					bufferedReader.close();
				}
				if (process != null && process.isAlive())
				{
					process.destroyForcibly();
				}
			}
			catch (Exception e)
			{
				LOGGER.error("资源释放失败！", e);
			}
		}
	}
}