package com.paracamplus.ilp2.ilp2tme5.partie3.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTdeclaration;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;

import antlr4.ILPMLgrammar2tme5Partie3Listener;

import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTfactory;

import static antlr4.ILPMLgrammar2tme5Partie3Parser.*;

public class ILPMLListener implements ILPMLgrammar2tme5Partie3Listener {

	
	protected IASTfactory factory;
	
	public ILPMLListener(IASTfactory factory) {
		super();
		this.factory = factory;		
	}

	
	public void enterBreakLabeled(BreakLabeledContext ctx) {}


	public void exitBreakLabeled(BreakLabeledContext ctx) {
		ctx.node=factory.newBreakLabeled(ctx.label.getText()); 
	}

	public void enterContinueLabeled(ContinueLabeledContext ctx) {}


	public void exitContinueLabeled(ContinueLabeledContext ctx) {
		ctx.node=factory.newContinueLabeled(ctx.label.getText()); 
	}

	public void exitLoop(LoopContext ctx) {
		ctx.node = 
			ctx.label == null 
			?	factory.newLoop(ctx.condition.node, ctx.body.node)	
			:	factory.newLoopLabeled(ctx.condition.node, ctx.body.node,ctx.label.getText())
		;	
	}
	



	// ILP1


	public void exitVariable(VariableContext ctx) { 
		ctx.node = factory.newVariable(ctx.getText());
	}

	
	public void exitInvocation(
			InvocationContext ctx) { 
		ctx.node = factory.newInvocation(
				ctx.fun.node, 
				toExpressions(ctx.args));
	}

	
	public void exitConstFloat(
			ConstFloatContext ctx) { 
		ctx.node = factory.newFloatConstant(ctx.floatConst.getText());
	}

	
	public void	exitConstInteger(
			ConstIntegerContext ctx) { 
		ctx.node = factory.newIntegerConstant(ctx.intConst.getText());
	}

	
	public void exitBinding(BindingContext ctx) { 
		ctx.node = factory.newBlock(
				toBindings(ctx.vars, ctx.vals),
				ctx.body.node);
	}

	
	public void exitAlternative(
			AlternativeContext ctx) { 
		ctx.node = factory.newAlternative(
				ctx.condition.node, 
				ctx.consequence.node, 
				(ctx.alternant == null ? null : ctx.alternant.node));
	}

	
	public void exitSequence(
			SequenceContext ctx) {
		ctx.node = factory.newSequence(toExpressions(ctx.exprs));
	}

	
	public void exitConstFalse(
			ConstFalseContext ctx) { 
		ctx.node = factory.newBooleanConstant("false");
	}

	
	public void exitUnary(UnaryContext ctx) { 
		ctx.node = factory.newUnaryOperation(
				factory.newOperator(ctx.op.getText()),
				ctx.arg.node);
	}

	
	public void exitConstTrue(
			ConstTrueContext ctx) {
		ctx.node = factory.newBooleanConstant("true");
	}

	
	public void exitConstString(
			ConstStringContext ctx) { 
		/*
		 * On enlève le " initial et final, et on interprète les codes
		 * d'échapement \n, \r, \t, \"
		 */
		String s = ctx.getText();
		StringBuilder buf = new StringBuilder();
		for (int i = 1; i < s.length() - 1; i++) {
			if (s.charAt(i) == '\\' && i < s.length() - 2) {
				switch (s.charAt(i+1)) {
				case 'n': buf.append('\n'); i++; break;
				case 'r': buf.append('\r'); i++; break;
				case 't': buf.append('\t'); i++; break;
				case '"': buf.append('\"'); i++; break;
				default: buf.append(s.charAt(i));
				}
			}
			else buf.append(s.charAt(i));
		}
		ctx.node = factory.newStringConstant(buf.toString());
	}

	
	public void exitBinary(BinaryContext ctx) { 
		ctx.node = factory.newBinaryOperation(
				factory.newOperator(ctx.op.getText()),
				ctx.arg1.node,
				ctx.arg2.node);				
	}

		
	
	/* Utilitaires de conversion ANTLR vers AST */
	
	protected IASTexpression[] toExpressions(
			List<ExprContext> ctxs) {
		if (ctxs == null) return new IASTexpression[0];
		IASTexpression[] r = new IASTexpression[ctxs.size()];
		int pos = 0;
		for (ExprContext e : ctxs) {
			r[pos++] = e.node;
		}
		return r;
	}
	
	protected IASTvariable[] toVariables(
			List<Token> vars, boolean addSelf) {
		if (vars == null) return new IASTvariable[0];
		IASTvariable[] r = new IASTvariable[vars.size() + (addSelf ? 1 : 0)];
		int pos = 0;
		if (addSelf) {
			// Les déclarations de méthodes ont une variable "self" implicite
			r[pos++] = factory.newVariable("self");
		}
		for (Token v : vars) {
			r[pos++] = factory.newVariable(v.getText());
		}
		return r;
	}

	protected String[] toStrings(List<Token> vars) {
		if (vars == null) return new String[0];
		String[] r = new String[vars.size()];
		int pos = 0;
		for (Token v : vars) {
			r[pos++] = v.getText();
		}
		return r;
	}

	protected IASTblock.IASTbinding[] toBindings(
			List<Token> vars, 
			List<ExprContext> exprs) {
		if (vars == null) return new IASTblock.IASTbinding[0];
		/* par construction, vars et ctxs ont la même taille */
		assert(vars.size() == exprs.size());
		IASTblock.IASTbinding[] r = new IASTblock.IASTbinding[exprs.size()];
		int pos = 0;
		for (Token v : vars) {
			r[pos] = factory.newBinding(
					factory.newVariable(v.getText()),
					exprs.get(pos).node
					);
			pos++;
		}
		return r;			
	}

	public void enterEveryRule(ParserRuleContext arg0) {}
	public void exitEveryRule(ParserRuleContext arg0) {}
	public void visitErrorNode(ErrorNode arg0) {}
	public void visitTerminal(TerminalNode arg0) {}
	public void enterConstInteger(ConstIntegerContext ctx) {}
	public void enterProg(ProgContext ctx) {}
	public void enterConstFloat(ConstFloatContext ctx) {}
	public void enterVariable(VariableContext ctx) {}
	public void enterBinary(BinaryContext ctx) {}
	public void enterAlternative(AlternativeContext ctx) {}	
	public void enterConstFalse(ConstFalseContext ctx) {}
	public void enterSequence(SequenceContext ctx) {}
	public void enterConstTrue(ConstTrueContext ctx) {}
	public void enterBinding(BindingContext ctx) {}
	public void enterConstString(ConstStringContext ctx) {}
	public void enterUnary(UnaryContext ctx) {}
	public void enterInvocation(InvocationContext ctx) {}


	// ILP2
	
	
	public void exitProg(ProgContext ctx) { 
		List<IASTfunctionDefinition> f = new ArrayList<>();
		for (GlobalFunDefContext d : ctx.defs) {
			IASTdeclaration x = d.node;
			f.add((IASTfunctionDefinition)x);
		}
		IASTexpression e = factory.newSequence(toExpressions(ctx.exprs));
		ctx.node = factory.newProgram(
				f.toArray(new IASTfunctionDefinition[0]),
				e);
	}

	
	public void exitGlobalFunDef(GlobalFunDefContext ctx) {
		ctx.node = factory.newFunctionDefinition(
				factory.newVariable(ctx.name.getText()),
				toVariables(ctx.vars, false), 
				ctx.body.node);
	}

	
	public void exitVariableAssign(VariableAssignContext ctx) {
		ctx.node = factory.newAssignment(
				factory.newVariable(ctx.var.getText()),
				ctx.val.node);		
	}
   
	
	
	public void enterGlobalFunDef(GlobalFunDefContext ctx) {}
	public void enterVariableAssign(VariableAssignContext ctx) {}
	public void enterLoop(LoopContext ctx) {}


}
