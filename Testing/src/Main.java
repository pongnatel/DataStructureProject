public class Main {
    public static void main(String[] args) throws Exception {
        int max = 0;
        for(int i = 1; i < 10000; i++){
            System.out.println("Attempt " + i);
            SecretKeyGuesser guesser = new SecretKeyGuesser();

            guesser.start();
            if(guesser.counter >= max) max = guesser.counter;
//            max += guesser.counter;
        }

//        double avg = ( (double) max/ (double) 100000);
//        System.out.println("Max guess: " + avg);
        System.out.println("Max guess: " + max);


//        SecretKeyGuess guesser = new SecretKeyGuess();
//
//        guesser.start();
    }
}