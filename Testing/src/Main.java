public class Main {
    public static void main(String[] args) throws Exception {
        int max = 0;

        // Try 100000 different passwords
        for(int i = 1; i < 100000; i++){
            System.out.println("Attempt " + i);
            SecretKeyGuesser guesser = new SecretKeyGuesser();
            // Activate the start() method
            guesser.start();
            // Count the total number of guesses
            max += guesser.counter;
        }

        // Calculate the average number of guesses
        double avg = ( (double) max/ (double) 100000);
        System.out.println("Average guess: " + avg);
    }
}