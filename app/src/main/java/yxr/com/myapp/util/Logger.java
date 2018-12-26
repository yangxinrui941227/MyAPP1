package yxr.com.myapp.util;

import android.util.Log;

import java.util.logging.Level;

public class Logger {
    private static final int LEVEL = 1;

    private static final int verbose = 1;
    private static final int debug = 2;
    private static final int info = 3;
    private static final int warnning = 4;
    private static final int error = 5;

    public static void log(String tag,String msg){
        if (LEVEL == verbose){
            Log.v(tag,msg);
        }else if (LEVEL==debug){
            Log.d(tag,msg);
        }else if (LEVEL == info){
            Log.i(tag, msg);
        }else if (LEVEL == warnning){
            Log.w(tag, msg);
        }else if (LEVEL == error){
            Log.e(tag, msg);
        }
    }

}
