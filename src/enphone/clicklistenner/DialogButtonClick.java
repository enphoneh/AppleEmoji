package enphone.clicklistenner;

import com.enphoneh.appleemoji.R;

import enphone.SystemManeger.RebootHelper;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class DialogButtonClick implements OnClickListener {

	Context mContext;
	Dialog mDialog;
	protected RebootHelper mRebootHelper;
	public DialogButtonClick(Context context,Dialog dialog) {
		mContext = context;
		mDialog = dialog;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.apply_dialog_reboot) {
			mRebootHelper = new RebootHelper(mContext);
			mRebootHelper.rebootSystem();
			mDialog.dismiss();
		} else if (id == R.id.apply_dialog_delay) {
			mDialog.dismiss();
		} else if (id == R.id.fail_dialog_sure) {
			mDialog.dismiss();
		}
	}

}
