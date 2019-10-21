
class TestMain 
{
    public static void main(String a[])
    {
        System.out.println("the result is: " + create_objects.create_int() );
        
        TestConstructor test = new TestConstructor(12);
        int age = test.age;
        System.out.println("the age is: " + age );

    }
}
