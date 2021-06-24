
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
//        App app = new App();
////        List<String>  list =app.generateNum(400,40);
////        Random random = new Random();
////        String a =null;
////        String b = null;
////        while (!list.isEmpty()){
////            int n1 = random.nextInt(list.size());
////            list.remove(a =list.get(n1));
////            int n2 = random.nextInt(list.size());
////            list.remove(b=list.get(n2));
////            System.out.println(a+"==="+b);
////      }

        String orgi = "010-5426321";

        /* $1,$3是占位符，$1代表（.{3}）也就是010.          (.*)代表任意字符             $3代表 (.{4}) 也就是后四们6321*/
        orgi = orgi.replaceAll("^(.{3})(.*)(.{4})$", "$1*****$3");
        System.out.print(orgi);
    }

    public  List<String> generateNum(int start, int end){
        List<String> numberList = Stream.iterate(start, item -> item + 1).limit(end).map(item -> item.toString()).collect(Collectors.toList());
        return numberList;
    }
}
