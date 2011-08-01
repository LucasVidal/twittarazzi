package mypackage;

import net.rim.device.api.system.EventLogger;
import net.rim.device.api.ui.UiApplication;

public class PictureScrollField extends UiApplication
{
    public static void main(String[] args)
    {
        PictureScrollField theApp = new PictureScrollField();       
        EventLogger.register(0x4c9d3452d87922f2L, "Twittarazzi", EventLogger.VIEWER_STRING);
        theApp.enterEventDispatcher();
    }
    
    public PictureScrollField()
    {        
        pushScreen(new PictureScrollFieldScreen());
    }    
}

