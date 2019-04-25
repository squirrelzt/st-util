package com.useage.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaUsage {
    MyLambdaInterface aBlockOfCode = (s) -> System.out.println(s);

    public MyLambdaInterface getaBlockOfCode() {
        return aBlockOfCode;
    }

    public void setaBlockOfCode(MyLambdaInterface aBlockOfCode) {
        this.aBlockOfCode = aBlockOfCode;
    }

    public void enact(MyLambdaInterface myLambdaInterface, String s) {
        myLambdaInterface.doSomeShit(s);
    }

    public static void checkAndExecute(List<Person> personList, NameChecker nameChecker, Executor executor) {
        for (Person person : personList) {
            if (nameChecker.check(person)) {
                executor.execute(person);
            }
        }
    }

    public static void checkAndExecute1(List<Person> personList, Predicate<Person> predicate, Consumer<Person> consumer) {
//        for (Person person : personList) {
//            if (predicate.test(person)) {
//                consumer.accept(person);
//            }
//        }
        personList.forEach(person -> {
            if (predicate.test(person)) {
                consumer.accept(person);
            }
        });

        personList.stream()
                .filter(person -> person.getLastName().startsWith("qin"))
                .forEach(person -> System.out.println(person.getFirstName()));

        personList.stream()
                .filter(person -> person.getLastName().startsWith("qin"))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        LambdaUsage lambdaUsage = new LambdaUsage();
        MyLambdaInterface a = lambdaUsage.getaBlockOfCode();
        a.doSomeShit("one");

        lambdaUsage.enact(s -> System.out.println(s), "two");

        List<Person> emperors = Arrays.asList(
                new Person("shihuang", "qin", 80),
                new Person("wudi", "han", 70),
                new Person("taizong", "tang", 60)
        );

        checkAndExecute(emperors, person -> person.getLastName().startsWith("qin"), person -> System.out.println(person.getFirstName()));
        checkAndExecute1(emperors, person -> person.getLastName().startsWith("qin"), person -> System.out.println(person.getFirstName()));

        Person per = new Person("", "", 30);
        Optional<Person> personOpt = Optional.ofNullable(per);
        if (personOpt.isPresent()) {
            System.out.println(personOpt.get());
        } else {
            System.out.println(personOpt.orElse(new Person("test", "lastTest", 20)));
        }
    }
}

@FunctionalInterface
interface MyLambdaInterface {
    void doSomeShit(String s);
}

@FunctionalInterface
interface NameChecker {
    boolean check(Person person);
}

@FunctionalInterface
interface Executor {
    void execute(Person person);
}

