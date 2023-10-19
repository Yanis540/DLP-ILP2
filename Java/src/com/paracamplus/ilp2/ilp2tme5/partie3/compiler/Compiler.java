package com.paracamplus.ilp2.ilp2tme5.partie3.compiler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

import com.paracamplus.ilp1.compiler.AssignDestination;
import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.NoDestination;
import com.paracamplus.ilp1.compiler.VoidDestination;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTbreaklabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTcontinuelabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTlooplabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTvisitor;
import com.paracamplus.ilp2.ilp2tme5.partie3.compiler.normalizer.INormalizationFactory;
import com.paracamplus.ilp2.ilp2tme5.partie3.compiler.normalizer.NormalizationFactory;
import com.paracamplus.ilp2.ilp2tme5.partie3.compiler.normalizer.Normalizer;




public class Compiler 
extends  com.paracamplus.ilp2.compiler.Compiler 
implements IASTvisitor<Void, Compiler.Context, CompilationException> {
    public Compiler (IOperatorEnvironment ioe, IGlobalVariableEnvironment igve ) {
        super(ioe,igve); 
    }
    @Override
    public IASTCprogram normalize(IASTprogram program) 
            throws CompilationException {
        INormalizationFactory nf = new NormalizationFactory();
        Normalizer normalizer = new Normalizer(nf);
        IASTCprogram newprogram = normalizer.transform(program);
        return newprogram;
    }


    public Void visit(IASTbreaklabeled iast,Context context) throws CompilationException {
        emit("goto end"+iast.getBreakLabel()+";");
        emit("\n\n"); 
        return null; 
    }
    public Void visit(IASTcontinuelabeled iast,Context context) throws CompilationException {
        emit("goto "+iast.getContinueLabel()+";");
        emit("\n\n"); 
        return null; 
    }
    public Void visit(IASTlooplabeled iast, Context context)
            throws CompilationException {
        emit(iast.getLabel()+": while ( 1 ) { \n");
        IASTvariable tmp = context.newTemporaryVariable();
        emit("  ILP_Object " + tmp.getMangledName() + "; \n");
        Context c = context.redirect(new AssignDestination(tmp));
        iast.getCondition().accept(this, c);
        emit("  if ( ILP_isEquivalentToTrue(");
        emit(tmp.getMangledName());
        emit(") ) {\n");
        Context cb = context.redirect(VoidDestination.VOID_DESTINATION);
        iast.getBody().accept(this, cb);
        emit("\n} else { \n");
        emit("    break; \n");
        emit("\n}\n}\n");
        emit("end"+iast.getLabel()+": \n");
        whatever.accept(this, context);
        return null;
    }
    //! ADDED THESE VARIABLES JUST FOR FREE VARIABLE COLLECTOR + GLOBAL VARIABLE COLLECTOR 
    public String compile(IASTprogram program) 
            throws CompilationException {
        
        IASTCprogram newprogram = normalize(program);
        newprogram = ((IASTCprogram) optimizer.transform(newprogram));

        GlobalVariableCollector gvc = new GlobalVariableCollector();
        Set<IASTCglobalVariable> gvs = gvc.analyze(newprogram);
        newprogram.setGlobalVariables(gvs);
        
        FreeVariableCollector fvc = new FreeVariableCollector(newprogram);
        newprogram = (fvc.analyze());
      
        Context context = new Context(NoDestination.NO_DESTINATION);
        StringWriter sw = new StringWriter();
        try {
            out = new BufferedWriter(sw);
            visit(newprogram, context);
            out.flush();
        } catch (IOException exc) {
            throw new CompilationException(exc);
        }
        return sw.toString();
    }

}
