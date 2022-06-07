package org.dip;

public class Mother {
    public void narrate(IReader iReader) {
        System.out.println("妈妈开始讲故事");
        System.out.println(iReader.getContent());
    }
}
