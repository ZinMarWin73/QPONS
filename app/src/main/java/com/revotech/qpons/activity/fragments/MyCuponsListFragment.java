package com.revotech.qpons.activity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.revotech.qpons.R;
import com.revotech.qpons.activity.adapter.RecyclerViewAdapter;
import com.revotech.qpons.activity.constant.Constant;
import com.revotech.qpons.activity.data.pojo.ProductData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zinmarwin on 2/22/16.
 */
public class MyCuponsListFragment extends Fragment {
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
        return inflater.inflate(R.layout.fragment_mycoupon , container , false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_coupons_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        SetUPProductData();
        adapter = new RecyclerViewAdapter(prod_list , getContext() , Constant.MYCOUPONS);
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
            if(i % 2 == 0){
                prod.setExpired("true");
                Log.i("Expired ", prod.getExpired());


            }else{

                prod.setExpired("false");
            }
            prod_list.add(prod);
        }
    }
}
