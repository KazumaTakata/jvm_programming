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
 invokespecial Renderer/<init> (LCamera;II)V

 invokevirtual Renderer/render ()V

 return
.end method
.end class
