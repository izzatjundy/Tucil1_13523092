package Function;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import ADT.*;

public class Solve {
    
    private static Piece[][] getPieces(int n, int m, int p, String s, String path){
        Piece[][] res = new Piece[p][n*m*8];
        Piece walkPiece = new Piece();
        char[] signature, temp;
        int i, j;
        Scanner readFile;
        String shifter;

        File file = new File(path).getAbsoluteFile();
        try{
            readFile = new Scanner(file);
        }catch (Exception e){
            System.out.println("Error: " + e);
            return res;
        }
        

        i = 0;
        while(i<p){

            j = 0;
            while(j<n*m*8){

                res[i][j] = new Piece();
                res[i][j].matrix = new char[n][m];
                res[i][j].row = n;
                res[i][j].col = m;

                j+=1;
            }

            i+=1;
        }

        shifter = readFile.nextLine();
        shifter = readFile.nextLine();

        signature = readFile.nextLine().toCharArray();
        temp = signature;

        temp = Solve.fillRowWithMark(temp, m);

        while(p>0){

            res[p-1][0].matrix[0] = Arrays.copyOf(temp, m);

            i = 1;
            if(readFile.hasNextLine()){

                temp = readFile.nextLine().toCharArray();
                temp = Solve.fillRowWithMark(temp, m);

                while(temp[0] == signature[0]){

                    res[p-1][0].matrix[i] = Arrays.copyOf(temp, m);
                    // System.out.println(Arrays.toString(res[p-1][0].matrix[i]));

                    if(readFile.hasNextLine()){
                        temp = readFile.nextLine().toCharArray();
                        temp = Solve.fillRowWithMark(temp, m);
                    }else{
                        i+=1;
                        break;
                    }
                    
                    i+=1;
                }

            }

            res[p-1][0].fillPieceWithMark(i);
            // System.out.println("--------------");
            //res[p-1][0].printPiece();

            // piece ditaruh di berbagai posisi

            i = 0;
            // normal - awal
            walkPiece = Piece.copyOf(res[p-1][0]);
            while(i<n*m*1){

                res[p-1][i] = Piece.copyOf(walkPiece);
                // System.out.println("--------------");
                // res[p-1][i].printPiece();

                walkPiece.shiftRight();
                i+=1;
            }

            // normal - rotate 1
            walkPiece = Piece.copyOf(res[p-1][0]);
            walkPiece.rotateClockwise();
            while(i<n*m*2){

                res[p-1][i] = Piece.copyOf(walkPiece);

                walkPiece.shiftRight();
                i+=1;
            }

            // normal - rotate 2
            walkPiece = Piece.copyOf(res[p-1][0]);
            walkPiece.rotateClockwise();
            walkPiece.rotateClockwise();
            while(i<n*m*3){

                res[p-1][i] = Piece.copyOf(walkPiece);

                walkPiece.shiftRight();
                i+=1;
            }

            // normal - rotate 3
            walkPiece = Piece.copyOf(res[p-1][0]);
            walkPiece.rotateClockwise();
            walkPiece.rotateClockwise();
            walkPiece.rotateClockwise();
            while(i<n*m*4){

                res[p-1][i] = Piece.copyOf(walkPiece);

                walkPiece.shiftRight();
                i+=1;
            }

            // mirror - awal
            walkPiece = Piece.copyOf(res[p-1][0]);
            walkPiece.mirrorX();
            while(i<n*m*5){

                res[p-1][i] = Piece.copyOf(walkPiece);

                walkPiece.shiftRight();
                i+=1;
            }

            // mirror - rotate 1
            walkPiece = Piece.copyOf(res[p-1][0]);
            walkPiece.mirrorX();
            walkPiece.rotateClockwise();
            while(i<n*m*6){

                res[p-1][i] = Piece.copyOf(walkPiece);

                walkPiece.shiftRight();
                i+=1;
            }

            // mirror - rotate 2
            walkPiece = Piece.copyOf(res[p-1][0]);
            walkPiece.mirrorX();
            walkPiece.rotateClockwise();
            walkPiece.rotateClockwise();
            while(i<n*m*7){

                res[p-1][i] = Piece.copyOf(walkPiece);

                walkPiece.shiftRight();
                i+=1;
            }

            // mirror - rotate 3
            walkPiece = Piece.copyOf(res[p-1][0]);
            walkPiece.mirrorX();
            walkPiece.rotateClockwise();
            walkPiece.rotateClockwise();
            walkPiece.rotateClockwise();
            while(i<n*m*8){

                res[p-1][i] = Piece.copyOf(walkPiece);

                walkPiece.shiftRight();
                i+=1;
            }

            signature = Arrays.copyOf(temp, temp.length);
            p-=1;
        }

        readFile.close();

        return res;
    }

    private static boolean isFit(Piece a, Piece b){
        int i, j;
        
        i = 0;
        while(i<a.row){

            j = 0;
            while(j<a.col){
                if(a.matrix[i][j] != '.' && b.matrix[i][j] != '.'){
                    return false;
                }
                j+=1;
            }

            i+=1;
        }

        return true;
    }

    private static boolean isFull(Piece piece){
        int i, j;

        i = 0;
        while(i<piece.row){
            j = 0;
            while(j<piece.col){
                if(piece.matrix[i][j] == '.') return false;
                j+=1;
            }
            i+=1;
        }

        return true;
    }

    private static Piece placePiece(Piece board, Piece piece){

        // Prekondisi: board dan piece sudah pasti fit

        int i, j;

        i = 0;
        while(i<board.row){

            j = 0;
            while(j<board.col){

                if(piece.matrix[i][j] != '.') board.matrix[i][j] = piece.matrix[i][j];

                j+=1;
            }

            i+=1;
        }

        return board;
    }

    public static char[] fillRowWithMark(char[] s, int m){

        char[] hasil = new char[m];
        int i;

        i = 0;
        while(i<s.length){
            if(s[i] == ' '){
                hasil[i] = '.';
            }else{
                hasil[i] = s[i];
            }
            i+=1;
        }

        while(i<m){
            hasil[i] = '.';
            i+=1;
        }

        return hasil;
    }

    private static Piece permutation(Piece board, Piece[][] pieces, int pieceIndex){
        
        if(isFull(board)){
            if(pieceIndex < 0){
                return board;
            }else{
                return Piece.emptyBoard(board.row, board.col);
            }
        }else if(pieceIndex < 0){
            return Piece.emptyBoard(board.row, board.col);
        }else{

            Piece tempBoard, res;
            int i;

            i = 0;
            while(i<board.row*board.col*8){
                if(isFit(board, pieces[pieceIndex][i])){
                    tempBoard = placePiece(Piece.copyOf(board), pieces[pieceIndex][i]);
                    //tempBoard.printPiece();
                    res = permutation(tempBoard, pieces, pieceIndex-1);
                    //res.printPiece();

                    if(isFull(res)) return res;
                }
                i+=1;
            }
            
            return Piece.emptyBoard(board.row, board.col);
        }

    }

    public static Piece solve(int n, int m, int p, String s, String path){

        Piece res = new Piece();
        Piece[][] pieces = getPieces(n, m, p, s, path);

        res = permutation(Piece.emptyBoard(n, m), pieces, p-1);

        return res;
    }

}
