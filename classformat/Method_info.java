import java.util.ArrayList;

class Method_info {
  public int access_flags;
  public int name_index;
  public int descriptor_index;
  public int attributes_count;
  public ArrayList<Attribute_info> attributes;

  public Method_info() {
    this.access_flags = access_flags;
    this.name_index = name_index;
    this.descriptor_index = descriptor_index;
    this.attributes_count = attributes_count;
    this.attributes = new ArrayList<Attribute_info>();
  }

  public void printOut() {

    System.out.println("{");
    System.out.println("name_index: " + name_index);
    System.out.println("descriptor_index: " + descriptor_index);
    System.out.println("attributes_count: " + attributes_count);
    for (int i = 0; i < this.attributes.size(); i++) {
     Attribute_info attr_info  =  this.attributes.get(i);
     attr_info.printOut();
    }
    System.out.println("}");
  }
}
