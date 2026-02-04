# 项目简介 #

基于Napcat框架由Java编写的QQ机器人SDK项目

# 项目使用 #

## 1.下载
前往 [Releases](https://github.com/Sky-Rebel/SkyBot/releases) 页面，下载最新版本SkyBot的Jar文件。

## 2.引用

### IDEA：
在你的项目界面
1. 键入 **`Alt + \`**
2. 键入 **`Ctrl + Alt + Shift + S`**
3. 点击 **`Libraries`**
4. 点击 **`+`**
5. 点击 **`Java`**
6. 选中 **`SkyBot的jar`**

### Maven：
将Jar安装至本地Maven仓库
```bash
mvn install:install-file -Dfile=Jar_Adress -DgroupId=com.github.sky-rebel -DartifactId=SkyBot -Dversion=1.0.0-SNAPSHOT -Dpackaging=jar
```
在你的项目中引入依赖
```xml
<dependency>
    <groupId>com.github.sky-rebel</groupId>
    <artifactId>SkyBot</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
# 项目贡献 #
- Sky_Rebel
- 虚位以待
