import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        SecretKey key = new SecretKey();
//        Scanner scanner = new Scanner(System.in);
//        int result = 0;
//        do{
//            System.out.print("Your guess: ");
//            String guess = scanner.next();
//
//            result = key.guess(guess);
//            System.out.println("Correct place: " + result);
//        }while(result != key.length());
//        System.out.println(key);

        SecretKeyGuess2 guesser = new SecretKeyGuess2();
            SecretKey key = new SecretKey();

        guesser.start(key);
//        ======================
//        int sum1 = 0, sum2 = 0;
//        int i;
//        for(i = 1; i <= 100; i++){
//            SecretKey key = new SecretKey();
//            SecretKeyGuess guesser = new SecretKeyGuess();
//            guesser.start(key);
//            SecretKeyGuess2 guesser2 = new SecretKeyGuess2();
//            guesser2.start(key);
//            sum1 += guesser.counter;
//            sum2 += guesser2.counter;
//        }
//        System.out.println("i: " + (i-1));
//        System.out.println("Guess 1: " + (double)sum1/(double)(i-1));
//        System.out.println("Guess 2: " + (double)sum2/(double)(i-1));
//        ===================
    }
}