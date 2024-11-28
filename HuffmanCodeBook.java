package student;

import provided.BinarySequence;

public class HuffmanCodeBookk {

    private char[] letters;
    private BinarySequence[] sequence;
    private int size;

    public HuffmanCodeBookk() {
        letters = new char[100];
        sequence =  new BinarySequence[100];
        size = 0;
    }

    public void addSequence(char c, BinarySequence seq) {
        int idx = findPosition(c);

        if (idx < size && letters[idx] == c) {
            sequence[idx] = seq;
        } else {

            for (int i = size; i > idx; i--) {
                letters[i] = letters[i - 1];
                sequence[i] = sequence[i - 1];
            }
        }

        letters[idx] = c;
        sequence[idx] = seq;
        size++;
    }

    public boolean contains(char letter) {
        int index = binarySearch(letter);
        return index != -1;
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
        int i = binarySearch(c);
        if (i != -1) {
            return sequence[i];
        }
        return null;
    }


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

    public char[] getAllCharacters() {
        char[] allCharachters = new char[size];
        System.arraycopy(letters, 0, allCharachters, 0, size);
        return allCharachters;
    }

    public BinarySequence[] getAllSequences() {
        BinarySequence[] allSequences = new BinarySequence[size];
        System.arraycopy(sequence, 0, allSequences, 0, size);
        return allSequences;
    }

    public void printCharacters() {
        for (int i = 0; i < size; i++) {
            System.out.println(letters[i]);
        }
    }

}
