package org.ebeijia.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Text4j {

	public static String getTextContent(String url, String fileName) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bufr = null;
		try {
			fis = new FileInputStream(url + fileName + ".txt");
			String content = null;
			if (fis != null) {
				isr = new InputStreamReader(fis, "UTF-8");
				bufr = new BufferedReader(isr);
				content = bufr.readLine();
			}
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (bufr != null)bufr.close();
				if (isr != null)isr.close();
				if (fis != null)fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void writeTextContent(String url, String fileName, String content) {
		OutputStreamWriter osw = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(url + fileName + ".txt");
			osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fos != null){fos.close();}
				if (osw != null) {
					osw.flush();
					osw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}