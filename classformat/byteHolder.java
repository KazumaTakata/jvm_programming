import java.util.Arrays;

class ByteHolder {
  public byte[] filedata;
  public int curPos;

  public ByteHolder(byte[] data) {
    filedata = data;
    curPos = 0;
  }

  public byte getCurByte() {
    byte curByte = filedata[curPos];
    curPos += 1;
    return curByte;
  }

  public byte[] getNByte(int n) {
    byte[] slicedByte = Arrays.copyOfRange(this.filedata, this.curPos, this.curPos + n);
    this.curPos += n;
    return slicedByte;
  }
}
