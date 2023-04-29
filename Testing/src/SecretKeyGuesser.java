 public class SecretKeyGuesser {
    final private char[] letters = {'R','M','I','T'};
    private char max_freq_char;
    private int max_freq;
    final private int length = 16;

    // this attribute is used for evaluation
    int counter = 0;


    public void start(){
        SecretKey secretKey = new SecretKey();

        // Count the frequency of each letter
        // And store it in an array
        LetterArray letterArray = countFreq(secretKey);

        // Initialize the first string with all appearance of max_freq_char
        String guessString = new String(new char[length]).replace('\0', max_freq_char);

        int max_guess = max_freq; // So we have the max guess already

        boolean flag; // Flag to stop when we found the correct letter

        // This one loops through the guess string
        for(int index = 0; index < length; index++){
            flag = false;

            //this one loops through the letter array
            for(int i = 0; i <= letterArray.getLastAvailableLetter() && !flag; i++){
                char guessLetter = letterArray.getLetterByIndex(i);

                // Stop when we are sure this letter is correct,
                // So we move to next position without calling guess method
                if(i == letterArray.getLastAvailableLetter() && i > 1){
                    guessString = changeLetter(guessString, guessLetter, index);
                    letterArray.deductFrequency(i);
                    flag = true;
                    max_guess++;

                    // Reset the last available element
                    if(letterArray.getFreqByIndex(i) == 0)
                        letterArray.sortDescending();

                    // Move to next position
                    continue;
                }

                // Only check if the letter is different from the current letter at this position
                if(guessLetter != max_freq_char){
                    guessString = changeLetter(guessString, guessLetter, index);
                    int result = secretKey.guess(guessString); // Do the guessing
                    counter++; // Used to count performance to remove later

                    // Stop when we got 16 matches
                    if(result == 16) return;

                    // Case 1: the original letter (max_freq_char) is correct
                    if(result < max_guess){
                        guessString = changeLetter(guessString, max_freq_char, index);
                        letterArray.deductFrequency(0);
                        max_freq--;

                        // Sort the letterArray if we found the max_freq_char has changed
                        if(letterArray.getFreqByIndex(0) < letterArray.getFreqByIndex(1)) {
                            letterArray.sortDescending(); // Sorting
                            max_freq_char = letterArray.getLetterByIndex(0); // Reset max_freq_char
                            max_guess = max_guess + letterArray.getFreqByIndex(0) - max_freq; // Update max_guess
                            guessString = changeAllLetter(guessString, max_freq_char, index + 1); //Change remaining letters
                        }

                        max_freq = letterArray.getFreqByIndex(0); // Update max_freq
                        flag = true; // Check flag
                    }

                    // Case 2: the new letter is correct
                    else if (result > max_guess) {
                        letterArray.deductFrequency(i); //
                        letterArray.sortDescending();
                        max_guess = result;
                        flag = true;
                    }
                }
            }
        }

        // This one is for some strings that are completed before calling the last guess method.
        secretKey.guess(guessString);
        counter++;
    }


    // Create the first three strings for finding the frequency
    private String[] createTestString() {
        String[] result = new String[letters.length - 1];
        for(int i = 0; i < result.length; i++){
            result[i] = new String(new char[length]).replace('\0', letters[i]);
        }
        return result;
    }

    // Method to find the frequency
    private LetterArray countFreq(SecretKey secretKey) {
        LetterArray result = new LetterArray(letters.length);
        String[] testString = createTestString();
        int sum = 0;
        for(int i = 0; i < testString.length; i++){
            int count = secretKey.guess(testString[i]);
            result.add(letters[i], count, i);
            sum += count;

            counter ++; // Used to compare performance with other algorithms
        }

        int countLastLetter = length - sum;
        result.add(letters[letters.length - 1],countLastLetter,result.getSize() - 1);

        // Sort by frequency
        result.sortDescending();

        // Store the max frequency char and its frequency
        max_freq_char = result.getLetterByIndex(0);
        max_freq = result.getFreqByIndex(0);
        return result;
    }

    // This changeLetter function changes a char (with specific position) in a string
    // Return a new String with that letter changed
    // e.g "RRRRR" -> "RTRRR" (changing at index = 1)
    private String changeLetter(String s, char c, int index){
        String result = s;
        result = result.substring(0, index) + c
                + result.substring(index + 1);
        return result;
    }

    // This changeAllLetter function changes all the chars from a specific position to the end
    // Return a new string
    // e.g "RRRRR" -> "RTTTT" (starting from index = 1)
    private String changeAllLetter(String s, char c, int index){
        int rightSub = s.length() - index;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rightSub; i++){
            result.append(c);
        }
        result.insert(0, s.substring(0, index));
        return result.toString();
    }
}