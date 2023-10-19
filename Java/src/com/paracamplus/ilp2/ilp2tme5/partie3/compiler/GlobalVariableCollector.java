/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.ilp2tme5.partie3.compiler;

import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTbreaklabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTcontinuelabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTlooplabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTvisitor;


public class GlobalVariableCollector 
extends com.paracamplus.ilp2.compiler.GlobalVariableCollector
implements IASTvisitor<Set<IASTCglobalVariable>, Set<IASTCglobalVariable>,  CompilationException> {

    
    public Set<IASTCglobalVariable> visit(
        IASTbreaklabeled iast,
        Set<IASTCglobalVariable> result) 
                throws CompilationException {
        return result;
    }
   
    public Set<IASTCglobalVariable> visit(
        IASTcontinuelabeled iast,
        Set<IASTCglobalVariable> result) 
                throws CompilationException {
        return result;
    }
     @Override
	public Set<IASTCglobalVariable> visit(
            IASTlooplabeled iast,
            Set<IASTCglobalVariable> result) 
                    throws CompilationException {
        result = iast.getCondition().accept(this, result);
        result = iast.getBody().accept(this, result);
        return result;
    }
   
}
