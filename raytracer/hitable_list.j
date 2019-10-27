

.class public Hitable_list 
.super java/lang/Object
.implements Hitable


.field list [LHitable;
.field size I


.method public <init>([LHitable;I)V

aload_0
invokespecial java/lang/Object/<init>()V


aload_0
aload_1
putfield Hitable_list/list [LHitable;


aload_0
iload_2
putfield Hitable_list/size I

return

.end method

.method public hit (Lray;DDLHit_record;)I

;hit_anything 10  
iconst_0
istore 10

;closest_so_far 11
dload 4
dstore 11

; i 13
iconst_0
istore 13

loop:
    iload 13
    aload_0
    getfield Hitable_list/size I
    if_icmpge break
 
    aload_0
    getfield Hitable_list/list [LHitable;  
    iload 13  
    aaload    

 
    aload_1
    dload 2
    dload 11 
    aload 6 
   
    invokeinterface Hitable/hit (Lray;DDLHit_record;)I 7
    ifgt hitted
   
    iinc 13 1 
    goto loop 

hitted: 
    iconst_1    
    istore 10

    aload 6    
    getfield Hit_record/t D
    dstore 11
 
    iinc 13 1 
    goto loop

break:
    iload 10
    ireturn 

.end method



.end class
