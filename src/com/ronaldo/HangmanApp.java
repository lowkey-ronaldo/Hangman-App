package com.ronaldo;

import java.util.*;

public class HangmanApp {

    private String parolaLetta;
    private char carattereLetto;

    private String wordToGuess;
    private List<String> wrongChars = new ArrayList<>();
    String [] currentWord;


    private int numAttempts;
    private int maxAttempts;
    private boolean isTrovato;

    private int score;

    String [] words = {"Cybersecurity", "Programmatore", "Protocollo",
            "Firewall", "Architettura", "Compilatore", "Tecnologia", "Interfaccia", "Automazione", "Sicurezza"  };

    public HangmanApp() {
        this.maxAttempts = 5;
        this.isTrovato = false;
        this.numAttempts = 0;
        this.score = 0;
        this.resetWord();
    }

    Scanner scanner = new Scanner(System.in);

    public int getNumAttempts() {
        return numAttempts;
    }

    public void setNumAttempts(int numAttempts) {
        this.numAttempts = numAttempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public boolean getIsTrovato() {
        return isTrovato;
    }

    public void setTrovato(boolean trovato) {
        isTrovato = trovato;
    }

    public String[] getCurrentWord() {
        return currentWord;
    }

    public List<String> getWrongChars() {
        return wrongChars;
    }

    public String generateRandomWord(){
        Random rand = new Random();
        String randomWord = words[rand.nextInt(words.length)];

        return randomWord;
    }

    public void setWrongChars(char carattereLetto){

        boolean contieneLettera = wrongChars.contains(String.valueOf(carattereLetto));
        if(!contieneLettera){
            wrongChars.add(String.valueOf(carattereLetto) );
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void resetWord(){
        this.wordToGuess = this.generateRandomWord();
        this.wrongChars.clear();
        currentWord = new String[wordToGuess.length()];
        Arrays.fill(currentWord, "_ ");
    }

    public void cercaLettera(char choice){
        System.out.print("\nInserisci un carattere: ");
        carattereLetto = scanner.next().charAt(0);
        if (wordToGuess.toLowerCase().contains(String.valueOf(carattereLetto).toLowerCase())) {
            for (int j = 0; j < wordToGuess.length(); j++) {
                if (String.valueOf(wordToGuess.charAt(j)).equalsIgnoreCase(String.valueOf(carattereLetto))) {
                    if (j == 0) {
                        if (currentWord[j].contains("_")) {
                            currentWord[j] = String.valueOf(carattereLetto).toUpperCase() + " ";
                        }
                    } else {
                        if (currentWord[j].contains("_")) {
                            currentWord[j] = String.valueOf(carattereLetto) + " ";
                        }
                    }
                }
            }
        } else if (!wrongChars.contains(String.valueOf(carattereLetto))) {
            numAttempts++;
            setWrongChars(carattereLetto);
            System.out.println("\nLettera non presente. Hai già tentato: " + numAttempts + " volta");
        }

        // Parola corrente
        for(String s : currentWord)
            System.out.print(s);

        if(String.join("", currentWord).replace(" ", "").equalsIgnoreCase(wordToGuess)){
            setTrovato(true);
            setScore(this.getScore() + 10);
        }

        if(!getIsTrovato()) {
            System.out.println("\nList of wrong letters: " + this.getWrongChars() + "\n");
        }
    }

    public void cercaParola(char choice){
        scanner = new Scanner(System.in);
        System.out.print("Inserisci la parola: ");
        scanner = new Scanner(System.in);
        parolaLetta = scanner.nextLine();
        if(parolaLetta.equalsIgnoreCase(wordToGuess)){
            setTrovato(true);
            this.setScore(this.getScore() + 10);
        }
        else {
            numAttempts++;
            System.out.println("Parola sbagliata. Hai già tentato: " + numAttempts + " volta");
        }
    }

}
