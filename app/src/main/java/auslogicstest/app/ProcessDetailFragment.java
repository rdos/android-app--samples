package auslogicstest.app;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProcessDetailFragment extends Fragment {

    private int mProcessId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_process_detail, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mProcessId = getActivity().getIntent().getIntExtra("processId", 0);

        TextView textViewName = (TextView) getView().findViewById(R.id.text_process_name);
        TextView textViewBattery = (TextView) getView().findViewById(R.id.text_process_battery);
        TextView textViewMemory = (TextView) getView().findViewById(R.id.text_process_memory);
        ImageView imageViewIcon = (ImageView) getView().findViewById(R.id.image_process_icon);
        Button btnGoToProcessSettting = (Button) getView().findViewById(R.id.btn_go_to_process_setting);
        Button btnShowMaps = (Button) getView().findViewById(R.id.btn_show_maps);

        textViewName.setText(App.processMan.getAppName(mProcessId));
        textViewBattery.setText(App.processMan.getBattery(mProcessId));
        textViewMemory.setText(App.processMan.getMemory(mProcessId));
        imageViewIcon.setImageDrawable(App.processMan.getIcon(mProcessId));

        btnGoToProcessSettting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.processMan.showSetting(getActivity(), mProcessId);
            }
        });
        btnShowMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
