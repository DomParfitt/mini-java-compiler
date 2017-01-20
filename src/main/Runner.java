package main;

import java.lang.reflect.Field;

import grammar.AssignStm;
import grammar.CompoundStm;
import grammar.EseqExp;
import grammar.IdExp;
import grammar.LastExpList;
import grammar.NumExp;
import grammar.OpExp;
import grammar.PairExpList;
import grammar.PrintStm;
import grammar.Stm;

public class Runner {

	public static void main(String[] args) {
		
		Stm prog = new CompoundStm(
						new AssignStm("a",
								new OpExp(
										new NumExp(5),
										OpExp.PLUS, 
										new NumExp(3))),
						new CompoundStm(
								new AssignStm("b",
										new EseqExp(
												new PrintStm(
														new PairExpList(
																new IdExp("a"),
																new LastExpList(
																		new OpExp(
																				new IdExp("a"), 
																				OpExp.MINUS, 
																				new NumExp(1))))),
												new OpExp(
														new NumExp(10), 
														OpExp.TIMES, 
														new IdExp("a")))),
								new PrintStm(
										new LastExpList(
												new IdExp("b")))));
		
		Stm prog2 = new CompoundStm(new AssignStm("c", new NumExp(10)), new PrintStm(new LastExpList(new IdExp("c"))));
		
		PairExpList list = new PairExpList( //List 1
								new IdExp("a"),  //Head 1
								new PairExpList( //Tail 1 / List 2
										new NumExp(1), //Head 2
										new PairExpList( //Tail 2 / List 3
												new IdExp("b"), // Head 3
												new LastExpList( // Tail 3 / List 4
														new OpExp( //Head 4
															new NumExp(2), 
															OpExp.PLUS, 
															new NumExp(3)
															)
												)
										)
								)
							);
		
		Stm prog3 = new CompoundStm(new PrintStm(new LastExpList(new NumExp(10))), new PrintStm(new LastExpList(new NumExp(15))));
		
//		System.out.println(Interpreter.maxargs(prog));
//		System.out.println(Interpreter.maxargs(prog2));
		
//		System.out.println("List numargs: " + Interpreter.numargs(list)); //Should be 4
//		System.out.println("List maxargs: " + Interpreter.maxargs(list)); //Maybe 2? Actually 0
		
		Interpreter.interp(prog);
	}
	
	

}
