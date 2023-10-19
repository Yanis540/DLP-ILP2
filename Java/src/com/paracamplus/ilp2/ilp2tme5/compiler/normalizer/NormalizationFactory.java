package com.paracamplus.ilp2.ilp2tme5.compiler.normalizer;
import com.paracamplus.ilp2.ilp2tme5.ast.ASTbreak;
import com.paracamplus.ilp2.ilp2tme5.ast.ASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTcontinue;

public class NormalizationFactory extends com.paracamplus.ilp2.compiler.normalizer.NormalizationFactory
implements INormalizationFactory{
    
    public IASTbreak newBreak(){
        return new ASTbreak(); 
    };
    public IASTcontinue newContinue(){
        return new ASTcontinue(); 
    };
}
