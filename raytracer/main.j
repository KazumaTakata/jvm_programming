.class public Main 
.super java/lang/Object
.method public static main([Ljava/lang/String;)V

 new Renderer
 dup 
 
 new Camera 
 dup

 new vec3 
 dup
 ldc2_w -2.0 
 ldc2_w  -1.0
 ldc2_w -1.0
 invokespecial vec3/<init> (DDD)V   

 new vec3 
 dup
 ldc2_w 4.0
 ldc2_w 0.0
 ldc2_w 0.0
 invokespecial vec3/<init> (DDD)V   


 new vec3 
 dup
 ldc2_w 0.0 
 ldc2_w 2.0
 ldc2_w 0.0
 invokespecial vec3/<init> (DDD)V   


 new vec3 
 dup
 ldc2_w 0.0 
 ldc2_w 0.0
 ldc2_w 0.0
 invokespecial vec3/<init> (DDD)V   

 invokespecial Camera/<init> (Lvec3;Lvec3;Lvec3;Lvec3;)V
 
 

 
 sipush 200
 sipush 100

 ldc 2
 anewarray Hitable
 dup


 ;array index 
 ldc 0


 ;sphere 1 
 new Sphere 
 dup

 ;center 
 new vec3 
 dup
 ldc2_w 0.0 
 ldc2_w 0.0
 ldc2_w -1.0 
 invokespecial vec3/<init> (DDD)V   

 ;radius
 ldc2_w 0.5

 invokespecial Sphere/<init> (Lvec3;D)V


 ;store in array at 0
 aastore


 dup
 
 ;array index
 ldc 1

 ;sphere 2 
 new Sphere 
 dup
 
 ;center 
 new vec3 
 dup
 ldc2_w 0.0 
 ldc2_w -100.5
 ldc2_w -1.0 
 invokespecial vec3/<init> (DDD)V   

 ;radius
 ldc2_w 100.0

 invokespecial Sphere/<init> (Lvec3;D)V

 aastore

 astore 20
 
 new Hitable_list
 dup

 aload 20
 ldc 2 

 invokespecial Hitable_list/<init> ([LHitable;I)V
 


 invokespecial Renderer/<init> (LCamera;IILHitable_list;)V

 invokevirtual Renderer/render ()V

 return
.end method
.end class
