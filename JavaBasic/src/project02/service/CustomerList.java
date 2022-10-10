package project02.service;

import project02.bean.Customer;

import java.util.Arrays;

public class CustomerList {

    private Customer[] customers; //用来保存客户对象的数组
    private int total = 0;                 //记录已保存客户对象的数量

    //构造器，用来初始化customers数组
    public CustomerList(int totalCustomer) {
        customers = new Customer[totalCustomer];
    }

    //将参数customer添加到数组中最后一个客户对象记录之后
    public boolean addCustomer(Customer customer) {
        if (total >= customers.length) {
            return false;
        }

        customers[total++] = customer;
        return true;

    }

    //用参数customer替换数组中由index指定的对象
    public boolean replaceCustomer(int index, Customer cust) {

        if (index < 0 || index >= total) {
            return false;
        }

        customers[index] = cust;
        return true;

    }

    //从数组中删除参数index指定索引位置的客户对象记录
    public boolean deleteCustomer(int index) {
        if (index < 0 || index >= total) {
            return false;
        }

        for (int i = index; i < total-1; i++) {
            customers[i] = customers[i + 1];
        }

        customers[--total] = null;

        return true;

    }

    //返回数组中记录的所有客户对象
    public Customer[] getAllCustomers() {
        //System.out.println(Arrays.toString(customers));
        Customer[] allCustomers = new Customer[total];
        for (int i = 0; i < total; i++) {
            allCustomers[i] = customers[i];
        }
        return allCustomers;
    }

    //返回参数index指定索引位置的客户对象记录
    public Customer getCustomer(int index) {
        if (index < 0 || index >= total) {
            return null;
        }
        return customers[index];
    }

    public int getTotal() {
        return total;
    }

    public static void main(String[] args) {
        CustomerList customerList = new CustomerList(5);
        Customer c1 = new Customer("张三", '男', 18, "123456", "");
        Customer c2 = new Customer("李四", '女', 19, "1234567", "");
        Customer c3 = new Customer("王五", '男', 20, "12345678", "");
        Customer c4 = new Customer("赵六", '女', 21, "123456789", "");
        Customer c5 = new Customer("田七", '男', 22, "1234567890", "");

        customerList.addCustomer(c1);
        customerList.addCustomer(c3);
        customerList.addCustomer(c5);
        customerList.getAllCustomers();
        customerList.replaceCustomer(2,c4);
        customerList.getAllCustomers();
        customerList.deleteCustomer(0);
        customerList.getAllCustomers();
        System.out.println(customerList.getCustomer(1));
        System.out.println(customerList.total);


    }

}
