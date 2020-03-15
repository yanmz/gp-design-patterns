package com.design.factory.decorator;

import java.util.List;

public interface  GPCommunity {
     List<String>  column();
     default List<String> add(){
         return  null;
     };
}
