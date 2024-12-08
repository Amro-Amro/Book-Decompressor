package student;

import provided.BinarySequence;

/**
 * The HuffmanCodeBook class represents a collection of characters and their corresponding binary sequences
 * for use in Huffman encoding. It provides functionality for adding sequences, encoding strings, and
 * retrieving encoded sequences based on characters.
 */
public class HuffmanCodeBook {

    /**
     * An array of characters for the letters stored in the codebook.
     */
    private char[] letters;

    /**
     * An array of BinarySequence objects for the binary sequences corresponding to the characters.
     */
    private BinarySequence[] sequence;

    /**
     * The number of characters currently stored in the codebook.
     */
    private int size;

    /**
     * Constructs a new HuffmanCodeBook with an initial capacity.
     */
    public HuffmanCodeBook() {
        letters = new char[100];
        sequence =  new BinarySequence[100];
        size = 0;
    }

    /**
     * Adds a character and its corresponding binary sequence to the codebook. If the character already exists,
     * the sequence is updated. If the capacity is full, the arrays are resized.
     *
     * @param c the character to add
     * @param seq the binary sequence corresponding to the character
     */
    public void addSequence(char c, BinarySequence seq) {
        if (size == letters.length) {
            resize();
        }

        int idx = findPosition(c);

        if (idx < size && letters[idx] == c) {
            sequence[idx] = seq;
        } else {
            for (int i = size; i > idx; i--) {
                letters[i] = letters[i - 1];
                sequence[i] = sequence[i - 1];
            }

            letters[idx] = c;
            sequence[idx] = seq;
            size++;
        }
    }

    /**
     * Resizes the arrays when the capacity is full, doubling the size.
     */
    private void resize() {
        int newSize = letters.length * 2;
        char[] newLetters = new char[newSize];
        BinarySequence[] newSequence = new BinarySequence[newSize];

        System.arraycopy(letters, 0, newLetters, 0, letters.length);
        System.arraycopy(sequence, 0, newSequence, 0, sequence.length);

        letters = newLetters;
        sequence = newSequence;
    }

    /**
     * Checks whether a given character is contained in the codebook.
     *
     * @param letter the character to check
     * @return true if the character exists in the codebook, false otherwise
     */
    public boolean contains(char letter) {
        int index = binarySearch(letter);
        return index != -1;
    }

    /**
     * Checks whether all characters in the input string are contained in the codebook.
     *
     * @param letters a string containing characters to check
     * @return true if all characters in the string are contained, false otherwise
     */
    public boolean containsAll(String letters) {
        for (int i = 0; i < letters.length(); i++) {
            char currentChar = letters.charAt(i);
            if (!contains(currentChar)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the binary sequence corresponding to a given character.
     *
     * @param c the character to look up
     * @return the binary sequence for the character, or null if the character does not exist
     */
    public BinarySequence getSequence(char c) {
        int i = binarySearch(c);
        if (i != -1) {
            return sequence[i];
        }
        return null;
    }

    /**
     * Encodes a given string by putting together the binary sequences.
     *
     * @param s the string to encode
     * @return a BinarySequence representing the encoded string
     */
    public BinarySequence encode(String s) {
        BinarySequence encoded = new BinarySequence();

        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);

            BinarySequence seq = getSequence(curChar);

            if (seq != null) {
                encoded.append(seq);
            }
        }
        return encoded;
    }

    /**
     * Performs a binary search for a given character in the letters array.
     *
     * @param c the character to search for
     * @return the index of the character if found, and if not found then -1.
     */
    private int binarySearch(char c) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (letters[mid] == c) {
                return mid;
            } else if (letters[mid] < c) {
                left = mid + 1;
            } else if (letters[mid] > c) {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Finds the position to insert a new character to keep it sorted.
     *
     * @param c the character to find the insertion position for
     * @return the index where the character should be inserted
     */
    private int findPosition(char c) {
        int left = 0;
        int right = size;

        while (left < right) {
            int mid = (left + right) / 2;
            if (letters[mid] < c) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * @return an array of characters in the codebook.
     */
    public char[] getAllCharacters() {
        char[] allCharachters = new char[size];
        System.arraycopy(letters, 0, allCharachters, 0, size);
        return allCharachters;
    }

    /**
     * @return an array of BinarySequence objects in the codebook.
     */
    public BinarySequence[] getAllSequences() {
        BinarySequence[] allSequences = new BinarySequence[size];
        System.arraycopy(sequence, 0, allSequences, 0, size);
        return allSequences;
    }

    /**
     * Prints all characters currently stored in the codebook.
     */
    public void printCharacters() {
        for (int i = 0; i < size; i++) {
            System.out.println(letters[i]);
        }
    }

}
