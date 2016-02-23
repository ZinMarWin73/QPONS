package com.revotech.qpons.activity.fragments;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.analytics.ecommerce.Product;
import com.revotech.qpons.R;
import com.revotech.qpons.activity.DealDetailActivity;
import com.revotech.qpons.activity.adapter.RecyclerViewAdapter;
import com.revotech.qpons.activity.constant.Constant;
import com.revotech.qpons.activity.data.pojo.ProductData;
import com.revotech.qpons.activity.data.pojo.ProductHightLight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zinmarwin on 2/10/16.
 */
public class FeatureTabFragment extends Fragment implements RecyclerViewAdapter.ItemOnClickListener{

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    String [] productName = new String[]{"Super duper Cheesy Pizza @ Pizza Hut ", "Spaghetti with Tomato-Shrimp Sauce @ Pasta Mania" ,
    "Double Cheese Burger with Cheesy Fries @ Fat Boy" ,"Apple Watch Sport 38mm @ Technoland"};

    String [] price_origin_arr = new String[]{"12,500" , "6,500", "5,000","355,000"};
    String[] promotion_proce_arr = new String[]{"8,500" , "4,500" , "3,500" , "300,500"};
    int [] imageView_deal_ID = new int[]{R.drawable.deal ,R.drawable.deal2  , R.drawable.deal3 , R.drawable.deal4};
    List<ProductData> prod_list  ;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_featrue ,container , false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_listview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        SetUPProductData();
        adapter = new RecyclerViewAdapter(prod_list , getContext() , Constant.DEALS);
        adapter.setItemOnClickListener(this);
        recyclerView.setAdapter(adapter);



    }

    private void SetUPProductData() {
          prod_list = new ArrayList<ProductData>();

        for (int i = 0 ; i < productName.length ; i++){

            ProductData prod = new ProductData();
            prod.setmProduct_Image(imageView_deal_ID[i]);
            prod.setmProd_Name(productName[i]);
            prod.setmSold_Out("1,000 SOLD");
            prod.setmOrigin_Price(price_origin_arr[i]);
            prod.setmDiscount_Price(promotion_proce_arr[i]);
            prod.setmHightlight_id(0);

            prod.setExpired("false");

            prod_list.add(prod);
        }
    }

    @Override
    public void onItemClick(View view, int position) {



        Intent i = new Intent(getActivity(),DealDetailActivity.class);
         i.putExtra("prod" , prod_list.get(position) );
        Log.i("Prod" , prod_list.get(position).getmProduct_Image()+"");
        startActivity(i);
    }
}
