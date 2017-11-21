package com.git.kysersozelee.part2;


import com.git.kysersozelee.Country;
import com.git.kysersozelee.Customer;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CustomerManagerExample {
    public static void main(String[] args) {
        List<Customer> customerList = Arrays.asList(
                new Customer("Dongwoo", "Lee", 26, Country.KR),
                new Customer("Cristiano", "Ronaldo", 31, Country.PG),
                new Customer("David", "Beckaham", 44, Country.US)
        );

        List<Customer> older = customerList.stream()
                .filter(c -> c.getAge() > 30)
                .collect(Collectors.toList());


        Collections.sort(customerList, (c1, c2)->c1.getLastName().compareTo(c2.getLastName()));
        Collections.sort(customerList, Comparator.comparing(Customer::getLastName));


        log.debug("{}",older.size());
    }
}