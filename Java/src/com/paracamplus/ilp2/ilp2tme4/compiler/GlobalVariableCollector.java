/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.ilp2tme4.compiler;

import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTUnless;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTvisitor;


public class GlobalVariableCollector 
extends com.paracamplus.ilp2.compiler.GlobalVariableCollector
implements IASTvisitor<Set<IASTCglobalVariable>, Set<IASTCglobalVariable>,  CompilationException> {

    
    public Set<IASTCglobalVariable> visit(
        IASTUnless iast,
        Set<IASTCglobalVariable> result) 
                throws CompilationException {
        result = iast.getCondition().accept(this, result);
        result = iast.getBody().accept(this, result);
        return result;
    }
   
}
