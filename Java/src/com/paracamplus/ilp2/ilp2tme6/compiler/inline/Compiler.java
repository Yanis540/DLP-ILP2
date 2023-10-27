package com.paracamplus.ilp2.ilp2tme6.compiler.inline;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp2.compiler.normalizer.INormalizationFactory;
import com.paracamplus.ilp2.compiler.normalizer.NormalizationFactory;
import com.paracamplus.ilp2.compiler.normalizer.Normalizer;
import com.paracamplus.ilp2.ilp2tme6.optimizer.InlineTransform;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTprogram;

public class Compiler extends com.paracamplus.ilp2.compiler.Compiler {
    private InlineTransform transformer;  
    public Compiler(IOperatorEnvironment ioe, IGlobalVariableEnvironment igve,IASTfactory factory) {
		super(ioe, igve);
        this.transformer = new InlineTransform(factory);  
	}
     public IASTprogram inline(IASTprogram iast) throws CompilationException {
        iast = (IASTprogram)transformer.visit(iast, null); 
        return iast; 
    }

    public IASTCprogram normalize(IASTprogram program,ILexicalEnvironment lexenv ) throws CompilationException{
        program =this.inline(program); 
        INormalizationFactory nf = new NormalizationFactory();
        Normalizer normalizer = new Normalizer(nf);
        IASTCprogram newprogram = normalizer.transform(program);
        return newprogram;
    }
}
