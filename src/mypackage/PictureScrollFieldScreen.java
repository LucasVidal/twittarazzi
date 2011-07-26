package mypackage;


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.extension.component.PictureScrollField;
import net.rim.device.api.ui.extension.component.PictureScrollField.HighlightStyle;
import net.rim.device.api.ui.extension.component.PictureScrollField.ScrollEntry;

public class PictureScrollFieldScreen extends MainScreen implements FieldChangeListener
{
	private static final String[] urls = {	"http://yfrog.com/kfp3y9j",
											"http://yfrog.com/h05zme5j",
											"http://yfrog.com/h3kxa1kj",
											//"http://img.ly/4k8l",
											"http://yfrog.com/h2dnhc1j",
											"http://yfrog.com/h6ackmj",
											"http://yfrog.com/h067991723j",
											"http://yfrog.com/h8w9w2j",
											"http://yfrog.com/hsod24j"
											//"http://twitpic.com/3nqc39",
											//"http://twitpic.com/2si0wj",
											//"http://twitpic.com/2r2hve",
											//"http://twitpic.com/2dfj0u",
											//"http://twitpic.com/2b0lcq",
											//"http://twitpic.com/29sodk"
											};
	
    private final String sufix =";deviceside=true;interface=wifi";
    private final String yFrogSize = ":medium";
    
    private int index=0;
    
	private ScrollEntry[] entries ;
	private PictureScrollField pictureScrollField;
	
    public PictureScrollFieldScreen()
    {             
    	entries = initializeEntries();
        
        pictureScrollField = new PictureScrollField(250, 167);
        pictureScrollField.setData(entries, 0);
        pictureScrollField.setHighlightStyle(HighlightStyle.ILLUMINATE_WITH_SHRINK_LENS);
        pictureScrollField.setHighlightBorderColor(Color.BLUE);
		
        pictureScrollField.setBackground(BackgroundFactory.createSolidTransparentBackground(Color.BLACK, 150));

        pictureScrollField.setLabelsVisible(true); 
        add(pictureScrollField);
        
        ButtonField bf = new ButtonField( "Refresh" , ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER );
        bf.setChangeListener(this);
        add(bf);
    }
    
    public void fieldChanged( Field field , int context){
    	Dialog.inform("Trying to fetch\n"+urls[index]+yFrogSize+sufix);
    	entries=addNewPicture(entries,urls[index]+yFrogSize+sufix);
    	pictureScrollField.setData(entries, index);
    }

    private ScrollEntry[] addNewPicture(ScrollEntry[] entries_old, String url) {
    	WebBitmapField wbf = new WebBitmapField(url);
    	this.add(wbf);
        return entries_old;
	}
    
	private ScrollEntry[] initializeEntries() {
        setTitle("Picture Scroll Field Demo");
        
        Bitmap[] images = new Bitmap[1];
        String[] labels = new String[1];
        String[] tooltips = new String[1];
        ScrollEntry[] entries = new ScrollEntry[1];
        
        for (int i = 0; i < entries.length; i++)
        {
        	images[i] = Bitmap.getBitmapResource("not_available.jpg");
        	labels[i] = "Image Not Available";
        	tooltips[i] = "Image Not Available";
        }
    

        for (int i = 0; i < entries.length; i++) 
        { 
             entries[i] = new ScrollEntry(images[i], labels[i],tooltips[i]);
        }
        return entries;
	}


}