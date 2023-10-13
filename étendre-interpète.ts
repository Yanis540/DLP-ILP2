/*

! En gros l'interpète c lui le visiteur, si vous vous rappelez des cours de compilation 
! y'avait ce qu'on appelle les routines qui gèrent la logique du programme dans l'analyse sémantique du compilateur 
! sauf que dans notre cas l'interpète c'est lui le visiteur et c'est lui qui gère la logique 
! pour ça on a juste : 

?       1-  ILPMLListener : Utiliser un  nouveau listener (pour la grammaire qu'on a crée) #ceci est fait supposant qu'on a déjà créée : 
?           -   Factory : (IASTfactory  ASTfactory)
?           -   Noeud : (IASTNoeud  ASTNoeud) # le noeud qu'on souhaite rajouter 
?           -   Visitor : (IASTVisitor) # rajoute la nouvelle méthode visit (IASTNoeud) 
?           -   le listener rajoute les fonctions enter et exit de la nouvelle règle 
?       2-  ILPMLParser : crée un nouveau parser qui utilise notre grammaire et notre listener 
?       3-  Interpreter : Un nouveau interpreter qui étend l'ancien interpète et qui implémente le nouveau Visitor (permettant de visiter le nouveau noeud)
?           -   le code visit(IASTNoeud ) devrait ressembler à un truc pareil : 
public Object visit(IASTUnless iast,ILexicalEnvironment lexenv) throws EvaluationException {
    Object condition = iast.getCondition().accept(this, lexenv); 
    if(condition == null || ! (condition instanceof Boolean))
        return whatever; 
    Boolean b =  (Boolean)(condition); 
    if(b == true )
        return whatever;
?    return iast.getBody().accept(this,lexenv); # ça permet justement de retourner le résultats de getBody (en gros c comme si on appelait les noeuds ast récurisvement, et puis le résultat sera dans la méthode accept) 
}


*/