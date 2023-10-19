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
b1: while ( 1 ) { 
  ILP_Object ilptmp2; 
ilptmp2 = ILP_TRUE; 
  if ( ILP_isEquivalentToTrue(ilptmp2) ) {
b2: while ( 1 ) { 
  ILP_Object ilptmp3; 
ilptmp3 = ILP_TRUE; 
  if ( ILP_isEquivalentToTrue(ilptmp3) ) {
{ 
  ILP_Object ilptmp4; 
{ 
  ILP_Object ilptmp5; 
{ 
  ILP_Object ilptmp6; 
  ILP_Object ilptmp7; 
ilptmp6 = ILP_Integer2ILP(1); 
ilptmp7 = ILP_Integer2ILP(0); 
ilptmp5 = ILP_GreaterThan(ilptmp6, ilptmp7);
} 
  if ( ILP_isEquivalentToTrue(ilptmp5 ) ) {
goto endb1;


  } else {
ilptmp4 = ILP_FALSE; 

  }
}
{ 
  ILP_Object ilptmp8; 
{ 
  ILP_Object ilptmp9; 
  ILP_Object ilptmp10; 
ilptmp9 = ILP_Integer2ILP(1); 
ilptmp10 = ILP_Integer2ILP(0); 
ilptmp8 = ILP_GreaterThan(ilptmp9, ilptmp10);
} 
  if ( ILP_isEquivalentToTrue(ilptmp8 ) ) {
goto b1;


  } else {
ilptmp4 = ILP_FALSE; 

  }
}
(void)ilptmp4; 
} 

} else { 
    break; 

}
}
endb2: 
(void)ILP_FALSE; 

} else { 
    break; 

}
}
endb1: 
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
