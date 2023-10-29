package com.paracamplus.ilp2.partiel2019.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.partiel2019.interfaces.IASTfreeze;
import com.paracamplus.ilp2.partiel2019.interfaces.IASTvisitor;

public class ASTfreeze extends ASTexpression implements IASTfreeze {
    private IASTexpression[] arguments ; 
    private IASTexpression function ; 
    public ASTfreeze(IASTexpression function , IASTexpression[] arguments ){
        this.function = function ; 
        this.arguments = arguments ; 
    }
    @Override
    public IASTexpression getFunction() {
        return this.function; 
    }
    @Override
    public IASTexpression[] getArguments() {
        return this.arguments; 
    }
    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
    
}
