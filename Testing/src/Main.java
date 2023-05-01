public class Main {
    public static void main(String[] args) throws Exception {
        int max = 0;
        int sum = 0;

        // Try 100000 different passwords
        for(int i = 1; i <= 1000000; i++){
            System.out.println("Attempt " + i);
            SecretKeyGuesser guesser = new SecretKeyGuesser();
            // Activate the start() method
            guesser.start();
            // Count the total number of guesses
            sum += guesser.counter;
            if(max < guesser.counter) max = guesser.counter;
        }

        // Calculate the average number of guesses
        double avg = ( (double) sum/ (double) 1000000);
        System.out.println("Average guess: " + avg);
        System.out.println("Max guess: " + max);
    }
}