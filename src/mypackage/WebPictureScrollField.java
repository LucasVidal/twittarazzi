package mypackage;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.extension.component.PictureScrollField;
import net.rim.device.api.ui.extension.component.PictureScrollField.HighlightStyle;
import net.rim.device.api.ui.extension.component.PictureScrollField.ScrollEntry;

public class WebPictureScrollField extends PictureScrollField implements WebDataCallback{

	private ScrollEntry[] entries ;

    private final String sufix =";deviceside=true;interface=wifi";
    private final String yFrogSize = "";
	
	public WebPictureScrollField(int imageWidth,int imageHeight){
		super(imageWidth, imageHeight);
		entries=initializeEntries();
		this.setData(entries, 0);

    	this.setHighlightStyle(HighlightStyle.ILLUMINATE_WITH_SHRINK_LENS);
    	this.setHighlightBorderColor(Color.BLUE);
    	this.setBackground(BackgroundFactory.createSolidTransparentBackground(Color.BLACK, 150));
    	this.setLabelsVisible(true); 
  	}
	
	public WebPictureScrollField(int imageWidth,int imageHeight, ScrollEntry[] start_entries){
		super(imageWidth, imageHeight);
		this.setData(start_entries, 0);

    	this.setHighlightStyle(HighlightStyle.ILLUMINATE_WITH_SHRINK_LENS);
    	this.setHighlightBorderColor(Color.BLUE);
    	this.setBackground(BackgroundFactory.createSolidTransparentBackground(Color.BLACK, 150));
    	this.setLabelsVisible(true); 
	}
	
	private ScrollEntry[] initializeEntries() {
		ScrollEntry[] e=new ScrollEntry[1];
		e[0]=new ScrollEntry(Bitmap.getBitmapResource("not_available.jpg"),"label 0","tooltip 0");
		return e;
	}

	public void addBitmapFromUrl(String url){
	    try  
	    {  
	        Utils.getDataFromUrl(url+sufix, this);  
	    }  
	    catch (Exception e) {}
	}
	
	public void callback(String data) {


		
	   if (data.startsWith("Exception")) return;  
 
        try  
        {  
            byte[] dataArray = data.getBytes();  
            EncodedImage bitmap = EncodedImage.createEncodedImage(dataArray, 0, dataArray.length);
            addBitmap(bitmap);
        }  
        catch (final Exception e){}  
	}


	private void addBitmap(EncodedImage bitmap) {
		this.entries=addNewEntry(bitmap);
		this.setData(entries, this.getPictureCount()-1);
		this.getScreen().doPaint();
	}
	
	private ScrollEntry[] addNewEntry(EncodedImage bitmap) {

        ScrollEntry[] old_entries=entries;
        int count=getPictureCount()+1;
        
        Bitmap[] images = new Bitmap[count];
        String[] labels = new String[count];
        String[] tooltips = new String[count];
        this.entries = new ScrollEntry[count];
        
        //copying old entries
        for (int i = 0; i < count-1; i++)
        {
        	images[i] = old_entries[i].getPicture();
        	labels[i] = old_entries[i].getLabel();
        	tooltips[i] = old_entries[i].getCallout();

        	entries[i] = new ScrollEntry(images[i], labels[i],tooltips[i]);
        }
        
        //adding new bitmap
        images[count-1]=bitmap.getBitmap();
        labels[count-1]="Image "+(count-1);
        tooltips[count-1]="Image "+(count-1);
        this.entries[count-1]=new ScrollEntry(images[count-1],labels[count-1],tooltips[count-1]);

        return entries;
	}

	public int getPictureCount() {
		return this.entries.length;
	}

	public ScrollEntry[] getEntries() {
		return this.entries;
	}
}
