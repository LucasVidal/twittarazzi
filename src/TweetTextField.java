import mypackage.WebDataCallback;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.ui.component.TextField;


public class TweetTextField extends TextField implements WebDataCallback{

	public void callback(String data) {
		this.setText(data);
	}

	
	public Locale getPreferredInputLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTextInputStyle() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isUnicodeInputAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void updateInputStyle() {
		// TODO Auto-generated method stub
		
	}

	
}
