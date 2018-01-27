package com.weyko.shops.util;

import com.weyko.shops.log.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件工具类
 * 
 * @author LQ
 * @date 2014年12月30日 15:54
 */
public class FileUtils {
	public static final String DEFAULT_CHARSET_NAME = "UTF-8";
	/**
	 * 判断文件是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isExist(String path) {
		File file = new File(path);
		return file.exists();
	}

	/**
	 * 删除文件，可以是单个文件或文件夹
	 * 
	 * @param fileName
	 *            待删除的文件名
	 * @return 文件删除成功返回true,否则返回false
	 */

	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			return false;
		} else {
			if (file.isFile()) {
				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true,否则返回false
	 */
	private static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			return true;

		} else {
			return false;

		}
	}

	/**
	 * 
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param dir
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true,否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		if (!dir.endsWith(File.separator)) {// 如果dir不以文件分隔符结尾，自动添加文件分隔符
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		if (!dirFile.exists() || !dirFile.isDirectory()) {// 如果dir对应的文件不存在，或者不是一个目录，则退出
			return false;
		}
		boolean flag = true;
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			} else {// 删除子目录
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
		if (!flag) {
			return false;

		}
		if (dirFile.delete()) {// 删除当前目录
			return true;

		} else {
			return false;
		}
	}

	/**
	 * 把字符串写入文件
	 * 
	 * @param str
	 */
	public static void write(String path, byte[] str) {
		if (path != null && str != null) {
			try {
				FileOutputStream fos = new FileOutputStream(path);
				try {
					fos.write(str);
				}catch(IOException e){
					e.printStackTrace();
				}
				try {
					fos.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				LogUtil.w(e.getMessage());
			}
		}
	}
	/**
	 * 删除指定目录下的某些文件
	 * @param dir 指定目录
	 * @param tag 文件名标记
	 * @param timeLen 时间段（多少天）
	 * @return
	 */
	public static boolean deleteFilesInDirectory(String dir, String tag, int timeLen) {
		if (!dir.endsWith(File.separator)) {// 如果dir不以文件分隔符结尾，自动添加文件分隔符
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		if (!dirFile.exists() || !dirFile.isDirectory()) {// 如果dir对应的文件不存在，或者不是一个目录，则退出
			return false;
		}
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			String fileName=files[i].getName();
			//System.out.println("search file:"+fileName);
			if (files[i].isFile()&&fileName.startsWith(tag)) {
				int lastPoint=fileName.lastIndexOf(".");
				String date=fileName.substring(tag.length(), (lastPoint>0)?lastPoint:fileName.length()-1);
				//System.out.println("date:"+date);
				long timeStamp=TimeUtils.conversionDateToLong(date, TimeUtils.TimeFormatType.TIME_FOEMAT_Y_M_D);
				long curTime= System.currentTimeMillis();
				//System.out.println("timeStamp:"+timeStamp+"   curTimeStamp:"+curTime);
				if((curTime-timeStamp)>=timeLen*TimeUtils.UNIT_MS_DAY){
					String filePath=files[i].getAbsolutePath();
					//System.out.println("delete file:"+filePath);
					deleteFile(filePath);
				}
			}
		}
		return true;
	}
}