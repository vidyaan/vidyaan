public class AVLTree {

    // Node class representing each node in the AVL Tree
    static class Node {
        int data;
        Node left, right;
        int height;

        public Node(int data) {
            this.data = data;
            this.height = 1; // Height of a new node is 1 (leaf node)
        }
    }

    private Node root;

    // Utility method to get the height of a node
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // Utility method to get the balance factor of a node
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // Right rotation to balance the tree
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotation to balance the tree
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Insert a node into the AVL Tree
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // Recursive method to insert a new node and balance the tree
    private Node insertRec(Node node, int data) {
        // 1. Perform the normal BST insert
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insertRec(node.left, data);
        } else if (data > node.data) {
            node.right = insertRec(node.right, data);
        } else {
            return node; // Duplicate values are not allowed in AVL
        }

        // 2. Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. Get the balance factor of this node to check whether it became unbalanced
        int balance = getBalance(node);

        // If the node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // In-order traversal of the AVL Tree
    public void inorder() {
        inorderRec(root);
    }

    // Helper method for in-order traversal
    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.data + " ");
            inorderRec(node.right);
        }
    }

    // Main method to test the AVL Tree
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Insert nodes into the AVL Tree
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);
        tree.insert(25);

        // In-order traversal of the AVL Tree
        System.out.println("In-order traversal:");
        tree.inorder();  // Output should be sorted: 10 15 20 25 30
    }
}
