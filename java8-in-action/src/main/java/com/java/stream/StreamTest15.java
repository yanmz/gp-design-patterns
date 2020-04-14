package com.java.stream;

import com.java.stream.entity.Dish;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamTest15 {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );

        List<String> threeHighCaloricDishNames =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .map(Dish::getName)
                        .limit(3)
                        .collect(toList());
        System.out.println(threeHighCaloricDishNames);

        System.out.println("==============1===============");
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        System.out.println("==============2===============");

        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        System.out.println(vegetarianMenu);

        System.out.println("==============3===============");

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        System.out.println("================4==================");
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());
        System.out.println(dishes);

        System.out.println("===============5=================");
        List<Dish> dishes1 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        System.out.println(dishes1);

        System.out.println("================6================");
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        System.out.println("=================7================");
        boolean isHealthy = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);
        System.out.println(isHealthy);
        System.out.println("=================8==================");
        boolean isHealthy1 = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        System.out.println(isHealthy1);

        System.out.println("==================9====================");
        Optional<Dish> dish =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .findAny();
         dish.ifPresent(item-> System.out.println(item.getName()));
        System.out.println("==================10====================");
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst(); // 9
         firstSquareDivisibleByThree.ifPresent(item-> System.out.println(item));

        System.out.println("===============11============");
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int sum = integers.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        System.out.println("===============12===============");
        List<Integer> number = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Optional<Integer> max = number.stream().reduce((q,w)->q>w?q:w);
        max.ifPresent(System.out::println);


        System.out.println("================13=================");
        Long  l =  menu.stream().collect(counting());
        System.out.println(l);

        System.out.println("================14==================");
        Optional<Dish> collect = menu.stream().collect(maxBy(Comparator.comparingInt(Dish::getCalories)));
        collect.ifPresent(System.out::println);

        System.out.println("================15==================");
        double avgCalories =menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(avgCalories);

        System.out.println("================16==================");
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        System.out.println("==================17===============");
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu);


        System.out.println("==================18===============");
        String shortMenu1 = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenu1);

        System.out.println("==================19=================");
        int totalCalories = menu.stream().collect(reducing(  0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories);

        System.out.println("==================20=================");
        //Optional<Dish> mostCalorieDish = menu.stream().collect(((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
       // System.out.println(mostCalorieDish);

        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);


    }
}
