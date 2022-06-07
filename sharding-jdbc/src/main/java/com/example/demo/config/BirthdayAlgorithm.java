package com.example.demo.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.*;

/**
 * 出生日期年份相同存到一个数据源
 */
@Component
public class BirthdayAlgorithm implements PreciseShardingAlgorithm<String> {
    List<Date> dateList = new ArrayList<>();

    {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2020, 1, 1, 0, 0, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2021, 1, 1, 0, 0, 0);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2022, 1, 1, 0, 0, 0);
        dateList.add(calendar1.getTime());
        dateList.add(calendar2.getTime());
        dateList.add(calendar3.getTime());
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        // 获取属性数据库的值
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormatter.parse(preciseShardingValue.getValue(), Locale.CHINESE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 获取数据源的名称信息列表
        Iterator<String> iterator = collection.iterator();
        String target = null;
        for (Date s : dateList) {
            target = iterator.next();
            // 如果数据晚于指定的日期直接返回
            if (date.before(s)) {
                break;
            }
        }
        return target;
    }
}