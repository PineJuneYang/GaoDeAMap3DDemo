package com.unionpay.smartDG.route;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.services.route.RidePath;
import com.unionpay.smartDG.util.AMapUtil;

/**
 * 骑行路线详情
 */
public class RideRouteDetailActivity extends Activity {
	private RidePath mRidePath;
	private TextView mTitle,mTitleWalkRoute;
	private ListView mRideSegmentList;
	private RideSegmentListAdapter mRideSegmentListAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.unionpay.smartDG.R.layout.activity_route_detail);
		getIntentData();
		mTitle = (TextView) findViewById(com.unionpay.smartDG.R.id.title_center);
		mTitle.setText("骑行路线详情");
		mTitleWalkRoute = (TextView) findViewById(com.unionpay.smartDG.R.id.firstline);
		String dur = AMapUtil.getFriendlyTime((int) mRidePath.getDuration());
		String dis = AMapUtil
				.getFriendlyLength((int) mRidePath.getDistance());
		mTitleWalkRoute.setText(dur + "(" + dis + ")");
		mRideSegmentList = (ListView) findViewById(com.unionpay.smartDG.R.id.bus_segment_list);
		mRideSegmentListAdapter = new RideSegmentListAdapter(
				this.getApplicationContext(), mRidePath.getSteps());
		mRideSegmentList.setAdapter(mRideSegmentListAdapter);

	}

	private void getIntentData() {
		Intent intent = getIntent();
		if (intent == null) {
			return;
		}
		mRidePath = intent.getParcelableExtra("ride_path");
	}

	public void onBackClick(View view) {
		this.finish();
	}

}
