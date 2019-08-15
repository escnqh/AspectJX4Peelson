# AspectJXProject

基于[AspectJX（HujiangTechnology/gradle_plugin_android_aspectjx](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx)实现的工具集合

#### 1 aspectjxlog

切入注解`@DebugLog() `修饰的方法，打印日志信息

可以通过DebugLogConfig对日志进行配置：

```kotlin
// 全局日志标志
var GLOBAL_TAG = "AspectJX4Peelson"
// 全局Log开关
var showLog = true
// 全局时间打印开关
var showSpendTime = true
// 默认显示级别（只有这个级别及以上的日志会显示）
var defaultShowLevel = I
```

`@DebugLog()`参数列表：

```kotlin
// 单个注解的日志粒度配置
// 补充日志信息（再次过滤）
val tag: String = "",
// 当前日志级别
@LogLevel val logLevel: Int = D,
// 是否显示当前日志
val showLog: Boolean = true,
// 是否打印当前耗时
val showSpendTime: Boolean = true
```

```kotlin
/**
 * Debug log level
 */
const val I = 0
const val V = 1
const val D = 2
const val W = 3
const val E = 4
```

补充日志方法：单个对象打印值日志，受全局配置影响，Any的扩展函数，可以在任意地方调用

```kotlin
debugValueLog(name,value)
```

示例：

```kotlin
@DebugLog("find me", D, true, true)
fun testFun() {
        val s = "nqh"
        debugValueLog("s", s.toString())
        val messagePrinter = PrintMessageClass()
        messagePrinter.printMessage(s)
    }
}
```

日志输出：

```
2019-08-15 10:44:47.023 30155-30155/? D/AspectJX4Peelson: -------> find me: testFun()
2019-08-15 10:44:47.023 30155-30155/? D/AspectJX4Peelson: s = nqh
2019-08-15 10:44:47.024 30155-30155/? D/AspectJX4Peelson: <------- find me: testFun() [0ms]

```

其他问题参见源码

