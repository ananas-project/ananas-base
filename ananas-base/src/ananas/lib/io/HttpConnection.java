package ananas.lib.io;

import java.io.IOException;

public interface HttpConnection extends ContentConnection,  HttpStatus {

	// / request

	void setRequestMethod(String method);

	void setRequestHeader(String name, String value);

	// / response

	String getResponseHeader(String name);

	String getResponseMessage() throws IOException;

	int getResponseCode() throws IOException;

}
