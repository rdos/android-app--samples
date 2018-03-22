package auslogicstest.app;

import android.util.Log;

final class ProcessMan {

    private void getProcessInfo() {
        Log.d("aa", "aa");
    }


    public String getProcessName(int processUID) {
        return String.valueOf(processUID);
    }

//    PackageManager packageManager = getActivity().getPackageManager();
//    ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
//            try {
//        List<ActivityManager.RunningAppProcessInfo> processInfo = activityManager.getRunningAppProcesses();
//        // Присваиваем значок
//        String processName = packageManager.getNameForUid(processInfo.get(position).uid);
//        String packageName = processInfo.get(position).processName;
//        Drawable processIcon = packageManager.getApplicationIcon(processInfo.get(position).processName);
//
//        Log.d("ttest", "processName=" + processName);
//        Log.d("ttest", "packageName=" + packageName);
//        Log.d("ttest", "position=" + String.valueOf(position));
//        iconImageView.setImageDrawable(processIcon);
//        processNameTextView.setText(processName);
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        Log.d("ttest", e.getMessage());
//    }
}
