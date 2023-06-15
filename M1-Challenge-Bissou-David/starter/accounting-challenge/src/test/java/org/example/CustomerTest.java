package org.example;


import com.company.AccountRecord;
import com.company.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class CustomerTest {
    String[] data;
    Customer test;
    AccountRecord charge;

    @Before
    public void setup(){
        this.data = new String[]{"3", "Ace Chemical", "-95000", "12-15-2021"};
        this.test = new Customer();
        this.charge = new AccountRecord();

        test.setId(Integer.parseInt(data[0]));
        test.setName(data[1]);

        charge.setCharge(Integer.parseInt(data[2]));
        charge.setChargeDate(data[3]);
        test.addCharge(charge);
    }

    @Test
    public void getBalance() {
        Assert.assertEquals(-95000, this.test.getBalance() );

    }

    @Test
    public void testToString() {
        Assert.assertEquals("3 Ace Chemical -95000", this.test.toString());
    }
}