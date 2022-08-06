package backend;

/**
 * La scacchiera è una matrice di stringhe 8x8
 * Gli elementi della matrice sono in gergo scacchistico chiamate "case"
 * Una casa può essere vuota (Stringa = null)
 * oppure può essere occupata da un pezzo,
 * per il nome dei pezzi si è scelto un nome di 4 lettere, la cui ultima
 * lettera maiuscola rappresenta il colore del pezzo
 */
public class Scacchiera {
    private String[][] matrix = new String[8][8];

    public void setPezzo(int x,int y,String pezzo){
        if(     x<=0 ||
                x>=8 ||
                y<=0 ||
                y>=8){
            return;
        }
        this.matrix[x-1][y-1]=pezzo;
    }
    public void setPezzo(char c,int y,String pezzo){
        if(     c != 'a' &&
                c != 'b' &&
                c != 'd' &&
                c != 'e' &&
                c != 'f' &&
                c != 'g' &&
                c != 'h' ){
            return;
        }
        int x;
        switch(c) {
            case 'a':
                x=1;
                break;
            case 'b':
                x=2;
                break;
            case 'c':
                x=3;
                break;
            case 'd':
                x=4;
                break;
            case 'e':
                x=5;
                break;
            case 'f':
                x=6;
                break;
            case 'g':
                x=7;
                break;
            case 'h':
                x=8;
                break;
            default:
                x=9;
        }
        this.setPezzo(x,y,pezzo);
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
        scacchiera.setPezzo(1,2,"pedB");
        scacchiera.setPezzo('b',2,"pedN");
        scacchiera.print();
    }

}
