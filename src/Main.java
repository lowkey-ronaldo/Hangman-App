import com.ronaldo.HangmanApp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HangmanApp hangmanApp = new HangmanApp();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hangman Game");

        while (true) {
            while (hangmanApp.getNumAttempts() < hangmanApp.getMaxAttempts() && !hangmanApp.getIsTrovato()) {

                for (String s : hangmanApp.getCurrentWord())
                    System.out.print(s);

                System.out.println("\nVuoi già indovinare la parola: si[y] o no[n]");
                char choice = scanner.next().charAt(0);

                if (String.valueOf(choice).equalsIgnoreCase("y")) {
                    hangmanApp.cercaParola(choice);
                    if (hangmanApp.getIsTrovato()) {
                        System.out.print("Parola corretta!");
                        break;
                    }

                } else if (String.valueOf(choice).equalsIgnoreCase("n")) {
                    hangmanApp.cercaLettera(choice);
                    if(hangmanApp.getIsTrovato()){
                        System.out.println("\nParola corretta!");
                        break;
                    }

                }
            }

            // Fine gioco
            if (hangmanApp.getIsTrovato()) {
                System.out.println("\nScore: " + hangmanApp.getScore());
            } else {
                System.out.println("Numero di tentativi superato!");
                System.out.println("\nScore: " + hangmanApp.getScore());
            }

            System.out.println("Vuoi ancora giocare? si[y] o no[n]");
            scanner = new Scanner(System.in);
            char playAgain = scanner.next().charAt(0);
            if (String.valueOf(playAgain).equalsIgnoreCase("y")) {
                hangmanApp.setTrovato(false);
                hangmanApp.setNumAttempts(0);
                hangmanApp.resetWord();
            }
            else if(String.valueOf(playAgain).equalsIgnoreCase("n")){
                System.out.println("FINE GIOCO");
                break;
            }
        }


    }   // Fine main
}