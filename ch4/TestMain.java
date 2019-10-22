
class TestMain 
{
    public static void main(String a[])
    {
        System.out.println("the result is: " + create_objects.create_int() );
        
        TestConstructor test = new TestConstructor(12);
        int age = test.age;
        System.out.println("the age is: " + age );


        TestInvokeMethod test2 = new TestInvokeMethod();
        String sos  = test2.call();
        System.out.println("sos is: " + sos );


    }
}
