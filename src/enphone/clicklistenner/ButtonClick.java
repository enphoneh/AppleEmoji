package enphone.clicklistenner;

import java.util.ArrayList;
import java.util.List;

import com.enphoneh.appleemoji.R;

import enphone.DialogManeger.FailDialog;
import enphone.DialogManeger.RebootDialog;
import enphone.FileHelper.FileHelper;
import enphone.RootHelper.RootHelper;
import enphone.RootHelper.ShellUtils;
import enphone.RootHelper.ShellUtils.CommandResult;
import android.R.string;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ButtonClick implements OnClickListener {
	Context mContext = null;
	final static String FILEPATH = "/system/fonts/";
	final static String BACKUP_NAME = "NotoColorEmoji_backup.ttf";
	final static String EMOJI_NAME = "NotoColorEmoji.ttf";
	protected RootHelper mRootHelper = new RootHelper();
	protected FileHelper mFileHelper;
	protected RebootDialog mRebootDialog;
	protected FailDialog mFailDialog;
	public ButtonClick(Context context){
		mContext = context;
		mFileHelper = new FileHelper(context);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case com.enphoneh.appleemoji.R.id.btn_switchtoapple:
			if(mRootHelper.haveRoot())
			{
				if(mFileHelper.isFileExist(FILEPATH,BACKUP_NAME)){
					Toast.makeText(mContext, R.string.toast_apple, Toast.LENGTH_LONG).show();				
				}
				else{
					mFileHelper.CreateFile();
					String cmdFirst ="cp " + mFileHelper.getFilePath() + "/dzp /system/fonts/";
					String cmdSecond = "mv /system/fonts/NotoColorEmoji.ttf /system/fonts/NotoColorEmoji_backup.ttf";
					String cmdThird = "mv /system/fonts/dzp /system/fonts/NotoColorEmoji.ttf";
					String cmdChmod = "chmod 644 /system/fonts/NotoColorEmoji.ttf";
					String cmdChown = "chown root:root /system/fonts/NotoColorEmoji.ttf";
					List<String> commnandList = new ArrayList<String>();
		    		commnandList.add("mount -o rw,remount /system");
		    		commnandList.add(cmdFirst);
		    		commnandList.add(cmdSecond);
		    		commnandList.add(cmdThird);
		    		commnandList.add(cmdChmod);
		    		commnandList.add(cmdChown);
		    		CommandResult result = ShellUtils.execCommand(commnandList, true);
		    		mFileHelper.deleteTempFile();
		    		if(mFileHelper.isFileExist(FILEPATH, BACKUP_NAME) && mFileHelper.isFileExist(FILEPATH, EMOJI_NAME)){
						mRebootDialog = new RebootDialog(mContext, com.enphoneh.appleemoji.R.style.RebootDialog);
						mRebootDialog.setCanceledOnTouchOutside(false);
			    		mRebootDialog.show();
			    		mRebootDialog.setOnRebootListenner(new DialogButtonClick(mContext,mRebootDialog));
			    		mRebootDialog.setOnDelayListenner(new DialogButtonClick(mContext,mRebootDialog));
		    		}
		    		else{
		    			mFailDialog = new FailDialog(mContext, com.enphoneh.appleemoji.R.style.FailDialog);
		    			mFailDialog.setCanceledOnTouchOutside(false);
		    			mFailDialog.show();
		    			mFailDialog.setOnSureListenner(new DialogButtonClick(mContext,mFailDialog));
		    		}
				}
			
			}
			else{
				Toast.makeText(mContext, R.string.toast_no_root, Toast.LENGTH_LONG).show();		
			}
    		break;
		case com.enphoneh.appleemoji.R.id.btn_switchtoandroid:
			if(mFileHelper.isFileExist(FILEPATH, BACKUP_NAME)){
				String cmdSecond = "mv /system/fonts/NotoColorEmoji.ttf /system/fonts/dzp";
				String cmdThird = "mv /system/fonts/NotoColorEmoji_backup.ttf /system/fonts/NotoColorEmoji.ttf";
				String cmdChmod = "chmod 644 /system/fonts/NotoColorEmoji.ttf";
				String cmdChown = "chown root:root /system/fonts/NotoColorEmoji.ttf";
				List<String> commnandList = new ArrayList<String>();
	    		commnandList.add("mount -o rw,remount /system");
	    		commnandList.add(cmdSecond);
	    		commnandList.add(cmdThird);
	    		commnandList.add(cmdChmod);
	    		commnandList.add(cmdChown);
	    		CommandResult result = ShellUtils.execCommand(commnandList, true);
				mRebootDialog = new RebootDialog(mContext, com.enphoneh.appleemoji.R.style.RebootDialog);
				mRebootDialog.setCanceledOnTouchOutside(false);
	    		mRebootDialog.show();
	    		mRebootDialog.setOnRebootListenner(new DialogButtonClick(mContext,mRebootDialog));
	    		mRebootDialog.setOnDelayListenner(new DialogButtonClick(mContext,mRebootDialog));
			}
			else{
				Toast.makeText(mContext, R.string.toast_android, Toast.LENGTH_LONG).show();
			}	
			break;
		}		
	}

}
