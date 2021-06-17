package org.tensorflow.lite.examples.detection.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.tensorflow.lite.examples.detection.R;
import org.tensorflow.lite.examples.detection.logic.Board;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MakeMoveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MakeMoveFragment extends Fragment {

    //View
    private View v;
    private TextView moveFrom;
    private TextView moveTo;
    private ImageView cardOrg;
    private ImageView cardDest;
    private ImageView cardOrgRes;
    private ImageView cardDestRes;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MakeMoveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MakeMoveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MakeMoveFragment newInstance(String param1, String param2) {
        MakeMoveFragment fragment = new MakeMoveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.solitaire_solution_move, container, false);
        initialize(v);
        /* Set card pictures and names
        moveFrom.setText(card1.getCardname());
        moveTo.setText(card2.getCardname();

        */
        return v;

    }

    public void initialize(View v){
        moveFrom = v.findViewById(R.id.card_origin);
        moveTo = v.findViewById(R.id.card_destination);
        cardOrg = v.findViewById(R.id.card_origin_img);
        cardDest = v.findViewById(R.id.card_destination_img);
        cardOrgRes = v.findViewById(R.id.card_origin_img_result);
        cardDestRes = v.findViewById(R.id.card_destination_img_result);
    }
}