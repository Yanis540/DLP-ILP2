
/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.ilp2tme5.partie3.compiler;
import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTClocalVariable;
import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTbreaklabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTcontinuelabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTlooplabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTvisitor;
public class FreeVariableCollector extends com.paracamplus.ilp2.compiler.FreeVariableCollector
implements IASTvisitor<Void, Set<IASTClocalVariable>, CompilationException> {

    public FreeVariableCollector(IASTCprogram program) {
        super(program);
    }
    
   
    @Override
	public Void visit(IASTbreaklabeled iast, Set<IASTClocalVariable> variables)
            throws CompilationException {
        return null;
    }
    @Override
	public Void visit(IASTcontinuelabeled iast, Set<IASTClocalVariable> variables)
            throws CompilationException {
        return null;
    }
    public Void visit(IASTlooplabeled iast, Set<IASTClocalVariable> variables) throws CompilationException {
        iast.getCondition().accept(this, variables);
        iast.getBody().accept(this, variables);
        return null;
    }
    
}
