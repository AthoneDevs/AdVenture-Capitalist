package es.projectalpha.ac.utils;

import es.projectalpha.ac.AVC;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

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

			OutputStream output = new FileOutputStream(new File(AVC.getInstance().getFiles().getFUtils(), fileName));
			while((n = input.read(buffer)) != -1){
				if(n > 0) output.write(buffer, 0, n);
			}
			output.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
