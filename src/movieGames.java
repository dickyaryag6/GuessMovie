import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


class Game {
    private String title;
    private int titleLength;
    private List<String> wrongGuessed;
    private List<String> lettersGuessed;
    private boolean Finished;

    private List<String> movieUniqueLetters;

    Game(String title){
        this.title = title;
        titleLength = title.length();
        Finished = false;
        wrongGuessed=new ArrayList<String>();  
        lettersGuessed=new ArrayList<String>();  
        movieUniqueLetters=new ArrayList<String>();  

        for(int i=0;i<titleLength;i++) {
            if(!movieUniqueLetters.contains(String.valueOf(title.charAt(i))) & !Character.isWhitespace(title.charAt(i))) {
                movieUniqueLetters.add(String.valueOf(title.charAt(i)));
                // System.out.print(title.charAt(i));
            }
            
        }

        System.out.println(title);


        System.out.print("You are guessing : ");
        for (int i=0;i<this.title.length();i++){
            if (Character.isWhitespace(title.charAt(i))){
                System.out.print(" ");
            } else {
                System.out.print("_");
            }
        }
        System.out.println("\nYou have guessed ("+wrongGuessed.size()+") Letters : ");
        for(int j=0;j<wrongGuessed.size();j++){
            System.out.print(wrongGuessed.get(j));
        }
    }

    int getWrongGuessedSize() {
        return wrongGuessed.size();
    }

    boolean getFinished() {
        return Finished;
    }


    String getTitle() {
        return title;
    }

    int getUniqueLettersLength() {
        return movieUniqueLetters.size();
    }

    int getlettersGuessedLength() {
        return lettersGuessed.size();
    }

    void printGuessed(){
        System.out.print("You are guessing : ");

        for (int i=0;i<title.length();i++){
            if (lettersGuessed.contains(String.valueOf(title.charAt(i)))) {
                System.out.print(title.charAt(i));
            } else if (Character.isWhitespace(title.charAt(i))) {
                System.out.print(" ");
            }
            else {
                System.out.print("_");
            }
            
        }
        System.out.print("\nYou have guessed ("+wrongGuessed.size()+") Letters : ");
        for(int j=0;j<wrongGuessed.size();j++){
            System.out.print(wrongGuessed.get(j)+" ");
        }
        System.out.println();
    }

    void hasRightGuess(String guess) {

        if (!lettersGuessed.contains(guess)) {
            lettersGuessed.add(guess);
            if (lettersGuessed.size() == movieUniqueLetters.size()) {
                Finished = true;
                System.out.println("You have guessed "+title+" correctly");
                return;
            } 
        }
            printGuessed();
        
    }

    void hasWrongGuess(String guess) {
        wrongGuessed.add(guess);
        if(wrongGuessed.size() == 10) {
            Finished = true;
            System.out.println("You already guessed the wrong letter 10 times. Game Over!");
        } else {
            printGuessed();
        }


    }
}

public class movieGames {

    public static void main(String[] args) {
        File file = new File("movies.txt");

        List<String> movieTitles=new ArrayList<String>();  

        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                // System.out.println(line);
                movieTitles.add(line);  
                
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int index = (int) (Math.random() * movieTitles.size()); 
        String moviePicked = movieTitles.get(index);
        Game movie = new Game(moviePicked);

        Scanner scannerLetter = new Scanner(System.in);
        while(!movie.getFinished()) {

            System.out.print("Guess a letter :");
            String guess = scannerLetter.nextLine();
            if (movie.getTitle().contains(guess)) {
                // System.out.println(movie.movieUniqueLetters);
                movie.hasRightGuess(guess);
            } else {
                movie.hasWrongGuess(guess);
            }
        }
    }
}
    