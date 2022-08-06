package backend;

public class Scacchiera {
    private String[][] matrix = new String[8][8];

    public void setPezzo(int x,int y,String pezzo){
        this.matrix[x-1][y-1]=pezzo;
    }

    public void print(){
        for (int x=7;x>=0;x--){
            for (int y=0;y<8;y++){
                System.out.print(matrix[x][y]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scacchiera scacchiera = new Scacchiera();
        scacchiera.setPezzo(1,2,"pedina_bianca");
        scacchiera.print();
    }

}
