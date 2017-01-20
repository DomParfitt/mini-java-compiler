package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dataStructures.Tree;

public class TreeTest {
	
	public Tree t1, t2, t3, t4, t5;

	@Before
	public void setUp() throws Exception {
		t1 = new Tree(null, "b", null, null);
	}

	@Test
	public void test1() {
		assertTrue(Tree.contains("b", t1));
		assertFalse(Tree.contains("a", t1));
	}
	
	@Test
	public void test2() {
		t3 = Tree.insert("a", null, t1);
		assertTrue(Tree.contains("a", t3));
		
		t4 = Tree.insert("c", null, t3);
		assertTrue(Tree.contains("c", t4));
		
		t5 = Tree.insert("c", null, t4);
		assertTrue(Tree.contains("c", t5));
	}
	
	@Test
	public void test3() {
		
	}

}
