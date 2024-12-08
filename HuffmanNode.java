package student;

import provided.BinarySequence;

public class HuffmanNode {

    private HuffmanNode zero; //left
    private HuffmanNode one; //right
    private Character data;

    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.zero = zero;
        this.one = one;
        this.data = null;
    }

    public HuffmanNode(char data) {
        this.data = data;
        this.zero = null;
        this.one = null;
    }

    public HuffmanNode getZero() {
        return zero;
    }

    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    public HuffmanNode getOne() {
        return one;
    }

    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    public Character getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public boolean isLeaf() {
        return zero == null && one == null;
    }

    public boolean isValidNode() {
        if (isLeaf()) {
            return data != null;
        }
        return data == null && zero != null && one != null;
    }

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
