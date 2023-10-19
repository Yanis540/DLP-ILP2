package com.paracamplus.ilp2.ilp2tme5.partie3.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTcontinuelabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTvisitor;

public class ASTcontinuelabeled extends ASTexpression implements IASTcontinuelabeled {
    public ASTcontinuelabeled(String label){
        this.label = label; 
    }
    private String label ; 
    public String getContinueLabel(){
        return this.label; 
    }
     @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
        com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
        Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}
