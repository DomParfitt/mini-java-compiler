package main;

import dataStructures.IntAndTable;
import dataStructures.Table;
import grammar.AssignStm;
import grammar.CompoundStm;
import grammar.EseqExp;
import grammar.Exp;
import grammar.ExpList;
import grammar.IdExp;
import grammar.LastExpList;
import grammar.NumExp;
import grammar.OpExp;
import grammar.PairExpList;
import grammar.PrintStm;
import grammar.Stm;

/**
 * Class for static methods involved in interpreting code
 * @author Dom Parfitt
 *
 */
/**
 * @author Dom Parfitt
 *
 */
public class Interpreter {
	
	/**
	 * Method to get the number of arguments in a List of Expressions (i.e. the 
	 * length of the list)
	 * @param exps the List of Expressions
	 * @return the length of the list (i.e. the number of arguments present)
	 */
	public static int numargs(ExpList exps) {
		if(exps instanceof LastExpList) { //If the List is a terminator return 1, for the head
			return 1;
		} else { //If the list has a tail recurse through until you hit a terminator
			return 1 + numargs(((PairExpList) exps).tail);
		}
	}
	
	/**
	 * Method to get the maximum number of arguments that any PrintStm has with a given Statement
	 * @param s the Statement to test
	 * @return the maximum number of arguments provided to a PrintStm
	 */
	public static int maxargs(Stm s) {
		/*
		 * If the Statement is a PrintStm get the length of its Expression List and the
		 * maxargs of the Expression List and return whichever is bigger 
		 */
		if (s instanceof PrintStm) { 
			return Math.max(numargs (((PrintStm) s).exps), maxargs(((PrintStm) s).exps));
		} else if (s instanceof CompoundStm) {
			CompoundStm c = (CompoundStm) s;
			return Math.max(maxargs(c.stm1), maxargs(c.stm2));
		} else if (s instanceof AssignStm) {
			return maxargs(((AssignStm) s).exp);
		} else {
			return 0;
		}
	}
	
	/**
	 * Method to get the largest number of Statements that any Expression in a List has
	 * @param exps the Expression List
	 * @return the largest number of Statements for any Expression
	 */
	public static int maxargs(ExpList exps) {
		if (exps instanceof LastExpList) {
			LastExpList last = (LastExpList) exps;
			return maxargs(last.head);
		} else if (exps instanceof PairExpList) {
			PairExpList pair = (PairExpList) exps;
			return Math.max(maxargs(pair.head), maxargs(pair.tail));
		} else { //This should never be reached
			return 0;
		}
	}
	
	/**
	 * Method to get the largest number of Statements in an Expression
	 * @param exp the Expression
	 * @return the largest number of Statements
	 */
	public static int maxargs(Exp exp) {
		if (exp instanceof EseqExp) {
			EseqExp seqExp = (EseqExp) exp;
			return Math.max(maxargs(seqExp.stm1) , maxargs(seqExp.exp));
		} else if (exp instanceof OpExp) {
			OpExp opExp = (OpExp) exp;
			return Math.max(maxargs(opExp.left), maxargs(opExp.right));
		} else {
			return 0;
		}
	}
	
	/**
	 * Method to interpret a program as a whole (in the form of a Statement)
	 * @param s the Statement containing the program
	 */
	public static void interp(Stm s) {
		interpStm(s, new Table());
	}
	
	/**
	 * Method to interpret a Statement. Updates the given Table based
	 * on the Statement and returns a new Table which reflects these
	 * updates
	 * @param s the Statement to interpret
	 * @param t the original Table
	 * @return a new Table with the updates applied
	 */
	public static Table interpStm(Stm s, Table t) {
		if (s instanceof AssignStm) {
			AssignStm aStm = (AssignStm) s;
			IntAndTable intTable = interpExp(aStm.exp, t);
			return update(intTable.t, aStm.id, intTable.i); 
		} else if (s instanceof CompoundStm) {
			CompoundStm cStm = (CompoundStm) s;
			Table t1 = interpStm(cStm.stm1, t);
			return interpStm(cStm.stm2, t1);
		} else {
			PrintStm pStm = (PrintStm) s;
			IntAndTable t1 = interpExpList(pStm.exps, t);
			System.out.print(t1.i + " ");
			
			if (pStm.exps instanceof PairExpList) {
				PairExpList pair = (PairExpList) pStm.exps;
				return interpStm(new PrintStm(pair.tail), t1.t);
			} 
			System.out.println();
			return t1.t;	
		}
		
	}
	
	/**
	 * Method to interpret an Expression. Updates the given Table
	 * and also produces a return value in the form of an int.
	 * @param e the Expression to interpret
	 * @param t the Table to update
	 * @return
	 */
	public static IntAndTable interpExp(Exp e, Table t) {
		if (e instanceof EseqExp) {
			EseqExp sExp = (EseqExp) e;
			Table t1 = interpStm(sExp.stm1, t);
			return interpExp(sExp.exp, t1);
			
		} else if (e instanceof OpExp) {
			OpExp opExp = (OpExp) e;
			IntAndTable t1 = interpExp(opExp.left, t);
			IntAndTable t2 = interpExp(opExp.right, t1.t);
			
			switch (opExp.op) {
				case OpExp.PLUS:
					return new IntAndTable(t1.i + t2.i, t2.t);
				case OpExp.MINUS:
					return new IntAndTable(t1.i - t2.i, t2.t);
				case OpExp.TIMES:
					return new IntAndTable(t1.i * t2.i, t2.t);
				default: //Needs to be default otherwise compiler throws a fit
					return new IntAndTable(t1.i / t2.i, t2.t);
			}
			
		} else if (e instanceof NumExp) {
			NumExp num = (NumExp) e;
			return new IntAndTable(num.num, t);
		} else {
			IdExp id = (IdExp) e;
			return new IntAndTable(t.lookup(id.id), t);
		}
		
	}
	
	/**
	 * Method to interpret a List of Expressions. 
	 * @param l the List of Expressions
	 * @param t the Table representing the current State
	 * @return the result of the interpretation and the state
	 */
	public static IntAndTable interpExpList(ExpList l, Table t) {
		if (l instanceof PairExpList) {
			PairExpList pair = (PairExpList) l;
			return interpExp(pair.head, t);
			
			
		} else {
			LastExpList last = (LastExpList) l;
			return interpExp(last.head, t);
		}
	}
	
	/**
	 * Method to update a Table representing the State of the program
	 * with a new variable
	 * @param t the Table representing the State
	 * @param id the name of the variable
	 * @param i the value of the variable
	 * @return a new Table with the variable inserted at the front
	 */
	public static Table update(Table t, String id, int i) {
		return new Table(id, i, t);
	}
	
	

}
