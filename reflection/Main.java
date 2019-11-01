import java.lang.reflect.*;
import java.io.*;

 class Main {

    public static void main(String[] args) throws IllegalAccessException {
        try {
        String classstring = "java.lang.System";
        Class cls = Class.forName(classstring);

        String stringclassstring = "java.lang.String";        
        Class clsstring = Class.forName(stringclassstring);

        Field f = cls.getField("out");        
        Object ss =  f.get(cls); 
        Method mm = ss.getClass().getMethod("println", clsstring  );        
        mm.invoke(ss, "eee");//ss.println("eeee");
        





        //Object[] objects = new Object[3];
        //objects[0] = "foo";


        //Object obj = objects[0];

        //Class cl = Class.forName("java.lang.String");
        //Method method = cl.getMethod()

        //cl.class str = (cl.class) obj;



        } catch (InvocationTargetException | NoSuchMethodException |NoSuchFieldException | ClassNotFoundException eee){
        }
            
        } 

}
