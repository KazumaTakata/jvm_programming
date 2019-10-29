import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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

  public void parse_accessFlag() {
    ArrayList<Access_Flags> flags = new ArrayList<Access_Flags>();
    int flag = get_Unsigned_twoByte();
    int flag1 = flag % 16;

    if (flag1 == 1) {
      flags.add(Access_Flags.ACC_PUBLIC);
    }
    int flag2 = (flag >> 4) % 16;

    if (flag2 == 1) {
      flags.add(Access_Flags.ACC_FINAL);
    } else if (flag2 == 2) {
      flags.add(Access_Flags.ACC_SUPER);
    }

    int flag3 = (flag >> 8) % 16;

    if (flag3 == 2) {
      flags.add(Access_Flags.ACC_INTERFACE);
    } else if (flag3 == 4) {
      flags.add(Access_Flags.ACC_ABSTRACT);
    }
    int flag4 = (flag >> 12) % 16;

    if (flag4 == 1) {
      flags.add(Access_Flags.ACC_SYNTHETIC);
    } else if (flag4 == 2) {
      flags.add(Access_Flags.ACC_ANNOTATION);
    } else if (flag4 == 4) {
      flags.add(Access_Flags.ACC_ENUM);
    }

    System.out.println(flags);
  }

  public void parse_constantPool() {
    int constant_size = get_Unsigned_twoByte() - 1;
    System.out.println("constant pool size: " + constant_size);

    // constant_size = 7;
    for (int i = 0; i < constant_size; i++) {
      int curByte = 0xFF & bhold.getCurByte();

      switch (curByte) {
        case 0x0a:
          {
            int class_index = get_Unsigned_twoByte();
            int name_and_type_index = get_Unsigned_twoByte();
            int[] values = {class_index, name_and_type_index};
            ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_METHODREF, values);
            ele.PrintOut();

            break;
          }
        case 0x09:
          {
            int class_index = get_Unsigned_twoByte();
            int name_and_type_index = get_Unsigned_twoByte();
            int[] values = {class_index, name_and_type_index};
            ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_FIELDREF, values);
            ele.PrintOut();

            break;
          }
        case 0x08:
          {
            int string_index = get_Unsigned_twoByte();
            int[] values = {string_index};
            ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_STRING, values);
            ele.PrintOut();

            break;
          }
        case 0x07:
          {
            int name_index = get_Unsigned_twoByte();
            int[] values = {name_index};
            ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_CLASS, values);
            ele.PrintOut();

            break;
          }
        case 0x01:
          {
            int length = get_Unsigned_twoByte();
            byte[] bytes = bhold.getNByte(length);
            String s = new String(bytes, StandardCharsets.UTF_8);
            ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_UTF8, null, s);
            ele.PrintOut();

            break;
          }
        case 0x0c:
          {
            int name_index = get_Unsigned_twoByte();
            int descriptor_index = get_Unsigned_twoByte();
            int[] values = {name_index, descriptor_index};
            ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_NAMEANDTYPE, values);
            ele.PrintOut();

            break;
          }
      }
    }
  }
}
