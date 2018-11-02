package com.umeng.message.lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;
import java.util.List;

public class SharePopAdapter extends BaseAdapter {
	private ShareBean shareBean;
	private Context mContext;
	private LayoutInflater mInflater;

	public SharePopAdapter(ShareBean shareBean, Context context) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		if (shareBean == null) {
			this.shareBean = new ShareBean();
		} else {
			this.shareBean = shareBean;
		}
	}

	public ShareBean getShareBean() {
		return shareBean;
	}

	public void notifyDataChage(ShareBean shareBean) {
		if (shareBean == null) {
			this.shareBean = new ShareBean();
		} else {
			this.shareBean = shareBean;
		}
		this.notifyDataSetChanged();
	}

	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (shareBean == null || shareBean.getPlatforms() == null || shareBean.getPlatforms().size() == 0) {
			return 0;
		}
		return shareBean.getPlatforms().size();
	}

	@Override
	public Object getItem(int position) {
		return shareBean.getPlatforms().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.share_popwindow_item, null);
			viewHolder.share_name_tv = (TextView) convertView.findViewById(R.id.share_name_tv);
			viewHolder.share_logo_iv = (ImageView) convertView.findViewById(R.id.share_logo_iv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		try {
			if (shareBean != null && shareBean.getPlatforms() != null && shareBean.getPlatforms().size() >0) {
				SnsPlatform platform = shareBean.getPlatforms().get(position);
				viewHolder.share_name_tv.setText(platform.mShowWord);
				if (SHARE_MEDIA.WEIXIN_CIRCLE.equals(platform.mPlatform)){
					viewHolder.share_logo_iv.setImageDrawable(ShareTool.getDrawable(mContext, R.drawable.umeng_socialize_wxcircle));
				} else if (SHARE_MEDIA.WEIXIN.equals(platform.mPlatform)){
					viewHolder.share_logo_iv.setImageDrawable(ShareTool.getDrawable(mContext, R.drawable.umeng_socialize_wechat));
				} else if (SHARE_MEDIA.QQ.equals(platform.mPlatform)){
					viewHolder.share_logo_iv.setImageDrawable(ShareTool.getDrawable(mContext, R.drawable.umeng_socialize_qq));
				} else if (SHARE_MEDIA.QZONE.equals(platform.mPlatform)){
					viewHolder.share_logo_iv.setImageDrawable(ShareTool.getDrawable(mContext, R.drawable.umeng_socialize_qzone));
				} else if (SHARE_MEDIA.SINA.equals(platform.mPlatform)){
					viewHolder.share_logo_iv.setImageDrawable(ShareTool.getDrawable(mContext, R.drawable.umeng_socialize_sina));
				} else if (SHARE_MEDIA.ALIPAY.equals(platform.mPlatform)){
					viewHolder.share_logo_iv.setImageDrawable(ShareTool.getDrawable(mContext, R.drawable.umeng_socialize_alipay));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertView;
	}

	class ViewHolder {
		public ImageView share_logo_iv;
		public TextView share_name_tv;
	}
}