package mypackage;

import net.rim.device.api.ui.UiApplication;

public class PictureScrollField extends UiApplication
{
    public static void main(String[] args)
    {
        PictureScrollField theApp = new PictureScrollField();       
        theApp.enterEventDispatcher();
    }
    
    public PictureScrollField()
    {        
        pushScreen(new PictureScrollFieldScreen());
    }    
}

