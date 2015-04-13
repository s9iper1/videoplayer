package byteshaft.com.recorder;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends Activity implements ListView.OnItemClickListener {

    private VideoOverlay overlay;

    SurfaceView display;
    SlidingDrawer sd;
    LinearLayout li;
    String strDate;
    static String filePath;
    Helpers mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper = new Helpers(this);
        allReferences();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strDate = sdf.format(c.getTime());
        File folder = new File(Environment.getExternalStorageDirectory() + "/Recordings");
        if (!folder.exists()) {
            folder.mkdir();
        }
        overlay = new VideoOverlay(getApplicationContext());
        filePath = folder + "/" + "Video" + ".mp4";

        ListView list = mHelper.getListView();
        li.addView(list);
        if (Helpers.listDisplay = false) {
            li.addView(list);
            System.out.println("false");
        } else if (Helpers.listDisplay = true) {
            System.out.println("true");
        }
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String videoName = parent.getItemAtPosition(position).toString();
        getSelectedFromStorage(videoName);
    }

    private void getSelectedFromStorage(String item) {
        File[] getVideos;
        File folder = new File(Environment.getExternalStorageDirectory() + "/Recordings");
        getVideos = folder.listFiles();
        for (File file : getVideos) {
            if (file.getName().equals(item)) {
                String filename = file.getAbsolutePath();
                Intent intent = new Intent(getApplicationContext(), VideoPlayer.class);
                intent.putExtra("videoUri", filename);
                startActivity(intent);
                break;
            }
        }
    }

    private void allReferences() {
        sd = (SlidingDrawer) findViewById(R.id.slidingDrawer);
        li = (LinearLayout) findViewById(R.id.content);
    }

//    private void startPlayback() {
//        overlay.setVideoFile(filePath);
//        overlay.setVideoStartPosition(seekPosition);
//        overlay.startPlayback();
//    }
}
