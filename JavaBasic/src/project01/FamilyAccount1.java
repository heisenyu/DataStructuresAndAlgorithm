package project01;

public class FamilyAccount1 {

    public static void main(String[] args) {

        //初始化基本金
        int balance = 10000;
        String details = "收支\t账户金额\t收支金额\t说 明\n";
        boolean quit = false;

        while (!quit) {

            System.out.println("------------家庭收支记账软件------------");
            System.out.println("            1 收支明细");
            System.out.println("            2 登记收入");
            System.out.println("            3 登记支出");
            System.out.println("            4 退   出");
            System.out.print("            请选择(1-4)：");

            char choice = Utility.readMenuSelection();


            if (choice == '1') {
                System.out.println("-----------------当前收支明细记录-----------------");
                System.out.println(details);
            } else if (choice == '2') {
                System.out.print("本次收入金额：");
                int revenue = Utility.readNumber();
                System.out.print("本次收入说明：");
                String description = Utility.readString();
                balance += revenue;
                details += "收入\t" + balance + "\t" + revenue + "\t" + description + "\n";
            } else if (choice == '3') {
                System.out.print("本次支出金额：");
                int expenses = Utility.readNumber();
                System.out.print("本次支出说明：");
                String description = Utility.readString();
                balance -= expenses;
                details += "支出\t" + balance + "\t" + expenses + "\t" + description + "\n";
            } else if (choice == '4') {
                System.out.print("确认是否退出(Y/N)：");
                char isQuitFlag = Utility.readConfirmSelection();
                if ('Y' == isQuitFlag) quit = true;
            }

        }

    }
}
