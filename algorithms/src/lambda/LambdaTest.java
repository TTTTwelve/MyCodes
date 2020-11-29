package lambda;

import java.util.List;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Created by jiaqiwu on 2018/3/19.
 */
public class LambdaTest {

    public static void main(String[] args) {

//        new Thread(() -> System.out.println("use lambda in Thread")).start();
//
//        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//        features.forEach((n)-> System.out.println(n));
//
//        features.forEach(System.out::println);

        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
         filter(languages, (str)->str.toString().startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->str.toString().endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->str.toString().length() > 4);



    }

    public static void filter(List<String> names, Predicate condition){
        names.stream().filter((name)->(condition.test(name))).forEach((name)->System.out.println(name));
    }
}
