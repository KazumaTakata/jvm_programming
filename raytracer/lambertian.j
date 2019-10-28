

.class public lambertian 
.super java/lang/Object
.implements material 

.field private albedo Lvec3;


.method public <init> (Lvec3;)V

aload_0
invokespecial java/lang/Object/<init>()V


aload_0 
aload_1
putfield lambertian/albedo Lvec3;

return

.end method

.method public scatter  (Lray;LHit_record;Lvec3;Lray;)I

aload_2
getfield Hit_record/p Lvec3; 

aload_2
getfield Hit_record/normal Lvec3; 

invokestatic Renderer/random_in_unit_sphere ()Lvec3;

invokevirtual vec3/add (Lvec3;)Lvec3;
invokevirtual vec3/add (Lvec3;)Lvec3;

astore 10

aload 4

aload_2
getfield Hit_record/p Lvec3;

putfield ray/origin Lvec3;


aload 4

aload 10
aload_2 
getfield Hit_record/p Lvec3;
invokevirtual vec3/sub (Lvec3;)Lvec3;

putfield ray/direction Lvec3;




aload_3

aload_0
getfield lambertian/albedo Lvec3;
getfield vec3/x D

putfield vec3/x D


aload_3

aload_0
getfield lambertian/albedo Lvec3;
getfield vec3/y D

putfield vec3/y D


aload_3

aload_0
getfield lambertian/albedo Lvec3;
getfield vec3/z D

putfield vec3/z D

iconst_1
ireturn

.end method

.end class
