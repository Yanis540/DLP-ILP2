package com.paracamplus.ilp2.ilp2tme6.optimizer;


import java.util.Map;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.NormalizationEnvironment;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;

import java.util.HashMap;

public class InlineTransform extends CopyTransform<Void> {
    private RenameTransform renameTransform; 
    private Callanalysis callAnalysisTransform; 
    private Map<String,IASTfunctionDefinition> functions = new HashMap<>(); 
    public InlineTransform(IASTfactory factory){
        super(factory); 
        this.renameTransform = new RenameTransform(factory);
        this.callAnalysisTransform = new Callanalysis(factory);
    }
    public IASTprogram rename(IASTprogram iast) throws CompilationException {
        iast = (IASTprogram)renameTransform.visit(iast, NormalizationEnvironment.EMPTY); 
        return iast; 
    }
    public IASTprogram callAnalysis(IASTprogram iast) throws CompilationException {
        iast = (IASTprogram)callAnalysisTransform.visit(iast, null); 
        return iast; 
    }

    public IASTprogram visit(IASTprogram iast, Void data) throws CompilationException {
        iast = this.rename(iast);
        iast = this.callAnalysis(iast);
        
        for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
            System.out.println("Looking in : "+ fd.getName());
            this.functions.put(fd.getName(),
                factory.newFunctionDefinition(
                    fd.getFunctionVariable(),
                    fd.getVariables().clone(), 
                    fd.getBody().accept(this, data)
                )
            );
        }
        return super.visit(iast,data);
    }

    public IASTexpression visit(IASTinvocation iast, Void data) throws CompilationException{
        //! Get the function's variables (x,y, z .... )
        IASTvariable functionVariable = (IASTvariable)iast.getFunction();
        if(callAnalysisTransform.isRecursive(functionVariable))
            return iast;
        IASTfunctionDefinition function = this.functions.get(functionVariable.getName());
        if(function == null)
            return iast;

        //! 
        System.out.println("Invoced >>>"+function.getName());
        //! Initialize the binding ,with a couple : (variable,argument) 
        IASTvariable[] variables = function.getVariables();
        int argumentsLength = iast.getArguments().length;
        if(variables.length != argumentsLength){
            throw new CompilationException("Exepected "  + variables.length+" arguments but got : "+ argumentsLength);
        }

        //! matching in binding for each 
        IASTbinding[] bindings = new IASTbinding[argumentsLength]; 
        int i = 0 ; 
        for( IASTexpression argument : iast.getArguments()){
            bindings[i] = factory.newBinding(
                (IASTvariable)variables[i],
                argument.accept(this, data)
            );
            System.out.println(bindings[i]);
            i++; 
        }

        //! Initilize the body 
        IASTexpression body = function.getBody();
        
        return factory.newBlock(
            bindings, 
            body
        ); 
        
    }

}
