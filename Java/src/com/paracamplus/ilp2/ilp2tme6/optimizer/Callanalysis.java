package com.paracamplus.ilp2.ilp2tme6.optimizer;

import java.util.Map;
import java.util.Set;


import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;

import java.util.HashMap;
import java.util.HashSet;

public class Callanalysis extends CopyTransform<Void> {
    public Callanalysis(IASTfactory factory) {
        super(factory);
        initialiseAccessFunctions();
    }
    public void initialiseAccessFunctions (){
        this.accessedFunctions = new HashSet<>();
    }
    private Set<String> accessedFunctions = new HashSet<>();
    private Map<String,Set<String>> calls = new HashMap<>();
    private Set<String> recursive = new HashSet<>();

    public Set<String> getRecursiveFunctions(){
        return this.recursive; 
    }
    public Map<String,Set<String>> getFunctionCalls() {
        return this.calls; 
    }
      
	
    public IASTprogram visit(IASTprogram iast, Void data) throws CompilationException {
        IASTfunctionDefinition [] functions = new IASTfunctionDefinition[iast.getFunctionDefinitions().length];
        int i = 0 ; 
        for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
            initialiseAccessFunctions();
            functions[i] = visit(fd,data);
            i++; 
        }

        
        this.analyzeRecursiveFunctions();
        IASTexpression newBody = iast.getBody().accept(this, data);
        //* for(String rec : recursive)
        //*    System.out.println("Recursive function : " + rec);

        return factory.newProgram(
            functions, 
            newBody
        );
      
    }
    public boolean isRecursive(IASTvariable f){
        return recursive.contains(f.getName());
    }
    public void analyzeRecursiveFunctions(){
        forEntry: for(Map.Entry<String,Set<String>> entry : calls.entrySet()){
            //* System.out.println(entry.getKey() + "  calls :  " );
            for(String call : entry.getValue()){
                // Recursive function 
                //* System.out.println("- "+call);
                if(((String)call).equals(entry.getKey())){
                    recursive.add(entry.getKey());
                    continue forEntry; 
                }
                else {
                    // Si A -> B, alors voir si B -> A (détecter la récursivité mutelle ) 
                    Set<String> otherCalls = calls.get((String)call);
                    if(otherCalls !=null){
                        // if it's there then just 
                        for(String otherCall: otherCalls){
                            if(otherCall.equals(entry.getKey())){
                                recursive.add(entry.getKey());
                                recursive.add(otherCall);
                                break;
                            }
                        }
                    }
                }
            }
                
            //* System.out.println("########");
            
        }
    }
    public IASTfunctionDefinition visit(IASTfunctionDefinition iast, Void data) 
            throws CompilationException {

        // adding the function in iast functions 
        
        // System.out.println("Analyzing A function >>>>>" + iast.getName());
        
        IASTvariable[]variables =  new IASTvariable[iast.getVariables().length];
        int i = 0 ; 
        
        for( IASTvariable var : iast.getVariables()){
            variables[i] = factory.newVariable(var.getName()) ;
            i++; 
        }
        IASTexpression newBody =iast.getBody().accept(this, data);
        calls.put(iast.getFunctionVariable().getName(),accessedFunctions);


        return factory.newFunctionDefinition(
            iast.getFunctionVariable(), 
            variables,
            newBody
        );
    }
    public IASTexpression visit(IASTinvocation iast,Void data ) throws CompilationException {
        // adding to current function 
        IASTvariable function = ((IASTvariable)iast.getFunction());
        accessedFunctions.add(function.getName());
        IASTexpression[]arguments =  new IASTexpression[iast.getArguments().length];
        int i = 0 ; 
        for( IASTexpression argument : iast.getArguments()){
            arguments[i] = (IASTexpression)argument.accept(this, data); 
            i++; 
        }
        return factory.newInvocation(
            iast.getFunction().accept(this, data),
            arguments
        );
    }
   

}
