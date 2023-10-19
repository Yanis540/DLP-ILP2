package com.paracamplus.ilp2.ilp2tme5.partie3.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTbreaklabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTcontinuelabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTfactory;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTlooplabeled;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory {
    public IASTbreaklabeled newBreakLabeled(String label){
        return new ASTbreaklabeled(label); 
    }
    public IASTcontinuelabeled newContinueLabeled(String label){
        return new ASTcontinuelabeled(label); 
    }
    
    public IASTlooplabeled newLoopLabeled(IASTexpression condition, IASTexpression body,String label){
        return new ASTlooplabeled(condition,body,label); 
    }
}
