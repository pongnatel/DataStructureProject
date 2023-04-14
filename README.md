# DataStructureProject

### Idea: 

Step 1: Find the frequency of each letter in the correct password (e.g 'R':1 | 'M':5 | 'I':8 | 'T':2)
        and find the letter with the most frequency (max_freq_char)
        
Step 2: Create a test string for guessing, and always fill it with the max_freq_char

Step 3: Loop through each position in the test string and find the correct letter in correct position.
        For each position, always prioritize letter from more to less frequency
        
### Testing
- Has an average of 20.9 guesses for 1000 runs
- Never exceed 26 guesses for a password

### Implementation
- Object Letter is used to store the character and its frequency
- Object LetterArray is used to store all letters used to guess, providing sorting function, gettter, add, minus frequency... 
- Class SecreKeyGuess to perform the guessing
