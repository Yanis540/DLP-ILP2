package com.paracamplus.ilp2.ilp2tme6.optimizer;

import javax.xml.crypto.Data;

// import com.paracamplus.ilp1.compiler.EvaluationException;
import com.paracamplus.ilp1.interfaces.IASTalternative;
import com.paracamplus.ilp1.interfaces.IASTbinaryOperation;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interfaces.IASTboolean;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTfloat;
import com.paracamplus.ilp1.interfaces.IASTinteger;
import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp1.interfaces.IASTsequence;
import com.paracamplus.ilp1.interfaces.IASTstring;
import com.paracamplus.ilp1.interfaces.IASTunaryOperation;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTassignment;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTloop;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.interfaces.IASTvisitor;

public class CopyTransform implements IASTvisitor<IASTexpression,Data,EvaluationException> {
    public CopyTransform(IASTfactory factory){
        this.factory = factory; 
    }
    public IASTfactory factory; 
  
	
    public IASTprogram visit(IASTprogram iast, Data data) throws EvaluationException {
        
        IASTfunctionDefinition [] functions = new IASTfunctionDefinition[iast.getFunctionDefinitions().length];
        int i = 0 ; 
        for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) 
            functions[i] = (IASTfunctionDefinition)this.visit(fd,data);
        return factory.newProgram(
            functions, 
            iast.getBody().accept(this, data)
        );
      
    }
   
            
    
	public IASTexpression visit(IASTassignment iast, Data data) 
            throws EvaluationException {
        return factory.newAssignment(
            iast.getVariable(), 
            iast.getExpression().accept(this, data)
        );
    }
    
    public Object visit(IASTfunctionDefinition iast, Data data) 
            throws EvaluationException {
        return factory.newFunctionDefinition(
            iast.getFunctionVariable(), 
            iast.getVariables(),
            iast.getBody().accept(this, data)
        );
    }
    
	public IASTexpression visit(IASTinvocation iast, Data data) 
            throws EvaluationException {
        return factory.newInvocation(
            iast.getFunction().accept(this, data),
            iast.getArguments()
        );
    }
    
    
    
	public IASTexpression visit(IASTloop iast, Data data) 
            throws EvaluationException {
        return factory.newLoop(
            iast.getCondition().accept(this, data), 
            iast.getBody().accept(this, data)
        );
    }
    



    //------------------------------------
    public IASTexpression visit(IASTalternative iast, Data data) 
            throws EvaluationException {
        return factory.newAlternative(
            iast.getCondition().accept(this,data),
            iast.getConsequence().accept(this,data), 
            iast.getConsequence().accept(this, data)
        );
    }
    


    
	public IASTexpression visit(IASTunaryOperation iast, Data data) 
            throws EvaluationException {
        return factory.newUnaryOperation(
            iast.getOperator(), 
            iast.getOperand().accept(this, data)
        ); 
    }
    
    
	public IASTexpression visit(IASTbinaryOperation iast, Data data) 
            throws EvaluationException {
        return factory.newBinaryOperation(
            iast.getOperator(), 
            iast.getLeftOperand().accept(this, data),
            iast.getRightOperand().accept(this, data)
        ); 
    }

    
	public IASTexpression visit(IASTsequence iast, Data data) 
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
    
    
	public IASTexpression visit(IASTblock iast, Data data) 
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

    
	public IASTexpression visit(IASTboolean iast, Data data) 
            throws EvaluationException {
        return factory.newBooleanConstant(iast.getDescription()); 
    }
    
    
	public IASTexpression visit(IASTinteger iast, Data data) 
            throws EvaluationException {
        return factory.newIntegerConstant(iast.getDescription()); 
    }
    
    
	public IASTexpression visit(IASTfloat iast, Data data) 
            throws EvaluationException {
        return factory.newFloatConstant(iast.getDescription());
    }
    
    
	public IASTexpression visit(IASTstring iast, Data data) 
            throws EvaluationException {
        return factory.newStringConstant(iast.getDescription());
    }

    
	public IASTexpression visit(IASTvariable iast, Data data) 
            throws EvaluationException {
        return factory.newVariable(iast.getName()); 
    }


}
