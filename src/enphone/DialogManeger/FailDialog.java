package enphone.DialogManeger;

import com.enphoneh.appleemoji.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class FailDialog extends Dialog {
	
	private Button mSure;
	public FailDialog(Context context) {
		super(context);
		setFailDialog();
	}

	public FailDialog(Context context, int theme) {
		super(context, theme);
		setFailDialog();
	}

	public FailDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}
	
	private void setFailDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.fail_dialog, null);
        mSure = (Button) mView.findViewById(R.id.fail_dialog_sure);
        super.setContentView(mView);
    }
	
	public void setOnSureListenner(View.OnClickListener listener){
		mSure.setOnClickListener(listener);
	}
}
