package project02.ui;

import project02.bean.Customer;
import project02.service.CustomerList;
import project02.until.CMUtility;

public class CustomerView {
    private final CustomerList customerList = new CustomerList(10);

    //显示主菜单，响应用户输入，根据用户操作分别调用其他相应的成员方法（如addNewCustomer），以完成客户信息处理。
    public void enterMainMenu() {

        boolean isQuit = false;

        while (!isQuit) {
            System.out.println("\n-----------------客户信息管理软件-----------------\n");
            System.out.println("                   1 添 加 客 户");
            System.out.println("                   2 修 改 客 户");
            System.out.println("                   3 删 除 客 户");
            System.out.println("                   4 客 户 列 表");
            System.out.println("                   5 退       出\n");
            System.out.print("                   请选择(1-5)：");

            char choice = CMUtility.readMenuSelection();

            switch (choice) {
                case '1': {
                    addNewCustomer();
                    break;
                }
                case '2': {
                    modifyCustomer();
                    break;
                }
                case '3': {
                    deleteCustomer();
                    break;
                }
                case '4': {
                    listAllCustomers();
                    break;
                }
                case '5': {
                    System.out.print("确认是否退出(Y/N)：");
                    char isExit = CMUtility.readConfirmSelection();
                    if (isExit == 'Y') {
                        isQuit = true;
                    }
                }

            }
        }


    }

    private void addNewCustomer() {
        System.out.println("---------------------添加客户---------------------");
        System.out.print("姓名：");
        String name = CMUtility.readString(4);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(15);
        System.out.print("邮箱：");
        String email = CMUtility.readString(15);

        boolean isAdd = customerList.addCustomer(new Customer(name, gender, age, phone, email));

        if (isAdd) {
            System.out.println("---------------------添加完成---------------------");
        } else {
            System.out.println("----------------记录已满,无法添加-----------------");
        }


    }

    private void modifyCustomer() {
        System.out.println("---------------------修改客户---------------------");
        int id = 0;
        Customer customer = null;
        for (; ; ) {
            System.out.print("请选择待修改客户编号(-1退出)：");
            id = CMUtility.readInt();
            if (id == -1) return;

            customer = customerList.getCustomer(id - 1);

            if (customer == null) {
                System.out.println("无法找到指定客户！");
            } else break;

        }


        System.out.print("姓名(" + customer.getName() + ")：");
        String name = CMUtility.readString(4, customer.getName());
        System.out.println("性别(" + customer.getGender() + ")：");
        char gender = CMUtility.readChar(customer.getGender());
        System.out.println("年龄(" + customer.getAge() + ")：");
        int age = CMUtility.readInt(customer.getAge());
        System.out.println("电话(" + customer.getPhone() + ")：");
        String phone = CMUtility.readString(15, customer.getPhone());
        System.out.println("邮箱(" + customer.getEmail() + ")：");
        String email = CMUtility.readString(15, customer.getEmail());


        boolean isRepalce = customerList.replaceCustomer(id - 1, new Customer(name, gender, age, phone, email));

        if (isRepalce) {
            System.out.println("---------------------修改完成---------------------");
        } else {
            System.out.println("----------------无法找到指定客户,修改失败-----------------");
        }
    }

    private void deleteCustomer() {
        System.out.println("---------------------删除客户---------------------");


        int id = 0;
        Customer cust = null;
        for (; ; ) {
            System.out.print("请选择待删除客户编号(-1退出)：");
            id = CMUtility.readInt();
            if (id == -1) {
                return;
            }

            cust = customerList.getCustomer(id - 1);
            if (cust == null) {
                System.out.println("无法找到指定客户！");
            } else
                break;
        }

        System.out.print("确认是否删除(Y/N)：");
        char isDelete = CMUtility.readConfirmSelection();
        if (isDelete == 'N') {
            return;
        }

        boolean isDeleteSuccess = customerList.deleteCustomer(id - 1);
        if (isDeleteSuccess) {
            System.out.println("---------------------删除完成---------------------");
        } else {
            System.out.println("----------无法找到指定客户,删除失败--------------");
        }
    }

    private void listAllCustomers() {
        Customer[] customers = customerList.getAllCustomers();
        System.out.println("---------------------------客户列表---------------------------");
        if (customers.length == 0) {
            System.out.println("没有客户记录！");
        } else {
            System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
            for (int i = 0; i < customers.length; i++) {
                Customer customer = customers[i];
                System.out.println((i + 1) + "\t" + customer.getName() + "\t" + customer.getGender() + "\t" + customer.getAge() + "\t" + customer.getPhone() + "\t" + customer.getEmail());
            }
        }
        System.out.println("-------------------------客户列表完成-------------------------");
    }

    public static void main(String[] args) {
        new CustomerView().enterMainMenu();
    }


}
