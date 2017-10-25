package com.unionpay.smartDG;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.amap.api.maps.MapsInitializer;
import com.unionpay.smartDG.basic.Animate_CameraActivity;
import com.unionpay.smartDG.basic.BasicMapActivity;
import com.unionpay.smartDG.basic.CameraActivity;
import com.unionpay.smartDG.basic.EventsActivity;
import com.unionpay.smartDG.basic.GestureSettingsActivity;
import com.unionpay.smartDG.basic.HeatMapActivity;
import com.unionpay.smartDG.basic.LayersActivity;
import com.unionpay.smartDG.basic.LimitBoundsActivity;
import com.unionpay.smartDG.basic.LogoSettingsActivity;
import com.unionpay.smartDG.basic.MapOptionActivity;
import com.unionpay.smartDG.basic.MinMaxZoomLevelActivity;
import com.unionpay.smartDG.basic.PoiClickActivity;
import com.unionpay.smartDG.basic.ScreenShotActivity;
import com.unionpay.smartDG.basic.TwoMapActivity;
import com.unionpay.smartDG.basic.UiSettingsActivity;
import com.unionpay.smartDG.basic.ViewPagerWithMapActivity;
import com.unionpay.smartDG.basic.ZoomActivity;
import com.unionpay.smartDG.basic.map.MapImpMethodActivity;
import com.unionpay.smartDG.busline.BusStationActivity;
import com.unionpay.smartDG.busline.BuslineActivity;
import com.unionpay.smartDG.cloud.CloudActivity;
import com.unionpay.smartDG.district.DistrictActivity;
import com.unionpay.smartDG.district.DistrictWithBoundaryActivity;
import com.unionpay.smartDG.geocoder.GeocoderActivity;
import com.unionpay.smartDG.geocoder.ReGeocoderActivity;
import com.unionpay.smartDG.indoor.IndoorMapActivity;
import com.unionpay.smartDG.inputtip.InputtipsActivity;
import com.unionpay.smartDG.location.CustomLocationActivity;
import com.unionpay.smartDG.location.LocationModeSourceActivity;
import com.unionpay.smartDG.location.LocationModeSourceActivity_Old;
import com.unionpay.smartDG.offlinemap.OfflineMapActivity;
import com.unionpay.smartDG.opengl.OpenglActivity;
import com.unionpay.smartDG.overlay.ArcActivity;
import com.unionpay.smartDG.overlay.CircleActivity;
import com.unionpay.smartDG.overlay.ColourfulPolylineActivity;
import com.unionpay.smartDG.overlay.CustomMarkerActivity;
import com.unionpay.smartDG.overlay.GeodesicActivity;
import com.unionpay.smartDG.overlay.GroundOverlayActivity;
import com.unionpay.smartDG.overlay.InfoWindowActivity;
import com.unionpay.smartDG.overlay.MarkerActivity;
import com.unionpay.smartDG.overlay.MarkerAnimationActivity;
import com.unionpay.smartDG.overlay.MarkerClickActivity;
import com.unionpay.smartDG.overlay.MultiPointOverlayActivity;
import com.unionpay.smartDG.overlay.NavigateArrowOverlayActivity;
import com.unionpay.smartDG.overlay.PolygonActivity;
import com.unionpay.smartDG.overlay.PolylineActivity;
import com.unionpay.smartDG.overlay.TileOverlayActivity;
import com.unionpay.smartDG.poisearch.PoiAroundSearchActivity;
import com.unionpay.smartDG.poisearch.PoiIDSearchActivity;
import com.unionpay.smartDG.poisearch.PoiKeywordSearchActivity;
import com.unionpay.smartDG.poisearch.SubPoiSearchActivity;
import com.unionpay.smartDG.route.BusRouteActivity;
import com.unionpay.smartDG.route.DriveRouteActivity;
import com.unionpay.smartDG.route.RideRouteActivity;
import com.unionpay.smartDG.route.RouteActivity;
import com.unionpay.smartDG.route.WalkRouteActivity;
import com.unionpay.smartDG.routepoi.RoutePOIActivity;
import com.unionpay.smartDG.share.ShareActivity;
import com.unionpay.smartDG.smooth.SmoothMoveActivity;
import com.unionpay.smartDG.tools.CalculateDistanceActivity;
import com.unionpay.smartDG.tools.ContainsActivity;
import com.unionpay.smartDG.tools.CoordConverActivity;
import com.unionpay.smartDG.tools.GeoToScreenActivity;
import com.unionpay.smartDG.trace.TraceActivity;
import com.unionpay.smartDG.trace.TraceActivity_Simple;
import com.unionpay.smartDG.view.FeatureView;
import com.unionpay.smartDG.weather.WeatherSearchActivity;

/**
 * AMapV2地图demo总汇
 */
public final class MainActivity extends ListActivity {
	private static class DemoDetails {
		private final int titleId;
		private final int descriptionId;
		private final Class<? extends android.app.Activity> activityClass;

		public DemoDetails(int titleId, int descriptionId,
				Class<? extends android.app.Activity> activityClass) {
			super();
			this.titleId = titleId;
			this.descriptionId = descriptionId;
			this.activityClass = activityClass;
		}
	}

	private static class CustomArrayAdapter extends ArrayAdapter<DemoDetails> {
		public CustomArrayAdapter(Context context, DemoDetails[] demos) {
			super(context, com.unionpay.smartDG.R.layout.feature, com.unionpay.smartDG.R.id.title, demos);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			FeatureView featureView;
			if (convertView instanceof FeatureView) {
				featureView = (FeatureView) convertView;
			} else {
				featureView = new FeatureView(getContext());
			}
			DemoDetails demo = getItem(position);
			featureView.setTitleId(demo.titleId, demo.activityClass!=null);
			return featureView;
		}
	}

	private static final DemoDetails[] demos = {
//		            创建地图
			new DemoDetails(com.unionpay.smartDG.R.string.map_create, com.unionpay.smartDG.R.string.blank, null),
//			显示地图
			new DemoDetails(com.unionpay.smartDG.R.string.basic_map, com.unionpay.smartDG.R.string.basic_description,
					BasicMapActivity.class),
//			6种实现地图方式
			new DemoDetails(com.unionpay.smartDG.R.string.basic_map_6, com.unionpay.smartDG.R.string.basic_description_temp,
					MapImpMethodActivity.class),
//			Fragment创建地图
//			new DemoDetails(R.string.base_fragment_map, R.string.base_fragment_description,
//					BaseMapSupportFragmentActivity.class),
//			new DemoDetails(R.string.basic_texturemapview, R.string.basic_texturemapview_description,
//					TextureMapViewActivity.class),
			new DemoDetails(com.unionpay.smartDG.R.string.viewpager_map, com.unionpay.smartDG.R.string.viewpger_map_description,
					ViewPagerWithMapActivity.class),
//			地图多实例
		    new DemoDetails(com.unionpay.smartDG.R.string.multi_inst, com.unionpay.smartDG.R.string.blank,
		    		TwoMapActivity.class),
//		           室内地图
		    new DemoDetails(com.unionpay.smartDG.R.string.indoormap_demo, com.unionpay.smartDG.R.string.indoormap_description,
		            IndoorMapActivity.class),
//		    amapoptions实现地图
		    new DemoDetails(com.unionpay.smartDG.R.string.mapOption_demo,
					com.unionpay.smartDG.R.string.mapOption_description, MapOptionActivity.class),
//-----------与地图交互-----------------------------------------------------------------------------------------------
		    new DemoDetails(com.unionpay.smartDG.R.string.map_interactive, com.unionpay.smartDG.R.string.blank, null),
		    //缩放控件、定位按钮、指南针、比例尺等的添加
		    new DemoDetails(com.unionpay.smartDG.R.string.uisettings_demo,
					com.unionpay.smartDG.R.string.uisettings_description, UiSettingsActivity.class),
			//地图logo位置改变
			new DemoDetails(com.unionpay.smartDG.R.string.logo,
					com.unionpay.smartDG.R.string.uisettings_description, LogoSettingsActivity.class),
			//地图图层
			new DemoDetails(com.unionpay.smartDG.R.string.layers_demo, com.unionpay.smartDG.R.string.layers_description,
					LayersActivity.class),
			//缩放、旋转、拖拽和改变仰角操作地图
			new DemoDetails(com.unionpay.smartDG.R.string.gesture,
					com.unionpay.smartDG.R.string.uisettings_description, GestureSettingsActivity.class),
			//监听点击、长按、拖拽地图等事件
			new DemoDetails(com.unionpay.smartDG.R.string.events_demo, com.unionpay.smartDG.R.string.events_description,
					EventsActivity.class),
			//地图POI点击
			new DemoDetails(com.unionpay.smartDG.R.string.poiclick_demo,
					com.unionpay.smartDG.R.string.poiclick_description, PoiClickActivity.class),
			//改变地图中心点
			new DemoDetails(com.unionpay.smartDG.R.string.camera_demo, com.unionpay.smartDG.R.string.camera_description,
					CameraActivity.class),
			//地图动画效果
			new DemoDetails(com.unionpay.smartDG.R.string.animate_demo, com.unionpay.smartDG.R.string.animate_description,
					Animate_CameraActivity.class),
			//改变缩放级别	
			new DemoDetails(com.unionpay.smartDG.R.string.map_zoom, com.unionpay.smartDG.R.string.blank, ZoomActivity.class),

			//地图截屏
			new DemoDetails(com.unionpay.smartDG.R.string.screenshot_demo,
					com.unionpay.smartDG.R.string.screenshot_description, ScreenShotActivity.class),

			//自定义最小最大缩放级别
			new DemoDetails(com.unionpay.smartDG.R.string.set_min_max_zoomlevel,
					com.unionpay.smartDG.R.string.set_min_max_zoomlevel_description, MinMaxZoomLevelActivity.class),

			//自定义地图显示区域
			new DemoDetails(com.unionpay.smartDG.R.string.limit_bounds,
					com.unionpay.smartDG.R.string.limit_bounds_description, LimitBoundsActivity.class),
//----------------------------------------------------------------------------------------------------------------------------------------
			//在地图上绘制	
			new DemoDetails(com.unionpay.smartDG.R.string.map_overlay, com.unionpay.smartDG.R.string.blank, null),
			//绘制点
			new DemoDetails(com.unionpay.smartDG.R.string.marker_demo, com.unionpay.smartDG.R.string.marker_description,
					MarkerActivity.class),
			//marker点击回调
			new DemoDetails(com.unionpay.smartDG.R.string.marker_click, com.unionpay.smartDG.R.string.marker_click,
					MarkerClickActivity.class),
			//Marker 动画功能
			new DemoDetails(com.unionpay.smartDG.R.string.marker_animation_demo, com.unionpay.smartDG.R.string.marker_animation_description,
					MarkerAnimationActivity.class),
			//绘制地图上的信息窗口
			new DemoDetails(com.unionpay.smartDG.R.string.infowindow_demo, com.unionpay.smartDG.R.string.infowindow_demo, InfoWindowActivity.class),
			//绘制自定义点
			new DemoDetails(com.unionpay.smartDG.R.string.custommarker_demo, com.unionpay.smartDG.R.string.blank,
					CustomMarkerActivity.class),
			//绘制默认定位小蓝点
			new DemoDetails(com.unionpay.smartDG.R.string.locationmodesource_demo_old, com.unionpay.smartDG.R.string.locationmodesource_description,LocationModeSourceActivity_Old.class),
			new DemoDetails(com.unionpay.smartDG.R.string.locationmodesource_demo, com.unionpay.smartDG.R.string.locationmodesource_description,LocationModeSourceActivity.class),
			//绘制自定义定位小蓝点图标
			new DemoDetails(com.unionpay.smartDG.R.string.customlocation_demo, com.unionpay.smartDG.R.string.customlocation_demo,CustomLocationActivity.class),
			//绘制实线、虚线
			new DemoDetails(com.unionpay.smartDG.R.string.polyline_demo,
					com.unionpay.smartDG.R.string.polyline_description, PolylineActivity.class),
			//多彩线
			new DemoDetails(com.unionpay.smartDG.R.string.colourline_demo,
					com.unionpay.smartDG.R.string.colourline_description, ColourfulPolylineActivity.class),
			//大地曲线
			new DemoDetails(com.unionpay.smartDG.R.string.geodesic_demo, com.unionpay.smartDG.R.string.geodesic_description,
					GeodesicActivity.class),
//			绘制弧线
			new DemoDetails(com.unionpay.smartDG.R.string.arc_demo, com.unionpay.smartDG.R.string.arc_description,
					ArcActivity.class),
			//绘制带导航箭头的线
			new DemoDetails(com.unionpay.smartDG.R.string.navigatearrow_demo,
					com.unionpay.smartDG.R.string.navigatearrow_description,
					NavigateArrowOverlayActivity.class),
			//绘制圆
			new DemoDetails(com.unionpay.smartDG.R.string.circle_demo, com.unionpay.smartDG.R.string.circle_description,
					CircleActivity.class),
		    //矩形、多边形
			new DemoDetails(com.unionpay.smartDG.R.string.polygon_demo,
					com.unionpay.smartDG.R.string.polygon_description, PolygonActivity.class),
			//绘制热力图
			new DemoDetails(com.unionpay.smartDG.R.string.heatmap_demo,
					com.unionpay.smartDG.R.string.heatmap_description, HeatMapActivity.class),
			//绘制groundoverlay
			new DemoDetails(com.unionpay.smartDG.R.string.groundoverlay_demo,
					com.unionpay.smartDG.R.string.groundoverlay_description,GroundOverlayActivity.class),
			//绘制opengl
			new DemoDetails(com.unionpay.smartDG.R.string.opengl_demo, com.unionpay.smartDG.R.string.opengl_description,
					OpenglActivity.class),
			//绘制tileOverlay
			new DemoDetails(com.unionpay.smartDG.R.string.tileoverlay_demo, com.unionpay.smartDG.R.string.tileoverlay_demo,
					TileOverlayActivity.class),
			new DemoDetails(com.unionpay.smartDG.R.string.multipoint_demo, com.unionpay.smartDG.R.string.multipoint_description,
					MultiPointOverlayActivity.class),
//-----------------------------------------------------------------------------------------------------------------------------------------------------
			//获取地图数据
			new DemoDetails(com.unionpay.smartDG.R.string.search_data, com.unionpay.smartDG.R.string.blank, null),
			//关键字检索
			new DemoDetails(com.unionpay.smartDG.R.string.poikeywordsearch_demo,
					com.unionpay.smartDG.R.string.poikeywordsearch_description,
					PoiKeywordSearchActivity.class),
			//周边搜索
			new DemoDetails(com.unionpay.smartDG.R.string.poiaroundsearch_demo,
					com.unionpay.smartDG.R.string.poiaroundsearch_description,
					PoiAroundSearchActivity.class),
//			ID检索
			new DemoDetails(com.unionpay.smartDG.R.string.poiidsearch_demo,
					com.unionpay.smartDG.R.string.poiidsearch_demo,
					PoiIDSearchActivity.class),
			//沿途搜索
			new DemoDetails(com.unionpay.smartDG.R.string.routepoisearch_demo,
					com.unionpay.smartDG.R.string.routepoisearch_demo,
					RoutePOIActivity.class),
//			输入提示查询
			new DemoDetails(com.unionpay.smartDG.R.string.inputtips_demo, com.unionpay.smartDG.R.string.inputtips_description,
					InputtipsActivity.class),
//			POI父子关系
			new DemoDetails(com.unionpay.smartDG.R.string.subpoi_demo, com.unionpay.smartDG.R.string.subpoi_description,
					SubPoiSearchActivity.class),
//			天气查询
			new DemoDetails(com.unionpay.smartDG.R.string.weather_demo,
					com.unionpay.smartDG.R.string.weather_description, WeatherSearchActivity.class),
//			地理编码
			new DemoDetails(com.unionpay.smartDG.R.string.geocoder_demo,
					com.unionpay.smartDG.R.string.geocoder_description, GeocoderActivity.class),
//			逆地理编码
			new DemoDetails(com.unionpay.smartDG.R.string.regeocoder_demo,
					com.unionpay.smartDG.R.string.regeocoder_description, ReGeocoderActivity.class),
//			行政区划查询
			new DemoDetails(com.unionpay.smartDG.R.string.district_demo,
					com.unionpay.smartDG.R.string.district_description, DistrictActivity.class),
//			行政区边界查询
			new DemoDetails(com.unionpay.smartDG.R.string.district_boundary_demo,
					com.unionpay.smartDG.R.string.district_boundary_description,
					DistrictWithBoundaryActivity.class),
//			公交路线查询
			new DemoDetails(com.unionpay.smartDG.R.string.busline_demo,
					com.unionpay.smartDG.R.string.busline_description, BuslineActivity.class),
//			公交站点查询
			new DemoDetails(com.unionpay.smartDG.R.string.busstation_demo,
					com.unionpay.smartDG.R.string.blank, BusStationActivity.class),
//			云图
			new DemoDetails(com.unionpay.smartDG.R.string.cloud_demo, com.unionpay.smartDG.R.string.cloud_description,
					CloudActivity.class),
//			出行路线规划
			new DemoDetails(com.unionpay.smartDG.R.string.search_route, com.unionpay.smartDG.R.string.blank, null),
//			驾车出行路线规划
			new DemoDetails(com.unionpay.smartDG.R.string.route_drive, com.unionpay.smartDG.R.string.blank, DriveRouteActivity.class),
//			步行出行路线规划
			new DemoDetails(com.unionpay.smartDG.R.string.route_walk, com.unionpay.smartDG.R.string.blank, WalkRouteActivity.class),
//			公交出行路线规划
			new DemoDetails(com.unionpay.smartDG.R.string.route_bus, com.unionpay.smartDG.R.string.blank, BusRouteActivity.class),
//			骑行出行路线规划
			new DemoDetails(com.unionpay.smartDG.R.string.route_ride, com.unionpay.smartDG.R.string.blank, RideRouteActivity.class),
//			route综合demo
			new DemoDetails(com.unionpay.smartDG.R.string.route_demo, com.unionpay.smartDG.R.string.route_description,
					RouteActivity.class),
//			短串分享
			new DemoDetails(com.unionpay.smartDG.R.string.search_share, com.unionpay.smartDG.R.string.blank, null),
			new DemoDetails(com.unionpay.smartDG.R.string.share_demo, com.unionpay.smartDG.R.string.share_description,
					ShareActivity.class),
			
//			离线地图
			new DemoDetails(com.unionpay.smartDG.R.string.map_offline, com.unionpay.smartDG.R.string.blank, null),
			new DemoDetails(com.unionpay.smartDG.R.string.offlinemap_demo,
					com.unionpay.smartDG.R.string.offlinemap_description, OfflineMapActivity.class),
			
//			地图计算工具
			new DemoDetails(com.unionpay.smartDG.R.string.map_tools, com.unionpay.smartDG.R.string.blank, null),

//			其他坐标系转换为高德坐标系
			new DemoDetails(com.unionpay.smartDG.R.string.coordconvert_demo, com.unionpay.smartDG.R.string.coordconvert_demo, CoordConverActivity.class),
//			地理坐标和屏幕像素坐标转换
			new DemoDetails(com.unionpay.smartDG.R.string.convertgeo2point_demo, com.unionpay.smartDG.R.string.convertgeo2point_demo, GeoToScreenActivity.class),
//			两点间距离计算
			new DemoDetails(com.unionpay.smartDG.R.string.calculateLineDistance, com.unionpay.smartDG.R.string.calculateLineDistance, CalculateDistanceActivity.class),
//			判断点是否在多边形内
			new DemoDetails(com.unionpay.smartDG.R.string.contains_demo, com.unionpay.smartDG.R.string.contains_demo, ContainsActivity.class),


//			地图计算工具
			new DemoDetails(com.unionpay.smartDG.R.string.map_expand, com.unionpay.smartDG.R.string.blank, null),
//			轨迹纠偏
			new DemoDetails(com.unionpay.smartDG.R.string.trace_demo, com.unionpay.smartDG.R.string.trace_description, TraceActivity.class),
			new DemoDetails(com.unionpay.smartDG.R.string.trace_demo_simple, com.unionpay.smartDG.R.string.trace_description_simple, TraceActivity_Simple.class),
//			平滑移动
			new DemoDetails(com.unionpay.smartDG.R.string.smooth_move_demo, com.unionpay.smartDG.R.string.smooth_move_description, SmoothMoveActivity.class)



	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.unionpay.smartDG.R.layout.main_activity);
		setTitle("3D地图Demo" + MapsInitializer.getVersion());
		ListAdapter adapter = new CustomArrayAdapter(
				this.getApplicationContext(), demos);
		setListAdapter(adapter);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		System.exit(0);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		DemoDetails demo = (DemoDetails) getListAdapter().getItem(position);
		if (demo.activityClass != null) {
			Log.i("MY","demo!=null");
			startActivity(new Intent(this.getApplicationContext(),
					demo.activityClass));
		}

	}
}
