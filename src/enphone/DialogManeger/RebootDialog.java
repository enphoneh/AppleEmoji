package enphone.DialogManeger;

import com.enphoneh.appleemoji.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class RebootDialog extends Dialog {

	private Button mReboot;
	private Button mDelay;
		
	public RebootDialog(Context context) {
		super(context);
		setRebootDialog();
	}
	
	public RebootDialog(Context context , int theme) {
		super(context,theme);
		setRebootDialog();
	}
	
	public RebootDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		setRebootDialog();
	}
	
	private void setRebootDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.restart_dialog, null);
        mReboot = (Button) mView.findViewById(R.id.apply_dialog_reboot);
        mDelay = (Button) mView.findViewById(R.id.apply_dialog_delay);
        super.setContentView(mView);
    }
	
	public void setOnRebootListenner(View.OnClickListener listener){
		mReboot.setOnClickListener(listener);
	}
	
	public void setOnDelayListenner(View.OnClickListener listener){
		mDelay.setOnClickListener(listener);
	}

}
