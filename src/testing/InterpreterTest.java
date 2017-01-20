package testing;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Interpreter;
import misc.Table;

public class InterpreterTest {
	
	Table t1;

	@Before
	public void setUp() throws Exception {
		t1 = new Table("a", 3, new Table("c", 4, null));
	}

	@Test
	public void test() {
		assertEquals(t1.lookup("c"), 4);
		Table t2 = Interpreter.update(t1, "c", 7);
		assertEquals(t2.lookup("c"), 7);
	}

}
