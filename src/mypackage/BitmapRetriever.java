package mypackage;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.component.BitmapField;

public class BitmapRetriever extends BitmapField {
	
	public static final String pictureNotAvailable = "not_available.jpg";
	
	public static Bitmap connectServerForImage(String url) {
		byte[]  bitmap = null;//Utilities.getImage(url);
		
		HttpConnection httpConnection = null;
		InputStream inputStream = null;
		try {
			httpConnection = (HttpConnection) Connector.open(url, Connector.READ_WRITE);
			httpConnection.setRequestMethod(HttpConnection.GET);
			StringBuffer b = new StringBuffer();
			int lon = 0;
			int ch = 0;			
			int responseCode = httpConnection.getResponseCode();
			
			if (responseCode == HttpConnection.HTTP_OK) {
				inputStream = httpConnection.openInputStream();
				lon = (int) httpConnection.getLength();
				if (lon != -1) {
					for(int i = 0 ; i < lon ; i++ ) {
						if((ch = inputStream.read()) != -1) {
							b.append((char) ch);
						}
					}
					
				}  else {
					//Read until the connection is closed.
					while ((ch = inputStream.read()) != -1) {
						lon = inputStream.available() ;
						b.append((char)ch);
					}
				}
				bitmap = b.toString().getBytes();
				
			}
		} catch (Throwable e){
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (httpConnection != null) {
					httpConnection.close();
				}
			} catch (IOException e) {
				System.out.println("IOException: Closing conexion"
						+ e.getMessage());
			}
		}
		
		Bitmap resultBitmap=null;
		if (bitmap==null){
			resultBitmap= Bitmap.getBitmapResource(BitmapRetriever.pictureNotAvailable);
		}else{
			resultBitmap= Bitmap.createBitmapFromBytes(bitmap, 0, bitmap.length, 1);
		}
		return resultBitmap;
	}
}
