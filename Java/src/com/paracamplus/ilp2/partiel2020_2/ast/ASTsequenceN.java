package com.paracamplus.ilp2.partiel2020_2.ast;

import com.paracamplus.ilp1.ast.ASTsequence;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.partiel2020_2.interfaces.IASTvisitor;
import com.paracamplus.ilp2.partiel2020_2.interfaces.IASTsequenceN;

public class ASTsequenceN extends ASTsequence implements IASTsequenceN {
    private IASTexpression index; 
    public ASTsequenceN(IASTexpression[] expressions,IASTexpression index) {
        super(expressions);
        
        this.index = index; 
    }
    

    @Override
    public IASTexpression getIndex() {
        return this.index;
    }
     @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}
