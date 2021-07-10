# 发版日志

### 1.1
1. 增加注入默认的Java编译参数 "-Xlint:none"
2. Java编译的编码设置为UTF-8
3. clean时清理target和logs目录

### 1.2
1. 插件自动引入"java"插件，避免出现报错

### 1.3
1. 增加配置项（baseExt代码块）优化filter参数的注入

### 1.4(1.4.1)
1. 增加pattern（ant-style）来跳过或指定某些资源文件的filter操作

### 1.4.2
1. 修复数据获取异常

### 1.5
1. 优化ProcessResources任务支持通过方法控制是否缓存相关数据
