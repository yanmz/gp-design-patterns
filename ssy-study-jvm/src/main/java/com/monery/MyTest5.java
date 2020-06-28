package com.monery;

/**
 * jcmd  22952   VM.flags 查看jvm启动参数
 * jcmd 24264 help 列出当前运行java进程可以执行的操作
 * jcmd  pid  help  JFR.dump 查看具体命令的选项
 * jcmd pid  PerfCounter.print  查看jvm性能相关参数
 * jcmd pid VM.time 查看JVM启动时长
 * jcmd  pid   GC.class.histogram  查看系统中类的统计信息   jmap -histo  pid
 * jcmd pid Thread.print 打印线程堆栈信息
 * jcmd  pid GC.heap_dump  filename  导出heap_dump文件 导出的文件可通过jvisualvm 分析
 * jcmd pid VM.system_properties  查看jvm属性信息
 */
public class MyTest5 {
    public static void main(String[] args) {
        for (;;) {
            System.out.println("111111111111111111");
        }
    }
}
