package com.paracamplus.ilp2.ilp2tme4.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTUnless extends IASTexpression {
	public IASTexpression getCondition();
	public IASTexpression getBody() ;
}
