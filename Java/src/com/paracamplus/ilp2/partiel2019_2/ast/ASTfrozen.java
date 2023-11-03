package com.paracamplus.ilp2.partiel2019_2.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.partiel2019_2.interfaces.IASTfrozen;
import com.paracamplus.ilp2.partiel2019_2.interfaces.IASTvisitor;

public class ASTfrozen extends ASTexpression implements IASTfrozen{
    private IASTexpression function ; 
    public ASTfrozen(IASTexpression function){
        this.function = function ; 
    }
    @Override
    public IASTexpression getFunction() {
        return this.function; 
    }
    

    
    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
        com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
        Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}

  
    
}
