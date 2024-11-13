
public class AVLTree {

    // Node class to represent each node in the AVL tree
    static class Node {
        int data, height;
        Node left, right;

        Node(int data) {
            this.data = data;
            height = 1;
        }
    }

    private Node root;

    // Get height of a node
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // Get balance factor of a node
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // Right rotation
    private Node rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    // Left rotation
    private Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    // Insert a node
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.data) node.left = insertRec(node.left, key);
        else if (key > node.data) node.right = insertRec(node.right, key);

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && key < node.left.data) return rightRotate(node);
        if (balance < -1 && key > node.right.data) return leftRotate(node);
        if (balance > 1 && key > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // In-order traversal
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // Search for a value
    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(Node root, int key) {
        if (root == null) return false;
        if (root.data == key) return true;
        return key < root.data ? searchRec(root.left, key) : searchRec(root.right, key);
    }

    // Main to test AVL Tree
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(25);
        tree.insert(40);

        System.out.println("In-order traversal:");
        tree.inorder();  // Output: 10 20 25 30 40

        System.out.println("\nSearch for 25: " + tree.search(25)); // true
        System.out.println("Search for 50: " + tree.search(50)); // false
    }
}
