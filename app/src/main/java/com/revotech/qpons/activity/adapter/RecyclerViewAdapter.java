package com.revotech.qpons.activity.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.revotech.qpons.R;
import com.revotech.qpons.activity.constant.Constant;
import com.revotech.qpons.activity.data.pojo.ProductData;

import java.util.List;

/**
 * Created by zinmarwin on 2/10/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ListItemViewHolder> {

    private ItemOnClickListener itemOnClickListener;
    List<ProductData> productDataList;Context context;
    String context_status;

    public RecyclerViewAdapter(List<ProductData> productDataList, Context context, String deals) {

        this.productDataList = productDataList;
        this.context = context;
        this.context_status = deals;
    }

    @Override
    public RecyclerViewAdapter.ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_deal_item, parent, false);

        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ListItemViewHolder holder, int position) {


        final ProductData productData = productDataList.get(position);

        if(context_status.equals(Constant.DEALS)){

            holder.txt_Expired.setVisibility(View.GONE);
            holder.mProduct_sold.setVisibility(View.VISIBLE);
            holder.mProduct_Origin_Price.setVisibility(View.VISIBLE);
            holder.text_mmk_strike.setVisibility(View.VISIBLE);


            holder.imageView_Product.setBackgroundResource(productData.getmProduct_Image());
            holder.mProduct_name.setText(productData.getmProd_Name());
            holder.mProduct_sold.setText(productData.getmSold_Out());
            holder.mProduct_Origin_Price.setText(productData.getmOrigin_Price());
            holder.mProduct_Origin_Price.setPaintFlags(holder.mProduct_Origin_Price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            holder.mProducct_Promotion_price.setText(productData.getmDiscount_Price());
            holder.text_mmk_strike.setPaintFlags(holder.mProduct_Origin_Price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }else{

            if(productData.getExpired().equals("true")){
                holder.txt_Expired.setVisibility(View.VISIBLE);
                holder.imageView_Product.setImageResource(android.R.drawable.screen_background_dark_transparent);


            }else{
                holder.txt_Expired.setVisibility(View.GONE);

            }
            holder.mProduct_sold.setVisibility(View.GONE);
            holder.mProduct_Origin_Price.setVisibility(View.GONE);
            holder.text_mmk_strike.setVisibility(View.GONE);

            holder.imageView_Product.setBackgroundResource(productData.getmProduct_Image());

            holder.mProduct_name.setText(productData.getmProd_Name());
            holder.mProducct_Promotion_price.setText(productData.getmDiscount_Price());


        }




    }

    public interface ItemOnClickListener{
        void onItemClick(View view,int position);
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener){
        this.itemOnClickListener = itemOnClickListener;
    }

    @Override
    public int getItemCount() {
        return productDataList.size();
    }


    class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mProduct_name;
        TextView mProduct_sold;
        TextView mProduct_Origin_Price;
        TextView mProducct_Promotion_price;
        ImageView imageView_Product;
        TextView text_mmk_strike;

        TextView txt_Expired;


        public ListItemViewHolder(View itemView) {
            super(itemView);
            mProduct_name = (TextView) itemView.findViewById(R.id.txt_list_product_name);
            mProduct_sold= (TextView) itemView.findViewById(R.id.txt_list_sold);
            mProduct_Origin_Price = (TextView) itemView.findViewById(R.id.txt_list_orign_price);
            mProducct_Promotion_price =(TextView) itemView.findViewById(R.id.txt_listPromotion);

            imageView_Product = (ImageView) itemView.findViewById(R.id.imageView_list_prod);
            text_mmk_strike = (TextView) itemView.findViewById(R.id.text_mmk_stike);

            txt_Expired = (TextView) itemView.findViewById(R.id.txt_Expired);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {

            if(itemOnClickListener!=null){
                itemOnClickListener.onItemClick(itemView,getAdapterPosition());
            }
        }
    }
}
