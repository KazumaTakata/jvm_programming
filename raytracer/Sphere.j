
.class public Sphere 
.super java/lang/Object
.implements Hitable

.field private center Lvec3;
.field private radius D
.field private mat Lmaterial;

.method public <init> (Lvec3;DLmaterial;)V


aload_0
invokespecial java/lang/Object/<init>()V


aload_0 
aload_1
putfield Sphere/center Lvec3;

aload_0 
dload_2
putfield Sphere/radius D


aload_0 
aload 4
putfield Sphere/mat Lmaterial;


return

.end method

.method public hit  (Lray;DDLHit_record;)I


aload_1 
getfield ray/origin Lvec3;

aload_0
getfield Sphere/center Lvec3;

invokevirtual vec3/sub (Lvec3;)Lvec3;
astore 10

;a 11 
aload_1
getfield ray/direction Lvec3;

aload_1 
getfield ray/direction Lvec3;

invokevirtual vec3/dot (Lvec3;)D
dstore 11

;b 13
aload 10
 
aload_1
getfield ray/direction Lvec3;

invokevirtual vec3/dot (Lvec3;)D

dstore 13

;c 15
aload 10
dup
invokevirtual vec3/dot (Lvec3;)D

aload_0
getfield Sphere/radius D
dup2
dmul

dsub
dstore 15

;disc 17
dload 13
dup2
dmul


dload 11
dload 15
dmul

dsub
dstore 17

dload 17
ldc2_w 0.0

dcmpg
ifgt greater

;false
iconst_0
ireturn  


greater:
 dload 13
 dneg 
 dload 17
 invokestatic java/lang/Math/sqrt (D)D 
 dsub   
 
 dload 11
 ddiv
    
 ;temp 19
 dstore 19 

 dload 4
 dload 19
 ;tmax
 dcmpg 

;tmin
 dload 19
 dload 2
 dcmpg 

 iadd
 ldc 2   
 if_icmpge sphere_hit 



 dload 13
 dneg 
 dload 17
 invokestatic java/lang/Math/sqrt (D)D 
 dadd   
 
 dload 11
 ddiv

 ;temp 19 
 dstore 19 
 

 dload 4
 dload 19
 ;tmax
 dcmpg 


 ;tmin
 dload 19
 dload 2
 dcmpg 

 iadd
 ldc 2   
 if_icmpge sphere_hit 


 iconst_0
 ireturn 
 
sphere_hit: 
 
 aload 6
 dload 19
 putfield Hit_record/t D
 
 aload 6

 aload_1
 dload 19
 invokevirtual ray/point_at_parameter (D)Lvec3;
 putfield Hit_record/p Lvec3; 
 
 aload 6
 aload 6
 getfield Hit_record/p Lvec3;
 aload_0
 getfield Sphere/center Lvec3;
 invokevirtual vec3/sub (Lvec3;)Lvec3;
 ldc2_w 1.0
 aload_0
 getfield Sphere/radius D
 ddiv
 invokevirtual vec3/scalaMul (D)Lvec3; 

 putfield Hit_record/normal Lvec3; 

 aload 6
 aload_0
 getfield Sphere/mat Lmaterial;
 putfield Hit_record/mat Lmaterial;

 iconst_1
 ireturn 

.end method

.end class
