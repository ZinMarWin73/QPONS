package com.revotech.qpons.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;


import com.revotech.qpons.R;
import com.revotech.qpons.activity.adapter.SimpleImageBanner;
import com.revotech.qpons.activity.constant.Constant;
import com.revotech.qpons.activity.controller.LayoutController;
import com.revotech.qpons.activity.data.pojo.ProductData;
import com.revotech.qpons.activity.fragments.PaymentWebView;
import com.revotech.qpons.activity.fragments.TermNConditionFragment;
import com.revotech.qpons.activity.utils.DataProvider;

import org.w3c.dom.Text;

import java.text.NumberFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CheckOutActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
@Bind(R.id.button_buy)
    Button button_buy;

    @Bind(R.id.image_minus)ImageButton btn_minus;
    @Bind(R.id.image_plus) ImageButton btn_plus;
    @Bind(R.id.button_showitme_count) Button btn_showcount;

    @Bind(R.id.checkbox_sendgift) CheckBox chk_send_gift;
    @Bind(R.id.radio_cash)
    RadioButton rdo_cash;
    @Bind(R.id.radio_mpu) RadioButton rdo_mpu;


    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.txt_chkout_Promotion)
    TextView txt_promotionPrice;
    @Bind(R.id.txt_chkout_orign_price)
    TextView txt_origin_price ;
    @Bind(R.id.txt_checkout_total) TextView txt_Total;
    @Bind(R.id.txt_chkout_sold) TextView txt_Sold;
    @Bind(R.id.text_chk_mmk_stike) TextView txt_stike;

    @Bind(R.id.layout_dync_giftreceiver)
    RelativeLayout relayout_giftrever;

    @Bind(R.id.txt_checkout_username) TextView txt_Reciver_name;
    @Bind(R.id.txt_checkout_date) TextView txt_Showdate;

    ProductData prod_data ;
    LayoutController controller ;
    @Bind(R.id.sib_simple_usage)
    SimpleImageBanner sid_banner;

    boolean flag_gift = false ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        controller = new LayoutController();

        SetUpInitData(savedInstanceState);

        chk_send_gift.setOnCheckedChangeListener(this);

        button_buy.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
    }

    private void SetUpInitData(Bundle savedInstanceState) {

        if(savedInstanceState != null){

            prod_data = savedInstanceState.getParcelable("prod");
        }else{
            prod_data = getIntent().getParcelableExtra("prod");

        }


        collapsingToolbarLayout.setTitle(getTitle());
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        txt_origin_price.setText(prod_data.getmOrigin_Price());

        sid_banner.setSource(DataProvider.getList(DataProvider.urls)).startScroll();

        txt_origin_price.setPaintFlags(txt_origin_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        txt_stike.setPaintFlags(txt_stike.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        txt_promotionPrice.setText(prod_data.getmDiscount_Price());
        txt_Sold.setText(prod_data.getmSold_Out());

        btn_showcount.setText(1 + "");
        int qty = Integer.parseInt(btn_showcount.getText().toString());


        String total = controller.CalculateTotal(qty, prod_data.getmDiscount_Price());


        txt_Total.setText(total);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("prod", prod_data);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        int qty = Integer.parseInt(btn_showcount.getText().toString());

        switch (v.getId()){

            case R.id.button_buy:
                boolean checked = chk_send_gift.isChecked();


                    if(checked){

                            if(flag_gift){
                                ShowDialog();
                            }else {
                                Intent i = new Intent(this, SendAsGiftActivty.class);
                                startActivityForResult(i, Constant.REQUEST_CODE);
                            }
                        }else {
                         if(rdo_mpu.isChecked()){

                                Intent i = new Intent(this , WebViewPaymentActivity.class);
                                startActivity(i);
                         }
                        if(rdo_cash.isChecked()){

                             ShowDialog();

                         }

                    }

                break;
            case R.id.image_minus:


                int count = controller.ItemCount(btn_showcount , qty , Constant.DECREASE);

                String total = controller.CalculateTotal(count, prod_data.getmDiscount_Price());
                txt_Total.setText(total);

                break;
            case R.id.image_plus:

                int count1 = controller.ItemCount(btn_showcount , qty , Constant.INCREASE);

                String total1 = controller.CalculateTotal(count1 , prod_data.getmDiscount_Price());
                txt_Total.setText(total1);

                break;
        }


        }

    public void ShowDialog(){

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_thanks);
        Button btnOk = (Button) dialog.findViewById(R.id.button_OK);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });

        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){

            relayout_giftrever.setVisibility(View.VISIBLE);
            flag_gift = true;
            Bundle b = data.getExtras();
            String name = b.getString("name");
            String date = b.getString("date");

            txt_Reciver_name.setText(name);
            txt_Showdate.setText(date);


        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(buttonView.getId() == R.id.checkbox_sendgift){
            if(isChecked == false){

                relayout_giftrever.setVisibility(View.GONE);
            }

        }

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_cash:
                if (checked)


                    break;
            case R.id.radio_mpu:
                if (checked)

                    break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
