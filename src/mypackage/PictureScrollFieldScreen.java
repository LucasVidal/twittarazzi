package mypackage;


import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.extension.component.PictureScrollField.ScrollEntry;
import net.rim.device.api.ui.text.TextFilter;

public class PictureScrollFieldScreen extends MainScreen implements FieldChangeListener
{
	private static final String[] urls = {	//"image1.jpg",
											//"image2.jpg",
											//"image3.jpg",
											//"image4.jpg",
											//"image5.jpg",
											//"image6.jpg"
											"http://192.168.1.21/image1.jpg",
											"http://192.168.1.21/image2.jpg",
											"http://192.168.1.21/image3.jpg",
											"http://192.168.1.21/image4.jpg",
											"http://192.168.1.21/image5.jpg",
											"http://192.168.1.21/image6.jpg"
											//"http://yfrog.com/kfp3y9j",
											//"http://yfrog.com/h05zme5j",
											//"http://yfrog.com/h3kxa1kj",
											//"http://img.ly/4k8l",
											//"http://yfrog.com/h2dnhc1j",
											//"http://yfrog.com/h6ackmj",
											//"http://yfrog.com/h067991723j",
											//"http://yfrog.com/h8w9w2j",
											//"http://yfrog.com/hsod24j"
											//"http://twitpic.com/3nqc39",
											//"http://twitpic.com/2si0wj",
											//"http://twitpic.com/2r2hve",
											//"http://twitpic.com/2dfj0u",
											//"http://twitpic.com/2b0lcq",
											//"http://twitpic.com/29sodk"
											};

    
    private int index=1;
    
	private WebPictureScrollField pictureScrollField;
	private ButtonField loadNextBtn;
	private TextField textField;


	private ButtonField viewTweetsBtn;
	
    public PictureScrollFieldScreen()
    {             
        setTitle("Picture Scroll Field Demo");
    	
        pictureScrollField = new WebPictureScrollField(250, 167);

        loadNextBtn = new ButtonField( "Refresh" , ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER );
        loadNextBtn.setChangeListener(this);
        
        viewTweetsBtn = new ButtonField( "View tweets" , ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER );
        viewTweetsBtn.setChangeListener(this);
        
        textField = new TextField(TextField.FIELD_HCENTER | TextField.FIELD_VCENTER);
        textField.setText("no text");
        
        add(pictureScrollField);

        add(loadNextBtn);
        add(viewTweetsBtn);
        
        add(textField);
    }
    
    //listener general.
    public void fieldChanged( Field field , int context){
    	if (field.equals(loadNextBtn))	{
    		loadNewPicture();
    	}
    	if (field.equals(viewTweetsBtn)){
    		loadTweets();
    	}
    }

	private void loadTweets() {
		
	}

	private void loadNewPicture() {
		index++;
		if (index>=urls.length) index=0;
		pictureScrollField.addBitmapFromUrl(urls[index]);
	}

	


}