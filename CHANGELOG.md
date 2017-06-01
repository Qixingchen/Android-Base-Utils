## V1.0.6
修复 Serializable 相关的 Proguard 问题

## V1.0.5
添加一部分 proguard 规则，移除了没有使用到的库的规则

## V1.0.4
提供 consumerProguardFiles,应用不再需要复制 Proguard 语句

## V1.0.3
更新依赖： 

1. supportLibraryVersion 25.1.1 -> 25.3.1
1. buildToolsVersion 25.0.1 -> 25.0.2
1. okhttp 3.6.0 -> 3.7.0
1. stetho 1.4.2 -> 1.5.0

TextHelper 增加 为数字添加千分符 getNumberWithCommas ，并增加测试