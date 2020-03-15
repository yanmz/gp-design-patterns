package com.design.factory.proxy.staticproxy;

public class DynamicDataSourceEntry {
    //默认数据源
     public final static String DEFAULT_SOURCE = null;
    private final static ThreadLocal<String> local = new ThreadLocal<String>();
    private DynamicDataSourceEntry(){}
//清空数据源
  public static void clear() {
        local.remove();
    }

    public static String get() { return local.get(); }
    public static void restore() { local.set(DEFAULT_SOURCE); }
    public static void set(String source) { local.set(source); }
    public static void set(int year) { local.set("DB_" + year); }

}
