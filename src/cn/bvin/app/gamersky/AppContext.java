package cn.bvin.app.gamersky;

import android.app.Application;
import android.content.ContextWrapper;

public class AppContext extends Application{

  private static AppContext mInstance;
  
  public static ContextWrapper getInstance() {
    return mInstance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    mInstance = this;
  }

  
  
}
