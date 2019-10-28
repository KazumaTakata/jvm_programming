

.class public Hit_record 
.super java/lang/Object


.field t D
.field p Lvec3;
.field normal Lvec3;
.field mat  Lmaterial;

.method public <init> ()V

aload_0
invokespecial java/lang/Object/<init>()V


aload_0
ldc2_w 0.0
putfield Hit_record/t D


aload_0
aconst_null
putfield Hit_record/p Lvec3; 

aload_0
aconst_null
putfield Hit_record/normal Lvec3;

aload_0
aconst_null
putfield Hit_record/mat Lmaterial;



return 


.end method

.method public <init> (DLvec3;Lvec3;Lmat;)V

aload_0
invokespecial java/lang/Object/<init>()V


aload_0
dload_1
putfield Hit_record/t D

aload_0
aload 3
putfield Hit_record/p Lvec3; 

aload_0
aload 4
putfield Hit_record/normal Lvec3;

aload_0
aload 5 
putfield Hit_record/mat Lmaterial;


return 

.end method

.end class
