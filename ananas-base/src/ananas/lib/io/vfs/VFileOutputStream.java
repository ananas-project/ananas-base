package ananas.lib.io.vfs;

import java.io.IOException;
import java.io.OutputStream;

import ananas.lib.io.Connector;
import ananas.lib.io.OutputConnection;

public class VFileOutputStream extends OutputStream {

	private final VFile _file;
	private OutputStream _out;
	private OutputConnection _conn;

	public VFileOutputStream(VFile file) {
		this._file = file;
	}

	private OutputStream __get_inner() throws IOException {
		OutputStream inner = _out;
		if (inner == null) {
			OutputConnection conn = (OutputConnection) Connector.Factory
					.getDefault().open(_file.getURI());
			this._conn = conn;
			inner = conn.getOutputStream();
			this._out = inner;
		}
		return inner;
	}

	@Override
	public void write(int b) throws IOException {
		this.__get_inner().write(b);
	}

	@Override
	public void close() throws IOException {
		if (_conn == null)
			return;
		_out.close();
		_conn.close();
	}

}
