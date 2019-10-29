class Util {

  // code from
  // https://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
  //    String bytes = "ca";
  //    byte[] sample = Util.hexStringToByteArray(bytes);
  //    System.out.println(Arrays.toString(sample));

  public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      data[i / 2] =
          (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
    }
    return data;
  }

  // System.out.println(Arrays.toString(fileBytes));
  // System.out.println(Byte.toString(curB));
  // from
  // https://stackoverflow.com/questions/1748021/how-to-print-bytes-in-hexadecimal-using-system-out-println
  // System.out.printf("%02x", curB);

}
