#include <stdio.h> 
#include <stdlib.h> 
#include "ilp.h" 

/* Global variables */ 

/* Global prototypes */ 

/* Global functions */ 


ILP_Object ilp_program () 
{ 
{ 
  ILP_Object ilptmp1; 
while ( 1 ) { 
  ILP_Object ilptmp2; 
ilptmp2 = ILP_TRUE; 
  if ( ILP_isEquivalentToTrue(ilptmp2) ) {
break;


} else { 
    break; 

}
}
ilptmp1 = ILP_FALSE; 
ilptmp1 = ILP_Integer2ILP(1); 
return ilptmp1; 
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
