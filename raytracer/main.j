.class public Main 
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
 

 new vec3 
 dup
 ldc2_w 1.4
 ldc2_w 4.0
 ldc2_w 5.0
 invokespecial vec3/<init> (DDD)V   

 new vec3 
 dup
 ldc2_w 1.1
 ldc2_w 4.4
 ldc2_w 53.3
 invokespecial vec3/<init> (DDD)V   

 invokevirtual vec3/add (Lvec3;)Lvec3;

 dup 
 invokevirtual vec3/print ()V 
 
 

 new writeToFile
 dup 
 sipush 200
 sipush 100
 invokespecial writeToFile/<init> (II)V

 invokevirtual writeToFile/write ()V

 return
.end method
.end class
