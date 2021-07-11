package org.isl.v2;

public  class A{
	public void depend1(I1 i){
		i.method1();
	}
	public void depend2(I2 i){
		i.method2();
	}
	public void depend3(I2 i){
		i.method3();
	}
}