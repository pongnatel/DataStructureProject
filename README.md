# DataStructureProject

Welcome to our Data Structure Project in Data Structures and Algorithms [COSC2658]! 

## Idea
Our idea is to create a guessing algorithm for a secret key. Here are the steps we took to accomplish this:

1. We found the frequency of each letter in the correct password and identified the letter with the highest frequency.
2. We created a test string for guessing and filled it with the letter with the highest frequency.
3. We looped through each position in the test string, finding the correct letter in the correct position. We prioritized letters from more to less frequency for each position.

## Testing
Our algorithm has been thoroughly tested and has shown the following results:
- It has an average of 20.9 guesses for 1000 runs.
- It has never exceeded 26 guesses for a password.

## Implementation
To implement our algorithm, we used the following objects and classes:
- The `Letter` object stores a character and its frequency.
- The `LetterArray` object stores all letters used to guess, providing sorting function, getter, add, minus frequency...
- The `SecretKeyGuess` class performs the guessing.

## Contribution Scores
We are proud to present the contribution scores of our team members:
- Nguyen Minh Quan - s3927181: **3**
- Ngo Tran Bao Thach - s3927021: **3**
- Le Tan Phong - s3877819: **3**
- Nguyen Cong Gia Hien - s3884308: **3**

Thank you for taking the time to review our project! Here is the link to watch our YouTube demo video [here](https://www.youtube.com/watch?v=2L4l7DIF1zY) for more information.
