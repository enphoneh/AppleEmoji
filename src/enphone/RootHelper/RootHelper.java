package enphone.RootHelper;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class RootHelper {

	public RootHelper() {
		// TODO Auto-generated constructor stub
	}
	public int  execRootCmdSilent(String paramString)
	  {
	    try
	    {
	      Process localProcess = Runtime.getRuntime().exec("su");
	      Object localObject = localProcess.getOutputStream();
	      DataOutputStream localDataOutputStream = new DataOutputStream((OutputStream)localObject);
	      String str = String.valueOf(paramString);
	      localObject = str + "\n";
	      localDataOutputStream.writeBytes((String)localObject);
	      localDataOutputStream.flush();
	      localDataOutputStream.writeBytes("exit\n");
	      localDataOutputStream.flush();
	      localProcess.waitFor();
	      localObject = localProcess.exitValue();
	      return Integer.parseInt(localObject.toString());
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	    return -1;
	  }
	public boolean haveRoot()
	  {	 
	    int i = execRootCmdSilent("echo test"); //通过执行测试命令来检测
	    if (i != -1)  return true;
	    return false;
	  }
}
