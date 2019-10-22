.class TestInvokeMethod
.super java/lang/Object

.field age I

.method <init>()V
aload_0
invokespecial java/lang/Object/<init>()V

return

.end method

.method call()Ljava/lang/String;

aload_0
ldc 3.0
ldc 4.0
invokevirtual TestInvokeMethod/sumOfSquares (FF)F
invokestatic java/lang/String/valueOf (F)Ljava/lang/String;
areturn

.end method

.method sumOfSquares (FF)F

fload_1
dup
fmul
fload_2
dup
fmul
fadd
freturn

.end method 
.end class
