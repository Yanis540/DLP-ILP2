package com.paracamplus.ilp2.partiel2021_2.ast;

import com.paracamplus.ilp1.ast.ASTblock;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.partiel2021_2.interfaces.IASTblockrange;
import com.paracamplus.ilp2.partiel2021_2.interfaces.IASTvisitor;

public class ASTblockrange extends ASTblock implements IASTblockrange {

    public ASTblockrange(IASTbindingrange[] binding, IASTexpression body) {
        super(binding, body);
        this.bindingRange = binding ;
      
    }
    public static class ASTbindingrange extends ASTblock.ASTbinding implements IASTbindingrange {
        private IASTexpression low; 
        private IASTexpression high; 
        public ASTbindingrange (IASTvariable variable, IASTexpression initialisation,IASTexpression low,IASTexpression high) {
           super(variable,initialisation);
           this.low = low; 
           this.high = high; 

        }

        @Override
        public IASTexpression getLow() {
            return this.low;
        }

        @Override
        public IASTexpression getHigh() {
            return this.high;
        }
       
        
    }

    private IASTbindingrange[] bindingRange; 
    @Override
    public IASTbindingrange[] getBindingRange() {
        return bindingRange;
    }
    

    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}



}
