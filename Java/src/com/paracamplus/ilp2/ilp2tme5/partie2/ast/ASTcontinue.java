package com.paracamplus.ilp2.ilp2tme5.partie2.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp2.ilp2tme5.partie2.interfaces.IASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.partie2.interfaces.IASTvisitor;

public class ASTcontinue extends ASTexpression implements IASTcontinue {
    public ASTcontinue(){

    }

     @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
        com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
        Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}
