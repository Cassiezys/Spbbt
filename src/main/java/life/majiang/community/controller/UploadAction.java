//package life.majiang.community.controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import javax.servlet.ServletContext;
//
//import org.apache.struts2.ServletActionContext;
//
//import com.opensymphony.xwork2.ActionSupport;
//import com.qst.model.Upload;
//
//public class UploadAction extends ActionSupport{
//
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = 1L;
//
//
//	Upload upload;
//
//
//	public Upload getUpload() {
//		return upload;
//	}
//
//
//	public void setUpload(Upload upload) {
//		this.upload = upload;
//	}
//
//	public String upload() throws IOException {
//		System.out.println("file:" +upload.getFile().getName());
//		System.out.println("type:" +upload.getFileContextType());
//		System.out.println( "name:"+upload.getFileFileName());
//
//		//利用ServletActionContext来获取ServletContext全局资源对象
//		ServletContext sc = ServletActionContext.getServletContext();
//		//获取本地files,如果没有就创建一个
//		String path = sc.getRealPath("/files/"+upload.getFileFileName());
//		System.out.println("path:" + path);
//
//		//创建输出输入
//		//要放入的地址
//		FileOutputStream fos = new FileOutputStream(path);
//		//将上传的文件传入
//		FileInputStream fis = new FileInputStream(upload.getFile());
//
//		//byte
//		byte[] bs = new byte[1024];
//
//		int len = 0;
//		//读取  写入
//		while ( (len = (fis.read(bs))) != -1  ) {
//			fos.write(bs, 0, len);
//		}
//		//关闭
//		fos.close();
//		fis.close();
//
//
//		return "upload";
//	}
//
//}
