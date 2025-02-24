import java.io.File;
import java.util.Scanner;
import ADT.*;
import Function.*;

public class Main {

    public static void main(String[] args){

        String cmd, s, temp, solutionString, fileName;
        int n, m, p;
        long timeStart, timeFinish, duration;
        Scanner input = new Scanner(System.in), readFile;

        System.out.println("Selamat datang di Tugas Kecil 1 Strategi Algoritma oleh Muhammad Izzat Jundy");

        while(true){

            System.out.println();
            System.out.println("Program ini dapat membantumu menemukan solusi dari IQ Puzzler Pro. Ingin mencoba?");
            System.out.println("1. Input File");
            System.out.println("0. Keluar");
            System.out.print("> ");
            cmd = input.nextLine();

            if(cmd.equals("1")){

                while(true){
                    System.out.println();
                    System.out.println("Silakan masukkan nama file, diakhiri dengan \".txt\"");
                    System.out.print("> ");
                    fileName = input.nextLine();

                    if(fileName.endsWith(".txt")){

                        try{
                            temp = System.getProperty("user.dir");

                            while(!temp.endsWith("Tucil1_13523092")){

                                temp = temp.substring(0, temp.length() - 1);

                            }

                            File file = new File(temp, "\\test\\input\\" + fileName).getAbsoluteFile();
                            readFile = new Scanner(file);

                            timeStart = System.currentTimeMillis();

                            n = readFile.nextInt();
                            m = readFile.nextInt();
                            p = readFile.nextInt();
                            s = readFile.nextLine();
                            s = readFile.nextLine();
                            
                            Piece solution = new Piece();
                            solution = Solve.solve(n, m, p, s, temp + "\\test\\input\\" + fileName);

                            timeFinish = System.currentTimeMillis();
                            readFile.close();

                            System.out.println();
                            if(Solve.langkah != 0){

                                if(solution.isEmpty()){
                                    System.out.println("Tidak ditemukan solusi untuk mengisi board!");
                                }else{
                                    System.out.println("Diperoleh solusi sebagai berikut:");
                                    solution.printPiece();
                                }
    
                                duration = timeFinish - timeStart;
                                System.out.println();
                                System.out.println("Waktu yang diperlukan: " + duration + " ms");
    
                                System.out.println();
                                System.out.println("Banyak kasus yang ditinjau: " + Solve.langkah);

                            }else{
                                
                                System.out.println("Piece yang ada tidak sesuai dengan board!");

                                break;
                            }

                            while(true){
                                System.out.println();
                                System.out.println("Apakah Anda ingin menyimpan solusi? (ya/tidak)");
                                System.out.print("> ");
                                cmd = input.nextLine();

                                if(cmd.toLowerCase().equals("ya")){
                                    solutionString = Piece.pieceToString(solution);
                                    Solve.writeStringToFile(solutionString, temp + "\\test\\output\\" + fileName);
                                    break;
                                }else if(cmd.toLowerCase().equals("tidak")){
                                    break;
                                }else{
                                    System.out.println();
                                    System.out.println("Input tidak valid!");
                                }
                            }

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
                System.out.println();
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