package auslogicstest.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ProcessListFragment extends ListFragment {
    
    // определяем массив типа String
    final String[] mCatNames = new String[]{"Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Томасина", "Кристина", "Пушок", "Дымка",
            "Кузя", "Китти", "Масяня", "Симба"};

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProcessListAdapter processListAdapter = new ProcessListAdapter(getContext(),
                R.layout.fragment_process_list_item, mCatNames);
        setListAdapter(processListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_process_list, null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
//        startActivity();
        Toast.makeText(getActivity(),
                getListView().getItemAtPosition(position).toString(),
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("selectedValue", '1');
        startActivity(intent);
    }

    public class ProcessListAdapter extends ArrayAdapter<String> {

        private Context mContext;

        private ProcessListAdapter(Context context, int textViewResourceId,
                                  String[] objects) {
            super(context, textViewResourceId, objects);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // return super.getView(position, convertView, parent);

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View listItem = inflater.inflate(R.layout.fragment_process_list_item, parent,
                    false);
            TextView processNameTextView = (TextView) listItem.findViewById(R.id.textViewName);
           
            ImageView iconImageView = (ImageView) listItem.findViewById(R.id.imageViewIcon);
            processNameTextView.setText(App.processMan.getProcessName(position));
            return listItem;
        }
    }
}
