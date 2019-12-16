#include<iostream>
using namespace std;

class TreeNode {
    int value;
    TreeNode *left, *right;
	public:
    	TreeNode(int value) {
        	this->value = value; this->left = NULL; this->right = NULL;
    	}	
    	int getvalue() { return value; }
    	TreeNode* getLeft() { return left; }
    	TreeNode* getRight() { return right; }
    	void setLeft(TreeNode *left) { this->left = left; }
    	void setRight(TreeNode *right) { this->right = right; }
};
class Tree {
    TreeNode *root;
	public:
    	Tree() {
        	root = NULL;
    	}
    	TreeNode* getRoot() { return root; }
    	void insert(int num){
    		TreeNode *current_node, *temp_node;
    
    		current_node = NULL;
    		temp_node = this->root;
    		while (temp_node != NULL) {
        		current_node = temp_node;
        		if (num < current_node->getvalue())
            		temp_node = current_node->getLeft();
        		else
            		temp_node = current_node->getRight();          
    		}
    		TreeNode*new_node = new TreeNode(num);
    		if (current_node == NULL) this->root = new_node;
    		else if (num < current_node->getvalue())
        		current_node->setLeft(new_node);
    		else
	        	current_node->setRight(new_node);
		}
	    void postorder(TreeNode* root)   {
			if (root!=NULL) {
        		postorder(root->getLeft());
        		postorder(root->getRight());
    	    	cout << root->getvalue() << endl;
			}
    	}
};

int main() {
    int num,tc;
    Tree *t = new Tree;
    
    while (scanf("%d", &num) != EOF) {
        t->insert(num);
    }
    t->postorder(t->getRoot());
    
}
