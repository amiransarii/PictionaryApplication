package org.game.pictionary.util;
import android.util.Log;

import org.game.pictionary.constant.DebugMode;

import java.io.Serializable;

import static org.game.pictionary.constant.DebugMode.DEBUG_FLG;

/**
 * <code>LogUtils</code> class is a Log-related utility class
  @author amir.ansari
 */

public class LogUtils implements Serializable {

    private boolean isErrorLog = false;

    /**
     * SerialVersionUID
     */
      //initialize the tag
    private String tag;

    //check log message need to show or not
    private boolean isShowInfoLog;

    /**
     * private constructor
     */
    private LogUtils(){

    }


    /**
     *
     * @param tag is the activity name
     * @param msg the error message to display
     */
    public static void v(String tag, Object msg){
           if(DEBUG_FLG){
               Log.v(tag,msg == null ? "" : msg.toString());
           }
    }

    /**
     *
     * @param tag activity name
     * @param msg error message to be displayed
     * @param tr to handle exception
     */
      public static void  v(String tag , Object msg, Throwable tr){
          if (DEBUG_FLG){
              Log.v(tag,msg == null ? "": msg.toString(),tr);
          }
      }

    /**
     *
     * @param tag activity name
     * @param msg  error message to be displayed.
     */
       public static void  d(String tag , Object msg) {
           if (DEBUG_FLG) {
               Log.d(tag,msg == null ? " ":msg.toString());

           }
       }

       public static void  d(String tag, Object msg, Throwable tr){
           if(DEBUG_FLG){
               Log.d(tag,msg == null ? "":msg.toString(),tr);
           }
       }

    public static void i(String tag, Object msg, Throwable tr) {
        if (DEBUG_FLG) {
            Log.i(tag, msg == null ? "" : msg.toString(), tr);
        }
    }

    public static void w(String tag, Object msg) {
        if (DEBUG_FLG) {
            Log.w(tag, msg == null ? "" : msg.toString());
        }
    }

    public static void w(String tag, Object msg, Throwable tr) {
        if (DEBUG_FLG) {
            Log.w(tag, msg == null ? "" : msg.toString(), tr);
        }
    }

    public static void e(String tag, Object msg) {
        if (DEBUG_FLG) {
            Log.e(tag, msg == null ? "" : msg.toString());
        }
    }

    public static void e(String tag, Object msg, Throwable tr) {
        if (DEBUG_FLG) {
            Log.e(tag, msg == null ? "" : msg.toString(), tr);
        }
    }

}
