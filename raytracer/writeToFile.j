.class public writeToFile 
.super java/lang/Object

.field height I
.field width I

.method <init>(II)V
aload_0
invokespecial java/lang/Object/<init>()V

aload_0
iload_1
putfield writeToFile/width I 


aload_0
iload_2
putfield writeToFile/height I 

return 
.end method

.method write()V 

 ;Write to file 
new java/io/PrintWriter 
dup
ldc "sample.ppm"
invokespecial java/io/PrintWriter/<init> (Ljava/lang/String;)V 
 
dup
ldc "P3 "
invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V

dup
aload_0
getfield writeToFile/height I
invokestatic java/lang/String/valueOf (I)Ljava/lang/String;
invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V

dup
ldc " "
invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V


dup
aload_0
getfield writeToFile/width I
invokestatic java/lang/String/valueOf (I)Ljava/lang/String;
invokevirtual java/io/PrintWriter/print (Ljava/lang/String;)V

;var j col in 10
;var i row in 11
aload_0 
getfield writeToFile/height I
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
    getfield writeToFile/width I
    if_icmpge rowBreak

    ;main loop
    dup    
    iload 11
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
