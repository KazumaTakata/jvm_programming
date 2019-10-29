class Parser {
  private ByteHolder bhold;

  public Parser(ByteHolder _bhold) {
    bhold = _bhold;
  }

  public void parse_magic() {
    String magicword = "cafebabe";
    byte[] magicbytes = Util.hexStringToByteArray(magicword);

    for (int i = 0; i < 4; i++) {
      byte bytedata = bhold.getCurByte();
      if (bytedata != magicbytes[i]) {
        System.out.println("magic word cafebabe mismatch");
        System.out.println("this file is not java class file!!!");

        System.exit(1);
      }
    }
    System.out.println("magic_number: cafebabe");
  }

  public int get_Unsigned_twoByte() {
    int unsigned_high = (0xFF & bhold.getCurByte()) << 8;
    int unsigned_low = 0xFF & bhold.getCurByte();
    int unsigned = unsigned_high + unsigned_low;
    return unsigned;
  }

  public void parse_version() {
    byte mi_ver1 = bhold.getCurByte();
    byte mi_ver2 = bhold.getCurByte();
    byte maj_ver1 = bhold.getCurByte();
    byte maj_ver2 = bhold.getCurByte();

    System.out.println("magor_version: " + Byte.toString(maj_ver2));
    System.out.println("minor_version:" + Byte.toString(mi_ver2));
  }

  public void parse_constantPool() {
    int constant_size = get_Unsigned_twoByte() - 1;
    System.out.println("constant pool size: " + constant_size);

    int curByte = 0xFF & bhold.getCurByte();

    switch (curByte) {
      case 0x0a:
        int class_index = get_Unsigned_twoByte();
        int name_and_type_index = get_Unsigned_twoByte();
        int[] values = {class_index, name_and_type_index};
        ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_METHODREF, values);
        ele.PrintOut();

        break;
    }
  }
}
