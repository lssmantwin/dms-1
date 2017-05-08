package com.dms.export;

import java.io.IOException;
import java.io.InputStream;

public interface StreamSource {

	InputStream getStream() throws IOException;

}
