package mypackage;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.component.BitmapField;

public class WebBitmapField extends BitmapField implements WebDataCallback  
{  
    private EncodedImage bitmap = null;  
  
    public WebBitmapField(String url)  
    {  
        try  
        {  
            Utils.getWebData(url, this);  
        }  
        catch (Exception e) {}  
    }  
  
    public Bitmap getBitmap()  
    {  
        if (bitmap == null) return null;  
        return bitmap.getBitmap();  
    }  
  
    public void callback(final String data)  
    {  
        if (data.startsWith("Exception")) return;  
  
        try  
        {  
            byte[] dataArray = data.getBytes();  
            bitmap = EncodedImage.createEncodedImage(dataArray, 0, dataArray.length);
            setImage(bitmap);
        }  
        catch (final Exception e){}  
    }  
}  
