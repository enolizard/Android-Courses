package by.yakivan.starbuzz.Utils;

import android.content.Context;
import android.widget.Toast;

public class Toaster {
    public static void toastLong(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
