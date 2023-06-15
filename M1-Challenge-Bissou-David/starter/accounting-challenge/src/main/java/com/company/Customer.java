package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        int balance = 0;
        for (AccountRecord charge : charges) {
            balance += charge.getCharge();
        }
        return balance;
    }

    public List<AccountRecord> getCharges() {
        return charges;
    }

    public void addCharge(AccountRecord charge){
        this.charges.add(charge);
    }

    @Override
    public String toString() {
        String info = this.id + " " + this.name + " " + this.getBalance();
        return info;
    }

    //This link helped me override the equals() & hashCode() methods, so I could use the distinct method for the Customer Class type(non-primitive)
    //https://stackoverflow.com/questions/8180430/how-to-override-equals-method-in-java

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Customer other = (Customer) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public final int hashCode() {
        int result = this.id;
        if (this.getName() != null) {
            result = 31 * result + this.getName().hashCode();
        }
        return result;
    }

}
