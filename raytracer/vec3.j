.class public vec3 
.super java/lang/Object

.field x D
.field y D 
.field z D

.method <init>(DDD)V

aload_0
invokespecial java/lang/Object/<init>()V

aload_0
dload_1
putfield vec3/x D 


aload_0
dload_3
putfield vec3/y D


aload_0
dload 5
putfield vec3/z D 

return

.end method

.method scalaMul(D)Lvec3;

new vec3
dup

aload_0
getfield vec3/x D 
dload_1 
dmul

aload_0
getfield vec3/y D 
dload_1 
dmul

aload_0
getfield vec3/z D 
dload_1 
dmul


invokespecial vec3/<init> (DDD)V

areturn   

.end method

.method length()D

aload_0
getfield vec3/x D 
dup2 
dmul

aload_0
getfield vec3/y D 
dup2 
dmul

aload_0
getfield vec3/z D 
dup2 
dmul

dadd
dadd

invokestatic java/lang/Math/sqrt (D)D

dreturn

.end method

.method unit_vector()Lvec3;

aload_0

ldc2_w 1.0  
aload_0
invokevirtual vec3/length ()D
ddiv

invokevirtual vec3/scalaMul (D)Lvec3; 

areturn   





.end method

.method add(Lvec3;)Lvec3;

new vec3
dup

aload_0
getfield vec3/x D 
aload_1 
getfield vec3/x D
dadd


aload_0
getfield vec3/y D
aload_1 
getfield vec3/y D
dadd


aload_0
getfield vec3/z D
aload_1 
getfield vec3/z D
dadd

invokespecial vec3/<init> (DDD)V

areturn   


.end method


.method print()V


getstatic java/lang/System/out Ljava/io/PrintStream;

dup
ldc "("
invokevirtual java/io/PrintStream/print (Ljava/lang/String;)V


dup
aload_0
getfield vec3/x D
invokestatic java/lang/String/valueOf (D)Ljava/lang/String;
invokevirtual java/io/PrintStream/print (Ljava/lang/String;)V

dup
ldc ","
invokevirtual java/io/PrintStream/print (Ljava/lang/String;)V


dup
aload_0
getfield vec3/y D 
invokestatic java/lang/String/valueOf (D)Ljava/lang/String;
invokevirtual java/io/PrintStream/print (Ljava/lang/String;)V

dup
ldc ","
invokevirtual java/io/PrintStream/print (Ljava/lang/String;)V


dup
aload_0
getfield vec3/z D 
invokestatic java/lang/String/valueOf (D)Ljava/lang/String;
invokevirtual java/io/PrintStream/print (Ljava/lang/String;)V


dup
ldc ")"
invokevirtual java/io/PrintStream/print (Ljava/lang/String;)V



return
.end method

.end class
