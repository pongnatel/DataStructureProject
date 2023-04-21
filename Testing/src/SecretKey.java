public class SecretKey {
  private String correctKey = "";
  private int counter;

  private final int length = 16;
  public SecretKey() throws Exception {
    // for the real test, your program will not know this
//    correctKey = "IRMTTMTTTRMMIRRT";

//    correctKey = "RRRRRRRRRRRRRRRR";
//
//    correctKey = "EEE";
//
//    correctKey = "RRRRRRRMMMMMMIRF";

//    correctKey = "RTMRMIRIMRTMIITM";
//
    correctKey = "MRITIIMIMTIMTTMR";

//    for (int i = 0; i < length; i++){
//      int index = (int) (Math.random() * 4);
//      char[] char_array = {'R', 'M', 'I', 'T'};
//      correctKey += char_array[index];
//    }
//    counter = 0;
//    System.out.println(correctKey);

//    validate the correct key
    validate(correctKey);
  }

  private void validate(String key) throws Exception {
    // Check the length of the input
    if (key.length() != length) {
      throw new Exception("The length of the guessed key must be 16 characters!");
    }
    // Check if the input contains any letters besides from 'R', 'M', 'I', 'T'
    for (char i: key.toCharArray()) {
      if (i != 'R' && i != 'M' && i != 'I' && i != 'T') {
        throw new Exception("The guessed key must contain R, M, I, T only!");
      }
    }
  }

  @Override
  public String toString() {
    return "correctKey='" + correctKey + '\'' + '}';
  }

  public int guess(String guessedKey) {
    counter++;
    // Length Validation
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
}
