package com.hoangsv.lolguide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TruyenThuyetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TruyenThuyetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TruyenThuyetFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    final String DATABASE_NAME="vn_vn.sqlite";
    SQLiteDatabase database;

    TextView txtTruyenThuyet,txtTruyenThuyetND;
    //WebView webView;

    private OnFragmentInteractionListener mListener;

    public TruyenThuyetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TruyenThuyetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TruyenThuyetFragment newInstance(String param1, String param2) {
        TruyenThuyetFragment fragment = new TruyenThuyetFragment();
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
        View v = inflater.inflate(R.layout.fragment_truyen_thuyet, container, false);


        //webView= (WebView) v.findViewById(R.id.webview);
        txtTruyenThuyet= (TextView) v.findViewById(R.id.txtTruyenThuyet);
        txtTruyenThuyetND= (TextView) v.findViewById(R.id.txtTruyenThuyetND);

        addEvents();
        return v;
    }

    private void addEvents() {
        xuLyLayThongTin();
    }

    private void xuLyLayThongTin() {
        Bundle bundle=getArguments();
        String lore1=bundle.getString("lore1");

        txtTruyenThuyetND.setText(Html.fromHtml(lore1));

        /*bundle
        String lore2="<html><body>" + "<p style=\"color:#FF0000\" align=\"justify\">" +lore1+ "</p> " + "</body></html>";
        webView.loadData(lore1,"text/html;charset=utf-8", "utf-8");*/

        /*Xu ly bang cach rut ra tu database
        database = Database.initDatabase(getActivity(),DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM champion WHERE id=?",new String[]{id1});
        cursor.moveToFirst();
        String lore = cursor.getString(18);
        String lore1="<html><body>" + "<p style=\"color:#FF0000\" align=\"justify\">" +lore+ "</p> " + "</body></html>";
        webView.loadData(lore1,"text/html;charset=utf-8", "utf-8");*/


        /*Bundle bundle=getArguments();
        String id1=bundle.getString("id1");
        String lore1=bundle.getString("lore1");
        txtTruyenThuyet.setText(Html.fromHtml(lore1));*/
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
}
