package student;

import provided.BinarySequence;

public class HuffmanCodeBook {

    private char[] letters;
    private BinarySequence[] sequence;
    private int size;

    public HuffmanCodeBook() {
        letters = new char[10];
        sequence =  new BinarySequence[10];
        size = 0;
    }

    public void addSequence(char c, BinarySequence seq) {
        if (size == letters.length) {
            resize();
        }

        letters[size] = c;
        sequence[size] = seq;
        size++;
    }

    private void resize() {
        int newSize = letters.length * 2;
        char[] updatedLetters = new char[newSize];
        BinarySequence[] newBinarySeq = new BinarySequence[newSize];

        for (int i = 0; i < size; i++) {
            updatedLetters[i] = letters[i];
            newBinarySeq[i] = sequence[i];
        }

        letters = updatedLetters;
        sequence = newBinarySeq;
    }

    public boolean contains(char letter) {
        for (int i  = 0; i < size; i++) {
            if (letter == letters[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(String letters) {
        for (int i = 0; i < letters.length() ; i++) {
            char currentChar = letters.charAt(i);
            if (!contains(currentChar)) {
                return false;
            }
        }

        return true;
    }

    public BinarySequence getSequence(char c) {
        
    }


    public BinarySequence encode(String s) {
        
    }

}
