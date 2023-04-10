public class SecretKey {
  private String correctKey = "";
  private int counter;
  private final char[] char_array = {'R', 'M', 'I', 'T'};

  private final int length = 16;
  public SecretKey() {
    // for the real test, your program will not know this
//    correctKey = "RRRRRRRRRMITRMIT";
    for (int i = 0; i < length; i++){
      int index = (int) (Math.random() * 4);
      correctKey += char_array[index];
    }
    counter = 0;
  }

  @Override
  public String toString() {
    return "correctKey='" + correctKey + '\'' + '}';
  }

  public int guess(String guessedKey) {
    counter++;
    // validation
    if (guessedKey.length() != correctKey.length()) {
      return -1;
    }
    int matched = 0;
    for (int i = 0; i < guessedKey.length(); i++) {
      char c = guessedKey.charAt(i);
      if (c != 'R' && c != 'M' && c != 'I' && c != 'T') {
        return -1;
      }
      if (c == correctKey.charAt(i)) {
        matched++;
      }
    }
    if (matched == correctKey.length()) {
      System.out.println("Number of guesses: " + counter);
    }
    return matched;
  }

  public int length(){
    return length;
  }
}


//  public static void main(String[] args) {
//    new SecretKeyGuesser().start();
//  }
//}
