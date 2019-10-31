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
}
