package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    public static List<Customer> getDistinctCustomers(List<String[]> objects){

        List<Customer> customers = objects.stream().map(data -> {
            Customer temp = new Customer();
            temp.setId(Integer.parseInt(data[0]));
            temp.setName(data[1]);
            return temp;
        }).distinct().collect(Collectors.toList());

        return customers;

    }

    public static List<Customer> addRecordsToCustomers(List<Customer> customers, List<String[]> objects){
        customers.forEach(customer -> {
            List<String []> customerRecords = objects.stream().filter(data -> customer.getId() == Integer.parseInt(data[0])).collect(Collectors.toList());
            customerRecords.forEach(record -> {
                AccountRecord charge = new AccountRecord();
                charge.setCharge(Integer.parseInt(record[2]));
                charge.setChargeDate(record[3]);
                customer.addCharge(charge);
            });
        });

        return customers;
    }


    public static void main(String[] args) {

        List<Customer> customers = getDistinctCustomers(customerData);
        customers = addRecordsToCustomers(customers, customerData);

        System.out.println("Positive accounts:");
        customers.stream().filter( customer -> customer.getBalance() > 0).forEach(customer -> System.out.println(customer.toString()));
        System.out.println("Negative accounts:");
        customers.stream().filter( customer -> customer.getBalance() < 0).forEach(customer -> System.out.println(customer.toString()));
    }
}
