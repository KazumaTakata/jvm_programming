import java.util.ArrayList;

class ClassFormat {

  public int minor_version;
  public int major_version;
  public int constant_pool_count;
  public ArrayList<ConstantPoolElement> constantPoolList;
  public ArrayList<Access_Flags> flags;
  public int this_class;
  public int super_class;
  public int interface_count;
  public int[] interfaces;
  public int fields_count;
  public int[] fields;
  public int methods_count;
  public ArrayList<Method_info> methods;

  public ClassFormat() {
    this.constantPoolList = new ArrayList<ConstantPoolElement>();
    this.flags = new ArrayList<Access_Flags>();
  }

  public void printOut() {
    System.out.println("magor_version: " + this.major_version);
    System.out.println("minor_version:" + this.minor_version);
    System.out.println("constant pool size: " + this.constant_pool_count);

    for (int i = 0; i < this.constant_pool_count; i++) {
      System.out.print("#" + (i + 1) + " ");
      ConstantPoolElement ele = this.constantPoolList.get(i);
      ele.printOut();
    }

    System.out.println(this.flags);
    System.out.println("this_class: " + this.this_class);
    System.out.println("super_class: " + this.super_class);
    System.out.println("interface_count: " + this.interface_count);
    System.out.println("field_count: " + field_count);
    System.out.println("method_count: " + method_count);
  }
}
