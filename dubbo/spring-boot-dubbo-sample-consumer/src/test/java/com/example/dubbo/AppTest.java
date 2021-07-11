package com.example.dubbo;


import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     *  指定名称扩展点
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        //解析URL
        String loadbalance="gploadbalance";
        /**
         * ExtensionLoader.getExtensionLoader(LoadBalance.class) 可以理解在classpath下找到LoadBalance接口的全路径文件
         * 找到文件之后 再根据要查找的key 得到对应实现
         */
        LoadBalance loadBalance= ExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension(loadbalance);
        System.out.println(loadBalance);
    }


    @Test
    public void testSPI(){
        ExtensionLoader<Filter>
                loader=ExtensionLoader.getExtensionLoader(Filter.class);
        URL url=new URL("","",0);
            url=url.addParameter("cache","cache");
        List<Filter> filters=loader.getActivateExtension(url,"cache");
        System.out.println(filters.size());
    }
}
