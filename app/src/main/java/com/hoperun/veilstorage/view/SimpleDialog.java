package com.hoperun.veilstorage.view;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.hoperun.veilstorage.R;

public class SimpleDialog {
	public static void show(Context context, String msg) {
	
		AlertDialog.Builder dialog = new Builder(context);

		dialog.setTitle(context.getResources().getString(R.string.tips));
		dialog.setMessage(msg);
		dialog.setCancelable(true);
	
		dialog.setPositiveButton(context.getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		dialog.show();
	}

	public static void show(Context context, String msg, final OnPositiveClickListener positiveListener) {
		AlertDialog.Builder dialog = new Builder(context);
		dialog.setTitle(context.getResources().getString(R.string.tips));
		dialog.setMessage(msg);
		dialog.setCancelable(true);
		dialog.setPositiveButton(context.getResources().getString(R.string.confirm), new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (positiveListener != null) {
					positiveListener.onPositiveClick();
				}
			}
		});
		dialog.show();
	}

	public static void show(Context context, String msg, final OnNegativeClickListener negativeListener) {
		
		AlertDialog.Builder dialog = new Builder(context);

		dialog.setTitle(context.getResources().getString(R.string.tips));
		dialog.setMessage(msg);
		dialog.setCancelable(true);
		dialog.setNegativeButton(context.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (negativeListener != null) {
					negativeListener.onNegativeClick();
				}
			}
		});


		dialog.show();
	}

	public static void show(Context context, String msg, final OnNegativeClickListener negativeListener, final OnPositiveClickListener positiveListener) {
	
		AlertDialog.Builder dialog = new Builder(context);

		dialog.setTitle(context.getResources().getString(R.string.tips));
		dialog.setMessage(msg);
		dialog.setCancelable(true);
		dialog.setNegativeButton(context.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (negativeListener != null) {
					negativeListener.onNegativeClick();
				}
			}
		});
		dialog.setPositiveButton(context.getResources().getString(R.string.confirm), new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (positiveListener != null) {
					positiveListener.onPositiveClick();
				}

			}
		});

		dialog.show();
	}





	public static void forceShow(Context context, String msg) {
	
		AlertDialog.Builder dialog = new Builder(context);

		dialog.setTitle(context.getResources().getString(R.string.tips));
		dialog.setMessage(msg);
		dialog.setCancelable(false);
	
		dialog.setPositiveButton(context.getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		dialog.show();
	}

	public static void forceShow(Context context, String msg, final OnPositiveClickListener positiveListener) {
		
		AlertDialog.Builder dialog = new Builder(context);

		dialog.setTitle(context.getResources().getString(R.string.tips));
		dialog.setMessage(msg);
		dialog.setCancelable(false);

		dialog.setPositiveButton(context.getResources().getString(R.string.confirm), new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (positiveListener != null) {
					positiveListener.onPositiveClick();
				}
			}
		});

		dialog.show();
	}

	public static void forceShow(Context context, String msg, final OnNegativeClickListener negativeListener) {
		
		AlertDialog.Builder dialog = new Builder(context);

		dialog.setTitle(context.getResources().getString(R.string.tips));
		dialog.setMessage(msg);
		dialog.setCancelable(false);
		dialog.setNegativeButton(context.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (negativeListener != null) {
					negativeListener.onNegativeClick();
				}
			}
		});

		dialog.show();
	}

	public static void forceShow(Context context, String msg, final OnNegativeClickListener negativeListener, final OnPositiveClickListener positiveListener) {
		// android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth
		AlertDialog.Builder dialog = new Builder(context);

		dialog.setTitle(context.getResources().getString(R.string.tips));
		dialog.setMessage(msg);
		dialog.setCancelable(false);
		dialog.setNegativeButton(context.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (negativeListener != null) {
					negativeListener.onNegativeClick();
				}
			}
		});
		dialog.setPositiveButton(context.getResources().getString(R.string.confirm), new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (positiveListener != null) {
					positiveListener.onPositiveClick();
				}

			}
		});

		dialog.show();
	}




















	public interface OnPositiveClickListener {
		public void onPositiveClick();
	}

	public interface OnNegativeClickListener {
		public void onNegativeClick();
	}

}

