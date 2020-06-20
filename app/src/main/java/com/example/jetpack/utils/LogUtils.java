package com.example.jetpack.utils;

import android.util.Log;

import com.example.jetpack.BuildConfig;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

public final class LogUtils {
	/** 日志输出级别NONE */
	public static final int LEVEL_NONE = 0;
	/** 日志输出级别V */
	public static final int LEVEL_VERBOSE = 1;
	/** 日志输出级别D */
	public static final int LEVEL_DEBUG = 2;
	/** 日志输出级别I */
	public static final int LEVEL_INFO = 3;
	/** 日志输出级别W */                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
	public static final int LEVEL_WARN = 4;
	/** 日志输出级别E */
	public static final int LEVEL_ERROR = 5;

	/** 日志输出时的TAG */
	private static String mTag = "hao";
	/** 是否允许输出log */

	private static int mDebuggable = BuildConfig.DEBUG?5:0;

	/** 用于记时的变�??*/
	private static long mTimestamp = 6;
	/** 写文件的锁对�??*/
	private static final Object mLogLock = new Object();
	
	
	public static void dLong(String msg) {
		if (mDebuggable >= LEVEL_DEBUG) {
			//信息太长,分段打印
			//因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
			//  把4*1024的MAX字节打印长度改为2001字符数
			int max_str_length = 2001 - mTag.length();
			//大于4000时
			while (msg.length() > max_str_length) {
				Log.d(mTag, msg.substring(0, max_str_length));
				Log.e(mTag, "--------------------------------------");
				msg = msg.substring(max_str_length);
			}
			//剩余部分
			Log.d(mTag, msg);
		}
	}
	
	public static void iLong(String msg) {
		if (mDebuggable >= LEVEL_INFO) {
			//信息太长,分段打印
			//因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
			//  把4*1024的MAX字节打印长度改为2001字符数
			int max_str_length = 2001 - mTag.length();
			//大于4000时
			while (msg.length() > max_str_length) {
				Log.i(mTag, msg.substring(0, max_str_length));
				Log.e(mTag, "--------------------------------------");
				msg = msg.substring(max_str_length);
			}
			//剩余部分
			Log.i(mTag, msg);
		}
	}
		
		/** 以级别为 d 的形式输出LOG */
	public static void v(String msg) {
		if (mDebuggable >= LEVEL_VERBOSE) {
			Log.v(mTag, msg);
		}
	}

	/** 以级别为 d 的形式输出LOG */
	public static void d(String msg) {
		if (mDebuggable >= LEVEL_DEBUG) {
			Log.d(mTag, msg);
		}
	}

	/** 以级别为 i 的形式输出LOG */
	public static void i(String msg) {
		if (mDebuggable >= LEVEL_INFO) {
			Log.i(mTag, msg);
		}
	}

	/** 以级别为 w 的形式输出LOG */
	public static void w(String msg) {
		/*if (mDebuggable >= LEVEL_WARN) {
			Log.e(mTag, msg);
		}*/
		Log.e(mTag, msg);
	}

	/** 以级别为 w 的形式输出Throwable */
	public static void w(Throwable tr) {
		if (mDebuggable >= LEVEL_WARN) {
			Log.w(mTag, "", tr);
		}
	}

	/** 以级别为 w 的形式输出LOG信息和Throwable */
	public static void w(String msg, Throwable tr) {
		if (mDebuggable >= LEVEL_WARN && null != msg) {
			Log.w(mTag, msg, tr);
		}
	}

	/** 以级别为 e 的形式输出LOG */
	public static void e(String msg) {
		/*if (mDebuggable >= LEVEL_ERROR) {
			Log.e(mTag, msg);
		}*/
		Log.e(mTag, msg);
	}

	/** 以级别为 e 的形式输出Throwable */
	public static void e(Throwable tr) {
		if (mDebuggable >= LEVEL_ERROR) {
			Log.e(mTag, "", tr);
		}
	}
	
	public static String getErrorTrace(Throwable t) {
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer= stringWriter.getBuffer();
		return buffer.toString();
	}

	/** 以级别为 e 的形式输出LOG信息和Throwable */
	public static void e(String msg, Throwable tr) {
		if (mDebuggable >= LEVEL_ERROR && null != msg) {
			Log.e(mTag, msg, tr);
		}
	}

	/**
	 * 把Log存储到文件中
	 * 
	 * @param log
	 *            �??��存储的日�??
	 * @param path
	 *            存储路径
	 */
	public static void log2File(String log, String path) {
		log2File(log, path, true);
	}

	public static void log2File(String log, String path, boolean append) {
		synchronized (mLogLock) {
			//FileUtils.writeFile(log + "\r\n", path, append);
		}
	}

	/**
	 * 以级别为 e 的形式输出msg信息,附带时间戳，用于输出�??��时间段起始点
	 * 
	 * @param msg
	 *            �??��输出的msg
	 */
	public static void msgStartTime(String msg) {
		mTimestamp = System.currentTimeMillis();
        if (!StrUtils.IsKong(msg)) {
            e("[Started�?" + mTimestamp + "]" + msg);
        }
    }

	/** 以级别为 e 的形式输出msg信息,附带时间戳，用于输出�?个时间段结束�?* @param msg �?要输出的msg */
	public static void elapsed(String msg) {
		long currentTime = System.currentTimeMillis();
		long elapsedTime = currentTime - mTimestamp;
		mTimestamp = currentTime;
		e("[Elapsed�?" + elapsedTime + "]" + msg);
	}

	public static <T> void printList(List<T> list) {
		if (list == null || list.size() < 1) {
			return;
		}
		int size = list.size();
		i("---begin---");
		for (int i = 0; i < size; i++) {
			i(i + ":" + list.get(i).toString());
		}
		i("---end---");
	}

	public static <T> void printArray(T[] array) {
		if (array == null || array.length < 1) {
			return;
		}
		int length = array.length;
		i("---begin---");
		for (int i = 0; i < length; i++) {
			i(i + ":" + array[i].toString());
		}
		i("---end---");
	}
	
	public static int getDebuggableValue(){
		return mDebuggable;
	}
	public static void logeXSL(String msg){
		Log.d("xsl", msg);
	}
}
