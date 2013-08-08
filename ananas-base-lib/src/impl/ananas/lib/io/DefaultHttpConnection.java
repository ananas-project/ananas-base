package impl.ananas.lib.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;

import ananas.lib.io.Connection;
import ananas.lib.io.ConnectionFactory;
import ananas.lib.io.HttpConnection;

public class DefaultHttpConnection implements HttpConnection {

	private final URI _uri;
	private final HttpURLConnection _conn;
	private InputStream _in;
	private OutputStream _out;

	protected DefaultHttpConnection(URI uri) throws MalformedURLException,
			IOException {
		this._uri = uri;
		HttpURLConnection http = (HttpURLConnection) _uri.toURL()
				.openConnection();
		this._conn = http;
	}

	public static ConnectionFactory getFactory() {
		return new ConnectionFactory() {

			@Override
			public Connection openConnection(URI uri) throws IOException {
				return new DefaultHttpConnection(uri);
			}
		};
	}

	@Override
	public String getType() {
		return _conn.getContentType();
	}

	@Override
	public long getLength() {
		return _conn.getContentLength();
	}

	@Override
	public String getEncoding() {
		return _conn.getContentEncoding();
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		OutputStream out = this._out;
		if (out == null) {
			_out = out = _conn.getOutputStream();
		}
		return out;
	}

	@Override
	public void close() throws IOException {
		_conn.disconnect();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream in = this._in;
		if (in == null) {
			_in = in = _conn.getInputStream();
		}
		return in;
	}

	@Override
	public void setRequestMethod(String method) {
		try {
			_conn.setRequestMethod(method);
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setRequestHeader(String name, String value) {
		_conn.setRequestProperty(name, value);
	}

	@Override
	public String getResponseHeader(String name) {
		return _conn.getHeaderField(name);
	}

	@Override
	public String getResponseMessage() throws IOException {
		return _conn.getResponseMessage();
	}

	@Override
	public int getResponseCode() throws IOException {
		return _conn.getResponseCode();
	}

}
