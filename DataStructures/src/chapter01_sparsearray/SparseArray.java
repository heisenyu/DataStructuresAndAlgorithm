package chapter01_sparsearray;

import java.io.*;

public class SparseArray {
    public static void main(String[] args){

        //创建原始二维数组
        //0：表示无棋子 1：表示黑子 2：表示白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        //输出原始二维数组
        System.out.println("原始二维数组：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        //将二维数组转稀疏数组
        //1.先遍历二维数组得到非0数据的个数
        int zeroCount = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    zeroCount++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int[][] sparseArr = new int[zeroCount + 1][3];

        //3.给稀疏数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = zeroCount;

        //4.遍历二维数组，将非0的值存放到sparseArr中
        int count = 0; //count用于记录是第几个非0数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //打印稀疏数组
        System.out.println("稀疏数组：");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        // 将稀疏数组恢复成原始的二维数组
        // 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        // 2.在读取稀疏数组后几行的数据，并赋给原始的二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 输出恢复后的二维数组
        System.out.println("恢复后的二维数组：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        // 将稀疏数组存盘
        saveSparseArray(sparseArr);

        // 从文件中读取稀疏数组
        int[][] sparseArr2 = readSparseArray();
        System.out.println("从文件中读取的稀疏数组：");
        for (int[] row : sparseArr2) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }



    }

    private static int[][] readSparseArray() {
        int[][] sparseArr = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("./DataStructures/src/chapter01_sparsearray/map.data"))) {
            String line = reader.readLine();
            String[] split = line.split("\t");
            sparseArr = new int[Integer.parseInt(split[0])][Integer.parseInt(split[1])];


            while ((line = reader.readLine()) != null) {
                split = line.split("\t");
                sparseArr[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = Integer.parseInt(split[2]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sparseArr;
    }

    private static void saveSparseArray(int[][] sparseArr){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./DataStructures/src/chapter01_sparsearray/map.data"))) {
            for (int[] row : sparseArr) {
                for (int data : row) {
                    writer.write(data + "\t");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

