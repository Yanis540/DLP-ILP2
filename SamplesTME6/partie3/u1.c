#include <stdio.h> 
#include <stdlib.h> 
#include "ilp.h" 

/* Global variables */ 
ILP_Object add;
ILP_Object f;

/* Global prototypes */ 
ILP_Object ilp__f(ILP_Closure ilp_useless
,
    ILP_Object n1);
ILP_Object ilp__add(ILP_Closure ilp_useless
,
    ILP_Object n2);
ILP_Object ilp__b(ILP_Closure ilp_useless
);

/* Global functions */ 

ILP_Object ilp__f(ILP_Closure ilp_useless
,
    ILP_Object n1) {
{ 
  ILP_Object ilptmp1; 
ilptmp1 = ILP_Integer2ILP(1); 
  if ( ILP_isEquivalentToTrue(ilptmp1 ) ) {
{ 
  ILP_Object ilptmp2; 
ilptmp2 = n1; 
return ilp__add(NULL , ilptmp2);
}

  } else {
{ 
  ILP_Object ilptmp3; 
{ 
  ILP_Object ilptmp4; 
  ILP_Object ilptmp5; 
ilptmp4 = n1; 
ilptmp5 = ILP_Integer2ILP(1); 
ilptmp3 = ILP_Plus(ilptmp4, ilptmp5);
} 
return ilp__add(NULL , ilptmp3);
}

  }
}
}
struct ILP_Closure f_closure_object = { 
   &ILP_object_Closure_class, 
   { { ilp__f, 
       1, 
       { NULL } } } 
}; 

ILP_Object ilp__add(ILP_Closure ilp_useless
,
    ILP_Object n2) {
{ 
  ILP_Object ilptmp6; 
  ILP_Object ilptmp7; 
ilptmp6 = n2; 
ilptmp7 = ILP_Integer2ILP(1); 
return ILP_Plus(ilptmp6, ilptmp7);
} 
}
struct ILP_Closure add_closure_object = { 
   &ILP_object_Closure_class, 
   { { ilp__add, 
       1, 
       { NULL } } } 
}; 

ILP_Object ilp__b(ILP_Closure ilp_useless
) {
return ILP_Integer2ILP(1); 
}
struct ILP_Closure b_closure_object = { 
   &ILP_object_Closure_class, 
   { { ilp__b, 
       0, 
       { NULL } } } 
}; 


ILP_Object ilp_program () 
{ 
{ 
  ILP_Object ilptmp8; 
ilptmp8 = ILP_Integer2ILP(56); 
return ilp__f(NULL , ilptmp8);
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
