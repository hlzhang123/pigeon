package payne.framework.pigeon.core.formatting;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import payne.framework.pigeon.core.exception.FormatterException;
import payne.framework.pigeon.core.toolkit.IOToolkit;

public class FormInvocationFormatter extends URLInvocationFormatter implements InvocationFormatter {

	public String algorithm() {
		return "application/x-www-form-urlencoded";
	}

	public Object deserialize(Structure structure, InputStream in, String charset) throws FormatterException {
		try {
			String parameters = IOToolkit.toString(in);
			parameters = URLDecoder.decode(parameters, Charset.defaultCharset().name());
			in = new ByteArrayInputStream(parameters.getBytes(Charset.defaultCharset().name()));
			return super.deserialize(structure, in, charset);
		} catch (IOException e) {
			throw new FormatterException(e, this, in, structure);
		}
	}

}
