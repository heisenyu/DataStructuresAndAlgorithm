package Project01;

public class FamilyAccount {

    public static void main(String[] args) {

        //初始化基本金
        int balance = 10000;
        String details = "收支\t账户金额\t收支金额\t说 明\n";
        char isQuit = 'N';

        for (; ; ) {

            System.out.println("------------家庭收支记账软件------------");
            System.out.println("            1 收支明细");
            System.out.println("            2 登记收入");
            System.out.println("            3 登记支出");
            System.out.println("            4 退   出");
            System.out.print("            请选择(1-4)：");

            char choice = Utility.readMenuSelection();

            switch (choice) {

                case '1': {
                    System.out.println("-----------------当前收支明细记录-----------------");
                    System.out.println(details);
                    break;
                }
                case '2': {
                    System.out.print("本次收入金额：");
                    int revenue = Utility.readNumber();
                    System.out.print("本次收入说明：");
                    String description = Utility.readString();
                    balance += revenue;
                    details += "收入\t" + balance + "\t" + revenue + "\t" + description + "\n";
                    break;
                }
                case '3': {
                    System.out.print("本次支出金额：");
                    int expenses = Utility.readNumber();
                    System.out.print("本次支出说明：");
                    String description = Utility.readString();
                    balance -= expenses;
                    details += "支出\t" + balance + "\t" + expenses + "\t" + description + "\n";
                    break;
                }
                case '4': {
                    System.out.print("确认是否退出(Y/N)：");
                    isQuit = Utility.readConfirmSelection();
                    break;
                }
            }

            if ('Y' == isQuit){
                break;
            }

        }

    }
}
