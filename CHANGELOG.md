## V1.1.3
fix getFileNameFromUri crash

## V1.1.2
add apache commons io  
fix getDataFile and getCacheFile not create new file

## V1.1.1
add getDataDir() and  getDataFile(String name)

## V1.1.0
add ColoredText class
add FileUtils.bitmapToFile and FileUtils.notifyImageSaved

## V1.0.8
Deprecated Init.getInstance, use Init.init .

## V1.0.7
添加 getFileNameFromUri 方法  
更新依赖  
1. gradle tools 2.2.2 -> 2.3.2
1. supportLibraryVersion 25.3.1 -> 25.4.0
1. buildToolsVersion 25.0.2 -> 25.0.3
1. okhttp 3.7.0 -> 3.8.0

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