package com.paracamplus.ilp2.ilp2tme5.partie3.ast;

import org.antlr.v4.runtime.misc.Nullable;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvisitable;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTlooplabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTvisitor;

public class ASTlooplabeled extends com.paracamplus.ilp2.ast.ASTloop implements IASTlooplabeled, IASTvisitable {
    public ASTlooplabeled (IASTexpression condition, IASTexpression body,@Nullable String label) {
        super(condition,body); 
        this.label = label; 
    }
    public ASTlooplabeled (IASTexpression condition, IASTexpression body) {
        super(condition,body); 
    }
    private String label; 
    public String getLabel(){
        return this.label; 
    }
    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}

}
