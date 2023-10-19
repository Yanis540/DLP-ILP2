package com.paracamplus.ilp2.ilp2tme5.partie2.compiler.normalizer;
import com.paracamplus.ilp2.ilp2tme5.partie2.ast.ASTbreak;
import com.paracamplus.ilp2.ilp2tme5.partie2.ast.ASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.partie2.interfaces.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.partie2.interfaces.IASTcontinue;

public class NormalizationFactory extends com.paracamplus.ilp2.compiler.normalizer.NormalizationFactory
implements INormalizationFactory{
    
    public IASTbreak newBreak(){
        return new ASTbreak(); 
    };
    public IASTcontinue newContinue(){
        return new ASTcontinue(); 
    };
}
