package com.gupaoedu.demo.service;

import com.gupaoedu.demo.mvcfremak.annotation.GPService;
import com.gupaoedu.demo.service.impl.IDemoService;

/**
 * 核心业务逻辑
 */
@GPService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name + ",from service.";
	}

}
