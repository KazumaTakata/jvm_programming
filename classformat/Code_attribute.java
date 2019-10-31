import java.util.ArrayList;

class Code_attribute {
  public int max_stack;
  public int max_locals;
  public int code_length;
  public ArrayList<Code> codes;
  public int attributes_count;
  public ArrayList<Attribute_info> attributes;

  public void printOut() {

    System.out.println("     max_stack: " + max_stack);
    System.out.println("     max_locals: " + max_locals);
    System.out.println("     code_length: " + code_length);
    System.out.println("     {");
    for (int i = 0; i < codes.size(); i++) {
      Code code = codes.get(i);
      code.printOut();
    }
    System.out.println("     }");

    System.out.println("     attributes_count: " + attributes_count);

    for (int i = 0; i < attributes.size(); i++) {
      Attribute_info attr = attributes.get(i);
      attr.printOut();
    }
  }
}
