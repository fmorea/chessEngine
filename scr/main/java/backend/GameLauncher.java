package backend;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Command Line debug tool
 */
public class GameLauncher {
    private static GameLogic s = new GameLogic();
    public static void main(String[] args) throws InterruptedException {
        boolean hasMoved = true;
        boolean correctInput = true;
        boolean cheatmode = true;

        System.out.println("Welcome Man");
        System.out.println("to load pieces, write load and press enter");

        s.print();
        while(cheatmode || !s.getLegalMoves().isEmpty()){

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
                CommandPrompt.ask("","");

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
                        x > 8
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
                    s.updateLegalMoves();
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "load")){
                    s.createStandardChessboard();
                    s.print();
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "reset")){
                    for(int i=1;i<=8;i++){
                        for(int j=1;j<=8;j++){
                            s.setPezzo(i,j,null);
                        }
                    }
                    s.updateLegalMoves();
                    s.print();
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "lockturn")){
                    s.lockTurn();
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "legals")){
                    System.out.println(s.getLegalMoves().toString());
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "numoflegals")){
                    System.out.println(s.getLegalMoves().size());
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "jumpturn")){
                    s.jumpTurn();
                    s.updateLegalMoves();
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "check")){
                    System.out.println(s.isInCheck());
                    correctInput = true;
                }
                if(parsedStrings.size() == 1 &&
                        parsedStrings.get(0).equals( "print")){
                    s.print();
                    correctInput = true;
                }

            }

            if(s.getLegalMoves().isEmpty()){
                System.out.println("La partita non potrebbe continuare");
            }
        }

    }
}
