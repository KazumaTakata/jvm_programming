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

  public void parse_version() {
    byte mi_ver1 = bhold.getCurByte();
    byte mi_ver2 = bhold.getCurByte();
    byte maj_ver1 = bhold.getCurByte();
    byte maj_ver2 = bhold.getCurByte();

    System.out.println("magor_version: " + Byte.toString(maj_ver2));
    System.out.println("minor_version:" + Byte.toString(mi_ver2));
  }

  public void parse_constantPool() {
    int constant_size_high = (0xFF & bhold.getCurByte()) << 8;
    int constant_size_low = 0xFF & bhold.getCurByte();
    int constant_size = constant_size_high + constant_size_low - 1;
    System.out.println("constant pool size: " + constant_size);
  }
}
