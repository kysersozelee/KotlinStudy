package com.git.kysersozelee.part4;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
public class IntersectionTest {
    interface Delegate<T> {
        T delegate();
    }

    interface Identifier extends Delegate<Human> {

        default void identify() {
            System.out.println("Identifying....");
        }

        default void checkIdentifyNumber(String identifyNumber) {
            System.out.println("Identifying identifyNumber => " + identifyNumber);
        }

        default String getFirstName() {
            return delegate().getFirstName();
        }

        default String getLastName() {
            return delegate().getLastName();
        }

        default String setFirstName(String firstName) {
            delegate().setFirstName(firstName);
            return delegate().getFirstName();
        }

        default String setLastName(String lastName) {
            delegate().setLastName(lastName);
            return delegate().getLastName();
        }

        default String getIdentifyNumber() {
            return delegate().getIdentifyNumber();
        }
    }

    public static void main(String[] args) {
        Human dongwoo = new Customer("Dongwoo", "Lee");


        run(dongwoo, c -> {
            System.out.println("Hi, " + c.getFirstName());
        });


        run((Identifier) () -> dongwoo, customer -> {
            customer.identify();
            customer.checkIdentifyNumber(customer.getIdentifyNumber());
        });


    }


    public static <T> void run(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }







    /*

    interface Delegate<T>{
        T delegate();
    }

    interface Identify{
        default void idenfity(){
            System.out.println("identifying......");
    }
}

    public static void main(String[] args) {
        Customer customer = new Customer("Dongwoo", "Lee");
        run(customer, c->{
            System.out.println("hi " + c.getFirstName());
        });

        run((Delegate & Identify)()->customer, c->{
            c.idenfity();
        });
    }

    public static <T> void run(T t, Consumer<T> consumer){
        consumer.accept(t);
    }*/
}
