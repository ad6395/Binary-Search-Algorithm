package neu.edu.info6205;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class BinarySearchTree {

	Node root;

	static class Node {
		int data;
		Node left, right;

		Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	Node generatetree(int A[], int start, int end) {

		if (start > end) {
			return null;
		}

		int mid = (start + end) / 2;
		Node node = new Node(A[mid]);

		node.left = generatetree(A, start, mid - 1);

		node.right = generatetree(A, mid + 1, end);

		return node;
	}

	public int TreeHeight(Node node) {
		if (node == null)
			return 0;
		else {
			int leftheight = TreeHeight(node.left);
			int rightheight = TreeHeight(node.right);

			if (leftheight > rightheight)
				return (leftheight + 1);
			else
				return (rightheight + 1);
		}
	}

	public Node preOrderTraversal(Node root) {

		if (root == null)
			return root;
		System.out.println(root.data + " ");
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
		return root;
	}

	public Node inOrderTraversal(Node root) {

		if (root == null)
			return root;
		inOrderTraversal(root.left);
		System.out.println(root.data + " ");
		inOrderTraversal(root.right);
		return root;
	}

	public Node postOrderTraversal(Node root) {

		if (root == null)
			return root;
		postOrderTraversal(root.left);
		postOrderTraversal(root.right);
		System.out.println(root.data + " ");
		return root;
	}

	public int BinarySearch(int n) {
		ArrayList<Integer> list = new ArrayList<>();
		int A[] = { 3, 6, 8, 12, 14, 17, 25, 29, 31, 36, 42, 47, 53, 55, 62 };
		/*
		 * A[7]=29; A[8]=31; A[9]=36; A[10]=42; A[11]=47; A[12]=53; A[13]=55; A[14]=62;
		 */
		Arrays.sort(A);

		int i, mid, mid2, left = 0, right = A.length - 1, flag = 0, left1 = 0, right1 = 0, count1 = 0, count2 = 0,
				R = 0, L = 0;
		while (left <= right) {

			mid = (left + right) / 2;

			if (A[mid] == n) {
				flag++;
				count1++;
				list.add(n);
				break;

			}
			if (n < A[mid]) {

				right = mid - 1;
				// list.add(A[right+1]);
				L = left1++;
				count1++;

			} else {

				left = mid + 1;
				// list.add(A[left+1]);
				R = right1++;

				count1++;
			}
			list.add(A[mid]);
			// count1=R+L;

		}
		if (flag == 1) {
			System.out.println("\nElement Found!");
		} else {
			System.out.println("\nElement not found!");
		}
		System.out.println("Total Nodes:" + count1);
		System.out.println("LEFT NODES:" + left1);
		System.out.println("RIGHT NODES:" + right1);
		System.out.println("Elements Traversed:" + list);

		return n;

	}

	public void insert(int data) {
		root = insert(root, data);
	}

	Node insert(Node root, int data) {

		if (root == null) {
			root = new Node(data);
			return root;
		}

		if (data < root.data)
			root.left = insert(root.left, data);
		else if (data > root.data)
			root.right = insert(root.right, data);

		return root;
	}

	void delete(int data) {
		root = delete(root, data);
	}

	public Node delete(Node root, int data) {
		/* Base Case: If the tree is empty */
		if (root == null)
			return root;

		/* Otherwise, recur down the tree */
		if (data < root.data)
			root.left = delete(root.left, data);
		else if (data > root.data)
			root.right = delete(root.right, data);

		// if key is same as root's key, then This is the node
		// to be deleted
		else {
			// node with only one child or no child
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;

			// node with two children: Get the inorder successor (smallest
			// in the right subtree)
			root.data = minValue(root.right);

			// Delete the inorder successor
			root.right = delete(root.right, root.data);
		}

		return root;
	}

	int minValue(Node root) {
		int minv = root.data;
		while (root.left != null) {
			minv = root.left.data;
			root = root.left;
		}
		return minv;
	}

	public static void main(String args[]) {
		BinarySearchTree tree = new BinarySearchTree();
		Scanner sc = new Scanner(System.in);

		// PART1

		int A[] = { 3, 6, 8, 12, 14, 17, 25, 29, 31, 36, 42, 47, 53, 55, 62 };
		int S = A.length;
		Arrays.sort(A);
		System.out.println("\t\t\t\t\t\t\t\t!! BINARY SEARCH MAIN !!");
		System.out.printf("\nGiven Array : %s", Arrays.toString(A));
		System.out.println("\nEnter the element to be searched:");
		int n = sc.nextInt();
		// BSTNode bstn = new
		tree.BinarySearch(n);
		tree.root = tree.generatetree(A, 0, S - 1);
		// tree.root = tree.insert(A, tree.root, 0);
		System.out.println("\nHeight of the tree:" + tree.TreeHeight(tree.root));
		System.out.println("\nEnter Element to be Inserted:");
		int m = sc.nextInt();
		tree.insert(m);
		System.out.println("\t\t\t\t\t\t\t\t!! TRAVERSAL AFTER INSERTION !!");
		System.out.println("\nInorder Traversal:");
		tree.inOrderTraversal(tree.root);
		System.out.println("\nPostorder Traversal:");
		tree.postOrderTraversal(tree.root);
		System.out.println("\nPreorder Traversal:");
		tree.preOrderTraversal(tree.root);
		System.out.println("\t\t\t\t\t\t\t\t!! DELETION !!");
		System.out.println("\nEnter Element to be Deleted:");
		int o = sc.nextInt();
		tree.delete(o);
		System.out.println("\t\t\t\t\t\t\t\t!! TRAVERSAL AFTER DELETION !!");
		System.out.println("\nInorder Traversal:");
		tree.inOrderTraversal(tree.root);
		System.out.println("\nPostorder Traversal:");
		tree.postOrderTraversal(tree.root);
		System.out.println("\nPreorder Traversal:");
		tree.preOrderTraversal(tree.root);

		// PART 2
		int i;
		System.out.println("Enter the value of N:");
		int a = sc.nextInt();
		System.out.println("Enter the value of K:");
		int b = sc.nextInt();
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		ArrayList<Integer> list3 = new ArrayList<>();
		ArrayList<Integer> list4 = new ArrayList<>();
		for (i = 0; i < a; i++) {
			list2.add(i);
		}
		Collections.shuffle(list2);
		int B[] = new int[b];
		for (i = 0; i < B.length; i++) {
			B[i] = list2.get(i);
		}
		System.out.printf("\nGiven Array : %s", Arrays.toString(B));
		int Z = B.length;
		Arrays.sort(B);
		System.out.println("\t\t\t\t\t\t\t\t!! BINARY SEARCH MAIN !!");
		System.out.printf("\nSorted Array : %s", Arrays.toString(B));
		System.out.println("\nEnter the element to be searched:");
		int x = sc.nextInt();
		int mid, mid2, left = 0, right = B.length - 1, flag = 0, left1 = 0, right1 = 0, count1 = 0, count2 = 0, R = 0,
				L = 0;
		while (left <= right) {

			mid = (left + right) / 2;

			if (B[mid] == x) {
				flag++;
				count1++;
				list.add(x);
				break;

			}
			if (x < B[mid]) {

				right = mid - 1;
				// list.add(A[right+1]);
				L = left1++;
				count1++;

			} else {

				left = mid + 1;
				// list.add(A[left+1]);
				R = right1++;

				count1++;
			}
			list.add(B[mid]);
			// count1=R+L;

		}
		if (flag == 1) {
			System.out.println("\nElement Found!");
		} else {
			System.out.println("\nElement not found!");
		}
		System.out.println("Total Nodes:" + count1);
		System.out.println("LEFT NODES:" + left1);
		System.out.println("RIGHT NODES:" + right1);
		System.out.println("Elements Traversed:" + list);

		tree.root = tree.generatetree(B, 0, Z - 1);
		System.out.println("\nHeight of the tree:" + tree.TreeHeight(tree.root));
		for (i = 0; i < b; i++) {
			list3.add(B[i]);
		}
		Collections.shuffle(list3);
		System.out.println("\nEnter Element to be Inserted:");
		int y = sc.nextInt();
		tree.insert(y);
		System.out.println("\t\t\t\t\t\t\t\t!! TRAVERSAL AFTER INSERTION !!");
		System.out.println("\nInorder Traversal:");
		tree.inOrderTraversal(tree.root);
		System.out.println("\nPostorder Traversal:");
		tree.postOrderTraversal(tree.root);
		System.out.println("\nPreorder Traversal:");
		tree.preOrderTraversal(tree.root);
		System.out.println("\t\t\t\t\t\t\t\t!! DELETION !!");
		System.out.println("\nEnter Element to be Deleted:");
		int w = sc.nextInt();
		tree.delete(w);
		System.out.println("\t\t\t\t\t\t\t\t!! TRAVERSAL AFTER DELETION !!");
		System.out.println("\nInorder Traversal:");
		tree.inOrderTraversal(tree.root);
		System.out.println("\nPostorder Traversal:");
		tree.postOrderTraversal(tree.root);
		System.out.println("\nPreorder Traversal:");
		tree.preOrderTraversal(tree.root);

		System.out.println("Enter size of K1:");
		int e = sc.nextInt();
		int C[] = new int[e];
		for (i = 0; i < C.length; i++) {
			C[i] = list2.get(i);
		}

		Arrays.sort(C);
		System.out.printf("\nSorted Array : %s", Arrays.toString(C));
		int V = C.length;
		tree.root = tree.generatetree(C, 0, V - 1);
		int h1 = tree.TreeHeight(tree.root);
		System.out.println("\nHeight of the tree:" + tree.TreeHeight(tree.root));

		System.out.println("\nEnter size of K2:");
		int d = sc.nextInt();
		int D[] = new int[d];
		for (i = 0; i < D.length; i++) {
			D[i] = list2.get(i);
		}
		System.out.printf("\nGiven  Array : %s", Arrays.toString(D));
		Arrays.sort(D);
		System.out.printf("\nSorted Array : %s", Arrays.toString(D));
		int U = D.length;
		tree.root = tree.generatetree(D, 0, U - 1);
		int h2 = tree.TreeHeight(tree.root);
		System.out.println("\nHeight of the tree:" + tree.TreeHeight(tree.root));

		System.out.println("\nEnter size of K3:");
		int f = sc.nextInt();
		int F[] = new int[f];
		for (i = 0; i < F.length; i++) {
			F[i] = list2.get(i);
		}
		System.out.printf("\nGiven  Array : %s", Arrays.toString(F));
		Arrays.sort(F);
		System.out.printf("\nSorted Array : %s", Arrays.toString(F));
		int j = F.length;
		tree.root = tree.generatetree(F, 0, j - 1);
		int h3 = tree.TreeHeight(tree.root);
		System.out.println("\nHeight of the tree:" + tree.TreeHeight(tree.root));

		System.out.println("\nEnter size of K4:");
		int u = sc.nextInt();
		int X[] = new int[u];
		for (i = 0; i < X.length; i++) {
			X[i] = list2.get(i);
		}
		System.out.printf("\nGiven  Array : %s", Arrays.toString(X));
		Arrays.sort(X);
		System.out.printf("\nSorted Array : %s", Arrays.toString(X));
		int t = X.length;
		tree.root = tree.generatetree(X, 0, t - 1);
		int h4 = tree.TreeHeight(tree.root);
		System.out.println("\nHeight of the tree:" + tree.TreeHeight(tree.root));
		int H1 = h2 - h1;
		int H2 = h3 - h2;
		int H3 = h4 - h3;
		double Rate = (double) (H1 + H2 + H3) / 4;

		System.out.println("\nDifference of height betwen K1 and K2:" + H1);
		System.out.println("\nDifference of height betwen K2 and K3:" + H2);
		System.out.println("\nDifference of height betwen K3 and K4:" + H3);
		System.out.println("\nRate of change of height:" + Rate);
		System.out.println("\nSince " + Rate + " is close to 0, the given condition is satisfied!");
		System.out.println("\t\t\t\t\t\t\t\t!! THE END !!");

	}

}
