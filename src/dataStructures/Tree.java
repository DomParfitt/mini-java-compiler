package dataStructures;

/**
 * Class to represent a binary tree data structure
 * @author Dom Parfitt
 *
 */
public class Tree {
	
	public Tree left, right;
	public String key;
	public Object binding;
	
	public Tree(Tree left, String key, Object binding, Tree right) {
		this.left = left;
		this.right = right;
		this.key = key;
		this.binding = binding;
	}
	
//	/**
//	 * Shortcut method to insert into self
//	 * @param key the value of the node to be inserted
//	 * @return a new Tree with the new node inserted
//	 */
//	public Tree insert(String key) {
//		return insert(key, this);
//	}
	
	/**
	 * Method to insert a new node into the Tree
	 * @param key the value of the node to insert
	 * @param t the Tree to insert into
	 * @return a new Tree with the node inserted
	 */
	public static Tree insert(String key, Object binding, Tree t) {
		if (t == null) {
			return new Tree(null, key, binding, null);
		} else if (key.compareTo(t.key) < 0) {
			return new Tree(insert(key, binding, t.left), t.key, t.binding, t.right);
		} else if (key.compareTo(t.key) > 0) {
			return new Tree(t.left, t.key, t.binding, insert(key, binding, t.right));
		} else {
			return new Tree(t.left, key, binding, t.right);
		}
	}
	
//	/**
//	 * Shortcut method for checking a value within this Tree
//	 * @param key the value to check for
//	 * @return whether the value is present or not
//	 */
//	public boolean contains(String key) {
//		return contains(key, this);
//	}
	
	/**
	 * Method to check whether a Tree contains a given value
	 * @param key the value to check for
	 * @param t the Tree to check in
	 * @return a boolean whether the value is contained in the Tree
	 * or not
	 */
	public static boolean contains(String key, Tree t) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.key) < 0) {
			return contains(key, t.left);
		} else if (key.compareTo(t.key) > 0) {
			return contains(key, t.right);
		} else {
			return true;
		}
	}
	
//	/**
//	 * Shortcut method for lookup to look within this Tree
//	 * @param key the key to lookup
//	 * @return the Object bound to the key or null if the key is not present
//	 */
//	public Object lookup(String key) {
//		return lookup(key, this);
//	}
	
	/**
	 * Method to lookup the binding to a key in a Tree
	 * @param key the key to lookup
	 * @param t the Tree to look in
	 * @return the binding of the key or null if it isn't present
	 */
	public static Object lookup(String key, Tree t) {
		if (t == null) {
			return null;
		} else if (key.compareTo(t.key) < 0) {
			return lookup(key, t.left);
		} else if (key.compareTo(t.key) > 0) {
			return lookup(key, t.right);
		} else {
			return t.binding;
		}
	}
	
	public static Tree insertMulti(String[] keys, Tree t) {
		Tree temp = t;
		for(String key : keys) {
			temp = Tree.insert(key, null, temp);
		}
		
		return temp;
	}
	
	public static void printInOrder(Tree t) {
		if (t.left == null && t.right == null) {
			System.out.print(t.key);;
		} else if (t.left == null) {
			System.out.print(t.key + " ");
			printInOrder(t.right);
		} else if (t.right == null) {
			printInOrder(t.left);
			System.out.println(t.key + " ");
		} else {
			printInOrder(t.left);
			System.out.println(t.key + " ");
			printInOrder(t.right);
		}
	}
}
