package mypackage;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import net.rim.device.api.ui.UiApplication;

public class Utils {

	public static void getWebData(final String url,
			final WebDataCallback callback) throws IOException {
		Thread t = new Thread(new Runnable() {
			public void run() {
				HttpConnection connection = null;
				InputStream inputStream = null;

				try {
					//connection = (HttpConnection) Connector.open(ConnectionManager.getConnection(url),Connector.READ, true);
					connection = (HttpConnection) Connector.open(url,Connector.READ, true);
					inputStream = connection.openInputStream();
					byte[] responseData = new byte[10000];
					int length = 0;
					StringBuffer rawResponse = new StringBuffer();
					while (-1 != (length = inputStream.read(responseData))) {
						rawResponse.append(new String(responseData, 0, length));
					}
					int responseCode = connection.getResponseCode();
					if (responseCode != HttpConnection.HTTP_OK) {
						throw new IOException("HTTP response code: "+ responseCode);
					}

					final String result = rawResponse.toString();
					UiApplication.getUiApplication().invokeLater(
							new Runnable() {
								public void run() {
									callback.callback(result);
								}
							});
				} catch (final Exception ex) {
					UiApplication.getUiApplication().invokeLater(
							new Runnable() {
								public void run() {
									callback.callback("Exception ("
											+ ex.getClass() + "): "
											+ ex.getMessage());
								}
							});
				} finally {
					try {
						inputStream.close();
						inputStream = null;
						connection.close();
						connection = null;
					} catch (Exception e) {
					}
				}
			}
		});
		t.start();
	}
}