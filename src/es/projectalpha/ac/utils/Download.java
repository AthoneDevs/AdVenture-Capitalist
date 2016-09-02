package es.projectalpha.ac.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import es.projectalpha.ac.files.Files;

public class Download {

	private String link = "https://github.com/ProjectAlphaES/AdVenture-Capitalist/blob/master/build.schematic?raw=true";
	private String fileName = "build.schematic";

	public void downloadSchematic(){
		try{
			URL url = new URL(link);
			URLConnection c = url.openConnection();
			c.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)");

			InputStream input;
			input = c.getInputStream();
			byte[] buffer = new byte[4096];
			int n = -1;

			OutputStream output = new FileOutputStream(new File(Files.fileSchema, fileName));
			while((n = input.read(buffer)) != -1){
				if(n > 0){
					output.write(buffer, 0, n);
				}
			}
			output.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
