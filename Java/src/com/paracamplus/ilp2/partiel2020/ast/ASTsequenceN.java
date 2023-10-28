package com.paracamplus.ilp2.partiel2020.ast;

import com.paracamplus.ilp1.ast.ASTsequence;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.partiel2020.interfaces.IASTsequenceN;
import com.paracamplus.ilp2.partiel2020.interfaces.IASTvisitor;

public class ASTsequenceN extends ASTsequence implements IASTsequenceN {
    private IASTexpression position ; 
    public ASTsequenceN(IASTexpression[] expressions,IASTexpression position) {
        super(expressions);
        this.position = position;  
    }

    public IASTexpression getPosition(){
        return this.position; 
    }

    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}
