package com.chenmual.util;

import java.io.*;

public class ReadStrUtil {
	public static void main(String[] args) throws IOException {
//		read();
		checkTime();
	}

	public static void read() throws IOException {
//		FileInputStream fileInputStream = new FileInputStream("S08E04_EN.srt");
		FileReader fileReader = new FileReader("S08E04_EN.srt");
//		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String str1 = null;
		boolean needSpace = false;
//		boolean needNum = true;
//		String[] strs = new String[10];
//		StringBuffer stringBuffer = new StringBuffer();
 		int count = 0;
		while((str1 = bufferedReader.readLine()) != null ){
			str1 = str1.trim();
			char c = 0;
			if(str1.length() > 0){
				c = str1.charAt(str1.length() - 1);
//				if(needNum){
//					try {
//						int a = Integer.parseInt(str1);
//						if(a != count + 1){
//							stringBuffer.append(str1);
//						}
//					}catch(Exception e){
//						System.out.println("exception");
//					}
//				}
			}
//			needNum = false;
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
//				new Scanner(System.in).nextLine();
					count++;
//					needNum = true;
				}else{
					System.out.println(str1);
					if(needSpace){
						needSpace = false;
					}
				}
			}
		}
//		System.out.println(stringBuffer.toString());
//		System.out.println(count);
		bufferedReader.close();
	}

	/**
	 * 对比两个str字幕 打印第一个的时间和第2个的字幕
	 * @throws IOException
	 */
	public static void checkTime() throws IOException {
		FileReader fileReader1 = new FileReader("S08E04_EN.srt");
		BufferedReader bufferedReader1 = new BufferedReader(fileReader1);

		FileReader fileReader2 = new FileReader("S08E04_CN.srt");
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
}
