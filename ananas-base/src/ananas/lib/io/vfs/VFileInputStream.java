package ananas.lib.io.vfs;

import java.io.IOException;
import java.io.InputStream;

import ananas.lib.io.Connector;
import ananas.lib.io.InputConnection;

public class VFileInputStream extends InputStream {

	private final VFile _file;
	private InputStream _in;
	private InputConnection _conn;

	public VFileInputStream(VFile file) {
		this._file = file;
	}

	private InputStream __get_inner() throws IOException {
		InputStream in = _in;
		if (in == null) {
			InputConnection conn = (InputConnection) Connector.Factory
					.getDefault().open(_file.getURI());
			this._conn = conn;
			in = conn.getInputStream();
			this._in = in;
		}
		return in;
	}

	@Override
	public int read() throws IOException {
		return this.__get_inner().read();
	}

	@Override
	public void close() throws IOException {
		if (_conn == null)
			return;
		_in.close();
		_conn.close();
	}

}
