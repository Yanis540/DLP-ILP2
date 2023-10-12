package com.paracamplus.ilp2.ilp2tme4.parser.ilpml.method2;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTUnless;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTfactory;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTvisitor;
import com.paracamplus.ilp2.interfaces.IASTassignment;
import com.paracamplus.ilp2.interfaces.IASTloop;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp1.interfaces.IASTsequence;
import com.paracamplus.ilp1.interfaces.IASTstring;
import com.paracamplus.ilp1.interfaces.IASTunaryOperation;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interfaces.IASTalternative;
import com.paracamplus.ilp1.interfaces.IASTbinaryOperation;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp1.interfaces.IASTboolean;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTfloat;
import com.paracamplus.ilp1.interfaces.IASTinteger;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;


public class ASTUnlessTransform implements IASTvisitor<IASTexpression, Void, EvaluationException> {
    public ASTUnlessTransform(IASTfactory factory){
        this.factory = factory; 
    }
    public IASTfactory factory; 
  
	
    public IASTprogram visit(IASTprogram iast, Void data) throws EvaluationException {
        
        IASTfunctionDefinition [] functions = new IASTfunctionDefinition[iast.getFunctionDefinitions().length];
        int i = 0 ; 
        for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) 
            functions[i] = fd;
        return factory.newProgram(
            functions, 
            iast.getBody().accept(this, data)
        );
      
    }
    public IASTexpression visit(IASTUnless iast, Void data) throws EvaluationException{
        return factory.newAlternative(iast.getCondition(), factory.newSequence(new IASTexpression[0]), iast.getBody());
    }
            
    @Override
	public IASTexpression visit(IASTassignment iast, Void data) 
            throws EvaluationException {
        return factory.newAssignment(
            iast.getVariable(), 
            iast.getExpression().accept(this, data)
        );
    }
    
    public Object visit(IASTfunctionDefinition iast, Void data) 
            throws EvaluationException {
        return factory.newFunctionDefinition(
            iast.getFunctionVariable(), 
            iast.getVariables(),
            iast.getBody().accept(this, data)
        );
    }
    
    @Override
	public IASTexpression visit(IASTinvocation iast, Void data) 
            throws EvaluationException {
        return factory.newInvocation(
            iast.getFunction().accept(this, data),
            iast.getArguments()
        );
    }
    
    
    @Override
	public IASTexpression visit(IASTloop iast, Void data) 
            throws EvaluationException {
        return factory.newLoop(
            iast.getCondition().accept(this, data), 
            iast.getBody().accept(this, data)
        );
    }
    



    //------------------------------------
    public IASTexpression visit(IASTalternative iast, Void data) 
            throws EvaluationException {
        return factory.newAlternative(
            iast.getCondition().accept(this,data),
            iast.getConsequence().accept(this,data), 
            iast.getConsequence().accept(this, data)
        );
    }
    


    @Override
	public IASTexpression visit(IASTunaryOperation iast, Void data) 
            throws EvaluationException {
        return factory.newUnaryOperation(
            iast.getOperator(), 
            iast.getOperand().accept(this, data)
        ); 
    }
    
    @Override
	public IASTexpression visit(IASTbinaryOperation iast, Void data) 
            throws EvaluationException {
        return factory.newBinaryOperation(
            iast.getOperator(), 
            iast.getLeftOperand().accept(this, data),
            iast.getRightOperand().accept(this, data)
        ); 
    }

    @Override
	public IASTexpression visit(IASTsequence iast, Void data) 
            throws EvaluationException {
        IASTexpression[] expressions = iast.getExpressions();
        IASTexpression[] expressions_accepted = new IASTexpression[expressions.length];
        int i= 0; 
        for ( IASTexpression e : expressions ) {
            expressions_accepted [i]= e.accept(this, data);
            i++; 
        }
        return factory.newSequence(expressions_accepted);
    }
    
    @Override
	public IASTexpression visit(IASTblock iast, Void data) 
            throws EvaluationException {
        IASTbinding[] bindings = new IASTbinding[iast.getBindings().length]; 
        int i = 0 ; 
        for ( IASTbinding binding : iast.getBindings() ) {
            bindings [i]=  factory.newBinding(
                binding.getVariable(), 
                binding.getInitialisation().accept(this, data)
            );
            
            i++ ; 
        }
        
        return factory.newBlock(
            bindings, 
            iast.getBody().accept(this, data)
        ); 
    }

    @Override
	public IASTexpression visit(IASTboolean iast, Void data) 
            throws EvaluationException {
        return factory.newBooleanConstant(iast.getDescription()); 
    }
    
    @Override
	public IASTexpression visit(IASTinteger iast, Void data) 
            throws EvaluationException {
        return factory.newIntegerConstant(iast.getDescription()); 
    }
    
    @Override
	public IASTexpression visit(IASTfloat iast, Void data) 
            throws EvaluationException {
        return factory.newFloatConstant(iast.getDescription());
    }
    
    @Override
	public IASTexpression visit(IASTstring iast, Void data) 
            throws EvaluationException {
        return factory.newStringConstant(iast.getDescription());
    }

    @Override
	public IASTexpression visit(IASTvariable iast, Void data) 
            throws EvaluationException {
        return factory.newVariable(iast.getName()); 
    }
    
}
