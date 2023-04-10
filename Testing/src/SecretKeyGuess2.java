import java.util.HashMap;
import java.util.Map;

public class SecretKeyGuess2 {
    private char[] letters = {'R','M','I','T'};
    private char max_freq_char;
    private int max_freq;
    private Map<Character, Integer> map_char;
    int counter = 0;
    private int length = 16;

    // this createTestString function create String for countFreq function
    // For every char in array letters, create a String with length = password.length
    // e.g 'R' -> "RRRRRRRRRRRRRRRR"
    private String[] createTestString(){
        String[] result = new String[letters.length - 1];
        for(int i = 0; i < letters.length - 1; i++){
            result[i] = new String(new char[length]).replace('\0', letters[i]);
        }
        return result;
    }

    //This countFreq function count the frequency of each letter in the password
    //Used first 3 guess to count the frequency
    Map countFreq(SecretKey key){
        Map<Character, Integer> map = new HashMap();
        String[] strings = createTestString();
        for(int i = 0; i < strings.length; i++){
            int count = key.guess(strings[i]);
           // counter ++; //used to compare performance with other algorithms
            map.put(letters[i], count);
        }

        //count frequency of the last letter by subtract all other letters
        int countLastLetter = 0;
        for(int i = 0; i < letters.length - 1; i++){
            countLastLetter += map.get(letters[i]);
        }
        countLastLetter = length - countLastLetter;

        map.put(letters[letters.length - 1],countLastLetter);
        return map;
    }


    // This printMap function support viewing the map of letters and their remaining frequency
    // also see the char with most current frequency
    private void printMap() {
        map_char.forEach((key, value) -> System.out.print(key + ":" + value + ", "));
        System.out.println("Max char: " + max_freq_char);
    }

    // This changeLetter function changes a char (with specific position) in a string
    // return a new String with that letter changed
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
        String result = "";
        for (int i = 0; i < rightSub; i++){
            result += c;
        }
        result = s.substring(0,index) + result;
        return result;
    }

    //Find the letter with the max remaining frequency
    // return true if we find a new max freq letter
    // return false if max freq letter doesn't change
    private boolean findMax(){
        char temp = max_freq_char;
        max_freq = map_char.get(letters[0]);
        max_freq_char = letters[0];
        for(int i = 1; i < letters.length; i++){
            if(max_freq < map_char.get(letters[i])){
                max_freq = map_char.get(letters[i]);
                max_freq_char = letters[i];
            }
        }
        if(temp != max_freq_char) return true;
        return false;
    }

    public void start(SecretKey key){
//        SecretKey key = new SecretKey();

        // create a map char
        map_char = countFreq(key);

        //find the char with max frequency
        findMax();

        printMap();

        //Create the first test String with the max frequency letter
        String testString = new String(new char[length]).replace('\0',max_freq_char);

        int index = 0; //keep track of position in the test String
        int max_count = map_char.get(max_freq_char); //keep track of number of right position
        boolean flag; //flag = true when found the right letter at the right position
        System.out.println("First string: " + testString + ", Max count: " + max_count);

        //this loop starts to loop through the test string
        //using variable index as position
        do {
            System.out.println();
            printMap();
            flag = false; //reset flag

            //loop through the letter array
            for (int i = 0; i < 4 && !flag; i++) {
                //pick one letter to test
                //condition: that letter is not the max_freq_letter
                // and that letter still have remaining frequency
                if (letters[i] != max_freq_char && map_char.get(letters[i]) != 0) {
                    testString = changeLetter(testString,letters[i],index);
                    System.out.println(testString + ", index: " + index);
                    int result = key.guess(testString); //Guess
                    counter++;
                    //case 1: correct position deducted
                    // -> the previous letter is correct
                    if (result < max_count) {
                        testString = changeLetter(testString,max_freq_char,index);
                        flag = true;
                        index++; //update index
                        System.out.println("keep");
                        System.out.println("Max_count: " + max_count);
                        //update the map char
                        if(map_char.get(max_freq_char) != 0) map_char.put(max_freq_char, map_char.get(max_freq_char)-1);
                    }
                    //case 2: correct position added
                    // -> the current letter is correct
                    else if (result > max_count) {
                        flag = true;
                        //update the map char
                        if(map_char.get(testString.charAt(index)) != 0) map_char.put(testString.charAt(index), map_char.get(testString.charAt(index))-1);
                        index++; //update index
                        max_count = result; //update the max count
                        System.out.println("Change to " + testString.charAt(index-1));
                        System.out.println("result: " + result);
                        System.out.println("Max_count: " + max_count);
                    }
                }
            }

            //checking if a new max freq letter is found
            char tmp = max_freq_char;
            if(findMax() && max_count != length){
                testString = changeAllLetter(testString, max_freq_char, index);

                //update the max_count = current max_count + new letter frequency - last letter frequency
                max_count += map_char.get(max_freq_char) - map_char.get(tmp);

                //if max_count reaches maximum, return the last guess
                if(max_count == length){
                    System.out.println("Matched: " + key.guess(testString));
                    counter++;
                }
            }
        }while(max_count != length);
    }
}
