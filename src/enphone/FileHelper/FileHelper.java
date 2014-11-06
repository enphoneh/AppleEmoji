package enphone.FileHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class FileHelper {

	Context mContext;
	private String fileDirPath = android.os.Environment  
            .getExternalStorageDirectory().getAbsolutePath() + "/AppleEmoji";
	private String fileName = "dzp";// 要存储的文件名 
	public FileHelper(Context context) {
		mContext = context;
	}
	
	public String getFilePath()
	{
		return  fileDirPath;
	}
	
	public void CreateFile(){
		String filePath = fileDirPath + "/" + fileName;// 文件路径  
        try {  
            File dir = new File(fileDirPath);// 目录路径  
            if (!dir.exists()) {// 如果不存在，则创建路径名  
                System.out.println("要存储的目录不存在");  
                if (dir.mkdirs()) {// 创建该路径名，返回true则表示创建成功  
                    System.out.println("已经创建文件存储目录");  
                } else {  
                    System.out.println("创建目录失败");  
                }  
            }  
            // 目录存在，则将apk中raw中的需要的文档复制到该目录下  
            File file = new File(filePath);  
            if (!file.exists()) {// 文件不存在  
                System.out.println("要打开的文件不存在");  
                InputStream ins =mContext. getResources().openRawResource(  
                     com.enphoneh.appleemoji.R.raw.dzp );// 通过raw得到数据资源  
                System.out.println("开始读入");  
                FileOutputStream fos = new FileOutputStream(file);  
                System.out.println("开始写出");  
                byte[] buffer = new byte[8192];  
                int count = 0;// 循环写出  
                while ((count = ins.read(buffer)) > 0) {  
                    fos.write(buffer, 0, count);  
                }  
                System.out.println("已经创建该文件");  
                fos.close();// 关闭流  
                ins.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
	}
	
	public boolean isFileExist(String FilePath,String FileName)
	{
		File mFile = new File(FilePath);
		File[] files = mFile.listFiles();
		for(int i = 0 ; i < files.length ; i++){
			if(files[i].getName().toString().equals(FileName)){
				return true;
			}
		}		
		return false;
	}
	
	public void deleteTempFile(){
		String filePath = fileDirPath + "/" + fileName;// 文件路径 
		File mFile = new File(filePath);
		if(mFile.exists()){
			mFile.delete();
		}
	}
}
