# DataStructureProject

****Idea: 

Step 1: Find the frequency of each letter in the correct password (e.g 'R':1 | 'M':5 | 'I':8 | 'T':2)
        and find the letter with the most frequency (max_freq_char)
Step 2: Create a test string for guessing, and always fill it with the max_freq_char
Step 3: Loop through each position in the test string and find the correct letter in correct position.
        For each postion, the worse case is 3 guesses (can be optimized to 2 guesses)
        
****Testing
Has an average of 24.5 guesses for 1000 runs
