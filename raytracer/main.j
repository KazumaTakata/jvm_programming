.class public Main 
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
 new TestConstructor  
 dup
 bipush 123 
 invokespecial TestConstructor/<init> (I)V
 astore 10
 

 getstatic java/lang/System/out Ljava/io/PrintStream;
 ldc "my age is: "

 aload 10
 getfield TestConstructor/age I
 invokestatic java/lang/String/valueOf (I)Ljava/lang/String;
 invokevirtual java/lang/String/concat (Ljava/lang/String;)Ljava/lang/String;

 invokevirtual java/io/PrintStream/println (Ljava/lang/String;)V
 return
.end method
.end class
