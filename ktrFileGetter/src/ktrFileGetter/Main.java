package ktrFileGetter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {

	public static void main(String[] args) {
		System.out.println("Ktr file getter :\n");
		
		Main main = new Main();
		try {
			main.download(new URL("http://localhost:8080/base/helloworld"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void download(URL url) throws IOException {
	    URLConnection uc = url.openConnection();
	    int len = uc.getContentLength();
	    InputStream is = new BufferedInputStream(uc.getInputStream());
	    try {
	        byte[] data = new byte[len];
	        int offset = 0;
	        while (offset < len) {
	            int read = is.read(data, offset, data.length - offset);
	            if (read < 0) {
	                break;
	            }
	          offset += read;
	        }
	        if (offset < len) {
	            throw new IOException(
	                String.format("Read %d bytes; expected %d", offset, len));
	        }
	        System.out.println( new String (data));
	    } finally {
	        is.close();
	    }
	}
}
