package com.hoangsv.lolguide;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TongQuanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TongQuanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TongQuanFragment extends Fragment {

    ImageView imgChampionTQ, imgAnhNT, imgAnhQ, imgAnhW, imgAnhE, imgAnhR;
    TextView txtChampionNameTQ,txtChampionTitleTQ, txtChampionTagsTQ, txtHP, txtHPRegen, txtMP, txtMPRegen, txtArmor,
            txtSpellBlock, txtAttackDamage, txtAttackSpeed, txtAttackRange, txtCrit, txtMoveSpeed, txtTenNT, txtPhimTatNT,
            txtNoiDungNT, txtTenQ, txtTenW, txtTenE, txtTenR, txtPhimTatQ, txtPhimTatW, txtPhimTatE, txtPhimTatR, txtNoiDungQ,
            txtNoiDungW, txtNoiDungE, txtNoiDungR;
    ProgressBar progressBarChampionKiem, progressBarChampionCong, progressBarChampionLua, progressBarChampionLen;
    Button btnNT, btnQ, btnW, btnE, btnR;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TongQuanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TongQuanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TongQuanFragment newInstance(String param1, String param2) {
        TongQuanFragment fragment = new TongQuanFragment();
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
        View v = inflater.inflate(R.layout.fragment_tong_quan, container, false);
        imgChampionTQ= (ImageView) v.findViewById(R.id.imgChampionTQ);
        progressBarChampionKiem= (ProgressBar) v.findViewById(R.id.progressBarChampionKiem);
        progressBarChampionCong= (ProgressBar) v.findViewById(R.id.progressBarChampionCong);
        progressBarChampionLua= (ProgressBar) v.findViewById(R.id.progressBarChampionLua);
        progressBarChampionLen= (ProgressBar) v.findViewById(R.id.progressBarChampionLen);
        txtChampionNameTQ= (TextView) v.findViewById(R.id.txtChampionNameTQ);
        txtChampionTitleTQ= (TextView) v.findViewById(R.id.txtChampionTitleTQ);
        txtChampionTagsTQ= (TextView) v.findViewById(R.id.txtChampionTagsTQ);
        txtHP= (TextView) v.findViewById(R.id.txtHP);
        txtHPRegen= (TextView) v.findViewById(R.id.txtHPRegen);
        txtMP= (TextView) v.findViewById(R.id.txtMP);
        txtMPRegen= (TextView) v.findViewById(R.id.txtMPRegen);
        txtArmor= (TextView) v.findViewById(R.id.txtArmor);
        txtSpellBlock= (TextView) v.findViewById(R.id.txtSpellBlock);
        txtAttackDamage= (TextView) v.findViewById(R.id.txtAttackDamage);
        txtAttackSpeed= (TextView) v.findViewById(R.id.txtAttackSpeed);
        txtAttackRange= (TextView) v.findViewById(R.id.txtAttackRange);
        txtCrit= (TextView) v.findViewById(R.id.txtCrit);
        txtMoveSpeed= (TextView) v.findViewById(R.id.txtMoveSpeed);
        imgAnhNT = (ImageView) v.findViewById(R.id.imgAnhNT);
        btnNT = (Button) v.findViewById(R.id.btnNT);
        txtTenNT = (TextView) v.findViewById(R.id.txtTenNT);
        txtPhimTatNT = (TextView) v.findViewById(R.id.txtPhimTatNT);
        txtNoiDungNT = (TextView) v.findViewById(R.id.txtNoiDungNT);
        imgAnhQ = (ImageView) v.findViewById(R.id.imgAnhQ);
        btnQ = (Button) v.findViewById(R.id.btnQ);
        txtTenQ = (TextView) v.findViewById(R.id.txtTenQ);
        txtPhimTatQ = (TextView) v.findViewById(R.id.txtPhimTatQ);
        txtNoiDungQ = (TextView) v.findViewById(R.id.txtNoiDungQ);
        imgAnhW = (ImageView) v.findViewById(R.id.imgAnhW);
        btnW = (Button) v.findViewById(R.id.btnW);
        txtTenW = (TextView) v.findViewById(R.id.txtTenW);
        txtPhimTatW = (TextView) v.findViewById(R.id.txtPhimTatW);
        txtNoiDungW = (TextView) v.findViewById(R.id.txtNoiDungW);
        imgAnhE = (ImageView) v.findViewById(R.id.imgAnhE);
        btnE = (Button) v.findViewById(R.id.btnE);
        txtTenE = (TextView) v.findViewById(R.id.txtTenE);
        txtPhimTatE = (TextView) v.findViewById(R.id.txtPhimTatE);
        txtNoiDungE = (TextView) v.findViewById(R.id.txtNoiDungE);
        imgAnhR = (ImageView) v.findViewById(R.id.imgAnhR);
        btnR = (Button) v.findViewById(R.id.btnR);
        txtTenR = (TextView) v.findViewById(R.id.txtTenR);
        txtPhimTatR = (TextView) v.findViewById(R.id.txtPhimTatR);
        txtNoiDungR = (TextView) v.findViewById(R.id.txtNoiDungR);

        // Inflate the layout for this fragment

        Bundle bundle=getArguments();
        String image = bundle.getString("image1");
        Picasso.with(getActivity()).load("file:///android_asset/app/champion/"+image).into(imgChampionTQ);
        String name = bundle.getString("name1");
        txtChampionNameTQ.setText(name);
        String title = bundle.getString("title1");
        txtChampionTitleTQ.setText(title);

        String tags = bundle.getString("tags1");
        try {
            JSONArray jsonArrayTags = new JSONArray(tags);
            String tag = "";
            for (int i=0;i<jsonArrayTags.length();i++){
                tag = tag + jsonArrayTags.get(i).toString() + "  ";
            }

            txtChampionTagsTQ.setText(tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String info = bundle.getString("info1");
        try {
            JSONObject jsonObject = new JSONObject(info);
            String attack = jsonObject.getString("attack");
            String defense = jsonObject.getString("defense");
            String magic = jsonObject.getString("magic");
            String difficulty = jsonObject.getString("difficulty");
            progressBarChampionKiem.setProgress(Integer.parseInt(attack));
            progressBarChampionCong.setProgress(Integer.parseInt(defense));
            progressBarChampionLua.setProgress(Integer.parseInt(magic));
            progressBarChampionLen.setProgress(Integer.parseInt(difficulty));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String stats = bundle.getString("stats1");
        try {
            JSONObject jsonObject = new JSONObject(stats);
            String armor = jsonObject.getString("armor");
            String armorperlevel = jsonObject.getString("armorperlevel");
            String attackdamage = jsonObject.getString("attackdamage");
            String attackdamageperlevel = jsonObject.getString("attackdamageperlevel");
            String attackrange = jsonObject.getString("attackrange");
            String attackspeedoffset = jsonObject.getString("attackspeedoffset");
            String attackspeedperlevel = jsonObject.getString("attackspeedperlevel");
            String crit = jsonObject.getString("crit");
            String critperlevel = jsonObject.getString("critperlevel");
            String hp = jsonObject.getString("hp");
            String hpperlevel = jsonObject.getString("hpperlevel");
            String hpregen = jsonObject.getString("hpregen");
            String hpregenperlevel = jsonObject.getString("hpregenperlevel");
            String movespeed = jsonObject.getString("movespeed");
            String mp = jsonObject.getString("mp");
            String mpperlevel = jsonObject.getString("mpperlevel");
            String mpregen = jsonObject.getString("mpregen");
            String mpregenperlevel = jsonObject.getString("mpregenperlevel");
            String spellblock = jsonObject.getString("spellblock");
            String spellblockperlevel = jsonObject.getString("spellblockperlevel");

            txtHP.setText(hp + " (+" +hpperlevel+ ")" );
            txtHPRegen.setText(hpregen + " (+" +hpregenperlevel+ ")" );
            txtMP.setText(mp + " (+" +mpperlevel+ ")" );
            txtMPRegen.setText(mpregen + " (+" +mpregenperlevel+ ")" );
            txtArmor.setText(armor + " (+" +armorperlevel+ ")" );
            txtSpellBlock.setText(spellblock + " (+" +spellblockperlevel+ ")" );
            txtAttackDamage.setText(attackdamage + " (+" +attackdamageperlevel+ ")" );
            txtAttackSpeed.setText(attackspeedoffset + " (+" +attackspeedperlevel+ ")" );
            txtAttackRange.setText(attackrange);
            txtCrit.setText(crit + " (+" +critperlevel+ ")" );
            txtMoveSpeed.setText(movespeed);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String passive = bundle.getString("passive1");
        try {
            JSONObject jsonObject=new JSONObject(passive);
            String nameSkillP = jsonObject.getString("name");
            String description = jsonObject.getString("description");

            txtTenNT.setText(nameSkillP);
            txtNoiDungNT.setText(Html.fromHtml(description));

            JSONObject jsonObject1=jsonObject.getJSONObject("image");
            String full = jsonObject1.getString("full");
            String group = jsonObject1.getString("group");
            //txtPhimTatNT.setText(group);
            Picasso.with(getActivity()).load("file:///android_asset/app/passive/"+full).into(imgAnhNT);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String spells = bundle.getString("spells1");
        try {
            JSONArray jsonArray=new JSONArray(spells);
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            String nameSkillQ=jsonObject.getString("name");
            txtTenQ.setText(nameSkillQ);
            String description=jsonObject.getString("description");
            String loaiTieuHao = jsonObject.getString("costType");
            String tieuHao = jsonObject.getString("costBurn");
            String hoiChieu = jsonObject.getString("cooldownBurn");
            String rangeBurn = jsonObject.getString("rangeBurn");
            txtNoiDungQ.setText(Html.fromHtml(description+"<br/><br/>"+loaiTieuHao+": "+tieuHao+" "+loaiTieuHao+"<br/>Cooldown: "+hoiChieu+"s<br/>Range: "+rangeBurn));
            String key=jsonObject.getString("key");
            //txtPhimTatQ.setText(key);
            JSONObject jsonObject1=jsonObject.getJSONObject("image");
            String full = jsonObject1.getString("full");
            Picasso.with(getActivity()).load("file:///android_asset/app/spell/"+full).into(imgAnhQ);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            JSONArray jsonArray=new JSONArray(spells);
            JSONObject jsonObject=jsonArray.getJSONObject(1);
            String nameSkillW=jsonObject.getString("name");
            txtTenW.setText(nameSkillW);
            String description=jsonObject.getString("description");
            String loaiTieuHao = jsonObject.getString("costType");
            String tieuHao = jsonObject.getString("costBurn");
            String hoiChieu = jsonObject.getString("cooldownBurn");
            String rangeBurn = jsonObject.getString("rangeBurn");
            txtNoiDungW.setText(Html.fromHtml(description+"<br/><br/>"+loaiTieuHao+": "+tieuHao+" "+loaiTieuHao+"<br/>Cooldown: "+hoiChieu+"s<br/>Range: "+rangeBurn));
            String key=jsonObject.getString("key");
            //txtPhimTatW.setText(key);
            JSONObject jsonObject1=jsonObject.getJSONObject("image");
            String full = jsonObject1.getString("full");
            Picasso.with(getActivity()).load("file:///android_asset/app/spell/"+full).into(imgAnhW);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            JSONArray jsonArray=new JSONArray(spells);
            JSONObject jsonObject=jsonArray.getJSONObject(2);
            String nameSkillE=jsonObject.getString("name");
            txtTenE.setText(nameSkillE);
            String description=jsonObject.getString("description");
            String loaiTieuHao = jsonObject.getString("costType");
            String tieuHao = jsonObject.getString("costBurn");
            String hoiChieu = jsonObject.getString("cooldownBurn");
            String rangeBurn = jsonObject.getString("rangeBurn");
            txtNoiDungE.setText(Html.fromHtml(description+"<br/><br/>"+loaiTieuHao+": "+tieuHao+" "+loaiTieuHao+"<br/>Cooldown: "+hoiChieu+"s<br/>Range: "+rangeBurn));
            String key=jsonObject.getString("key");
            //txtPhimTatE.setText(key);
            JSONObject jsonObject1=jsonObject.getJSONObject("image");
            String full = jsonObject1.getString("full");
            Picasso.with(getActivity()).load("file:///android_asset/app/spell/"+full).into(imgAnhE);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            JSONArray jsonArray=new JSONArray(spells);
            JSONObject jsonObject=jsonArray.getJSONObject(3);
            String nameSkillR=jsonObject.getString("name");
            txtTenR.setText(nameSkillR);
            String description=jsonObject.getString("description");
            String loaiTieuHao = jsonObject.getString("costType");
            String tieuHao = jsonObject.getString("costBurn");
            String hoiChieu = jsonObject.getString("cooldownBurn");
            String rangeBurn = jsonObject.getString("rangeBurn");
            txtNoiDungR.setText(Html.fromHtml(description+"<br/><br/>"+loaiTieuHao+": "+tieuHao+" "+loaiTieuHao+"<br/>Cooldown: "+hoiChieu+"s<br/>Range: "+rangeBurn));
            String key=jsonObject.getString("key");
            //txtPhimTatR.setText(key);
            JSONObject jsonObject1=jsonObject.getJSONObject("image");
            String full = jsonObject1.getString("full");
            Picasso.with(getActivity()).load("file:///android_asset/app/spell/"+full).into(imgAnhR);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;

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
