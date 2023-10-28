package com.paracamplus.ilp2.partiel2021.ast;

import com.paracamplus.ilp1.ast.AST;
import com.paracamplus.ilp1.ast.ASTblock;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.partiel2021.interfaces.IASTrangeblock;
import com.paracamplus.ilp2.partiel2021.interfaces.IASTvisitor;

public class ASTrangeblock extends ASTblock implements IASTrangeblock {
    
    public static class ASTrangebinding extends AST implements IASTrangebinding {
        public ASTrangebinding (IASTvariable variable,IASTexpression low, IASTexpression high) {
            this.low = low;
            this.high = high;
            this.variable = variable;
        }
        private final IASTexpression low;
        private final IASTexpression high;
        private final IASTvariable variable;
        
        @Override
		public IASTexpression getLow () {
            return low;
        }
        @Override
		public IASTexpression getHigh () {
            return high;
        }
        @Override
		public IASTvariable getVariable () {
            return variable;
        }
    }
    public ASTrangeblock(IASTbinding[] binding, IASTexpression body,IASTrangebinding[] lowHighs ) {
        super(binding, body);
        this.lowHighs = lowHighs; 
    }
    private final IASTrangebinding[] lowHighs;
    @Override
    public IASTrangebinding[] getRangeBindings() {
        return lowHighs; 
    }
    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}
