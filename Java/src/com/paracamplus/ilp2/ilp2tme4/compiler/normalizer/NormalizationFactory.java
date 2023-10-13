package com.paracamplus.ilp2.ilp2tme4.compiler.normalizer;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.ilp2tme4.ast.ASTUnless;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTUnless;

public class NormalizationFactory extends com.paracamplus.ilp2.compiler.normalizer.NormalizationFactory
implements INormalizationFactory{
    public IASTUnless newUnless(IASTexpression condition,IASTexpression body){
        return new ASTUnless(condition, body); 
    };
}
