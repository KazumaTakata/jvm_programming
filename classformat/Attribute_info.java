enum Attribute {
  CONSTANTVALUE,
  CODE,
  STACKMAPTABLE,
  SOURCEFILE,
  LINENUMBERTABLE,
}

class Attribute_info {
  public Attribute name;
  public int attribute_length;
  public Code_attribute code_attribute;

  public void printOut() {

    System.out.println("   {");
    System.out.println("     name_index: " + name.name());
    System.out.println("     attribute_length: " + attribute_length);

    if (Attribute.CODE == name) {
        code_attribute.printOut();
    }

    System.out.println("   }");
  }
}
