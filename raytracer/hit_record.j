

.class Hit_record 


.field t D
.field p Lvec3;
.field normal Lvec3;

.method <init> ()V


aload_0
ldc2_w 0.0
putfield Hit_record/t D


aload_0
aconst_null
putfield Hit_record/p Lvec3; 

aload_0
aconst_null
putfield Hit_record/normal Lvec3;

return 



.end method

.method <init> (DLvec3;Lvec3;)V

aload_0
dload_1
putfield Hit_record/t D

aload_0
aload 3
putfield Hit_record/p Lvec3; 

aload_0
aload 4
putfield Hit_record/normal Lvec3;

return 

.end method

.end class
