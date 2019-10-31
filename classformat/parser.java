import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

class Parser {
  private ByteHolder bhold;
  private ArrayList<ConstantPoolElement> constantPoolList;
  public ClassFormat CFormat;

  public Parser(ByteHolder _bhold) {
    bhold = _bhold;
    constantPoolList = new ArrayList<ConstantPoolElement>();
    this.CFormat = new ClassFormat();
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
    int mi_ver = get_Unsigned_twoByte();
    int maj_ver = get_Unsigned_twoByte();

    this.CFormat.minor_version = mi_ver;
    this.CFormat.major_version = maj_ver;
  }

  public void parse_accessFlag() {
    ArrayList<Access_Flags> flags = new ArrayList<Access_Flags>();
    this.CFormat.flags = flags;
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
  }

  public void parse_class() {
    int this_class = get_Unsigned_twoByte();
    int super_class = get_Unsigned_twoByte();
    int interface_count = get_Unsigned_twoByte();
    this.CFormat.this_class = this_class;
    this.CFormat.super_class = super_class;
    this.CFormat.interface_count = interface_count;

    if (interface_count > 0) {}

    int field_count = get_Unsigned_twoByte();
    this.CFormat.fields_count = field_count;
    if (field_count > 0) {}

    int method_count = get_Unsigned_twoByte();
    this.CFormat.methods_count = method_count;
    ArrayList<Method_info> methods = parse_method(method_count);
    this.CFormat.methods = methods;
    int class_attr_count = get_Unsigned_twoByte();
    this.CFormat.attributes_count = class_attr_count;

    ArrayList<Attribute_info> attr_infos = parse_attribute(class_attr_count);
    this.CFormat.attributes = attr_infos;
    this.CFormat.printOut();
  }

  public ArrayList<Method_info> parse_method(int method_count) {

    ArrayList<Method_info> method_info_list = new ArrayList<Method_info>();
    for (int i = 0; i < method_count; i++) {
      Method_info method_info = new Method_info();
      method_info_list.add(method_info);

      int access_flags = get_Unsigned_twoByte();
      int name_index = get_Unsigned_twoByte();
      int descriptor_index = get_Unsigned_twoByte();
      int attributes_count = get_Unsigned_twoByte();

      method_info.access_flags = access_flags;
      method_info.name_index = name_index;
      method_info.descriptor_index = descriptor_index;
      method_info.attributes_count = attributes_count;
      ArrayList<Attribute_info> attr = parse_attribute(attributes_count);
      method_info.attributes = attr;
    }

    return method_info_list;
  }

  public ArrayList<Attribute_info> parse_attribute(int attributes_count) {
    ArrayList<Attribute_info> attr_list = new ArrayList<Attribute_info>();
    for (int i = 0; i < attributes_count; i++) {
      Attribute_info attr_info = new Attribute_info();
      attr_list.add(attr_info);
      int attribute_name_index = get_Unsigned_twoByte();
      int attribute_length = get_Unsigned_fourByte();

      attr_info.attribute_length = attribute_length;

      ConstantPoolElement const_ele = this.CFormat.constantPoolList.get(attribute_name_index - 1);
      switch (const_ele.string) {
        case "Code":
          {
            attr_info.name = Attribute.CODE;

            Code_attribute code_attr = new Code_attribute();
            attr_info.code_attribute = code_attr;

            int max_stack = get_Unsigned_twoByte();
            int max_locals = get_Unsigned_twoByte();
            int code_length = get_Unsigned_fourByte();

            code_attr.max_stack = max_stack;
            code_attr.max_locals = max_locals;
            code_attr.code_length = code_length;
            ArrayList<Code> codes = parse_code(code_length);
            code_attr.codes = codes;

            int exception_table_length = get_Unsigned_twoByte();

            System.out.println("     exception_table_length: " + exception_table_length);

            if (exception_table_length > 0) {}

            int attributes_count_of_code = get_Unsigned_twoByte();
            code_attr.attributes_count = attributes_count_of_code;
            ArrayList<Attribute_info> attributes = parse_attribute(attributes_count_of_code);
            code_attr.attributes = attributes;

            break;
          }
        case "LineNumberTable":
          {
            attr_info.name = Attribute.LINENUMBERTABLE;

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
            attr_info.name = Attribute.SOURCEFILE;

            int sourcefile_index = get_Unsigned_twoByte();
            System.out.println("     sourcefile_index: " + sourcefile_index);

            break;
          }
      }
      System.out.println("    }");
    }
    return attr_list;
  }

  public ArrayList<Code> parse_code(int code_length) {
    ArrayList<Code> code_list = new ArrayList<Code>();

    for (int i = 0; i < code_length; i++) {
      Code code = new Code();
      code_list.add(code);
      int opcode = 0xFF & bhold.getCurByte();

      switch (opcode) {
        case 0x2a:
          {
            code.opcode = Opcode.ALOAD_0;
            break;
          }
        case 0xb7:
          {
            code.opcode = Opcode.INVOKESPECIAL;
            code.args.add(get_Unsigned_twoByte());
            i += 2;
            break;
          }
        case 0xb1:
          {
            code.opcode = Opcode.RETURN;
            break;
          }
        case 0xb2:
          {
            code.opcode = Opcode.GETSTATIC;
            code.args.add(get_Unsigned_twoByte());
            i += 2;
            break;
          }
        case 0x12:
          {
            code.opcode = Opcode.lDC;
            code.args.add(0xFF & bhold.getCurByte());
            i += 1;
            break;
          }
        case 0xb6:
          {
            code.opcode = Opcode.INVOKEVIRTUAL;
            code.args.add(get_Unsigned_twoByte());
            i += 2;
            break;
          }
        default:
          {
            System.out.printf("        no opcode for %02x\n", opcode);
          }
      }
    }
    return code_list;
  }

  public void parse_constantPool() {
    int constant_size = get_Unsigned_twoByte() - 1;
    this.CFormat.constant_pool_count = constant_size;
    for (int i = 0; i < constant_size; i++) {
      int curByte = 0xFF & bhold.getCurByte();
      ConstantPoolElement ele;
      switch (curByte) {
        case 0x0a:
          {
            int class_index = get_Unsigned_twoByte();
            int name_and_type_index = get_Unsigned_twoByte();
            int[] values = {class_index, name_and_type_index};
            ele = new ConstantPoolElement(PoolTag.CONSTANT_METHODREF, values);
            this.constantPoolList.add(ele);

            break;
          }
        case 0x09:
          {
            int class_index = get_Unsigned_twoByte();
            int name_and_type_index = get_Unsigned_twoByte();
            int[] values = {class_index, name_and_type_index};
            ele = new ConstantPoolElement(PoolTag.CONSTANT_FIELDREF, values);
            this.constantPoolList.add(ele);

            break;
          }
        case 0x08:
          {
            int string_index = get_Unsigned_twoByte();
            int[] values = {string_index};
            ele = new ConstantPoolElement(PoolTag.CONSTANT_STRING, values);
            this.constantPoolList.add(ele);

            break;
          }
        case 0x07:
          {
            int name_index = get_Unsigned_twoByte();
            int[] values = {name_index};
            ele = new ConstantPoolElement(PoolTag.CONSTANT_CLASS, values);
            this.constantPoolList.add(ele);

            break;
          }
        case 0x01:
          {
            int length = get_Unsigned_twoByte();
            byte[] bytes = bhold.getNByte(length);
            String s = new String(bytes, StandardCharsets.UTF_8);
            ele = new ConstantPoolElement(PoolTag.CONSTANT_UTF8, null, s);
            this.constantPoolList.add(ele);

            break;
          }
        case 0x0c:
          {
            int name_index = get_Unsigned_twoByte();
            int descriptor_index = get_Unsigned_twoByte();
            int[] values = {name_index, descriptor_index};
            ele = new ConstantPoolElement(PoolTag.CONSTANT_NAMEANDTYPE, values);
            this.constantPoolList.add(ele);

            break;
          }
      }
    }
    this.CFormat.constantPoolList = this.constantPoolList;
  }
}
