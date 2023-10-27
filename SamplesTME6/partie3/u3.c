#include <stdio.h> 
#include <stdlib.h> 
#include "ilp.h" 

/* Global variables */ 
ILP_Object add;

/* Global prototypes */ 
ILP_Object ilp__add(ILP_Closure ilp_useless
,
    ILP_Object n1);

/* Global functions */ 

ILP_Object ilp__add(ILP_Closure ilp_useless
,
    ILP_Object n1) {
{ 
  ILP_Object ilptmp29; 
{ 
  ILP_Object ilptmp30; 
  ILP_Object ilptmp31; 
ilptmp30 = n1; 
ilptmp31 = ILP_Integer2ILP(0); 
ilptmp29 = ILP_Equal(ilptmp30, ilptmp31);
} 
  if ( ILP_isEquivalentToTrue(ilptmp29 ) ) {
return ILP_Integer2ILP(0); 

  } else {
{ 
  ILP_Object ilptmp32; 
  ILP_Object ilptmp33; 
ilptmp32 = n1; 
{ 
  ILP_Object ilptmp34; 
{ 
  ILP_Object ilptmp35; 
  ILP_Object ilptmp36; 
ilptmp35 = n1; 
ilptmp36 = ILP_Integer2ILP(1); 
ilptmp34 = ILP_Minus(ilptmp35, ilptmp36);
} 
ilptmp33 = ilp__add(NULL , ilptmp34);
}
return ILP_Plus(ilptmp32, ilptmp33);
} 

  }
}
}
struct ILP_Closure add_closure_object = { 
   &ILP_object_Closure_class, 
   { { ilp__add, 
       1, 
       { NULL } } } 
}; 


ILP_Object ilp_program () 
{ 
{ 
  ILP_Object ilptmp37; 
ilptmp37 = ILP_Integer2ILP(2); 
return ilp__add(NULL , ilptmp37);
}

} 

static ILP_Object ilp_caught_program () {
  struct ILP_catcher* current_catcher = ILP_current_catcher;
  struct ILP_catcher new_catcher;

  if ( 0 == setjmp(new_catcher._jmp_buf) ) {
    ILP_establish_catcher(&new_catcher);
    return ilp_program();
  };
  return ILP_current_exception;
}

int main (int argc, char *argv[]) 
{ 
  ILP_START_GC; 
  ILP_print(ilp_caught_program()); 
  ILP_newline(); 
  return EXIT_SUCCESS; 
} 
