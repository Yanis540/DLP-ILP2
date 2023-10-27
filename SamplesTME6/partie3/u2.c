#include <stdio.h> 
#include <stdlib.h> 
#include "ilp.h" 

/* Global variables */ 
ILP_Object even;
ILP_Object odd;

/* Global prototypes */ 
ILP_Object ilp__odd(ILP_Closure ilp_useless
,
    ILP_Object n1);
ILP_Object ilp__even(ILP_Closure ilp_useless
,
    ILP_Object n2);

/* Global functions */ 

ILP_Object ilp__odd(ILP_Closure ilp_useless
,
    ILP_Object n1) {
{ 
  ILP_Object ilptmp9; 
{ 
  ILP_Object ilptmp10; 
  ILP_Object ilptmp11; 
ilptmp10 = n1; 
ilptmp11 = ILP_Integer2ILP(0); 
ilptmp9 = ILP_Equal(ilptmp10, ilptmp11);
} 
  if ( ILP_isEquivalentToTrue(ilptmp9 ) ) {
return ILP_FALSE; 

  } else {
{ 
  ILP_Object ilptmp12; 
{ 
  ILP_Object ilptmp13; 
  ILP_Object ilptmp14; 
ilptmp13 = n1; 
ilptmp14 = ILP_Integer2ILP(1); 
ilptmp12 = ILP_Equal(ilptmp13, ilptmp14);
} 
  if ( ILP_isEquivalentToTrue(ilptmp12 ) ) {
return ILP_TRUE; 

  } else {
{ 
  ILP_Object ilptmp15; 
{ 
  ILP_Object ilptmp16; 
  ILP_Object ilptmp17; 
ilptmp16 = n1; 
ilptmp17 = ILP_Integer2ILP(1); 
ilptmp15 = ILP_Minus(ilptmp16, ilptmp17);
} 
return ilp__even(NULL , ilptmp15);
}

  }
}

  }
}
}
struct ILP_Closure odd_closure_object = { 
   &ILP_object_Closure_class, 
   { { ilp__odd, 
       1, 
       { NULL } } } 
}; 

ILP_Object ilp__even(ILP_Closure ilp_useless
,
    ILP_Object n2) {
{ 
  ILP_Object ilptmp18; 
{ 
  ILP_Object ilptmp19; 
  ILP_Object ilptmp20; 
ilptmp19 = n2; 
ilptmp20 = ILP_Integer2ILP(0); 
ilptmp18 = ILP_Equal(ilptmp19, ilptmp20);
} 
  if ( ILP_isEquivalentToTrue(ilptmp18 ) ) {
return ILP_TRUE; 

  } else {
{ 
  ILP_Object ilptmp21; 
{ 
  ILP_Object ilptmp22; 
  ILP_Object ilptmp23; 
ilptmp22 = n2; 
ilptmp23 = ILP_Integer2ILP(1); 
ilptmp21 = ILP_Equal(ilptmp22, ilptmp23);
} 
  if ( ILP_isEquivalentToTrue(ilptmp21 ) ) {
return ILP_FALSE; 

  } else {
{ 
  ILP_Object ilptmp24; 
{ 
  ILP_Object ilptmp25; 
  ILP_Object ilptmp26; 
ilptmp25 = n2; 
ilptmp26 = ILP_Integer2ILP(1); 
ilptmp24 = ILP_Minus(ilptmp25, ilptmp26);
} 
return ilp__odd(NULL , ilptmp24);
}

  }
}

  }
}
}
struct ILP_Closure even_closure_object = { 
   &ILP_object_Closure_class, 
   { { ilp__even, 
       1, 
       { NULL } } } 
}; 


ILP_Object ilp_program () 
{ 
{ 
  ILP_Object ilptmp27; 
{ 
  ILP_Object ilptmp28; 
ilptmp28 = ILP_Integer2ILP(56); 
ilptmp27 = ilp__odd(NULL , ilptmp28);
}
return ILP_Not(ilptmp27);
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
