import java.util.ArrayList;

enum Opcode {
  ALOAD_0,
  INVOKESPECIAL,
  INVOKEVIRTUAL,
  RETURN,
  GETSTATIC,
  lDC,
}

class Code {
  public Opcode opcode;
  public ArrayList<Integer> args;

  public Code() {
    this.args = new ArrayList<Integer>();
  }

  public void printOut() {
    System.out.print("     ");
    System.out.print(this.opcode);

    for (int i = 0; i < this.args.size(); i++) {
      System.out.print(" #" + args.get(i));
    }
    System.out.print("\n");

  }
}
