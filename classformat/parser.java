import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

class Parser {
  private ByteHolder bhold;
  private ArrayList<ConstantPoolElement> constantPoolList;

  public Parser(ByteHolder _bhold) {
    bhold = _bhold;
    constantPoolList = new ArrayList<ConstantPoolElement>();
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

  public int get_Unsigned_fourByte() {
    int unsigned_4 = (0xFF & bhold.getCurByte()) << 8 * 3;
    int unsigned_3 = (0xFF & bhold.getCurByte()) << 8 * 2;
    int unsigned_2 = (0xFF & bhold.getCurByte()) << 8 * 1;
    int unsigned_1 = (0xFF & bhold.getCurByte()) << 8 * 0;
    int unsigned = unsigned_4 + unsigned_3 + unsigned_2 + unsigned_1;
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

  public void parse_class() {
    int this_class = get_Unsigned_twoByte();
    System.out.println("this_class: " + this_class);
    int super_class = get_Unsigned_twoByte();
    System.out.println("super_class: " + super_class);
    int interface_count = get_Unsigned_twoByte();
    System.out.println("interface_count: " + interface_count);

    if (interface_count > 0) {}

    int field_count = get_Unsigned_twoByte();
    System.out.println("field_count: " + field_count);

    if (field_count > 0) {}

    int method_count = get_Unsigned_twoByte();
    System.out.println("method_count: " + method_count);

    parse_method(method_count);

    int class_attr_count = get_Unsigned_twoByte();
    System.out.println("attributes_count: " + class_attr_count);

    parse_attribute(class_attr_count);
  }

  public void parse_method(int method_count) {

    for (int i = 0; i < method_count; i++) {
      int access_flags = get_Unsigned_twoByte();
      int name_index = get_Unsigned_twoByte();
      int descriptor_index = get_Unsigned_twoByte();
      int attributes_count = get_Unsigned_twoByte();

      System.out.println("{");
      System.out.println("name_index: " + name_index);
      System.out.println("descriptor_index: " + descriptor_index);
      System.out.println("attributes_count: " + attributes_count);
      parse_attribute(attributes_count);
      System.out.println("}");
    }
  }

  public void parse_attribute(int attributes_count) {
    for (int i = 0; i < attributes_count; i++) {
      int attribute_name_index = get_Unsigned_twoByte();
      int attribute_length = get_Unsigned_fourByte();

      System.out.println("   {");
      System.out.println("     name_index: " + attribute_name_index);
      System.out.println("     attribute_length: " + attribute_length);

      ConstantPoolElement const_ele = this.constantPoolList.get(attribute_name_index - 1);
      switch (const_ele.string) {
        case "Code":
          {
            int max_stack = get_Unsigned_twoByte();
            int max_locals = get_Unsigned_twoByte();
            int code_length = get_Unsigned_fourByte();
            System.out.println("     max_stack: " + max_stack);
            System.out.println("     max_locals: " + max_locals);
            System.out.println("     code_length: " + code_length);
            parse_code(code_length);
            break;
          }
        case "LineNumberTable":
          {
            int line_number_table_length = get_Unsigned_twoByte();
            System.out.println("     line_number_table_length:" + line_number_table_length);
            System.out.println("      {");
            for (int j = 0; j < line_number_table_length; j++) {
              int start_pc = get_Unsigned_twoByte();
              int line_number = get_Unsigned_twoByte();
              System.out.println("     start_pc: " + start_pc);
              System.out.println("     line_number: " + line_number);
            }
            System.out.println("      }");
            break;
          }
        case "SourceFile":
          {
            int sourcefile_index = get_Unsigned_twoByte();
            System.out.println("     sourcefile_index: " + sourcefile_index);

            break;
          }
      }
      System.out.println("    }");
    }
  }

  public void parse_code(int code_length) {
    for (int i = 0; i < code_length; i++) {
      int opcode = 0xFF & bhold.getCurByte();

      switch (opcode) {
        case 0x2a:
          {
            System.out.println("          aload_0");
            break;
          }
        case 0xb7:
          {
            System.out.print("          invokespecial");
            int index = get_Unsigned_twoByte();
            i += 2;
            System.out.println("#" + index);
            break;
          }
        case 0xb1:
          {
            System.out.println("          return");
            break;
          }
        case 0xb2:
          {
            System.out.print("          getstatic");
            int index = get_Unsigned_twoByte();
            i += 2;
            System.out.println("#" + index);
            break;
          }
        case 0x12:
          {
            System.out.print("          ldc");
            int index = 0xFF & bhold.getCurByte();
            i += 1;
            System.out.println("#" + index);

            break;
          }
        case 0xb6:
          {
            System.out.print("          invokevirtual");
            int index = get_Unsigned_twoByte();
            i += 2;
            System.out.println("#" + index);

            break;
          }
        default:
          System.out.printf("        no opcode for %02x\n", opcode);
      }
    }

    int exception_table_length = get_Unsigned_twoByte();
    System.out.println("     exception_table_length: " + exception_table_length);

    if (exception_table_length > 0) {}

    int attributes_count = get_Unsigned_twoByte();
    System.out.println("     attributes_count: " + attributes_count);

    parse_attribute(attributes_count);
  }

  public void parse_constantPool() {
    int constant_size = get_Unsigned_twoByte() - 1;
    System.out.println("constant pool size: " + constant_size);

    // constant_size = 7;
    for (int i = 0; i < constant_size; i++) {
      int curByte = 0xFF & bhold.getCurByte();

      System.out.print("#" + (i + 1) + " ");

      switch (curByte) {
        case 0x0a:
          {
            int class_index = get_Unsigned_twoByte();
            int name_and_type_index = get_Unsigned_twoByte();
            int[] values = {class_index, name_and_type_index};
            ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_METHODREF, values);
            ele.PrintOut();
            this.constantPoolList.add(ele);
            break;
          }
        case 0x09:
          {
            int class_index = get_Unsigned_twoByte();
            int name_and_type_index = get_Unsigned_twoByte();
            int[] values = {class_index, name_and_type_index};
            ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_FIELDREF, values);
            ele.PrintOut();
            this.constantPoolList.add(ele);

            break;
          }
        case 0x08:
          {
            int string_index = get_Unsigned_twoByte();
            int[] values = {string_index};
            ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_STRING, values);
            ele.PrintOut();
            this.constantPoolList.add(ele);

            break;
          }
        case 0x07:
          {
            int name_index = get_Unsigned_twoByte();
            int[] values = {name_index};
            ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_CLASS, values);
            ele.PrintOut();
            this.constantPoolList.add(ele);

            break;
          }
        case 0x01:
          {
            int length = get_Unsigned_twoByte();
            byte[] bytes = bhold.getNByte(length);
            String s = new String(bytes, StandardCharsets.UTF_8);
            ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_UTF8, null, s);
            ele.PrintOut();
            this.constantPoolList.add(ele);

            break;
          }
        case 0x0c:
          {
            int name_index = get_Unsigned_twoByte();
            int descriptor_index = get_Unsigned_twoByte();
            int[] values = {name_index, descriptor_index};
            ConstantPoolElement ele = new ConstantPoolElement(PoolTag.CONSTANT_NAMEANDTYPE, values);
            ele.PrintOut();
            this.constantPoolList.add(ele);

            break;
          }
      }
    }
  }
}
