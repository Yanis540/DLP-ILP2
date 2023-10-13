/*


!   > CONSEIL : Voir l'interpète car là bas on définit comment utiliser le Noeud ast 

?   Par contre pour le compilateur nous avons un peu plus de pain sur la plance 
?   On remarque que dans le code du Compiler que c'est bien un visiteur, cependant on remarque 
!   qu'il intéragit avec du code C ! on remarque aussi qu'il utilise le Normalizer ainsi qu'il définit une 
!   méthode Compile qui elle aussi utilise un GlobalVariableCollector aninsi que FreeVriable Collector (qui sont des visiteurs eux aussi)
!   on remarque que le normalizer utilise un NormalizerFactory # Normalizer permet en gros de renomer chaque variable en gardant son nom d'origine + son nom unique  
?   en gros faut donc apporter des modifications au niveau de : 
!       -   INormalizerFactory : Interface étend le NormalizerFactory + rajoute la signature de la nouvelle création 
!       -   NormalizerFactory : étend l'ancien Normalizer+ implémente son interface, il crée des noeuds AST 
!       -   Normalizer :  
!               -   Implémente notre visiteur 
!               -   Retourne un noeud ast en appelant récursivement les sous noeuds : 
    public IASTUnless visit(IASTUnless iast,INormalizationEnvironment env) throws CompilationException {
        return ((INormalizationFactory) factory).newUnless(
            iast.getCondition().accept(this, env), 
            iast.getBody().accept(this,env)
        ); 
    }
!       -   GlobalVariableCollector : Noeud visiteur implemente notre visiteur + étend l'ancienne classe  et retourne le résultat de la visite 
!               (le résultat de chaque accepte sera affecté à result qui est utilisé lui même comme paramètre )
    public Set<IASTCglobalVariable> visit(
        IASTUnless iast,
        Set<IASTCglobalVariable> result) 
                throws CompilationException {
        result = iast.getCondition().accept(this, result);
        result = iast.getBody().accept(this, result);
        return result;
    }
!       -   FreeVariableCollector :  Noeud visiteur implemente notre visiteur + étendr l'ancienne clase, et accepte récusrivement les autres parties du programme  généralement retourne un null ; 
	public Void visit(IASTUnless iast, Set<IASTClocalVariable> variables)
            throws CompilationException {
        iast.getCondition().accept(this, variables);
        iast.getBody().accept(this, variables);
        return null;
    }
!       -   Compiler : Visiteur qui hérite de l'ancien compilateur + implémente notre VIsiteur, il définit 3 méthodes : 
!           -   normalize : copier coller le code du compiler, faudra changer juste l'import du normalizer (utilise celui qu'on a définit nous même)
!           -   visit(IASTNoeud, Context context) : en gros celui là permet d'exprimer du code C en Java, le emit c pour écrire du code C, et iast.getBody() permet d'exprimer la logique du code
!           -   compile : copier coller le code du Compiler, faut juste modifier les imports à utiliser les notres 

!       -   CompilerTest : utiliser le ILPMLParser qu'on a définit dans l'interpète + le Compiler qu'on a définit
*/