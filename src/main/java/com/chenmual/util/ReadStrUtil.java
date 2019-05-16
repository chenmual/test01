package com.chenmual.util;

import java.io.*;

public class ReadStrUtil {
	public static String enfile = "S08E05_EN.srt";
	public static String cnfile = "S08E05_CN.srt";
	public static void main(String[] args) throws IOException {
//		read();
//		checkNum();
		checkTime();
	}

	/**
	 * 读取一个英文字幕 把所有非结尾换行整合成一行
	 * @throws IOException
	 */
	public static void read() throws IOException {
		FileReader fileReader = new FileReader(enfile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String str1 = null;
		boolean needSpace = false;
 		int count = 0;
		while((str1 = bufferedReader.readLine()) != null ){
			str1 = str1.trim();
			char c = 0;
			if(str1.length() > 0){
				c = str1.charAt(str1.length() - 1);
			}
			if(c <= 122 && c >= 97){
				System.out.print(str1 + " ");
				needSpace = true;
			}else{
				if("".equals(str1)){
					if(needSpace){
						System.out.println();
						needSpace = false;
					}
					System.out.println(str1);
					count++;
				}else{
					System.out.println(str1);
					if(needSpace){
						needSpace = false;
					}
				}
			}
		}
		bufferedReader.close();
	}

	public static void checkNum() throws IOException {
		FileReader fileReader = new FileReader(cnfile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String str1 = null;
		boolean needNum = true;
		StringBuffer stringBuffer = new StringBuffer();
		int countDown = 1;
		int sum = 1;
		while((str1 = bufferedReader.readLine()) != null ){
			str1 = str1.trim();
			countDown--;
			if(countDown == 0){
					try {
						int a = Integer.parseInt(str1);
						if(a != sum){
							stringBuffer.append(str1);
						}
					}catch(Exception e){
						System.out.println("exception:" + str1);
					}
			}
			if("".equals(str1)){
				countDown = 1;
				sum++;
			}
		}
		System.out.println(stringBuffer.toString());
		bufferedReader.close();
	}

	/**
	 * 对比两个str字幕 打印第一个的时间和第2个的字幕
	 * @throws IOException
	 */
	public static void checkTime() throws IOException {
		FileReader fileReader1 = new FileReader(enfile);
		BufferedReader bufferedReader1 = new BufferedReader(fileReader1);

		FileReader fileReader2 = new FileReader(cnfile);
		BufferedReader bufferedReader2 = new BufferedReader(fileReader2);

		int read1 = 2;
		int read2 = 2;
		String str = null;
		int stop = 2;
		boolean readType = false;
		while(true){
			if(readType){
				//读1
				str = bufferedReader1.readLine();
				if(str == null){
					stop--;
					readType = false;
					continue;
				}
				str = str.trim();

				if(read1 == 1){
					System.out.println(str);// + "aaaaa"
					readType = false;
					read1--;
				}else if(read1 == 2){
					read1--;
				}
				if("".equals(str)){
					read1 = 2;
				}
			}else{
				//读2
				str = bufferedReader2.readLine();
				if(str == null){
					stop--;
					if(stop == 0){
						break;
					}
					continue;
				}
				str = str.trim();
				if(read2 == 1){
					readType = true;
					read2--;
				}else if(read2 == 2){
					read2--;
					System.out.println(str);
				}else{
					System.out.println(str);
				}
				if("".equals(str)){
					read2 = 2;
				}
			}
		}

		bufferedReader1.close();
		bufferedReader2.close();
	}
	/**
	 *
	 str1.replace("：", ":");
	 str1.replace("（", "(");
	 str1.replace("）", ")");
	 str1.replace("？", "?");
	 */
}
