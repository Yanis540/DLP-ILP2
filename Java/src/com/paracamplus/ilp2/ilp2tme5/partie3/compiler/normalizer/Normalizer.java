package com.paracamplus.ilp2.ilp2tme5.partie3.compiler.normalizer;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.compiler.normalizer.NormalizationEnvironment;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.compiler.interfaces.IASTCfunctionDefinition;
import com.paracamplus.ilp2.compiler.interfaces.IASTCglobalFunctionVariable;
import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTbreaklabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTcontinuelabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTlooplabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTvisitor;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;

public class Normalizer 
extends  com.paracamplus.ilp2.compiler.normalizer.Normalizer  
implements IASTvisitor<IASTexpression, INormalizationEnvironment, CompilationException>   {
    public Normalizer (INormalizationFactory factory) {
    	super(factory);
    }
    @Override
    public IASTCprogram transform(IASTprogram program) 
            throws CompilationException {
        INormalizationEnvironment env = NormalizationEnvironment.EMPTY;
        IASTfunctionDefinition[] functions = program.getFunctionDefinitions();
        IASTCfunctionDefinition[] funs = 
                new IASTCfunctionDefinition[functions.length];
        for ( IASTfunctionDefinition function : functions ) {
            IASTCglobalFunctionVariable gfv =
                    ((INormalizationFactory)factory).newGlobalFunctionVariable(function.getName());
            env = env.extend(gfv, gfv);
        }
        for ( int i=0 ; i<functions.length ; i++ ) {
            IASTfunctionDefinition function = functions[i];
            IASTCfunctionDefinition newfunction = visit(function, env);
            funs[i] = newfunction;
        }
        
        IASTexpression body = program.getBody();
        IASTexpression newbody = body.accept(this, env);
        return ((INormalizationFactory)factory).newProgram(funs, newbody);
    }
    
    public IASTbreaklabeled visit(IASTbreaklabeled iast,INormalizationEnvironment env) throws CompilationException {
        return ((INormalizationFactory) factory).newBreakLabeled(iast.getBreakLabel()); 
    }
    public IASTcontinuelabeled visit(IASTcontinuelabeled iast,INormalizationEnvironment env) throws CompilationException {
        return ((INormalizationFactory) factory).newContinueLabeled(iast.getContinueLabel()); 
    }
    public IASTlooplabeled visit(IASTlooplabeled iast,INormalizationEnvironment env) throws CompilationException {
        return ((INormalizationFactory) factory).newLoopLabeled(
            iast.getCondition().accept(this, env), 
            iast.getBody().accept(this, env), 
            iast.getLabel()
        ); 
    }
}
