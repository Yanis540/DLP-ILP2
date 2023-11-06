// Generated from d:/Yanis/FAC/M1/S1/DLP/TP/ilp2/ilp2/ANTLRGrammars/ILPMLgrammar2.g4 by ANTLR 4.13.1

    package antlr4;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ILPMLgrammar2Parser}.
 */
public interface ILPMLgrammar2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ILPMLgrammar2Parser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ILPMLgrammar2Parser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPMLgrammar2Parser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ILPMLgrammar2Parser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPMLgrammar2Parser#globalFunDef}.
	 * @param ctx the parse tree
	 */
	void enterGlobalFunDef(ILPMLgrammar2Parser.GlobalFunDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPMLgrammar2Parser#globalFunDef}.
	 * @param ctx the parse tree
	 */
	void exitGlobalFunDef(ILPMLgrammar2Parser.GlobalFunDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Binding}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBinding(ILPMLgrammar2Parser.BindingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Binding}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBinding(ILPMLgrammar2Parser.BindingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Loop}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLoop(ILPMLgrammar2Parser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Loop}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLoop(ILPMLgrammar2Parser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVariable(ILPMLgrammar2Parser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVariable(ILPMLgrammar2Parser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Alternative}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAlternative(ILPMLgrammar2Parser.AlternativeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Alternative}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAlternative(ILPMLgrammar2Parser.AlternativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Invocation}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInvocation(ILPMLgrammar2Parser.InvocationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Invocation}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInvocation(ILPMLgrammar2Parser.InvocationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstFloat}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstFloat(ILPMLgrammar2Parser.ConstFloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstFloat}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstFloat(ILPMLgrammar2Parser.ConstFloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Sequence}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSequence(ILPMLgrammar2Parser.SequenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Sequence}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSequence(ILPMLgrammar2Parser.SequenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VariableAssign}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssign(ILPMLgrammar2Parser.VariableAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VariableAssign}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssign(ILPMLgrammar2Parser.VariableAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstFalse}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstFalse(ILPMLgrammar2Parser.ConstFalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstFalse}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstFalse(ILPMLgrammar2Parser.ConstFalseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnary(ILPMLgrammar2Parser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnary(ILPMLgrammar2Parser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstTrue}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstTrue(ILPMLgrammar2Parser.ConstTrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstTrue}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstTrue(ILPMLgrammar2Parser.ConstTrueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstInteger}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstInteger(ILPMLgrammar2Parser.ConstIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstInteger}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstInteger(ILPMLgrammar2Parser.ConstIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstString}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstString(ILPMLgrammar2Parser.ConstStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstString}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstString(ILPMLgrammar2Parser.ConstStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Binary}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBinary(ILPMLgrammar2Parser.BinaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Binary}
	 * labeled alternative in {@link ILPMLgrammar2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBinary(ILPMLgrammar2Parser.BinaryContext ctx);
}