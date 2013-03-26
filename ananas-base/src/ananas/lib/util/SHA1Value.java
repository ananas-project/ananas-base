package ananas.lib.util;

public class SHA1Value {

	private final byte[] mData;
	private static final int sha1_buffer_length = 20;

	public SHA1Value() {
		this.mData = new byte[sha1_buffer_length];
		for (int i = mData.length - 1; i >= 0; i--) {
			mData[i] = 0;
		}
	}

	public SHA1Value(byte[] buffer) {
		this.mData = SHA1Value.bufferForData(buffer, 0, buffer.length);
	}

	public SHA1Value(byte[] buffer, int offset, int length) {
		this.mData = SHA1Value.bufferForData(buffer, offset, length);
	}

	private static byte[] bufferForData(byte[] buff, int off, int len) {
		if (len == sha1_buffer_length) {
			byte[] data = new byte[len];
			for (int i = len - 1; i >= 0; i--) {
				data[i] = buff[off + i];
			}
			return data;
		} else {
			throw new RuntimeException("the length is not a sha-1 value.");
		}
	}

	public byte[] getBytes() {
		byte[] out = new byte[this.mData.length];
		for (int i = out.length - 1; i >= 0; i--) {
			out[i] = this.mData[i];
		}
		return out;
	}

}
