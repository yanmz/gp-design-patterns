package com.gupaoedu.demo.student;


import java.util.EventListener;

public interface MethodExecutionEventListener extends EventListener {
    /**
     * 3 * 处理方法开始执行的时候发布的MethodExecutionEvent事件
     */
    void onMethodBegin(MethodExecutionEvent evt);

    /**
     * 处理方法执行将结束时候发布的MethodExecutionEvent事件 4
     */
    void onMethodEnd(MethodExecutionEvent evt);
}
