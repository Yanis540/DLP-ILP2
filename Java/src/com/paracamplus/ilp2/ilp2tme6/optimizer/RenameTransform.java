package com.paracamplus.ilp2.ilp2tme6.optimizer;



import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.compiler.normalizer.NoSuchLocalVariableException;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;

public class RenameTransform extends CopyTransform<INormalizationEnvironment>{
    private int counter= 0; 
    public RenameTransform(IASTfactory factory) {
        super(factory);
    }
    //! En gros le but : ou nous dit qu'il faut renomer les variables des paramètres de fonctions ainsi que les variables 
    //! il nous a dit qu'on devrait utiliser Inormalization Environment, il faut savoir que le Inormalization environnement 
    //? est une pile, la méthode : renaming(variable) permet de récupérer une variable de cette pile,
    //? le principe en gros c'est vraiment de créer une nouvelle variable avec le nouveau nom (nom_counter)
    //? puis en gros rajouteer le couple (variableRenamed, variable) (exactement comme dans le normalizer) 
    //! pour ça faudra modifier 3 choses :  
    //!     -   Iastfunction definition : pour que chaque variable des paramètres renommer ces variables
    //!     -   IASTblock  : ON rappelle qu'un block est constitué de binding, un binding c un couple (variable, valeur ou initialisation qui est une expression)
    //!     -   IASTvariable  : quand on visite une variable faudra non pas retourner la variable mais chercher un renaming (pourquoi je ne sais pas trop )
    
    
    public IASTfunctionDefinition visit(IASTfunctionDefinition iast, INormalizationEnvironment env) 
            throws CompilationException {
        INormalizationEnvironment newEnv = env; 
        IASTvariable[]variables =  new IASTvariable[iast.getVariables().length];
        int i = 0 ; 
        for( IASTvariable var : iast.getVariables()){
            IASTvariable renamedVariable = factory.newVariable(var.getMangledName()+"_"+counter); 
            counter ++;  
            variables[i] = renamedVariable; 
            newEnv = newEnv.extend(var, renamedVariable);
            i++; 
        }
        return factory.newFunctionDefinition(
            iast.getFunctionVariable(), 
            variables,
            iast.getBody().accept(this, newEnv)
        );
    }

    public IASTexpression visit(IASTblock iast, INormalizationEnvironment env) throws CompilationException{
        INormalizationEnvironment newEnv = env; 
        IASTbinding[] bindings = new IASTbinding[iast.getBindings().length]; 
        int i=0; 
        for ( IASTbinding binding : iast.getBindings() ) {
            IASTvariable variable = binding.getVariable();
            IASTvariable renamedVariable= factory.newVariable(variable.getMangledName()+"_"+counter); 
            System.out.println("RENAMED VARIABLE" + variable.getName());
            bindings[i] = factory.newBinding(
                renamedVariable, 
                binding.getInitialisation().accept(this, env)
            );
            newEnv = newEnv.extend(variable,renamedVariable);
            counter ++ ; 
        }
        return (IASTexpression)factory.newBlock(
            bindings, 
            iast.getBody().accept(this, newEnv)
        );
    }

    public IASTvariable visit(IASTvariable iast, INormalizationEnvironment env) throws CompilationException{
        try{
            return env.renaming(iast);
        }

        catch(NoSuchLocalVariableException e){
            return iast; 
        }
    }



    
}
