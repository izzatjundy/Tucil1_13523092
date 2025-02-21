import java.io.File;
import java.util.Scanner;
import ADT.*;
import Function.*;

public class Main {

    public static void main(String[] args){

        String cmd, s, temp;
        int n, m, p;
        long timeStart, timeFinish, duration;
        Scanner input = new Scanner(System.in), readFile;


        System.out.println("Selamat datang di Tugas Kecil 1 Strategi Algoritma oleh Muhammad Izzat Jundy");

        while(true){

            System.out.println();
            System.out.println("Program ini dapat membantumu menemukan solusi dari puzzle. Ingin mencoba?");
            System.out.println("1. Input File");
            System.out.println("0. Keluar");
            System.out.print("> ");
            cmd = input.nextLine();

            if(cmd.equals("1")){

                while(true){
                    System.out.println();
                    System.out.println("Silakan masukkan nama file, diakhiri dengan \".txt\"");
                    System.out.print("> ");
                    cmd = input.nextLine();

                    if(cmd.endsWith(".txt")){

                        try{
                            temp = System.getProperty("user.dir");

                            while(!temp.endsWith("Tucil1_13523092")){

                                temp = temp.substring(0, temp.length() - 1);

                            }

                            File file = new File(temp, "/test/" + cmd).getAbsoluteFile();
                            readFile = new Scanner(file);

                            timeStart = System.currentTimeMillis();

                            n = readFile.nextInt();
                            m = readFile.nextInt();
                            p = readFile.nextInt();
                            s = readFile.nextLine();
                            s = readFile.nextLine();

                            // System.out.println(n);
                            // System.out.println(m);
                            // System.out.println(p);
                            // System.out.println(s);
                            
                            Piece solution = new Piece();
                            solution = Solve.solve(n, m, p, s, temp + "/test/" + cmd);

                            timeFinish = System.currentTimeMillis();

                            System.out.println("Diperoleh solusi sebagai berikut:");
                            solution.printPiece();

                            if(solution.isEmpty()) System.out.println("Tidak ditemukan solusi untuk mengisi board!");

                            duration = timeFinish - timeStart;
                            System.out.println("Waktu yang diperlukan: " + duration);

                            readFile.close();

                            break;
                        }catch (Exception e){
                            System.out.println();
                            System.out.println("Error message: " + e);
                        }

                    }else{

                        System.out.println();
                        System.out.println("Input tidak valid!");

                    }
                }            

            }else if(cmd.equals("0")){
                System.out.println("Terima kasih telah menjajal program ini!");
                break;
            }else{
                System.out.println();
                System.out.println("Silakan masukkan input berupa angka yang sesuai!");
            }
        }

        input.close();        

    }

}