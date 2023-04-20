
public class SecretKeyGuesser {
    final private char[] letters = {'R','M','I','T'};
    private char max_freq_char;
    private int max_freq;
    final private int length = 16;
    int counter = 0;


    public void start(){
        SecretKey secretKey = new SecretKey();

        //count the frequency of each letter
        //and store it in an array
        LetterArray letterArray = countFreq(secretKey);

        //initialize the first string with all appearance of max_freq_char
        String guessString = new String(new char[length]).replace('\0', max_freq_char);

        int max_guess = max_freq; // so we have the max guess already

        boolean flag; //flag to stop when we found the correct letter

        //this one loops through the guess string
        for(int index = 0; index < length; index++){
            flag = false;

            //this one loops through the letter array
            for(int i = 0; i <= letterArray.getLastAvailableElement() && !flag; i++){
                char guessLetter = letterArray.getLetterByIndex(i);

                //prunning
                //stop when we are sure this letter is correct,
                //so we move to next position without calling guess method
                if(i == letterArray.getLastAvailableElement() && i > 1){
                    guessString = changeLetter(guessString, guessLetter, index);
                    letterArray.deductFrequency(i);
                    flag = true;
                    max_guess++;

                    // reset the last available element
                    if(letterArray.getFreqByIndex(i) == 0) letterArray.sortDescending();
                    continue; // move to next position
                }

                // only check if the letter is different from the current letter at this position
                if(guessLetter != max_freq_char){
                    guessString = changeLetter(guessString, guessLetter, index);
                    int result = secretKey.guess(guessString); // do the guessing
                    counter++; //used to count performance -> remove later

                    //stop when we got 16 matches
                    if(result == 16) return;

                    // case 1: the original letter (max_freq_char) is correct
                    if(result < max_guess){
                        guessString = changeLetter(guessString, max_freq_char, index);
                        letterArray.deductFrequency(0);
                        max_freq--;

                        //sort the letterArray if we found the max_freq_char has changed
                        if(letterArray.getFreqByIndex(0) < letterArray.getFreqByIndex(1)) {
                            letterArray.sortDescending(); //sorting
                            max_freq_char = letterArray.getLetterByIndex(0); //reset max_freq_char
                            max_guess = max_guess + letterArray.getFreqByIndex(0) - max_freq; //update max_guess
                            guessString = changeAllLetter(guessString, max_freq_char, index + 1); //change remaining letters
                        }

                        max_freq = letterArray.getFreqByIndex(0); //update max_freq
                        flag = true; //check flag
                    }

                    //case 2: the new letter is correct
                    else if (result > max_guess) {
                        letterArray.deductFrequency(i); //
                        letterArray.sortDescending();
                        max_guess = result;
                        flag = true;
                    }
                }
            }
        }

        //this one is for some strings that are completed before calling the last guess method.
        secretKey.guess(guessString);
        counter++;
    }


    //create the first three strings for finding the frequency
    private String[] createTestString() {
        String[] result = new String[letters.length - 1];
        for(int i = 0; i < result.length; i++){
            result[i] = new String(new char[length]).replace('\0', letters[i]);
        }
        return result;
    }

    //method to find the frequency
    private LetterArray countFreq(SecretKey secretKey) {
        LetterArray result = new LetterArray(letters.length);
        String[] testString = createTestString();
        int sum = 0;
        for(int i = 0; i < testString.length; i++){
            int count = secretKey.guess(testString[i]);
            result.add(letters[i], count, i);
            sum += count;

            counter ++; //used to compare performance with other algorithms
        }

        int countLastLetter = length - sum;
        result.add(letters[letters.length - 1],countLastLetter,result.getSize() - 1);

        //sort by frequency
        result.sortDescending();

        //store the max frequency char and its frequency
        max_freq_char = result.getLetterByIndex(0);
        max_freq = result.getFreqByIndex(0);
        return result;
    }

    // This changeLetter function changes a char (with specific position) in a string
    // return a new String with that letter changed
    // e.g "RRRRR" -> "RTRRR" (changing at index = 1)
    private String changeLetter(String s, char c, int index){
        String result = s;
        result = result.substring(0, index) + c
                + result.substring(index + 1);
        return result;
    }

    //this changeAllLetter function changes all the chars from a specific position to the end
    // return a new string
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
