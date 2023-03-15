// Marco Soekmono
// 3/10/23
// CS145
// Lab 6

// This program will be able to create a binary search tree database
// of members containing attributes that is able to be added, deleted,
// edited, and printed out on all its possible traversals.

// For extra credit, I made sure user input was foolproof and that the
// traversals were printable to another file


// class initiation
public class Dictionary {

    // declare necessary variables
    public Member data;
    public Dictionary left;
    public Dictionary right;

    // empty constructor
    public Dictionary() {
    }

    // constructor with data
    public Dictionary(Member data) {
        this.data = data;
    }

    // full constructor initiation
    public Dictionary(Member data, Dictionary left, Dictionary right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    // add a node into the binary tree
    public Dictionary add(Dictionary root, Member node) {
        if (root == null) {
            root = new Dictionary(node);
        } else if (root.data == null) {
            root = new Dictionary(node);
        } else if (root.data.getPrimaryKey() > node.getPrimaryKey()) {
            root.left = add(root.left, node);
        } else if (root.data.getPrimaryKey() < node.getPrimaryKey()) {
            root.right = add(root.right, node);
        } else {
            System.out.println("Duplicate primary key!");
        }
        return root;
    }

    // get minimum of a dictionary subtree
    public Member getMin(Dictionary root) {
        if (root.left != null) {
            return getMin(root.left);
        } else {
            return root.data;
        }
    }

    // remove a node from binary tree
    public Dictionary remove(Dictionary root, Member node) {
        if (root == null) {
            return null;
        } else if (root.data == null) {
            return null;
        } else if (root.data.getPrimaryKey() > node.getPrimaryKey()) {
            root.left = remove(root.left, node);
        } else if (root.data.getPrimaryKey() < node.getPrimaryKey()) {
            root.right = remove(root.right, node);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                root.data = getMin(root.right);
                root.right = remove(root.right, root.data);
            }
        }

        return root;
    }

    // get a member on the tree based on a given primary key
    public Member get(Dictionary root, int primaryKey) {
        if (root.data.getPrimaryKey() > primaryKey) {
            return get(root.left, primaryKey);
        } else if (root.data.getPrimaryKey() < primaryKey) {
            return get(root.right, primaryKey);
        } else {
            return root.data;
        }
    }

    // edit a member with a new one
    public Dictionary edit(Dictionary root, Member old, Member replacement) {
        root = root.remove(root, old);
        if (root == null) {
            root = new Dictionary();
        }
        root = root.add(root, replacement);
        return root;
    }

    // return a string of every entry in preorder traversal
    public String printPreOrder(Dictionary root) {
        String concatenate = "";

        if (root == null) {
            return "";
        }
        if (root.data == null) {
            return "";
        }
        concatenate += "\n" + root.data.toString();
        concatenate += "\n" + printPreOrder(root.left);
        concatenate += "\n" + printPreOrder(root.right);

        return concatenate;
    }

    // return a string of every entry in inorder traversal
    public String printInOrder(Dictionary root) {
        String concatenate = "";

        if (root == null) {
            return "";
        }
        if (root.data == null) {
            return "";
        }
        concatenate += printInOrder(root.left) + "\n";
        concatenate += root.data.toString() + "\n";
        concatenate += printPreOrder(root.right) + "\n";

        return concatenate;
    }

    // return a string of every entry in postorder traversal
    public String printPostOrder(Dictionary root) {
        String concatenate = "";

        if (root == null) {
            return "";
        }
        if (root.data == null) {
            return "";
        }
        concatenate += printPostOrder(root.left) + "\n";
        concatenate += printPostOrder(root.right) + "\n";
        concatenate += root.data.toString() + "\n";

        return concatenate;
    }

    // lookup based on a traversal value
    public String lookup(Dictionary root, int traversal) {
        if (traversal == 1) {
            return printPreOrder(root);
        } else if (traversal == 2) {
            return printInOrder(root);
        } else if (traversal == 3) {
            return printPostOrder(root);
        } else {
            return "";
        }
    }

    // return total amount of nodes in a tree
    public int totalNodes(Dictionary root) {
        if (root == null) {
            return 0;
        }
        if (root.data == null) {
            return 0;
        }

        int left = totalNodes(root.left);
        int right = totalNodes(root.right);

        return 1+left+right;
    }

    // return boolean if a primary key exists in a tree
    public boolean contains(Dictionary root, int primaryKey) {
        if (root == null) {
            return false;
        } 
        if (root.data == null) {
            return false;
        }

        if (root.data.getPrimaryKey() > primaryKey) {
            return contains(root.left, primaryKey);
        } else if (root.data.getPrimaryKey() < primaryKey) {
            return contains(root.right, primaryKey);
        } else if ( root.data.getPrimaryKey() == primaryKey){
            return true;
        } else {
            return false;
        }

    }
}