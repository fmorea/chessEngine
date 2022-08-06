package backend;

public class Scacchiera {
    static String[][] matrix = new String[8][8];

    public static void setPezzo(int x,int y,String pezzo){
        matrix[x-1][y-1]=pezzo;
    }

    public static void printMatrix(String[][] matrix){
        for (int x=7;x>=0;x--){
            for (int y=0;y<8;y++){
                System.out.print(matrix[x][y]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        setPezzo(1,2,"pedina_bianca");
        printMatrix(matrix);
    }

}
