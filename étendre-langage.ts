/*

Pour étendre le langage on passe toujours par les mêmes étapes : 
!   -   Étendre la grammaire
!   -   Créer un nouveau ILPMLListener et ILPMLParser(Parser utilise le nouveau listener)
?           -   permettent en gros à l'analyseur de reconnaitr les nouvelles règles
!   -   Rajouter les interfaces de la nouvelle règle ainsi que les noeuds ast
!   -   Étendre le visiteur 
!   -   Créer un nouveau Factory ainsi qu'un nouveau IASTfactory (pour créer des nouveaux noeuds)
?           -   le factory serait prrobablement utiliser dans ILPMLListener pour rajouter dans ctx.node 
?               un nouveau noeud (genre ctx.node = factory.new<Qqch>(ctx.<TRUC_DECLARE_DANS.G4>.node))
!   -   optionnellement rajouter des noeuds ASTC pour le compilateur
!   -   Créer un nouveau InterpreterTest qui utiliser le nouveau parser 



!   ######################## REMARQUE ########################
*   PARFOIS genre on se retrouve à étendre des fonctionnalités déjà existante (e.g: unless utilise if), pour ça nous proposons deux autres méthodes : 

!   Modifions directement le Listener 
?       -   L'approche est simple, si votre nouvelle feature utilise une ancienne, vous n'avez qu'à créer un nouveau ILPMLListener 
?           ce listener en réalité implémente Le Listener, dans le code de enter ou exit de la méthode (e.g : exitUnless)
?           on rajoute le code pour utiliser directement l'ancienne structure
*   e.g : 
public void exitUnless(UnlessContext ctx){
* Le unless utilise alternative, dans à chaque rencontre d'une règle contexte il met dans le ctx.node (le noeud AST) une alternative
    ctx.node = factory.newAlternative(
        ctx.condition.node, 
        factory.newSequence(new IASTexpression[0]), 
        ctx.body.node
    );
} 
*   Remarque : Faudra penser à rajouter un nouveau ILPMLParser qui utilise ce listener et le Lexer de la nouvelle grammaire

!   Utiliser une passerelle 
?       -   Le principe est de se positionner dans le Parser, et après avoir obtenu le programme par le Parser
?           on transforme chaque Noeud ASTunless -> en ASTalternative 
?       -   C'est faire en ajoutant : 
?               -   ASTUnless(+ineterface) : une nouvelle fabrique 
?               -   ASTFacotry (+ interface) : hérite de la fabrique d'ilp 2 + crée une nouvelle instance newUnless 
?               -   IVisitor : hérite du visiteur d'ilp2 + rajoute celui de useLess
?               -   ILPMLListener : implémente le listener de la nouvelle grammaire, pour unless il crée à partir de la fabrique 
?                   (la nouvelle fabrique qu'on a crée) et elle retourne le newUnless dans l'objet ctx.node (ctx.node = factory.newUnless(....))
?               -   ASTUnlessTransform: cette classe est un visiteur, elle permet de parcourir le programme et de transformer chaque Noeud Unless => alternative 
?                   faut juste savoir qu'en réalité vous faites appel récursive 
!                   sauf la différence que vous faites appel à la fabrique pour crée un nouveau noeud, genre par exemple pour une condition vous acceptez la visite 
!                   mais la valeur de retour est une expression, donc faudra mettre tt ça dans la fabrique 
*                   e.g : 
public IASTexpression visit(IASTassignment iast, Void data) throws EvaluationException {
    return factory.newAssignment(
        iast.getVariable(), 
        iast.getExpression().accept(this, data) // permet de visiter le noeud, et l'appel récursive permet aussi de retorune l'expression qui sera traiter dans les feuilles 
    );
}   

?               -   ILPMLParser: la dernière étape est de rajouter un nouveau Parser, qui utilisera notre nouveau ILPMLListener, il parcour le programm par walker.walk
?                   le programmesera dans tree.node, on utilisera ce programme pour visiter notre fameux visiteur ASTunless transform 


*/