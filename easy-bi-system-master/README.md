# 简易BI系统

### 项目地址

https://gitee.com/lu-junkai/easy-bi-system

## Bi-api后端api

### 1. 使用环境java8，springboot 2.6.7 版本,用nacos作为服务中心，使用openfeign进行远程调用


目录结构如下：

```
│  pom.xml              整个项目的依赖管理           
├─common                通用包
│  │  pom.xml           通用包依赖管理
│  ├─common-utils       通用工具类
│  └─service-base  		服务通用配置
│      
└─service 				服务模块 
│  ├─datasource-service 源数据模块（源数据处理）
│  ├─metadata-service  	元数据模块（数据集信息）
│  ├─system-service  	系统模块（登录注册获取验证码）
│  └─main-service  		主服务，负责调用后端api，处理后展示给前端，对日志缓存做了统一处理
│      
└─bi-api.sh 			操作api服务的shell脚本 
```

### 2.环境搭建：

Linux环境

1. 将Bi-api 使用maven打包，获得system-service、metadata-service、datasource-service、main-service
   四个jar包，将其和bi-api.sh上传至服务器
2. 运行脚本

   ```shell
   bash bi-api.sh start
   ```

3. 关闭脚本

```shell
bash bi-api.sh start
```

4. 查看运行状态
```
bash bi-api.sh psAll
```

### 日志目录

```shell
/opt/easy-bi-system/api-log
```

其中api-log/main-service下的日志为统一的日志

## web前端项目目录