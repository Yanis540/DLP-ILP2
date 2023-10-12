package com.paracamplus.ilp2.ilp2tme4.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTUnless;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTvisitor; 

public class ASTUnless extends ASTexpression implements IASTUnless{
    public ASTUnless(IASTexpression condition,IASTexpression body) {
		this.condition = condition;
		this.body = body;
	}
	private final IASTexpression condition;
	private final IASTexpression body;
	@Override
	public IASTexpression getCondition() {
		return condition;
	}
	@Override
	public IASTexpression getBody() {
		return body;
	}
    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}
