package auslogicstest.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.text.format.Formatter;
import android.util.Log;

import java.util.List;

final class ProcessMan {

    private final Context mContext;
    private final PackageManager mPackageManager;
    private final ActivityManager mActivityManager;
    private List<ActivityManager.RunningAppProcessInfo> mProcessInfoList;

    public ProcessMan(Context context) {
        super();
        mContext = context;
        mPackageManager = context.getPackageManager();
        mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        try {
            mProcessInfoList = mActivityManager.getRunningAppProcesses();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ttest", e.getMessage());
        }
    }

    public int getProcessCount() {
        return mProcessInfoList.size();
    }

    public String getAppLable(int processId) {
        String result;
        try {
            ApplicationInfo applicationInfo = mPackageManager.getApplicationInfo(getPackageName(processId), 0);
            result = (String) mPackageManager.getApplicationLabel(applicationInfo);
        } catch (Exception e) {
            result = "Unknown";
        }
        return result;
    }

    public String getAppName(int processId) {
        return getAppLable(processId);
    }

    public String getPackageName(int processId) {
        return mProcessInfoList.get(processId).processName;
    }

    public int getPID(int processId) {
        return mProcessInfoList.get(processId).pid;
    }
    //CpuUsageInfo
    public String getBattery(int processId) {
        return String.valueOf(android.os.Debug.threadCpuTimeNanos());
    }

    public String getMemory(int processId) {
        android.os.Debug.MemoryInfo[] memoryInfoArray = mActivityManager.getProcessMemoryInfo(new int[]{this.getPID(processId)});
        return Formatter.formatFileSize(mContext, memoryInfoArray[0].getTotalPss() * 1024);
    }

    public Drawable getIcon(int processId) {
        Drawable resultDrawable;
        try {
            resultDrawable = mPackageManager.getApplicationIcon(this.getPackageName(processId));
        } catch (Exception e) {
            resultDrawable = mContext.getResources().getDrawable(R.drawable.ic_launcher);
        }
        return resultDrawable;
    }


    public void showSetting(Activity activity, int processId) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + this.getPackageName(processId)));

        activity.startActivity(intent);
    }

//    // Присваиваем значок
//    String processName = packageManager.getNameForUid(processInfo.get(position).uid);
//    String packageName = processInfo.get(position).processName;
//    Drawable processIcon =
//
//        Log.d("ttest", "processName=" + processName);
//        Log.d("ttest", "packageName=" + packageName);
//        Log.d("ttest", "position=" + String.valueOf(position));
//        iconImageView.setImageDrawable(processIcon);
//        processNameTextView.setText(processName);





    /*
    ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
activityManager.getMemoryInfo(memoryInfo);

Log.i(TAG, " memoryInfo.availMem " + memoryInfo.availMem + "\n" );
Log.i(TAG, " memoryInfo.lowMemory " + memoryInfo.lowMemory + "\n" );
Log.i(TAG, " memoryInfo.threshold " + memoryInfo.threshold + "\n" );

List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();

Map<Integer, String> pidMap = new TreeMap<Integer, String>();
for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses)
{
    pidMap.put(runningAppProcessInfo.pid, runningAppProcessInfo.processName);
}

Collection<Integer> keys = pidMap.keySet();

for(int key : keys)
{
    int pids[] = new int[1];
    pids[0] = key;
    android.os.Debug.MemoryInfo[] memoryInfoArray = activityManager.getProcessMemoryInfo(pids);
    for(android.os.Debug.MemoryInfo pidMemoryInfo: memoryInfoArray)
    {
        Log.i(TAG, String.format("** MEMINFO in pid %d [%s] **\n",pids[0],pidMap.get(pids[0])));
        Log.i(TAG, " pidMemoryInfo.getTotalPrivateDirty(): " + pidMemoryInfo.getTotalPrivateDirty() + "\n");
        Log.i(TAG, " pidMemoryInfo.getTotalPss(): " + pidMemoryInfo.getTotalPss() + "\n");
        Log.i(TAG, " pidMemoryInfo.getTotalSharedDirty(): " + pidMemoryInfo.getTotalSharedDirty() + "\n");
    }
}
     */
}
