package impl.ananas.lib.io;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

import ananas.lib.io.Connection;
import ananas.lib.io.ConnectionFactory;

public class DefaultHttpsConnection extends DefaultHttpConnection {

	private DefaultHttpsConnection(URI uri) throws MalformedURLException,
			IOException {
		super(uri);
	}

	public static ConnectionFactory getFactory() {
		return new ConnectionFactory() {

			@Override
			public Connection openConnection(URI uri) throws IOException {
				return new DefaultHttpsConnection(uri);
			}
		};
	}

}
