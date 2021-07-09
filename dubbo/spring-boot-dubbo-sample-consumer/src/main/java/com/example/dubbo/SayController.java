package com.example.dubbo;

import com.gupaoedu.springboot.dubbo.ISayHelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class SayController {

    @DubboReference(registry = {"shanghai","hunan"},
            protocol = "dubbo",
            loadbalance = "consistenthash",
            mock = "com.gupaoedu.springboot.dubbo.springbootdubbosampleconsumer.MockSayHelloService",
            timeout = 500,
            cluster = "failfast",check = false,methods = {
            @Method(loadbalance = "",name ="" )
    },retries = 5)
    ISayHelloService sayHelloService;

    @GetMapping("/say")
    public String say(){
        return sayHelloService.sayHello("Mic");
    }

}
