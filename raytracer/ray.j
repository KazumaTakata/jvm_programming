.class public ray 
.super java/lang/Object

.field origin Lvec3; 
.field direction Lvec3; 

.method <init>(Lvec3;Lvec3;)V

aload_0
invokespecial java/lang/Object/<init>()V

aload_0
aload_1
putfield ray/origin Lvec3; 


aload_0
aload_2
putfield ray/direction Lvec3; 

return

.end method

.method point_at_parameter(D)Lvec3;

aload_0
getfield ray/origin Lvec3;

aload_0
getfield ray/direction Lvec3;
dload_1

invokevirtual vec3/scalaMul (D)Lvec3;
invokevirtual vec3/add (Lvec3;)Lvec3;

areturn


.end method



.end class
