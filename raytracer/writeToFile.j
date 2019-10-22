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



invokevirtual java/io/PrintWriter/close ()V


 return
.end method
.end class
