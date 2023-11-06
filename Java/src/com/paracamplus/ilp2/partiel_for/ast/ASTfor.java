package com.paracamplus.ilp2.partiel_for.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.interfaces.IASTassignment;
import com.paracamplus.ilp2.partiel_for.interfaces.IASTfor;
import com.paracamplus.ilp2.partiel_for.interfaces.IASTvisitor;

public class ASTfor extends ASTexpression implements IASTfor {
    public ASTfor (IASTassignment assign,IASTexpression condition , IASTexpression iterator,IASTexpression body) {
        this.assign = assign ; 
        this.iterator = iterator ; 
        this.condition = condition;
        this.body = body;
    }
    private final IASTexpression condition;
    private final IASTexpression body;
    private IASTassignment assign;
    private IASTexpression iterator;

    @Override
	public IASTexpression getCondition() {
        return condition;
    }

    @Override
	public IASTexpression getBody() {
        return body;
    }
	public IASTassignment getAssignment() {
        return this.assign;
    }
	public IASTexpression getIterator() {
        return this.iterator;
    }

   @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}
