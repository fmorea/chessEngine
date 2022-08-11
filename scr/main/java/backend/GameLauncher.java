package backend;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Questa classe fa il parsing dell'input dell'utente,
 * L'utente deve infatti scrivere la posizione di origine e quella di
 * destinazione del pezzo che vuole muovere.
 */
public class GameLauncher {
    private static Scacchiera s = new Scacchiera();
    public static void main(String[] args) throws InterruptedException {
        boolean hasMoved = true;
        boolean correctInput = true;
        boolean cheatmode = false;
        System.out.println("Per personalizzare il pezzo in caso di promozione del pedone\n" +
                "lo si puo' fare in qualsiasi momento, scrivendo promotion seguito dal tipo di pezzo\n" +
                "altrimenti e' attivata automaticamente la promozione a donna\n");
        CommandPrompt.ask("Premere un tasto per iniziare una nuova partita"
                ,"   *   *   *   *   *   *   *   *   *   *   ");
        while (!CommandPrompt.inputLetto()){};
        if (!CommandPrompt.gotFromTerminal().equals("cheat")){
            s.createStandardChessboard();
        }
        else{
            cheatmode = true;
            System.out.println("Welcome Man");
        }
        while(!s.validMoves().isEmpty() || cheatmode){
            s.print();
            if(!hasMoved){
                System.out.println("Mossa precedente non legale");
            }
            if(!correctInput){
                System.out.println("Controllare l'input precedentemente inserito, attenersi all'esempio");
                correctInput = true;
            }
            if(s.toccaAlBianco()){
                System.out.println("Tocca al bianco");
            }
            else{
                System.out.println("Tocca al nero");
            }
            if(!cheatmode) {
                CommandPrompt.ask("Inserisci casella di origine e di destinazione\n" +
                                "esempio sintassi ritenute corrette: \na2 a4\n" +
                                "oppure: a2a4\n" +
                                "oppure: 12 14\n" +
                                "oppure: 1214\n"
                        , "MOSSA: ");
            }
            else{
                if(s.validMoves().isEmpty()){
                    CommandPrompt.ask("LA PARTITA E' FINITA","DEBUG_CMD:");
                }
                else{
                    CommandPrompt.ask("Developer Mode Enabled","COMANDO:");
                }

            }

            // ciclo while necessario perché l'input è non bloccante (non si sa mai che
            // in futuro non si debbano utilizzare threads ed un sistema "ad eventi")
            while (!CommandPrompt.inputLetto()){};

            ArrayList<String> parsedStrings =
                    new ArrayList<>(Arrays.asList(CommandPrompt.gotFromTerminal().split(" ")));
            if(     parsedStrings.size() == 2 &&
                    parsedStrings.get(1)!=null &&
                    parsedStrings.get(0)!=null &&
                    !parsedStrings.get(0).isEmpty() &&
                    !parsedStrings.get(1).isEmpty()
            ) {
                int x0 = parsedStrings.get(0).charAt(0) - 'a' + 1;
                int y0 = Character.getNumericValue(parsedStrings.get(0).charAt(1));
                int x = parsedStrings.get(1).charAt(0) - 'a' + 1;
                int y = Character.getNumericValue(parsedStrings.get(1).charAt(1));

                if (    y0 <= 0 ||
                        y0 > 8 ||
                        x0 <= 0 ||
                        x0 > 8 ||
                        y <= 0 ||
                        y > 8 ||
                        x <= 0 ||
                        x > 8
                ) {
                    // Forse l'utente non ha utilizzato la notazione con la lettera ma quella con il numero?
                    x0 = Character.getNumericValue(parsedStrings.get(0).charAt(0));
                    x = Character.getNumericValue(parsedStrings.get(1).charAt(0));
                }
                if (    y0 <= 0 ||
                        y0 > 8 ||
                        x0 <= 0 ||
                        x0 > 8 ||
                        y <= 0 ||
                        y > 8 ||
                        x <= 0 ||
                        x > 8 ||
                        parsedStrings.size() == 2
                ) {
                    correctInput = false;
                } else {
                    hasMoved = s.move(y0, x0, y, x);
                }
            }
            else if (parsedStrings.size() == 1 &&
                    parsedStrings.get(0)!=null &&
                    !parsedStrings.get(0).isEmpty()){
                int x0 = parsedStrings.get(0).charAt(0) - 'a' + 1;
                int y0 = Character.getNumericValue(parsedStrings.get(0).charAt(1));
                int x = parsedStrings.get(0).charAt(2) - 'a' + 1;
                int y = Character.getNumericValue(parsedStrings.get(0).charAt(3));

                if (    y0 <= 0 ||
                        y0 > 8 ||
                        x0 <= 0 ||
                        x0 > 8 ||
                        y <= 0 ||
                        y > 8 ||
                        x <= 0 ||
                        x > 8
                ) {
                    // Forse l'utente non ha utilizzato la notazione con la lettera ma quella con il numero?
                    x0 = Character.getNumericValue(parsedStrings.get(0).charAt(0));
                    x = Character.getNumericValue(parsedStrings.get(0).charAt(2));
                }
                if (    y0 <= 0 ||
                        y0 > 8 ||
                        x0 <= 0 ||
                        x0 > 8 ||
                        y <= 0 ||
                        y > 8 ||
                        x <= 0 ||
                        x > 8 ||
                        parsedStrings.size() != 1
                ) {
                    correctInput = false;
                } else {
                    hasMoved = s.move(y0, x0, y, x);
                }
            }
            else correctInput = false;
            if(parsedStrings.size() == 1 &&
                    parsedStrings.get(0).equals( "cheat")){
                cheatmode=true;
            }
            if(parsedStrings.size() == 2 &&
                    parsedStrings.get(0).equals( "promotion")){
                s.promotion(parsedStrings.get(1));
                correctInput = true;
            }
            if (cheatmode){
                if (parsedStrings.size() == 4 &&
                    parsedStrings.get(0).equals( "set")){
                    int x =Character.getNumericValue(parsedStrings.get(1).charAt(0));
                    int y= Character.getNumericValue(parsedStrings.get(2).charAt(0));
                    String pezzo = parsedStrings.get(3);
                    s.setPezzo(y,x,pezzo);
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "load")){
                    s.createStandardChessboard();
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "reset")){
                    for(int i=1;i<=8;i++){
                        for(int j=1;j<=8;j++){
                            s.setPezzo(i,j,null);
                        }
                    }
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "lockturn")){
                    s.lockTurn();
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "legals")){
                    System.out.println(s.validMoves().toString());
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "jumpturn")){
                    s.jumpTurn();
                    correctInput = true;
                }
                if(parsedStrings.size() == 5 &&
                        parsedStrings.get(0).equals( "islegal")){
                    int x0 =Character.getNumericValue(parsedStrings.get(1).charAt(0));
                    int y0= Character.getNumericValue(parsedStrings.get(2).charAt(0));
                    int x =Character.getNumericValue(parsedStrings.get(3).charAt(0));
                    int y= Character.getNumericValue(parsedStrings.get(4).charAt(0));
                    System.out.println(s.isLegalMove(y0,x0,y,x));
                    correctInput = true;

                }

            }
            if(s.validMoves().isEmpty()){
                System.out.println("La partita è finita");
            }
        }

    }
}
