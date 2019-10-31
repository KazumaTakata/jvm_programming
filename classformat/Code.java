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
  public int[] args;
}
