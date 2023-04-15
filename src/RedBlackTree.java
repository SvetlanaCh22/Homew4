import java.util.ArrayList;
import java.util.List;

// Красно-черное дерево
public class RedBlackTree {

    Node root; // корневая нода

    private enum Color {
        RED, BLACK
    }
    private class Node {
        private int value;
        private Color color;
        private Node leftchild;
        private Node rightchild;

        @Override
        public String toString() {
            return "Node{" + "value=" + value + ", color=" + color + "}";
        }
    }

    // смена цвета ячейки дерева
    private void colorSwap(Node node) {
        node.rightchild.color = Color.BLACK;
        node.leftchild.color = Color.BLACK;
        node.color = Color.RED;
    }

    // левый поворот дерева
    private Node leftSwap(Node node) {
        Node leftChild = node.leftchild;
        Node betweenChild = leftChild.rightchild;
        leftChild.rightchild = node;
        node.leftchild = betweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }

    // правый поворот дерева
    private Node rightSwap(Node node) {
        Node rightchild = node.rightchild;
        Node betweenChild = rightchild.leftchild;
        rightchild.leftchild = node;
        node.rightchild = betweenChild;
        rightchild.color = node.color;
        node.color = Color.RED;
        return rightchild;
    }

    // перебалансировка элементов дерева
    private Node rebalnce(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.rightchild != null && result.rightchild.color == Color.RED && (result.leftchild == null || result.leftchild.color == Color.BLACK)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.leftchild != null && result.leftchild.color == Color.RED && result.leftchild.leftchild != null && result.leftchild.leftchild.color == Color.RED) {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.leftchild != null && result.leftchild.color == Color.RED && result.rightchild != null && result.rightchild.color == Color.RED) {
                needRebalance = true;
                colorSwap(result);
            }
        }
        while (needRebalance) ;
        return result;
    }

    // добавление элемента дерева
    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalnce(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }

    private boolean addNode(Node node, int value) {
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value) {
                if (node.leftchild != null) {
                    boolean result = addNode(node.leftchild, value);
                    node.leftchild = rebalnce(node.leftchild);
                    return result;
                } else {
                    node.leftchild = new Node();
                    node.leftchild.color = Color.RED;
                    node.leftchild.value = value;
                    return true;
                }
            } else {
                if (node.rightchild != null) {
                    boolean result = addNode(node.rightchild, value);
                    node.rightchild = rebalnce(node.rightchild);
                    return result;
                } else {
                    node.rightchild = new Node();
                    node.rightchild.color = Color.RED;
                    node.rightchild.value = value;
                    return true;
                }
            }
        }
    }

}