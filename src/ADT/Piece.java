package ADT;

import java.util.Arrays;

import Function.*;

public class Piece{

    public char matrix[][];
    public int row;
    public int col;

    public char MARK = '.';

    public void toPiece(char a[][], int row, int col){
        this.matrix = a;
        this.row = row;
        this.col = col;
    }

    public boolean isEmpty(){
        int i, j;

        i = 0;
        while(i<this.row){
            j = 0;
            while(j<this.col){
                if(this.matrix[i][j] != '.') return false;
                j+=1;
            }
            i+=1;
        }

        return true;
    }

    public static Piece copyOf(Piece p){

        int i, j;
        Piece res = new Piece();

        res.row = p.row;
        res.col = p.col;
        res.matrix = new char[res.row][res.col];

        i = 0;
        while(i<res.row){
            j = 0;
            while(j<res.col){
                res.matrix[i][j] = p.matrix[i][j];
                j+=1;
            }
            i+=1;
        }

        return res;
    }

    public static Piece emptyBoard(int n, int m){

        int i, j;
        Piece res = new Piece();

        res.row = n;
        res.col = m;
        res.matrix = new char[n][m];

        i = 0;
        while(i<res.row){

            j = 0;
            while(j<res.col){

                res.matrix[i][j] = '.';

                j+=1;
            }

            i+=1;
        }

        return res;
    }

    public void shiftRight(){
        boolean isOnRight, isOnBottom;
        int i, j, k, mostLeft;

        isOnRight = false;
        i = 0;
        while(i<this.row){

            if(this.matrix[i][this.col-1] != MARK){

                isOnRight = true;

                break;
            }

            i+=1;
        }

        isOnBottom = false;
        i = 0;
        while(i<this.col){

            if(this.matrix[this.row-1][i] != MARK){

                isOnBottom = true;

                break;
            }

            i+=1;
        }

        if(isOnRight){

            if(!isOnBottom){

                // mendapatkan posisi part dari piece yang paling kiri
                mostLeft = -1;
                i = 0;
                while(i<this.col){

                    j = 0;
                    while(j<this.row){

                        if(this.matrix[j][i] != '.'){
                            mostLeft = i;
                            break;
                        }

                        j+=1;
                    }

                    if(mostLeft!=-1) break;

                    i+=1;
                }

                // mindahin piece ke paling kiri dan turun 1
                i = this.row-1;
                while(i>0){

                    j = mostLeft;
                    k = 0;
                    while(j<this.col){

                        this.matrix[i][k] = this.matrix[i-1][j];

                        j+=1;
                        k+=1;
                    }

                    while(k<this.col){
                        
                        this.matrix[i][k] = MARK;

                        k+=1;
                    }

                    i-=1;
                }

                // ngosongin atasnya
                i = 0;
                while(i<this.col){
                    this.matrix[0][i] = '.';
                    i+=1;
                }

            }

        }else{

            i = this.row-1;
            while(i>=0){

                j = this.col-1;
                while(j>0){

                    this.matrix[i][j] = this.matrix[i][j-1];

                    j-=1;
                }

                i-=1;
            }

            i = 0;
            while(i<this.row){
                this.matrix[i][0] = '.';
                i+=1;
            }

        }
    }

    // NOTE: fungsi rotateRight hanya bisa dipakai saat piece berada di pojok kiri atas (awal)
    public void rotateClockwise(){

        int i, j, mostRight, mostBottom;
        Piece temp = new Piece();
        temp.row = this.row;
        temp.col = this.col;
        temp.matrix = new char[temp.row][temp.col];
        i = 0;
        while(i<this.row){
            j = 0;
            while(j<this.col){
                temp.matrix[i][j] = MARK;
                j+=1;
            }
            i+=1;
        }

        // mencari panjang x piece
        mostRight = -1;
        i = this.col-1;
        while(i>=0){

            j = 0;
            while(j<this.row){

                if(this.matrix[j][i] != MARK){
                    mostRight = i;
                    break;
                }

                j+=1;
            }

            if(mostRight!=-1) break;

            i-=1;
        }

        // mencari panjang y piece
        mostBottom = -1;
        i = this.row-1;
        while(i>=0){

            j = 0;
            while(j<this.col){

                if(this.matrix[i][j] != MARK){
                    mostBottom = i;
                    break;
                }

                j+=1;
            }

            if(mostBottom!=-1) break;

            i-=1;
        }

        if(mostRight < this.row && mostBottom < this.col){

            i = 0;
            while(i<=mostBottom){
                j = 0;
                while(j<=mostRight){
                    temp.matrix[j][i] = this.matrix[mostBottom-i][j];
                    j+=1;
                }
                i+=1;
            }

            i = 0;
            while(i<this.row){
                this.matrix[i] = Arrays.copyOf(temp.matrix[i], this.col);
                i+=1;
            }

        }

    }

    // NOTE: fungsi mirrorX hanya bisa digunakan kalo piece-nya ada di pojok kiri atas (awal)
    public void mirrorX(){

        int i, j, mostRight;
        char temp;

        mostRight = -1;

        i = this.col-1;
        while(i>=0){

            j = 0;
            while(j<this.row){

                if(this.matrix[j][i] != MARK){
                    mostRight = i;
                    break;
                }

                j+=1;
            }

            if(mostRight!=-1) break;

            i-=1;
        }

        i = 0;
        while(i<mostRight-i){

            j = 0;
            while(j<this.row){
                temp = this.matrix[j][i];
                this.matrix[j][i] = this.matrix[j][mostRight-i];
                this.matrix[j][mostRight] = temp;
                j+=1;
            }

            i+=1;
        }

    }

    public void printPiece(){

        int i, j;
        char temp;
        
        i = 0;
        while(i<this.row){

            j = 0;
            while(j<this.col){

                temp = this.matrix[i][j];
                if(temp == 'A' || temp == 'H' || temp == 'O' || temp == 'V') System.err.print("\u001B[31m" + temp + "\u001B[0m");
                else if(temp == 'B' || temp == 'I' || temp == 'P' || temp == 'W') System.err.print("\u001B[32m" + temp + "\u001B[0m");
                else if(temp == 'C' || temp == 'J' || temp == 'Q' || temp == 'X') System.err.print("\u001B[33m" + temp + "\u001B[0m");
                else if(temp == 'D' || temp == 'K' || temp == 'R' || temp == 'Y') System.err.print("\u001B[34m" + temp + "\u001B[0m");
                else if(temp == 'E' || temp == 'L' || temp == 'S' || temp == 'Z') System.err.print("\u001B[35m" + temp + "\u001B[0m");
                else if(temp == 'F' || temp == 'M' || temp == 'T') System.err.print("\u001B[36m" + temp + "\u001B[0m");
                else if(temp == 'G' || temp == 'N' || temp == 'U') System.err.print("\u001B[35m" + temp + "\u001B[0m");

                j+=1;
            }

            System.out.println();

            i+=1;
        }

    }

    public void fillPieceWithMark(int startAt){
        int i;
        char[] temp = new char[0];

        i = startAt;
        while(i<this.row){
            this.matrix[i] = Arrays.copyOf(Solve.fillRowWithMark(temp, this.col), this.col);
            i+=1;
        }
    }

    public static String pieceToString(Piece piece){
        int i, j;
        String res;

        res = "";
        
        i = 0;
        while(i<piece.row){

            j = 0;
            while(j<piece.col){

                res+=piece.matrix[i][j];

                j+=1;
            }

            res+="\n";

            i+=1;
        }

        return res;
    }

    public static boolean areEqual(Piece a, Piece b){

        if(a.row != b.row || a.col != b.col) return false;

        int i, j;

        i = 0;
        while(i<a.row){

            j = 0;
            while(j<a.col){

                if(a.matrix[i][j] != b.matrix[i][j]) return false;

                j+=1;
            }

            i+=1;
        }

        return true;
    }

    public int pieceSpace(){
        int i, j, count;

        count = 0;
        i = 0;
        while(i<this.row){
            j = 0;
            while(j<this.col){
                if(this.matrix[i][j] != '.') count += 1;
                j+=1;
            }
            i+=1;
        }

        return count;
    }
}