package student;

import provided.BinarySequence;

public class HuffmanCodeTree {
    private HuffmanNode root;

    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }

    public HuffmanCodeTree(HuffmanCodeBook codebook) {
        root = new HuffmanNode(null,null);

        char[] letters = codebook.getAllCharacters();
        BinarySequence[] sequence = codebook.getAllSequences();

        for (int i = 0; i < letters.length; i++) {
            put(sequence[i], letters[i]);
        }
    }

    public boolean isValid() {
        return root != null && root.isValidTree();
    }

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
