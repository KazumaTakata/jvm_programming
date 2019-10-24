.class public Renderer 
.super java/lang/Object

.field camera LCamera;
.field height I
.field width I
.field writer  Ljava/io/PrintWriter;

.method <init> (LCamera;II)V
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


.method static color(Lray;)Lvec3;

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
   
    invokestatic Renderer/color (Lray;)Lvec3;  

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
