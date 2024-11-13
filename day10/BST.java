public class BST {

    // Node class to represent each node in the tree
    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    // Root of the Binary Search Tree
    private Node root;

    // Constructor
    public BST() {
        root = null;
    }

    // Insert a node into the BST
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // Recursive function to insert a new key
    private Node insertRec(Node root, int data) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // Otherwise, recur down the tree
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        // Return the (unchanged) node pointer
        return root;
    }

    // Search for a node in the BST
    public boolean search(int data) {
        return searchRec(root, data);
    }

    // Recursive function to search a key in the BST
    private boolean searchRec(Node root, int data) {
        // Base cases: root is null or key is present at the root
        if (root == null) {
            return false;
        }
        if (root.data == data) {
            return true;
        }

        // Key is greater than root's data
        if (data > root.data) {
            return searchRec(root.right, data);
        }

        // Key is smaller than root's data
        return searchRec(root.left, data);
    }

    // In-order traversal of the tree
    public void inorder() {
        inorderRec(root);
    }

    // Recursive function for in-order traversal
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // Delete a node in the BST
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    // Recursive function to delete a node
    private Node deleteRec(Node root, int data) {
        // Base case: the tree is empty
        if (root == null) {
            return root;
        }

        // Recur down the tree
        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            // Node to be deleted found

            // Case 1: Node has no children (leaf node)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: Node has only one child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node has two children
            // Get the inorder successor (smallest in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    // Find the node with the minimum value
    private int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    // Main function to test the BST implementation
    public static void main(String[] args) {
        BST tree = new BST();

        // Inserting nodes
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // In-order traversal
        System.out.println("In-order traversal:");
        tree.inorder();  // Output: 20 30 40 50 60 70 80
        System.out.println();

        // Search for a node
        System.out.println("Search for 40: " + tree.search(40));  // true
        System.out.println("Search for 100: " + tree.search(100)); // false

        // Deleting a node
        tree.delete(20);
        System.out.println("In-order traversal after deletion of 20:");
        tree.inorder();  // Output: 30 40 50 60 70 80
        System.out.println();
    }
}
