package com.gupaoedu.demo.mvc.service.impl;

import com.gupaoedu.demo.mvc.service.IDemoService;
import com.gupaoedu.demo.spring.framework.annotation.GPService;

/**
 * 核心业务逻辑
 */
@GPService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name + ",from service.";
	}

}
