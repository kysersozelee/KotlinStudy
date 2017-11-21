package com.git.kysersozelee.part3;


import com.git.kysersozelee.Country;
import com.git.kysersozelee.Customer;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Slf4j
public class CustomerManagerExample {
    public static void main(String[] args) {
        List<Customer> customerList = Arrays.asList(
                new Customer("Dongwoo", "Lee", 26, Country.KR),
                new Customer("Cristiano", "Ronaldo", 31, Country.PG),
                new Customer("David", "Beckaham", 44, Country.US)
        );

        customerList.stream().forEach(c -> printMovie(c));
        customerList.stream().forEach(c -> performByCondition(c, customer -> customer.getAge()> 30, customer -> System.out.println(customer), customer -> System.out.println(customer)));
        customerList.stream().forEach(c -> performByCondition(c, customer -> customer.getAge() > 30, customer -> System.out.println("Hi" + customer.getFirstName() + ". This is pocketMonster"), customer -> System.out.println("Hi" + customer.getFirstName() + ". This is pocketMonster")));
        customerList.stream().forEach(c -> performByCondition(c, customer -> customer.getAge() > 30, CustomerManagerExample::showMovie, CustomerManagerExample::showPocketMonster));
    }


    public static void showMovie(Customer customer){
        System.out.println("Hi" + customer.getFirstName() + ". This is Movie");
    }

    public static void showPocketMonster(Customer customer){
        System.out.println("Hi" + customer.getFirstName() + ". This is PocketMonster");
    }


    public static <T> void performByCondition(T customer, Predicate<T> p, Consumer<T> f1, Consumer<T> f2) {
        if (p.test(customer)) {
            f1.accept(customer);
        } else {
            f2.accept(customer);
        }
    }
/*
    public static void showMovie(Customer customer){
        System.out.println("Hi" + customer.getFirstName() + ". This is Movie");
    }

    public static void showPocketMonster(Customer customer){
        System.out.println("Hi" + customer.getFirstName() + ". This is PocketMonster");
    }

    public static void printMovie(Customer customer, Predicate<Customer> checkBoolean, Consumer<Customer> f1, Consumer<Customer> f2) {
        if (checkBoolean.test(customer)) {
            f1.accept(customer);
        } else {
            f2.accept(customer);
        }
    }
*/

    public static void printMovie(Customer customer) {
        if (Country.KR.getRegion().equals(customer.getCountry().getRegion())) {
            System.out.println("Hi, " + customer.getLastName() + " " + customer.getFirstName());
        } else {
            System.out.println("Hi, " + customer.getFirstName() + " " + customer.getLastName());
        }
    }

}