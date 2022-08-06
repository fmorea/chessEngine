package backend;

import frontend.CommandPrompt;

import java.util.ArrayList;
import java.util.Arrays;

public class EditMode {
    private static Scacchiera s = new Scacchiera();
    public static void main(String[] args) throws InterruptedException {
        boolean end = false;
        while(!end){
            s.print();
            CommandPrompt.ask("Inserisci casella di origine e di destinazione\n" +
                    "esempio: a2 a4\n" +
                    "oppure: 12 14\n"
                    ,"MOSSA: ");
            while (!CommandPrompt.inputLetto()){};
            ArrayList<String> parsedStrings =
                    new ArrayList<>(Arrays.asList(CommandPrompt.gotFromTerminal().split(" ")));

            int x0 = parsedStrings.get(0).charAt(0) - 'a' + 1;
            int y0 = Character.getNumericValue(parsedStrings.get(0).charAt(1));
            int x = parsedStrings.get(1).charAt(0) - 'a' + 1;
            int y = Character.getNumericValue(parsedStrings.get(1).charAt(1));

            if(     y0<=0 ||
                    y0>8 ||
                    x0<=0 ||
                    x0>8 ||
                    y<=0 ||
                    y>8 ||
                    x<=0 ||
                    x>8
            ){
                // Forse l'utente non ha utilizzato la notazione con la lettera ma quella con il numero?
                x0 = Character.getNumericValue(parsedStrings.get(0).charAt(0));
                x = Character.getNumericValue(parsedStrings.get(1).charAt(0));
            }
            if(     y0<=0 ||
                    y0>8 ||
                    x0<=0 ||
                    x0>8 ||
                    y<=0 ||
                    y>8 ||
                    x<=0 ||
                    x>8 ||
                    parsedStrings.size() != 2
            ){

                System.out.println("Mossa non valida");
            }
            else {
                s.move(y0, x0, y, x);
            }



        }
    }
}
