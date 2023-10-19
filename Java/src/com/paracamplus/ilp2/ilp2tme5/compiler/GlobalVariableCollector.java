/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.ilp2tme5.compiler;

import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTvisitor;


public class GlobalVariableCollector 
extends com.paracamplus.ilp2.compiler.GlobalVariableCollector
implements IASTvisitor<Set<IASTCglobalVariable>, Set<IASTCglobalVariable>,  CompilationException> {

    
    public Set<IASTCglobalVariable> visit(
        IASTbreak iast,
        Set<IASTCglobalVariable> result) 
                throws CompilationException {
        return result;
    }
   
    public Set<IASTCglobalVariable> visit(
        IASTcontinue iast,
        Set<IASTCglobalVariable> result) 
                throws CompilationException {
        return result;
    }
   
}
