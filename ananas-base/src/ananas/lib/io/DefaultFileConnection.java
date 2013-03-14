package ananas.lib.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class DefaultFileConnection implements IContentConnection {

	// private final URI mURI;
	private InputStream mIn;
	private final File mFile;

	public DefaultFileConnection(URI uri) {
		// this.mURI = uri;
		this.mFile = new File(uri.getPath());
	}

	public static IConnectionFactory getFactory() {
		return new IConnectionFactory() {

			@Override
			public IConnection openConnection(URI uri) throws IOException {
				DefaultFileConnection conn = new DefaultFileConnection(uri);
				return conn;
			}
		};
	}

	@Override
	public void close() throws IOException {
		InputStream in = this.mIn;
		if (in != null) {
			in.close();
		}
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream in = this.mIn;
		if (in == null) {
			in = new FileInputStream(this.mFile);
			this.mIn = in;
		}
		return in;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getLength() {
		return this.mFile.length();
	}

	@Override
	public String getEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

}
