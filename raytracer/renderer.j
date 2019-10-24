.class public Renderer 
.super java/lang/Object

.field camera LCamera;
.field height I
.field width I
.field hitable_list LHitable_list;
.field writer  Ljava/io/PrintWriter;

.method <init> (LCamera;IILHitable_list;)V
aload_0
invokespecial java/lang/Object/<init>()V

aload_0
aload_1
putfield Renderer/camera LCamera; 



aload_0
iload_2
putfield Renderer/width I 


aload_0
iload_3
putfield Renderer/height I 

aload_0
aload 4
putfield Renderer/hitable_list LHitable_list;

return 
.end method


.method ppmHeader()V

new java/io/PrintWriter 
dup


dup
ldc "sample.ppm"
invokespecial java/io/PrintWriter/<init> (Ljava/lang/String;)V 


astore 15
aload_0
aload 15
putfield Renderer/writer  Ljava/io/PrintWriter;


 
dup
ldc "P3"
invokevirtual java/io/PrintWriter/println (Ljava/lang/String;)V

dup
aload_0
getfield Renderer/width I
invokestatic java/lang/String/valueOf (I)Ljava/lang/String;
invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V

dup
ldc " "
invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V


dup
aload_0
getfield Renderer/height I
invokestatic java/lang/String/valueOf (I)Ljava/lang/String;
invokevirtual java/io/PrintWriter/println (Ljava/lang/String;)V

dup
ldc "255"
invokevirtual java/io/PrintWriter/println (Ljava/lang/String;)V

return

.end method

.method static hit_sphere (Lvec3;DLray;)D

aload_3 
getfield ray/origin Lvec3;
aload_0

invokevirtual vec3/sub (Lvec3;)Lvec3;
astore 10



aload_3 
getfield ray/direction Lvec3;

aload_3 
getfield ray/direction Lvec3;

invokevirtual vec3/dot (Lvec3;)D
dstore 11



aload 10
 
aload_3 
getfield ray/direction Lvec3;

invokevirtual vec3/dot (Lvec3;)D

ldc2_w 2.0
dmul

dstore 13


aload 10
dup
invokevirtual vec3/dot (Lvec3;)D

dload_1
dup2
dmul

dsub

dstore 15


dload 13
dup2
dmul


dload 11
dload 15
ldc2_w 4.0
dmul
dmul

dsub
dstore 17

dload 17
ldc2_w 0.0

dcmpg
ifgt greater
ldc2_w -1.0
dreturn  


greater:
 dload 13
 dneg 
 dload 17
 invokestatic java/lang/Math/sqrt (D)D 
 dsub   
 
 ldc2_w 2.0
 dload 11
 dmul
 ddiv

 dreturn 


.end method

.method static color(Lray;LHitable_list;)Lvec3;

aload_1
aload_0
ldc2_w 0.0
ldc2_w 1000.0
new Hit_record  
dup
invokespecial vec3/<init> ()V


; hit_record
astore 30
aload 30

invokevirtual Hitable_list/hit (Lray;DDLHit_record;)I
 

dstore 20
dload 20
ldc2_w 0.0
dcmpg
ifgt hit 

aload_0
getfield ray/direction Lvec3;
invokevirtual vec3/unit_vector ()Lvec3;
getfield vec3/y D
ldc2_w 1.0
dadd
ldc2_w 0.5
dmul
dstore 10

new vec3
dup
ldc2_w 1.0
ldc2_w 1.0
ldc2_w 1.0

invokespecial vec3/<init> (DDD)V

ldc2_w 1.0
dload 10
dsub

invokevirtual vec3/scalaMul (D)Lvec3;

new vec3
dup
ldc2_w 0.5
ldc2_w 0.7
ldc2_w 1.0

invokespecial vec3/<init> (DDD)V

dload 10

invokevirtual vec3/scalaMul (D)Lvec3;

invokevirtual vec3/add (Lvec3;)Lvec3;

areturn


hit:

aload_0
dload 20
invokevirtual ray/point_at_parameter (D)Lvec3;

 
new vec3
dup
ldc2_w 0.0
ldc2_w 0.0
ldc2_w -1.0

invokespecial vec3/<init> (DDD)V

invokevirtual vec3/sub (Lvec3;)Lvec3;
invokevirtual vec3/unit_vector ()Lvec3;


new vec3
dup
ldc2_w 1.0
ldc2_w 1.0
ldc2_w 1.0

invokespecial vec3/<init> (DDD)V

invokevirtual vec3/add (Lvec3;)Lvec3;

ldc2_w 0.5
invokevirtual vec3/scalaMul (D)Lvec3;

areturn


.end method


.method outputToFile (Lvec3;)V

aload_0
getfield Renderer/writer Ljava/io/PrintWriter; 

dup
aload_1
getfield vec3/x D
d2i
invokestatic java/lang/String/valueOf (I)Ljava/lang/String;
invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V

dup
ldc " "
invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V


dup
aload_1
getfield vec3/y D
d2i
invokestatic java/lang/String/valueOf (I)Ljava/lang/String;
invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V



dup
ldc " "
invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V



dup
aload_1
getfield vec3/z D
d2i
invokestatic java/lang/String/valueOf (I)Ljava/lang/String;
invokevirtual java/io/PrintWriter/println (Ljava/lang/String;)V

return

.end method

.method render()V 


aload_0
invokevirtual Renderer/ppmHeader ()V

;var j col in 10
;var i row in 11
aload_0 
getfield Renderer/height I
istore 10 

bipush 0
istore 11 



colLoop:
    iload 10
    sipush 0 
    if_icmplt colBreak 

rowLoop:
    iload 11
    aload_0
    getfield Renderer/width I
    if_icmpge rowBreak

    ;main loop
    aload_0
    getfield Renderer/camera LCamera;
        

    ;var u
    iload 11
    i2d
    aload_0 
    getfield Renderer/width I
    i2d
    ddiv
    


    ;var v
    iload 10
    i2d
    aload_0 
    getfield Renderer/height I
    i2d
    ddiv
 
    invokevirtual Camera/genRay (DD)Lray;
 
     
    iload_0
    getfield Renderer/hitable_list LHitable_list;    
 
    invokestatic Renderer/color (Lray;LHitable_list;)Lvec3;  

    ldc2_w 255.99D
    invokevirtual vec3/scalaMul (D)Lvec3;
    
    astore 13
    aload_0
    aload 13    

    invokevirtual Renderer/outputToFile (Lvec3;)V 
    
    iinc 11 1
    goto rowLoop

rowBreak:
    bipush 0
    istore 11 

    iinc 10 -1
    goto colLoop

colBreak:

aload_0
getfield Renderer/writer Ljava/io/PrintWriter;
invokevirtual java/io/PrintWriter/close ()V


 return
.end method
.end class
