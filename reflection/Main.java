import java.lang.reflect.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        try {
        String classstring = "java.lang.System";
        Class cls = Class.forName(classstring);
        

        Field f = cls.getField("out");        
        PrintStream ss = (PrintStream) f.get(System.class); 
        ss.println("eeee");
        } catch (NoSuchFieldException | ClassNotFoundException eee){
        }
            
        } 

}
