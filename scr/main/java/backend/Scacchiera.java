package backend;

/**
 * La scacchiera è una matrice di stringhe 8x8
 * Gli elementi della matrice sono in gergo scacchistico chiamate "case"
 * Una casa può essere vuota (Stringa = null)
 * oppure può essere occupata da un pezzo,
 * per il nome dei pezzi si è scelto un nome di 4 lettere, la cui ultima
 * lettera maiuscola rappresenta il colore del pezzo (B per bianco, N per nero)
 * la prima lettera rappresenta il nome del pezzo in italiano (p,d,t,a,c)
 * le rimanenti 2 lettere centrali del nome possono essere utilizzate come meta-dati in casi particolari
 */
public class Scacchiera {
    private String[][] matrix;
    private boolean toccaAlBianco = true;

    public Scacchiera(){
        this.matrix = new String[8][8];
    }

    public void createStandardChessboard(){
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
        System.out.print("  a||1 ");
        System.out.print("b||2 ");
        System.out.print("c||3 ");
        System.out.print("d||4 ");
        System.out.print("e||5 ");
        System.out.print("f||6 ");
        System.out.print("g||7 ");
        System.out.print("h||8 ");
        System.out.println();
        for (int y=7;y>=0;y--){
            for (int x=0;x<8;x++){
                if(x==0){
                    y=y+1;
                    System.out.print(y+ " ");
                    y=y-1;
                }
                if (matrix[y][x]!=null && matrix[y][x].charAt(0) != 'p'){
                    System.out.print(matrix[y][x]);
                }
                else if (matrix[y][x]!=null && matrix[y][x].charAt(0) == 'p'){
                    System.out.print("ped" + getColorePezzo(y+1,x+1));
                }
                else{
                    System.out.print(" -- ");
                }
                if(x==7){
                    y=y+1;
                    System.out.print(" " + y);
                    y=y-1;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.print("  a||1 ");
        System.out.print("b||2 ");
        System.out.print("c||3 ");
        System.out.print("d||4 ");
        System.out.print("e||5 ");
        System.out.print("f||6 ");
        System.out.print("g||7 ");
        System.out.print("h||8 ");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public boolean move(int y0, int x0, int y, int x){
        if ( isEmpty(y0,x0) || // se la casella di partenza non è vuota
                ((y0 == y) && (y == x)) ||  // se la casella di partenza è diversa da quella di destinazione
                (toccaAlBianco() && isNero(y0,x0)) || // se vuoi muovere un pezzo di colore diverso
                (toccaAlNero() && isBianco(y0,x0)) ||
                !this.isLegalMove(y0,x0,y,x)
        ){
            return false;
        }
        else{
            forceMove(y0,x0,y,x);
            toccaAlBianco = !toccaAlBianco;
            return true;
        }
    }

    public void forceMove(int y0, int x0, int y, int x){
        String temp = this.getPezzo(y0,x0);
        this.setPezzo(y0,x0,null);
        this.setPezzo(y,x,temp);
    }

    public char getTipoPezzo(int y,int x){
        if (this.getPezzo(y,x)!=null){
            return this.getPezzo(y,x).charAt(0);
        }
        else return '0';

    }
    public char getColorePezzo(int y,int x){
        if (getPezzo(y,x)!=null){
            return this.getPezzo(y,x).charAt(3);
        }
        else return '0';

    }

    public boolean isEmpty(int y, int x){
        if (this.getPezzo(y,x)!=null){
            return false;
        }
        else return true;
    }

    public boolean isBianco(int y,int x) {
        if (getColorePezzo(y,x)== 'B'){
            return true;
        }
        else return false;
    }

    public boolean isNero(int y,int x) {
        if (getColorePezzo(y,x)== 'N'){
            return true;
        }
        else return false;
    }

    public boolean isNotRe(int y, int x) {
        if (getTipoPezzo(y,x) != 'r' && !isEmpty(y,x)){
            return true;
        }
        else return false;
    }

    public boolean thereIsAnEatablePieceByPawn(int y0,int x0,int y,int x){
        boolean answer = false;
        if(getColorePezzo(y,x) != getColorePezzo(y0,x0) && getColorePezzo(y0,x0) != '0' && getColorePezzo(y,x)!='0'){
            if((x == x0 - 1 && isInsideChessBoard(x0-1)) ||
                    (x == x0 + 1 && isInsideChessBoard(x0+1)) ){
                if(isBianco(y0,x0)){
                    if(y == y0 + 1){
                        answer = true;
                    }
                }
                else if(isNero(y0,x0)){
                    if(y == y0 - 1){
                        answer = true;
                    }
                }
            }
        }
        return answer;
    }

    public boolean legalPawnMove(int y0, int x0, int y, int x){
        boolean isLegalMove = false;
        //Segnala che è la prima volta che muovo il pezzo (en passant related)
        if (getPezzo(y0,x0).charAt(1)=='e'){
            String pedone = getPezzo(y0,x0);
            pedone = pedone.replace('e', '1');
            setPezzo(y0,x0,pedone);
        }
        else{
            String pedone = getPezzo(y0,x0);
            pedone = pedone.replace('1', '_');
            setPezzo(y0,x0,pedone);
        }
        if (isNotRe(y,x)){
            isLegalMove = thereIsAnEatablePieceByPawn(y0,x0,y,x);
        }
        else if (isEmpty(y,x)){
            if (x0 == x){
                isLegalMove = true;
                // Segnala che il pedone si è mosso in avanti di uno (En passant related)
                if (y == y0 + 1 || y == y0-1){
                    String pedone = getPezzo(y0,x0);
                    pedone = pedone.replace('d', '_');
                    setPezzo(y0,x0,pedone);
                }
            }
        }
        return  isLegalMove;
    }

    public boolean isEnPassantAble(int y, int x){
        if(     getTipoPezzo(y,x) == 'p'     &&
                getPezzo(y,x).charAt(2)=='d' &&
                getPezzo(y,x).charAt(1)=='1'
        ){
            return true;
        }
        else return false;
    }

    private boolean isLegalMove(int y0, int x0, int y, int x) {
        boolean isLegalMove = false;
        switch(getTipoPezzo(y0,x0)) {
            case 'p': // pedina

                // En passant fix
                if (isEmpty(y,x)){
                    if(isBianco(y+1,x) && y-1==4 && y0==4 &&
                            getTipoPezzo(y+1,x)=='p' &&
                            isEnPassantAble(y+1,x) &&
                            getColorePezzo(y+1,x)!=getColorePezzo(y0,x0)
                    ){
                        forceMove(y+1,x,y,x);
                    }
                    if(isNero(y-1,x) && y-1==5 && y0==5 &&
                            getTipoPezzo(y-1,x)=='p' &&
                            isEnPassantAble(y-1,x) &&
                            getColorePezzo(y-1,x)!=getColorePezzo(y0,x0)
                    ){
                        forceMove(y-1,x,y,x);
                    }
                }


                // Standard pawn logic
                if(isBianco(y0,x0)){
                    if((y0 == 2 && y == 4 ) || (y == y0 + 1 && isInsideChessBoard(y0+1))){
                        isLegalMove = legalPawnMove(y0,x0,y,x);
                        }
                    }
                else if (isNero(y0,x0)){
                    if((y0 == 7 && y == 5 ) || (y == y0 - 1 && isInsideChessBoard(y0-1))){
                        isLegalMove = legalPawnMove(y0,x0,y,x);
                    }
                }
                break;
            case 't':
                int distance, minimum;
                boolean thereIsAPieceInTheMiddle = false;
                if((isNotRe(y,x) && getColorePezzo(y,x) != getColorePezzo(y0,x0)) ||
                    isEmpty(y,x)) {
                    if (y0 == y) {
                        distance = Math.max(x0, x) - Math.min(x0, x);
                        // se c'è un pezzo in mezzo la mossa è illegale
                        minimum = Math.min(x0, x);
                        for (int i = 1; i < distance; i++) {
                            if (!isEmpty(y, minimum + i)) {
                                thereIsAPieceInTheMiddle = true;
                            }
                        }
                        isLegalMove = !thereIsAPieceInTheMiddle;
                    }
                    if (x0 == x) {
                        distance = Math.max(y0, y) - Math.min(y0, y);//2
                        // se c'è un pezzo in mezzo la mossa è illegale
                        minimum = Math.min(y0, y);//5
                        for (int i = 1; i < distance; i++) {
                            if (!isEmpty(minimum + i, x)) {
                                thereIsAPieceInTheMiddle = true;
                            }
                        }
                        isLegalMove = !thereIsAPieceInTheMiddle;
                    }
                }
                break;
            default:
                isLegalMove = true; // debug purposes
        }
        return isLegalMove;
    }

    private boolean isInsideChessBoard(int w){
        if(w<=0 || w>8){
            return  false;
        }
        else return true;
    }


    public boolean toccaAlBianco() {
        return toccaAlBianco;
    }

    public boolean toccaAlNero() {
        return !toccaAlBianco;
    }
}
