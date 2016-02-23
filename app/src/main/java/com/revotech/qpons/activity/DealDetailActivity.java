package com.revotech.qpons.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.revotech.qpons.R;
import com.revotech.qpons.activity.controller.LayoutController;
import com.revotech.qpons.activity.data.pojo.ProductData;
import com.revotech.qpons.activity.data.pojo.ProductHightLight;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DealDetailActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    ArrayList<ProductHightLight> prod_hightlisth_array = new ArrayList<ProductHightLight>();
    @Bind(R.id.layout_dym_hightlight)
    LinearLayout linear_layout;
    @Bind(R.id.layout_fineprint) LinearLayout linearlayout_findprint;
    @Bind(R.id.layout_thid_deal) LinearLayout linearLayout_thisdetal;
    @Bind(R.id.btn_buyNow) Button btn_buyNow;

    @Bind(R.id.txt_detail_product_name)
    TextView txt_productName;

    @Bind(R.id.txt_detail_sold) TextView txt_sold_out;
    @Bind(R.id.txt_detail_orign_price) TextView txt_Origin_price;
    @Bind(R.id.txt_detail_Promotion) TextView txt_Promotion_pric;
    @Bind(R.id.text_mmk_stike) TextView mmk_stike;

    int [] imageView_deal_ID = new int[]{R.drawable.deal ,R.drawable.deal4  , R.drawable.deal2 , R.drawable.deal3};

    String hight_light [] = new String[]{"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. \n" ,
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n" ,
            "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n",
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
    };
   // String fine_print [] = new String[]{};
   int pos ;
    ProductData prod_bundle;
    LayoutController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState != null){

            prod_bundle = savedInstanceState.getParcelable("prod1");
        }else{
            prod_bundle = getIntent().getParcelableExtra("prod");

        }
        //Log.i("Prod Bundle " , prod_bundle.getmProduct_Image() + "");

        PrepareListData();

        btn_buyNow.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putParcelable("prod1" , prod_bundle);
        super.onSaveInstanceState(outState);

    }

    private void PrepareListData() {
       controller = new LayoutController();

        collapsingToolbarLayout.setTitle(getTitle());
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setBackgroundResource(prod_bundle.getmProduct_Image());
        //img_product.setImageResource(prod_bundle.getmProduct_Image());

        txt_Origin_price.setText(prod_bundle.getmOrigin_Price());
        txt_Origin_price.setPaintFlags(txt_Origin_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mmk_stike.setPaintFlags(mmk_stike.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        txt_Promotion_pric.setText(prod_bundle.getmDiscount_Price());
        txt_sold_out.setText(prod_bundle.getmSold_Out());



            ProductHightLight productHightLight = new ProductHightLight();
            productHightLight.setmHight_id(0);
            for(int i = 0 ; i < hight_light.length ; i++){
                productHightLight.setmDesription(hight_light[i]);
                prod_hightlisth_array.add(productHightLight);

            }


           controller.addTextView(linear_layout, this , hight_light);
           controller.addTextView(linearlayout_findprint, this , hight_light);
           controller.addTextView(linearLayout_thisdetal , this, hight_light);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.




        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {


        Intent i = new Intent(this, CheckOutActivity.class);
        i.putExtra("prod" , prod_bundle);
        startActivity(i);
    }
}

