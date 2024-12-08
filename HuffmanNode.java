package student;

/**
 * Represents a node in a Huffman tree. Each node can either hold a character or leaf node,
 * or point to other nodes based on the binary Huffman encoding.
 */
public class HuffmanNode {

    /**
     * left child.
     */
    private HuffmanNode zero;

    /**
     * right child.
     */
    private HuffmanNode one;

    /**
     * charachter being sorted in the node.
     */
    private Character data;

    /**
     * Constructs an internal HuffmanNode with two child nodes.
     *
     * @param zero The left child node.
     * @param one The right child node.
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.zero = zero;
        this.one = one;
        this.data = null;
    }

    /**
     * Constructs a leaf HuffmanNode that stores the date, or
     * a charachter. This node does not have child nodes.
     *
     * @param data The character being stored in this node.
     */
    public HuffmanNode(char data) {
        this.data = data;
        this.zero = null;
        this.one = null;
    }

    /**
     * @return The left child node.
     */
    public HuffmanNode getZero() {
        return zero;
    }

    /**
     * @param zero The node to set as the left child.
     */
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    /**
     * @return The right child node.
     */
    public HuffmanNode getOne() {
        return one;
    }

    /**
     * @param one The node to set as the right child.
     */
    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    /**
     * @return The character stored in the node, or null if the node is an internal node.
     */
    public Character getData() {
        return data;
    }

    /**
     * @param data The character to store in this node.
     */
    public void setData(char data) {
        this.data = data;
    }

    /**
     * Checks if the node is a leaf node.
     *
     * @return True if the node is a leaf node, false otherwise.
     */
    public boolean isLeaf() {
        return zero == null && one == null;
    }

    /**
     * Checks if this node is a valid Huffman node.
     * A valid node is either a leaf with data or an internal node with two children.
     *
     * @return True if the node is valid, false otherwise.
     */
    public boolean isValidNode() {
        if (isLeaf()) {
            return data != null;
        }
        return data == null && zero != null && one != null;
    }

    /**
     * This method recursively checks if the entire tree rooted at this node
     * is a valid Huffman tree. Each node in the tree must be a valid node.
     *
     * @return True if the tree is valid, false otherwise.
     */
    public boolean isValidTree() {
        if (!isValidNode()) {
            return false;
        }

        if (zero != null && !zero.isValidTree()) {
            return false;
        }

        if (one != null && !one.isValidTree()) {
            return false;
        }

        return true;
    }

}
