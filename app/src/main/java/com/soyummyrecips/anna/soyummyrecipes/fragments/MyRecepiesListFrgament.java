package com.soyummyrecips.anna.soyummyrecipes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.soyummyrecips.anna.soyummyrecipes.R;
import com.soyummyrecips.anna.soyummyrecipes.Utils.Utils;
import com.soyummyrecips.anna.soyummyrecipes.adapters.MyRecepieListAdapter;
import com.soyummyrecips.anna.soyummyrecipes.adapters.RecipeNewListAdapter;
import com.soyummyrecips.anna.soyummyrecipes.pojo.UserList;
import com.soyummyrecips.anna.soyummyrecipes.serverconnection.APIClient;
import com.soyummyrecips.anna.soyummyrecipes.serverconnection.APIInterface;

public class MyRecepiesListFrgament extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    APIInterface apiInterface;
    UserList eventResponseList;
    private RecyclerView recyclerView;
    private MyRecepieListAdapter mAdapter;
    Context mContext;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        ////////////////////////////////////////////////////////////

        apiInterface = APIClient.getClient().create(APIInterface.class);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myrecipe, container, false);

        Gson gson = new Gson();
        String list = Utils.readAssetsValues(getActivity());
        // Log.e("aaaaaaaaaaaaa","e "+Utils.readAssetsValues(getActivity()));
        eventResponseList = gson.fromJson(list, UserList.class);
        //  Log.e("aaaaaaaaaaaaa","e "+eventResponseList);

        recyclerView = (RecyclerView) view.findViewById(R.id.mainrecipielist);
        onCreateAdapter();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private  void onCreateAdapter(){

        mAdapter = new MyRecepieListAdapter(eventResponseList.data);
        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
    }
}
