import java.util.HashMap;
import java.util.Map;

public class SecretKeyGuess {
    private char[] letters = {'R','M','I','T'};
    private char min_freq_char;
    private int min_freq;
    private Map<Character, Integer> map_char;
    int counter = 0;

    Map countFreq(SecretKey key){
        Map<Character, Integer> map = new HashMap();
        String[] strings = {"RRRRRRRRRRRRRRRR", "MMMMMMMMMMMMMMMM", "IIIIIIIIIIIIIIII"};
        int min = 0;
        for(int i = 0; i < 3; i++){
            int count = key.guess(strings[i]);
            counter ++;
            map.put(letters[i], count);
        }
        int countT = 16 - (map.get(letters[0]) + map.get(letters[1]) + map.get(letters[2]));
        map.put(letters[3],countT);

        return map;
    }


    private void printMap() {
        map_char.forEach((key, value) -> System.out.print(key + ":" + value + ", "));
        System.out.println("Min char: " + min_freq_char);
    }

    private String changeLetter(String s, char c, int index){
        String result = s;
        result = result.substring(0, index) + c
                + result.substring(index + 1);
        return result;
    }

    private boolean findMin(){
        char temp = min_freq_char;
        min_freq = map_char.get(letters[0]);
        min_freq_char = letters[0];
        for(int i = 1; i < letters.length; i++){
            if((map_char.get(letters[i]) != 0) && (min_freq == 0 || min_freq > map_char.get(letters[i]))){
                min_freq = map_char.get(letters[i]);
                min_freq_char = letters[i];
            }
        }
        if(temp != min_freq_char) return true;
        return false;
    }

    private String changeAllLetter(String s, char c, int index){
        int rightSub = s.length() - index;
        String result = "";
        for (int i = 0; i < rightSub; i++){
            result += c;
        }
        result = s.substring(0,index) + result;
        return result;
    }



    public void start(SecretKey key){
//        SecretKey key = new SecretKey();
        map_char = countFreq(key);
        //find the char with min frequency
        findMin();
        printMap();
        String s = "";

        for(int i = 0; i < 16; i++){
            s += min_freq_char;
        }


        int index = 0;
        int max_count = map_char.get(min_freq_char);
        boolean flag;
        System.out.println("First string: " + s + ", Max count: " + max_count);


        do {
            System.out.println();
            printMap();
            flag = false;
            for (int i = 0; i < 4 && !flag; i++) {
                if (letters[i] != min_freq_char && map_char.get(letters[i]) != 0) {
                    char currentLetter = s.charAt(index);
                    s = changeLetter(s,letters[i],index);
                    System.out.println(s + ", index: " + index);
                    int result = key.guess(s);
                    counter ++;
                    if (result < max_count) {
                        s = changeLetter(s,currentLetter,index);
                        flag = true;
                        index++;
                        System.out.println("keep");
                        System.out.println("Max_count: " + max_count);
                        if(map_char.get(currentLetter) != 0) map_char.put(currentLetter, map_char.get(currentLetter)-1);
                    } else if (result > max_count) {
                        flag = true;
                        if(map_char.get(s.charAt(index)) != 0) map_char.put(s.charAt(index), map_char.get(s.charAt(index))-1);
                        index++;
                        max_count = result;
                        System.out.println("Change to " + s.charAt(index-1));
                        System.out.println("result: " + result);
                        System.out.println("Max_count: " + max_count);
                    }
                }
            }
            char tmp = min_freq_char;
            if(findMin() && max_count != 16){
                s = changeAllLetter(s, min_freq_char, index);
                max_count += map_char.get(min_freq_char) - map_char.get(tmp);
                if(max_count == 16){
                    System.out.println("Matched: " + key.guess(s));
                    counter ++;
                }
            }
        }while(max_count != 16);
    }
}
