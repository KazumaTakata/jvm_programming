.class public Renderer 
.super java/lang/Object

.field camera LCamera;
.field height I
.field width I

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

.method render()V 

 ;Write to file 
new java/io/PrintWriter 
dup
ldc "sample.ppm"
invokespecial java/io/PrintWriter/<init> (Ljava/lang/String;)V 
 
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
    dup    
    iload 11
    i2d
    aload_0 
    getfield Renderer/width I
    i2d
    ddiv
    ldc2_w 255.99D
    dmul
    d2i
    invokestatic java/lang/String/valueOf (I)Ljava/lang/String;
    invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V

    dup
    ldc " "
    invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V


    dup    
    iload 10
    i2d
    aload_0 
    getfield Renderer/height I
    i2d
    ddiv
    ldc2_w 255.99D
    dmul
    d2i
    invokestatic java/lang/String/valueOf (I)Ljava/lang/String;
    invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V



    dup
    ldc " "
    invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V


    dup    
    ldc2_w 0.2D
    ldc2_w 255.99D
    dmul
    d2i
    invokestatic java/lang/String/valueOf (I)Ljava/lang/String;
    invokevirtual java/io/PrintWriter/println (Ljava/lang/String;)V


    iinc 11 1
    goto rowLoop

rowBreak:
    bipush 0
    istore 11 

    iinc 10 -1
    goto colLoop

colBreak:





invokevirtual java/io/PrintWriter/close ()V


 return
.end method
.end class
