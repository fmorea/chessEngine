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
    private String[][] matrix;
    private boolean toccaAlBianco = true;

    public Scacchiera(){
        this.matrix = new String[8][8];

        this.setPezzo(2,1,"pedB");
        this.setPezzo(2,2,"pedB");
        this.setPezzo(2,3,"pedB");
        this.setPezzo(2,4,"pedB");
        this.setPezzo(2,5,"pedB");
        this.setPezzo(2,6,"pedB");
        this.setPezzo(2,7,"pedB");
        this.setPezzo(2,8,"pedB");

        this.setPezzo(7,1,"pedN");
        this.setPezzo(7,2,"pedN");
        this.setPezzo(7,3,"pedN");
        this.setPezzo(7,4,"pedN");
        this.setPezzo(7,5,"pedN");
        this.setPezzo(7,6,"pedN");
        this.setPezzo(7,7,"pedN");
        this.setPezzo(7,8,"pedN");

        this.setPezzo(1,1,"torB");
        this.setPezzo(1,8,"torB");
        this.setPezzo(1,2,"cavB");
        this.setPezzo(1,7,"cavB");
        this.setPezzo(1,3,"alfB");
        this.setPezzo(1,6,"alfB");
        this.setPezzo(1,4,"donB");
        this.setPezzo(1,5,"re_B");

        this.setPezzo(8,1,"torN");
        this.setPezzo(8,8,"torN");
        this.setPezzo(8,2,"cavN");
        this.setPezzo(8,7,"cavN");
        this.setPezzo(8,3,"alfN");
        this.setPezzo(8,6,"alfN");
        this.setPezzo(8,4,"donN");
        this.setPezzo(8,5,"re_N");
    }

    public void setPezzo(int y,int x,String pezzo){
        if(     y<=0 ||
                y>8 ||
                x<=0 ||
                x>8){
            return;
        }
        this.matrix[y-1][x-1]=pezzo;
    }
    public void setPezzo(char c,int x,String pezzo){
        if(     c != 'a' &&
                c != 'b' &&
                c != 'd' &&
                c != 'e' &&
                c != 'f' &&
                c != 'g' &&
                c != 'h' ){
            return;
        }
        int y;
        switch(c) {
            case 'a':
                y=1;
                break;
            case 'b':
                y=2;
                break;
            case 'c':
                y=3;
                break;
            case 'd':
                y=4;
                break;
            case 'e':
                y=5;
                break;
            case 'f':
                y=6;
                break;
            case 'g':
                y=7;
                break;
            case 'h':
                y=8;
                break;
            default:
                y=9;
        }
        this.setPezzo(y,x,pezzo);
    }

    public String getPezzo(int y, int x){
        if(     y<=0 ||
                y>8 ||
                x<=0 ||
                x>8){
            return "outOfBound";
        }
        return matrix[y-1][x-1];
    }

    public void print(){
        System.out.print("  a/1  ");
        System.out.print("b/2  ");
        System.out.print("c/3  ");
        System.out.print("d/4  ");
        System.out.print("e/5  ");
        System.out.print("f/6  ");
        System.out.print("g/7  ");
        System.out.print("h/8  ");
        System.out.println();
        for (int y=7;y>=0;y--){
            for (int x=0;x<8;x++){
                if(x==0){
                    y=y+1;
                    System.out.print(y+ " ");
                    y=y-1;
                }
                System.out.print(matrix[y][x]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public boolean move(int y0, int x0, int y, int x){
        if ( this.getPezzo(y0,x0) == null ||
                ((y0 == y) && (y == x)) ||
                (this.toccaAlBianco && this.getPezzo(y0,x0).charAt(3) == 'N') ||
                (!this.toccaAlBianco && this.getPezzo(y0,x0).charAt(3) == 'B')){
            return false;
        }
        else{
            String temp = this.getPezzo(y0,x0);
            this.setPezzo(y0,x0,null);
            this.setPezzo(y,x,temp);
            toccaAlBianco = !toccaAlBianco;
            return true;
        }
    }

    public boolean toccaAlBianco() {
        return toccaAlBianco;
    }
}