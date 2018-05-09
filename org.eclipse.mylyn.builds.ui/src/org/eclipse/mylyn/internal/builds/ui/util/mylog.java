package org.eclipse.mylyn.internal.builds.ui.util;

import java.io.*;
import org.eclipse.core.runtime.Platform;

public class mylog  {

  FileWriter writer = null;
  String pluging_id = "";
  
  public mylog(String pluging){pluging_id = pluging; }
  public void log(String content)
  {
       try {  
  	          writer = new FileWriter( Platform.getLocation().toOSString() + "/.metadata/.mylog", true ); 
              writer.write(pluging_id + ":" + content+"\r\n");  
              writer.close();  
           } catch (IOException e1) {  
              e1.printStackTrace();  
           }        
  	 return;
  }
}