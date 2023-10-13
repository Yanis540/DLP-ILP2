/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.ilp2tme4.compiler;
import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTClocalVariable;
import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTUnless;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTvisitor;
public class FreeVariableCollector extends com.paracamplus.ilp2.compiler.FreeVariableCollector
implements IASTvisitor<Void, Set<IASTClocalVariable>, CompilationException> {

    public FreeVariableCollector(IASTCprogram program) {
        super(program);
    }
    
   
     @Override
	public Void visit(IASTUnless iast, Set<IASTClocalVariable> variables)
            throws CompilationException {
        iast.getCondition().accept(this, variables);
        iast.getBody().accept(this, variables);
        return null;
    }
    
}
