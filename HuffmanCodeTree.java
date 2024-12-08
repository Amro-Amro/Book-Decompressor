package student;

import provided.BinarySequence;

/**
 * Represents a Huffman code tree used for encoding and decoding characters.
 * This tree is constructed based on a Huffman codebook or a given root node.
 */
public class HuffmanCodeTree {

    /**
     * The root of the Huffman tree.
     */
    private HuffmanNode root;

    /**
     * Constructs a HuffmanCodeTree with a specified root node.
     *
     * @param root The root node of the Huffman tree.
     */
    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }

    /**
     * Constructs a HuffmanCodeTree using a HuffmanCodeBook.
     * Each character and its corresponding binary sequence from the codebook is used
     * to construct the tree.
     *
     * @param codebook The HuffmanCodeBook containing characters and binary sequences.
     */
    public HuffmanCodeTree(HuffmanCodeBook codebook) {
        root = new HuffmanNode(null, null);

        char[] letters = codebook.getAllCharacters();
        BinarySequence[] sequence = codebook.getAllSequences();

        for (int i = 0; i < letters.length; i++) {
            put(sequence[i], letters[i]);
        }
    }

    /**
     * Checks if the Huffman tree is valid.
     * A valid Huffman tree has a valid root node and all subtrees are also valid.
     *
     * @return True if the tree is valid, false otherwise.
     */
    public boolean isValid() {
        return root != null && root.isValidTree();
    }

    /**
     * Adds a character to the Huffman code tree based on a given binary sequence.
     * It either moves left which is 0 or right - 1.
     *
     * @param seq The binary sequence that represents the path to the character.
     * @param letter The character added to the tree.
     */
    public void put(BinarySequence seq, char letter) {
        HuffmanNode curNode = root;

        for (int i = 0; i < seq.size(); i++) {
            boolean curBit = seq.get(i);

            if (curBit) {
                if (curNode.getOne() == null) {
                    curNode.setOne(new HuffmanNode(null, null));
                }
                curNode = curNode.getOne();
            } else {
                if (curNode.getZero() == null) {
                    curNode.setZero(new HuffmanNode(null, null));
                }
                curNode = curNode.getZero();
            }
        }
        curNode.setData(letter);
    }

    /**
     * This method decodes a binary sequence into a string using the Huffman tree.
     * The method appends characters from leaf nodes to the result.
     *
     * @param s The binary sequence to be decoded.
     * @return The decoded string.
     */
    public String decode(BinarySequence s) {
        HuffmanNode node = root;
        StringBuilder decoder = new StringBuilder();

        for (int i = 0; i < s.size(); i++) {
            boolean boolInSeq = s.get(i);

            if (boolInSeq) {
                node = node.getOne();
            } else {
                node = node.getZero();
            }

            if (node.isLeaf()) {
                decoder.append(node.getData());

                node = root;
            }
        }

        return decoder.toString();
    }
}
