package com.design.factory.singtelon;

public class Singleton {
	private static Singleton instance;

	private Singleton() {
	}
	static {
		System.out.println("111");
	}

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}