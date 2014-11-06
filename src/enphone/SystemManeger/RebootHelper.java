package enphone.SystemManeger;

import enphone.RootHelper.RootHelper;
import android.content.Context;

public class RebootHelper {

	Context mContext;
	RootHelper mRootHelper;
	
	public RebootHelper(Context context) {
		mContext = context;
	}
	public void rebootSystem(){
		String reboot = "reboot";
		mRootHelper = new RootHelper();
		mRootHelper.execRootCmdSilent(reboot);
	}
}
