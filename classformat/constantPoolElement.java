enum PoolTag {
  CONSTANT_CLASS,
  CONSTANT_FIELDREF,
  CONSTANT_METHODREF,
  CONSTANT_INTERFACEMETHODREF,
  CONSTANT_STRING,
  CONSTANT_INTEGER,
  CONSTANT_FLOAT,
  CONSTANT_LONG,
  CONSTANT_DOUBLE,
  CONSTANT_NAMEANDTYPE,
  CONSTANT_UTF8,
  CONSTANT_METHODHANDLE,
  CONSTANT_METHODTYPE,
  CONSTANT_INVOKEDYNAMIC,
}

class ConstantPoolElement {

  public PoolTag tag;
  public int[] values;

  public ConstantPoolElement(PoolTag tag, int[] values) {
    this.tag = tag;
    this.values = values;
  }

  public void PrintOut() {
    System.out.print("tag: " + this.tag.name());
    System.out.print(" value: ");
    for (int i = 0; i < this.values.length; i++) {
      System.out.print("#" + String.valueOf(values[i]));
    }
    System.out.println("");
  }
}
