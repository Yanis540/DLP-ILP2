package com.paracamplus.ilp2.partiel2019_2.ast;

import com.paracamplus.ilp1.ast.ASTinvocation;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.partiel2019_2.interfaces.IASTfreeze;
import com.paracamplus.ilp2.partiel2019_2.interfaces.IASTvisitor;

public class ASTfreeze extends ASTinvocation implements IASTfreeze{

    public ASTfreeze(IASTexpression function, IASTexpression[] arguments) {
        super(function, arguments);
    }


    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
        com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
        Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}
