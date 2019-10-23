.class public Camera 
.super java/lang/Object

.field origin Lvec3; 
.field horizontal Lvec3;
.field vertical Lvec3; 
.field lower_left_coner Lvec3;

.method <init>(Lvec3;Lvec3;Lvec3;Lvec3;)V

aload_0
invokespecial java/lang/Object/<init>()V


aload_0 
aload_1
putfield Camera/lower_left_coner Lvec3; 


aload_0 
aload_2
putfield Camera/horizontal Lvec3; 


aload_0 
aload_3
putfield Camera/vertical Lvec3; 


aload_0 
aload 4
putfield Camera/origin Lvec3; 

return

.end method


.method getTargetPoint (DD)Lvec3;

aload_0
getfield Camera/lower_left_coner Lvec3;

aload_0 
getfield Camera/horizontal Lvec3;
dload_1 
invokevirtual vec3/scalaMul (D)Lvec3;

aload_0 
getfield Camera/vertical Lvec3;
dload_3 
invokevirtual vec3/scalaMul (D)Lvec3;


invokevirtual vec3/add (Lvec3;)Lvec3;

invokevirtual vec3/add (Lvec3;)Lvec3;


areturn

.end method


.method genRay (DD)Lray;

new ray 
dup

aload_0 
getfield Camera/origin Lvec3;

aload_0
dload_1
dload_3
invokevirtual Camera/getTargetPoint (DD)Lvec3;


invokespecial ray/<init> (Lvec3;Lvec3;)V

areturn 

.end method

.end class
