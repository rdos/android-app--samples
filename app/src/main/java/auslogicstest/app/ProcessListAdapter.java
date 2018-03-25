package auslogicstest.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProcessListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final int mLayoutListItemId;

    public ProcessListAdapter(Context context, int layoutListItemId) {
        super();
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayoutListItemId = layoutListItemId;
    }

    @Override
    public int getCount() {
        return App.processMan.getProcessCount();
    }

    @Override
    public Object getItem(int position) {
        //TODO: add Model?
        return App.processMan.getAppName(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = mInflater.inflate(mLayoutListItemId, parent, false);

        TextView appNameTextView = (TextView) listItemView.findViewById(R.id.text_app_name_list);
        TextView packageNameTextView = (TextView) listItemView.findViewById(R.id.text_package_name_list);
        ImageView iconImageView = (ImageView) listItemView.findViewById(R.id.image_icon_list);

        appNameTextView.setText(App.processMan.getAppName(position));
        packageNameTextView.setText(App.processMan.getPackageName(position));
        iconImageView.setImageDrawable(App.processMan.getIcon(position));
        return listItemView;
    }

}