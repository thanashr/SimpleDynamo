package edu.buffalo.cse.cse486586.simpledynamo;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;
import static android.content.ContentValues.TAG;

public class SimpleDynamoProvider extends ContentProvider {
	static final int[] REMOTE_PORT_ARRAY = new int[] {11108, 11112, 11116, 11120, 11124};
	HashMap<String,Integer> shavalues = new HashMap<String, Integer>();
	static final int SERVER_PORT = 10000;
	private  Uri mUri;
	private static final String KEY_FIELD = "key";
	private static final String VALUE_FIELD = "value";
	static String myPort;
	String portStr="";
	static String singleport;
	String p1 = "";
	String p2 = "";
	String s1 = "";
	String s2 = "";
	static String type = "";
	static String succ1="";
	static String succ2="";
	static String pred1 ="";
	static String pred2 ="";
	static String currenthash = "";
	static int currentindex=0;
	String mp1 = "";
	String mp2 = "";
	String ms1 = "";
	String ms2 = "";
	private final ReentrantLock lockobj =new ReentrantLock();
	ArrayList<String> list = new ArrayList<String>();
	MatrixCursor testCursor = new MatrixCursor(new String[]{"key", "value"});
	public static final Object globalSyncObj = new Object();
	private Uri buildUri(String scheme, String authority) {
		Uri.Builder uriBuilder = new Uri.Builder();
		uriBuilder.authority(authority);
		uriBuilder.scheme(scheme);
		return uriBuilder.build();
	}

	@Override
	public synchronized int delete(Uri uri, String selection, String[] selectionArgs) {
		synchronized(this){
			if (selection.equals("@")) {
				try {
					File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort)/2 + "/");
					String contents[] = myObj.list();
					for (int i = 0; i < contents.length; i++) {
						try {
							File myObj1 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort)/2 + "/" + contents[i]);
							if (myObj1.delete()) {
//						Log.i("File", "deleted successfully");
							} else {
//						Log.i("File", " not deleted successfully");
							}
						} catch (Exception e) {
							Log.e("1111 login activity", "File not found: " + e.toString());
						}
					} } catch (Exception e) {
					Log.e("1111 login activity", "File not found: " + e.toString());
				}
				try {
					File myObj2 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred1) / 2 + "/");
					String contents2[] = myObj2.list();
					for (int i = 0; i < contents2.length; i++) {
						try {
							File myObj3 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred1) / 2 + "/" + contents2[i]);
							if (myObj3.delete()) {
//						Log.i("File", "deleted successfully");
							} else {
//						Log.i("File", " not deleted successfully");
							}
						} catch (Exception e) {
							Log.e("login activity", "File not found: " + e.toString());
						}
					}
				} catch (Exception e) {
					Log.e("1111 login activity", "File not found: " + e.toString());
				}
				try {
					File myObj4 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred2) / 2 + "/");
					String contents3[] = myObj4.list();
					for (int i = 0; i < contents3.length; i++) {
						try {
							File myObj5 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred2) / 2 + "/" + contents3[i]);
							if (myObj5.delete()) {
//						Log.i("File", "deleted successfully");
							} else {
//						Log.i("File", " not deleted successfully");
							}
						} catch (Exception e) {
							Log.e("login activity", "File not found: " + e.toString());
						}
					}
				} catch (Exception e) {
					Log.e("login activity", "Can not read file: " + e.toString());
				}
			}
			else if (selection.equals("*")) {
				try {
					File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort) / 2 + "/");
					String contents[] = myObj.list();
					for (int i = 0; i < contents.length; i++) {
						try {
							File myObj1 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort) / 2 + "/" + contents[i]);
							if (myObj1.delete()) {
//							Log.i("File", "deleted successfully");
							} else {
//							Log.i("File", " not deleted successfully");
							}
						} catch (Exception e) {
							Log.e("login activity", "File not found: " + e.toString());
						}
					}
				} catch (Exception e) {
					Log.e("1111 login activity", "File not found: " + e.toString());
				}
				try {
					File myObj2 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred1) / 2 + "/");
					String contents2[] = myObj2.list();
					for (int i = 0; i < contents2.length; i++) {
						try {
							File myObj3 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred1) / 2 + "/" + contents2[i]);
							if (myObj3.delete()) {
//							Log.i("File", "deleted successfully");
							} else {
//							Log.i("File", " not deleted successfully");
							}
						} catch (Exception e) {
							Log.e("login activity", "File not found: " + e.toString());
						}
					}
				} catch (Exception e) {
					Log.e("1111 login activity", "File not found: " + e.toString());
				}
				try {
					File myObj4 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/"+shavalues.get(pred2)/2+"/");
					String contents3[] = myObj4.list();
					for(int i=0; i<contents3.length; i++) {
						try {
							File myObj5 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred2) / 2 + "/" + contents3[i]);
							if (myObj5.delete()) {
//							Log.i("File", "deleted successfully");
							} else {
//							Log.i("File", " not deleted successfully");
							}
						} catch (Exception e) {
							Log.e("login activity", "File not found: " + e.toString());
						}
					}
				} catch (Exception e) {
					Log.e("login activity", "77 File not found: " + e.toString());
				}
				try {
					Socket socket81 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ1));
					String msgToSend81 = "deletingStar," ;                         // sending msg : deletingStar
					DataOutputStream output81 = new DataOutputStream(socket81.getOutputStream());
//				Log.i(TAG," Sending from deleteStar to succ : "+msgToSend81);
					output81.writeUTF(msgToSend81);
				}  catch (IOException e) {
					try {
						Log.e("Exception", "2 File write failed: " + e.toString());
						Socket socket82 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ2));
						String msgToSend82 = "deletingStar,";                         // sending msg : deletingStar
						DataOutputStream output82 = new DataOutputStream(socket82.getOutputStream());
//				Log.i(TAG," Sending from deleteStar to succ : "+msgToSend82);
						output82.writeUTF(msgToSend82);
					} catch(Exception ae){
						Log.e("login activity", "77 File not found: " + ae.toString());
					}
				}
			}
			else{
				String originalnode = "";
				int originalport=0;
				String present_key=selection; //k
				String present_key_hash = "";
				String thisPorthash = "";
				try {
					present_key_hash = genHash(present_key);   // hashk
				} catch (Exception e) {
				}
//			Log.i(TAG, "-------------in Insert-----Key, keyhashhhh-" + present_key + "val : " + present_key_hash);
				for (int item = 0 ; item < list.size(); item ++) { // 4 0
					try {
//				Log.i(TAG, "first item " + list.get(0) + " ," + item + " " + list.size());
						String predecessor1 = "";
						String predecessor2 = "";
						String successor1 = "";
						String successor2 = "";
						String currentnode = list.get(item);    //1ghjkoiuhg``
						int currentPort = shavalues.get(currentnode);
						Log.i(TAG, " currentnode , currentPort: " + currentnode + "," + currentPort + "," + item + "," + myPort);
						if (item > 0) {  // 1 2 3 4
							if (item > 1) {   // 2 3 4 5
								predecessor1 = list.get(item - 1);
								predecessor2 = list.get(item - 2);
							} else  // 1
							{
								predecessor1 = list.get(item - 1);
								predecessor2 = list.get(list.size() - 1);
							}
						} else //  0
						{
							predecessor1 = list.get(list.size() - 1);
							predecessor2 = list.get(list.size() - 2);
						}
						String firstnode = list.get(0);
						if (item < list.size() - 1) { // 0 1 2 3
							if (item < list.size() - 2) // 0 1 2
							{
								successor1 = list.get(item + 1);
								successor2 = list.get(item + 2);
							} else   //3
							{
								successor1 = list.get(item + 1);
								successor2 = list.get(0);
							}
						} else //4
						{
							successor1 = list.get(0);
							successor2 = list.get(1);
						}

						Log.e(TAG, "pred succ values" + "," + item + "," + predecessor1 + "," + predecessor2 + "," + successor1 + "," + successor2);
						// presentkey 		presentkeyhash	 	presentvalue : befroe for loop
						// currentnode - 33djhfgvad        currentport - 11108    predecessor1  p2   s1  s2  firstnode     : in for loop
						int rangecheck1 = predecessor1.compareTo(present_key_hash);  //neg        // -1 greater than predecessor1....1 lesser than predecessor1
						int rangecheck2 = currentnode.compareTo(present_key_hash);   //pos or eq       // -1 greater than currentnode....1 lesser than currentnode
						if ((rangecheck2 >= 0 && rangecheck1 <= 0) || ((predecessor1.compareTo(currentnode) >= 0) && (rangecheck1 <= 0)) || ((predecessor1.compareTo(currentnode) >= 0) && rangecheck2 >= 0)) { // in range
							originalnode = currentnode;
							originalport = currentPort;
							p1 = predecessor1;
							p2 = predecessor2;
							s1 = successor1;
							s2 = successor2;
							Log.i(TAG, " in range--original node,port---: " + originalnode+originalport);
							Log.i(TAG, " in range--p1,p2,s1,s2---: " + p1+p2+s1+s2);
							break;
						}
					} catch (Exception e) {
						Log.e(TAG, "ServerTask UnknownHostException" + e.getMessage());
					}
				}
				try {
					thisPorthash = genHash(portStr);
				}catch (Exception e) {
					Log.e(TAG, "ServerTask UnknownHostException" + e.getMessage());
				}

				if (thisPorthash.equals(originalnode)){
					try {

						File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort) / 2 + "/" + selection);
						if (myObj.delete()) {
							Log.i("File", "deleted key successfully");
						} else {
							Log.i("File", " not deleted key successfully");
						}
					} catch (Exception e) {
						Log.e("login activity", "File not found: " + e.toString());
					}
					Log.i(TAG, " connectng succ1: " + shavalues.get(succ1));
					try{
						Socket socket10 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ1));
						String msgToSend10 = "DeletingKey" + "," + present_key + "," + present_key_hash + "," + Integer.parseInt(myPort) / 2;
						DataOutputStream output10 = new DataOutputStream(socket10.getOutputStream());
						Log.i(TAG, " sending msg to s1 from delete " + msgToSend10);
						output10.writeUTF(msgToSend10);
						String msg10 = "";
						DataInputStream inp10 = new DataInputStream(socket10.getInputStream());                          // getting msg :   done             <if no msg> ioexception type="single"
						msg10 = inp10.readUTF();
						Log.i(TAG, " got msg from s1 in delete: " + msg10);
						socket10.close();
					} catch (Exception e) {
						Log.e("login activity", "File not found: " + e.toString());
					}
					try{

						Log.i(TAG," connectng succ2: "+ shavalues.get(succ2));
						Socket socket12 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ2));
						String msgToSend12 = "DeletingKey" + "," + present_key + "," + present_key_hash  + ","+Integer.parseInt(myPort)/2 ;
						DataOutputStream output12 = new DataOutputStream(socket12.getOutputStream());
						Log.i(TAG," sendng msg to s2 from delete "+ msgToSend12);
						output12.writeUTF(msgToSend12);
						String msg12 = "";
						DataInputStream inp12 = new DataInputStream(socket12.getInputStream());                          // getting msg :   done             <if no msg> ioexception type="single"
						msg12 = inp12.readUTF();
						Log.i(TAG," got msg from s2 from delete: "+ msg12);
						socket12.close();
					} catch (IOException e) {
						Log.e("Exception", " 4 File write failed: " + e.toString());
					}
				}
				else{          // out of range
					try {  // send msg : DeletingKey , presentkey , prehashkey
						Log.i("Exception", " Original Port" + originalport);
						Socket socket13 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), originalport);
						String msgToSend13 = "DeletingKey" + "," + present_key + "," + present_key_hash + "," + originalport / 2;
						DataOutputStream output13 = new DataOutputStream(socket13.getOutputStream());
						Log.i(TAG, " sendng to original in delete: " + msgToSend13);
						output13.writeUTF(msgToSend13);
						String msg13 = "";
						DataInputStream inp13 = new DataInputStream(socket13.getInputStream());                          // getting msg :   one value             <if no msg> ioexception type="single"
						msg13 = inp13.readUTF();
						Log.i(TAG, " got from original in delete: " + msg13);
						socket13.close();
					} catch (Exception e) {
						Log.e("login activity", "File not found: " + e.toString());
					}
					try {

						Log.i(TAG, " connectng succ1: " + shavalues.get(s1));
						Socket socket14 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(s1));
						String msgToSend14 = "DeletingKey" + "," + present_key + "," + present_key_hash + "," + originalport / 2;
						DataOutputStream output14 = new DataOutputStream(socket14.getOutputStream());
						Log.i(TAG, " sending msg to s1 from delete " + msgToSend14);
						output14.writeUTF(msgToSend14);
						String msg14 = "";
						DataInputStream inp14 = new DataInputStream(socket14.getInputStream());                          // getting msg :   done             <if no msg> ioexception type="single"
						msg14 = inp14.readUTF();
						Log.i(TAG, " got msg from s1 in delete: " + msg14);
						socket14.close();
					} catch (Exception e) {
						Log.e("login activity", "File not found: " + e.toString());
					}
					try{


						Log.i(TAG," connectng succ2: "+ shavalues.get(s2));
						Socket socket15 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(s2));
						String msgToSend15 = "DeletingKey" + "," + present_key + "," + present_key_hash  + ","+originalport/2 ;
						DataOutputStream output15 = new DataOutputStream(socket15.getOutputStream());
						Log.i(TAG," sendng msg to s2 from delete "+ msgToSend15);
						output15.writeUTF(msgToSend15);
						String msg15 = "";
						DataInputStream inp15 = new DataInputStream(socket15.getInputStream());                          // getting msg :   done             <if no msg> ioexception type="single"
						msg15 = inp15.readUTF();
						Log.i(TAG," got msg from s2 from delete: "+ msg15);
						socket15.close();

					} catch (IOException e) {
						Log.e("Exception", " 4 File write failed: " + e.toString());
					}


				}
			}


			// TODO Auto-generated method stub
			return 0;
		} }

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized Uri insert(Uri uri, ContentValues values) {
		synchronized (this) {


//		lock.lock();
//		if (lock.isHeldByCurrentThread()) {
//			lock.unlock();
//		}

			Log.i(TAG, "----------initial---in Insert---list---is : " + list);
			String originalnode = "";
			int originalport = 0;
			String present_key = (String) values.get("key"); //k
			String present_key_hash = "";
			String thisPorthash = "";
			try {
				present_key_hash = genHash(present_key);   // hashk
			} catch (Exception e) {

			}
			String present_value = (String) values.get("value"); //v
			Log.e(TAG, "-------------in Insert-----Key, keyhashhhh-" + present_key + "val : " + present_key_hash);

			//inside itself

			for (int item = 0; item < list.size(); item++) { // 4 0
				try {
//				Log.i(TAG, "first item " + list.get(0) + " ," + item + " " + list.size());
					String predecessor1 = "";
					String predecessor2 = "";
					String successor1 = "";
					String successor2 = "";
					String currentnode = list.get(item);    //1ghjkoiuhg``
					int currentPort = shavalues.get(currentnode);
					Log.i(TAG, " currentnode , currentPort: " + currentnode + "," + currentPort + "," + item + "," + myPort);

					if (item > 0) {  // 1 2 3 4
						if (item > 1) {   // 2 3 4 5
							predecessor1 = list.get(item - 1);
							predecessor2 = list.get(item - 2);
//						Log.i(TAG, " item grt 0 , predecessor1: " + predecessor1);
						} else  // 1
						{
							predecessor1 = list.get(item - 1);
							predecessor2 = list.get(list.size() - 1);
						}
					} else //  0
					{
						predecessor1 = list.get(list.size() - 1);
						predecessor2 = list.get(list.size() - 2);
					}
					String firstnode = list.get(0);
					if (item < list.size() - 1) { // 0 1 2 3
						if (item < list.size() - 2) // 0 1 2
						{
							successor1 = list.get(item + 1);
							successor2 = list.get(item + 2);

//						Log.i(TAG, " item less than size , succ1: " + successor1);
						} else   //3
						{
							successor1 = list.get(item + 1);
							successor2 = list.get(0);
						}
					} else //4
					{
						successor1 = list.get(0);
						successor2 = list.get(1);
					}

					Log.e(TAG, "pred succ values" + "," + item + "," + predecessor1 + "," + predecessor2 + "," + successor1 + "," + successor2);
					// presentkey 		presentkeyhash	 	presentvalue : befroe for loop
					// currentnode - 33djhfgvad        currentport - 11108    predecessor1  p2   s1  s2  firstnode     : in for loop
					int rangecheck1 = predecessor1.compareTo(present_key_hash);  //neg        // -1 greater than predecessor1....1 lesser than predecessor1
					int rangecheck2 = currentnode.compareTo(present_key_hash);   //pos or eq       // -1 greater than currentnode....1 lesser than currentnode
					if ((rangecheck2 >= 0 && rangecheck1 <= 0) || ((predecessor1.compareTo(currentnode) >= 0) && (rangecheck1 <= 0)) || ((predecessor1.compareTo(currentnode) >= 0) && rangecheck2 >= 0)) { // in range

						originalnode = currentnode;
						originalport = currentPort;
						mp1 = predecessor1;
						mp2 = predecessor2;
						ms1 = successor1;
						ms2 = successor2;
						Log.i(TAG, " in range--original node,port---: " + originalnode + originalport);
						Log.i(TAG, " in range-m-p1,p2,s1,s2---: " + mp1 + mp2 + ms1 + ms2);
						break;
					}

				} catch (Exception e) {
					Log.e(TAG, "ServerTask UnknownHostException" + e.getMessage());
				}
			}
			try {
				thisPorthash = genHash(portStr);
			} catch (Exception e) {
				Log.e(TAG, "ServerTask UnknownHostException" + e.getMessage());
			}

			if (thisPorthash.equals(originalnode)) {
				try {
					File Files = new File(getContext().getApplicationInfo().dataDir + "/files/" + (Integer.parseInt(myPort) / 2) + "/" + present_key);
					Log.i(TAG, " 16created file at Insert: " + Files);
					//delete file before write
					if (Files.delete()) {
						Log.i("File", "deleted key successfully");
					} else {
						Log.i("File", " not deleted key successfully");
					}

//						Log.i(TAG," deleted file: "+ first);

					BufferedWriter buf1 = new BufferedWriter(new FileWriter(Files, true));
					buf1.append(present_value);
					Log.i(TAG, " Buffer1 in file: " + buf1.toString());
					//buf.newLine();  // pointer will be nextline
					buf1.close();

				}catch (Exception e) {
					Log.e(TAG, " 001 ServerTask UnknownHostException" + e.getMessage());
				}

// send msg : Insertion , presentkey , prehashkey , prevalue , foldername
				try {
					Log.i(TAG, " connectng succ1: " + shavalues.get(succ1));
					Socket socket16 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ1));
					String msgToSend16 = "Insertion" + "," + present_key + "," + present_key_hash + "," + present_value + "," + Integer.parseInt(myPort) / 2;
					DataOutputStream output16 = new DataOutputStream(socket16.getOutputStream());
					Log.i(TAG, " sending msg to s1 from insert " + msgToSend16);

					output16.writeUTF(msgToSend16);
					String msg16 = "";
					DataInputStream inp16 = new DataInputStream(socket16.getInputStream());                          // getting msg :   done             <if no msg> ioexception type="single"
					msg16 = inp16.readUTF();
					Log.i(TAG, " got msg from s1 in insert: " + msg16);
					socket16.close();
				}catch (IOException e) {
					Log.e("Exception", " 4 File write failed: " + e.toString());
				}
				try {
					Log.i(TAG, " connectng succ2: " + shavalues.get(succ2));
					Socket socket17 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ2));
					String msgToSend17 = "Insertion" + "," + present_key + "," + present_key_hash + "," + present_value + "," + Integer.parseInt(myPort) / 2;
					DataOutputStream output17 = new DataOutputStream(socket17.getOutputStream());
					Log.i(TAG, " sendng msg to s2 from insert " + msgToSend17);

					output17.writeUTF(msgToSend17);
					String msg17 = "";
					DataInputStream inp17 = new DataInputStream(socket17.getInputStream());                          // getting msg :   done             <if no msg> ioexception type="single"
					msg17 = inp17.readUTF();
					Log.i(TAG, " got msg from s2 from insert: " + msg17);
					socket17.close();
				} catch (IOException e) {
					Log.e("Exception", " 4 File write failed: " + e.toString());
				}



			} else {
				try {  // send msg : Insertion , presentkey , prehashkey , prevalue , original port
					Log.i("Exception", " Original Port" + originalport);
					Socket socket18 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), originalport);
					String msgToSend18 = "Insertion" + "," + present_key + "," + present_key_hash + "," + present_value + "," + originalport / 2;
					DataOutputStream output18 = new DataOutputStream(socket18.getOutputStream());
					Log.i(TAG, " sendng to original in insert: " + msgToSend18);
					output18.writeUTF(msgToSend18);
					String msg18 = "";
					DataInputStream inp18 = new DataInputStream(socket18.getInputStream());                          // getting msg :   done             <if no msg> ioexception type="single"
					msg18 = inp18.readUTF();
					Log.i(TAG, " got from original in insert: " + msg18);
					socket18.close();
				} catch (IOException e) {
					Log.e("Exception", " 4 File write failed: " + e.toString());
				}

				// send msg : Insertion , presentkey , prehashkey , prevalue , foldername
				try{
					Socket socket4 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(ms1));
					String msgToSend4 = "Insertion" + "," + present_key + "," + present_key_hash + "," + present_value + "," + originalport / 2;
					DataOutputStream output4 = new DataOutputStream(socket4.getOutputStream());
					Log.i(TAG, "sendng  msg " +  shavalues.get(ms1) + ":::"+msgToSend4);
					output4.writeUTF(msgToSend4);
					String msg4 = "";
					DataInputStream inp4 = new DataInputStream(socket4.getInputStream());                          // getting msg :   done             <if no msg> ioexception type="single"
					msg4 = inp4.readUTF();
					Log.i(TAG, " got msg " + msg4);
					socket4.close(); }
				catch (IOException e) {
					Log.e("Exception", " 4 File write failed: " + e.toString());
				}
				try{
					Log.i(TAG, " next2 " + shavalues.get(ms2));
					Socket socket5 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(ms2));
					String msgToSend5 = "Insertion" + "," + present_key + "," + present_key_hash + "," + present_value + "," + originalport / 2;
					DataOutputStream output5 = new DataOutputStream(socket5.getOutputStream());
					Log.i(TAG, "sendng  msg " +  shavalues.get(ms2) + ":::"+ msgToSend5);
					output5.writeUTF(msgToSend5);
					String msg5 = "";
					DataInputStream inp5 = new DataInputStream(socket5.getInputStream());                          // getting msg :   done             <if no msg> ioexception type="single"
					msg5 = inp5.readUTF();
//						Log.i(TAG," got msg "+ msg5);
					socket5.close();


				} catch (IOException e) {
					Log.e("Exception", " 4 File write failed: " + e.toString());
				}
			}

			return null;
		}
	}
	private class ClientTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... msgs) {



			try{
				String message3 = "";
				String got_key3 = "";
				String got_value3 = "";

				try {
					Socket socket3 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ1));      //connecting to  succ from port X
					Log.i(TAG, " Inside Client connected to " + shavalues.get(succ1));
					String msgToSend3 = "duplicates," + Integer.parseInt(myPort) / 2;                                                              // sending msg : duplicates, X
					DataOutputStream output3 = new DataOutputStream(socket3.getOutputStream());
					output3.writeUTF(msgToSend3);
					Log.i(TAG, "  Client sending duplicate message : " + msgToSend3);


					DataInputStream inp3 = new DataInputStream(socket3.getInputStream());                          // getting msg :   duplicates
					message3 = inp3.readUTF();
					Log.i(TAG, " 5 Client got duplicates  : " + message3);
					output3.close();
					inp3.close();
					socket3.close();
				} catch(Exception e){
					Socket socket73 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ2));      //connecting to  succ from port X
					Log.i(TAG, " Inside Client connected to " + shavalues.get(succ2));
					String msgToSend3 = "duplicates," + Integer.parseInt(myPort) / 2;                                                              // sending msg : duplicates, X
					DataOutputStream output73 = new DataOutputStream(socket73.getOutputStream());
					output73.writeUTF(msgToSend3);
					Log.i(TAG, "  Client sending duplicate message : " + msgToSend3);


					DataInputStream inp73 = new DataInputStream(socket73.getInputStream());                          // getting msg :   duplicates
					message3 = inp73.readUTF();
					Log.i(TAG, " 5 Client got duplicates  : " + message3);
					output73.close();
					inp73.close();
					socket73.close();
				}
				if (message3 != null && message3 != "" && !message3.isEmpty() && message3.length() != 0 && !message3.equalsIgnoreCase(null) && !message3.equalsIgnoreCase("null") &&
						!message3.equalsIgnoreCase("NULL")) {
					String[] compound3 = message3.split(",");
					for (int i = 0; i < compound3.length; i++) {    //  key1 : value1
						String[] oneMsg = compound3[i].split(":");
						got_key3 = oneMsg[0];
						got_value3 = oneMsg[1];

						Log.e(TAG," in server Insertion: "+ got_key3 +","+ got_value3);
						try {
							File Files3 = new File(getContext().getApplicationInfo().dataDir + "/files/" + Integer.parseInt(myPort) / 2 + "/" + got_key3);
							if (Files3.delete()) {
							}
							Log.e(TAG, " ---inserting!!!! at oncreate: " + got_key3);
							BufferedWriter buf3 = new BufferedWriter(new FileWriter(Files3, true));
							Log.i(TAG, " Value: " + got_value3);
							buf3.append(got_value3);
							buf3.close();
							Log.i(TAG, " appended in file: " + Files3);
						} 	catch (Exception e) {
							Log.e(TAG, "ClientTaskException");
						}
					}
				}
			}
			catch (Exception e){
				Log.i(TAG,"First succ");
			}
			String message9 = "";
			String got_key9 = "";
			String got_value9 = "";
			try {


				Socket socket9 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(pred1));      //connecting to  succ from port X
				Log.i(TAG, " Inside Client connected to " + shavalues.get(pred1));
				String msgToSend9 = "duplicates," + shavalues.get(pred1) / 2;                                                              // sending msg : duplicates, X
				DataOutputStream output9 = new DataOutputStream(socket9.getOutputStream());
				output9.writeUTF(msgToSend9);
				Log.i(TAG, "  Client sending duplicate message : " + msgToSend9);

				DataInputStream inp9 = new DataInputStream(socket9.getInputStream());                          // getting msg :   duplicates
				message9 = inp9.readUTF();
				Log.i(TAG, " 5 Client got duplicates  : " + message9);
				output9.close();
				inp9.close();
				socket9.close();
			} catch(Exception e){
				try{
					Socket socket73 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ1));      //connecting to  succ from port X
					Log.i(TAG, " Inside Client connected to " + shavalues.get(succ1));
					String msgToSend3 = "duplicates," + shavalues.get(pred1) / 2;                                                              // sending msg : duplicates, X
					DataOutputStream output73 = new DataOutputStream(socket73.getOutputStream());
					output73.writeUTF(msgToSend3);
					Log.i(TAG, "  Client sending duplicate message : " + msgToSend3);


					DataInputStream inp73 = new DataInputStream(socket73.getInputStream());                          // getting msg :   duplicates
					message9 = inp73.readUTF();
					Log.i(TAG, " 5 Client got duplicates  : " + message9);
					output73.close();
					inp73.close();
					socket73.close(); } catch (Exception we) {
					Log.e(TAG, "ClientTaskException");
				}
			}
			if (message9 != null && message9 != "" && !message9.isEmpty() && message9.length() != 0 && !message9.equalsIgnoreCase(null) && !message9.equalsIgnoreCase("null") &&
					!message9.equalsIgnoreCase("NULL")) {
				String[] compound9 = message9.split(",");
				for (int i = 0; i < compound9.length; i++) {    //  key1 : value1
					String[] oneMsg = compound9[i].split(":");
					got_key9 = oneMsg[0];
					got_value9 = oneMsg[1];

					Log.e(TAG, " in server Insertion: " + got_key9);
					try {
						File Files9 = new File(getContext().getApplicationInfo().dataDir + "/files/" + shavalues.get(pred1) / 2 + "/" + got_key9);
						if (Files9.delete()) {
						} else {
						}
						Log.e(TAG, " ---inserting!!!! at oncreate: " + got_key9);
						BufferedWriter buf9 = new BufferedWriter(new FileWriter(Files9, true));
						buf9.append(got_value9);
						buf9.close();
						Log.i(TAG, " appended in file: " + Files9);
					} catch (Exception e) {
						Log.e(TAG, "ClientTaskException");
					}
				}
			}
			String message11 = "";
			String got_key11 = "";
			String got_value11 = "";
			try {


				Socket socket11 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(pred2));      //connecting to  succ from port X
				Log.i(TAG, " Inside Client connected to " + shavalues.get(pred2));
				String msgToSend11 = "duplicates," + shavalues.get(pred2) / 2;                                                              // sending msg : duplicates, X
				DataOutputStream output11 = new DataOutputStream(socket11.getOutputStream());
				output11.writeUTF(msgToSend11);
				Log.i(TAG, "  Client sending duplicate message : " + msgToSend11);

				DataInputStream inp11 = new DataInputStream(socket11.getInputStream());                          // getting msg :   duplicates
				message11 = inp11.readUTF();
				Log.i(TAG, " 5 Client got duplicates  : " + message11);
				output11.close();
				inp11.close();
				socket11.close();
			}  catch(Exception e){
				try{
					Socket socket73 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(pred1));      //connecting to  succ from port X
					Log.i(TAG, " Inside Client connected to " + shavalues.get(pred1));
					String msgToSend3 = "duplicates," + shavalues.get(pred2) / 2;                                                              // sending msg : duplicates, X
					DataOutputStream output73 = new DataOutputStream(socket73.getOutputStream());
					output73.writeUTF(msgToSend3);
					Log.i(TAG, "  Client sending duplicate message : " + msgToSend3);


					DataInputStream inp73 = new DataInputStream(socket73.getInputStream());                          // getting msg :   duplicates
					message11 = inp73.readUTF();
					Log.i(TAG, " 5 Client got duplicates  : " + message11);
					output73.close();
					inp73.close();
					socket73.close(); } catch (Exception ae) {
					Log.e(TAG, "ClientTaskException");
				}
			}
			if (message11 != null && message11 != "" && !message11.isEmpty() && message11.length() != 0 && !message11.equalsIgnoreCase(null) && !message11.equalsIgnoreCase("null") &&
					!message11.equalsIgnoreCase("NULL")) {
				String[] compound11 = message11.split(",");


				for (int i = 0; i < compound11.length; i++) {    //  key1 : value1
					String[] oneMsg = compound11[i].split(":");
					got_key11 = oneMsg[0];
					got_value11 = oneMsg[1];

					Log.e(TAG, " in server Insertion: " + got_key11);
					try {

						File Files7 = new File(getContext().getApplicationInfo().dataDir + "/files/" + shavalues.get(pred2) / 2 + "/" + got_key11);
						if (Files7.delete()) {
						} else {
						}
						Log.e(TAG, " ---inserting!!!! at oncreate: " + got_key11);
						BufferedWriter buf7 = new BufferedWriter(new FileWriter(Files7, true));
						buf7.append(got_value11);
						buf7.close();
						Log.i(TAG, " appended in file: " + Files7);
					} catch (Exception e) {
						Log.e(TAG, "ClientTaskException");
					}
				}
			}




			return null;
		}
		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			if (lockobj.isHeldByCurrentThread()) {
				lockobj.unlock();
			}
		}
	}

	private class ServerTask extends AsyncTask<ServerSocket, String, Void> {

		@Override
		protected Void doInBackground(ServerSocket... sockets) {
			ServerSocket serverSocket = sockets[0];
			String messagetosend="";
//			Log.i(TAG,"Inside server .myPort is"+myPort + "hy");

			while(true) {
//				Log.i(TAG,"---2--Inside server .myPort is"+myPort + "hy");
				Socket socket = null;
				lockobj.lock();
				if (lockobj.isHeldByCurrentThread()) {
					lockobj.unlock();
				}
				try {

					socket = serverSocket.accept();
//					Log.i(TAG,"----4---Inside server .myPort is"+myPort + "hy");
					DataInputStream input = new DataInputStream(socket.getInputStream());
					DataOutputStream output = new DataOutputStream(socket.getOutputStream());
					String gotmessage = "";

					gotmessage = input.readUTF();                 // got messsage :    insertion , pkey , pkey_hash ,  pvalue,folder
					Log.i(TAG," S3 GOT MESSAGEs FROM OTHER PORTS :--------"+gotmessage);
					if(gotmessage == null || gotmessage == "")
						continue;
					String[] msg_array = gotmessage.split(",");                   // split gotmessage
					String message = msg_array[0];

//					if(message.equals("QueryingOriginal")){  // QueryingOriginal , presentkey , prehashkey, originalport
//						String data = "";
//						try {
//							try {
//
//								File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + msg_array[3] + "/" + msg_array[1]);
//								Scanner myReader = new Scanner(myObj);
////								Log.i("reader", String.valueOf(myReader));
//
//								while (myReader.hasNextLine()) {
//									data = myReader.nextLine();
////									Log.i("data", data);
//								}
//
//
//							} catch (FileNotFoundException e) {
//								Log.e("login activity", "File not found: " + e.toString());
//							}
//
//							output.writeUTF(data);
//							input.close();
//							output.close();
//							socket.close();
//
//
//
//						} catch (IOException e) {
//							Log.e("Exception", " 4 File write failed: " + e.toString());
//						}
//
//
//					}
					if(message.equals("duplicates")){    // duplicates , parentPort
						String ParentPort = msg_array[1];
						String result = "";


						String data = " ";
						File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(ParentPort)  + "/");
						if(myObj.isDirectory()) {
							String contents[] = myObj.list();
							for (int i = 0; i < contents.length; i++) {
								File myObj1 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(ParentPort) + "/" + contents[i]);
								Scanner myReader = new Scanner(myObj1);
//								Log.i("reader", String.valueOf(myReader));
								while (myReader.hasNextLine()) {
									data = myReader.nextLine();
//									Log.i("data", data);
								}
								result = result + contents[i] + ":" + data + ",";
							}
						}
						Log.i("S sendng dupli", result);
						output.writeUTF(result);   // sending key1:value1 , key2 : value2 , key3 : value3
						input.close();
						output.close();
						socket.close();


					}
					else if(message.equals("queryingStar")){  // queryingStar , port
						String startport = msg_array[1];
						String result = "";
						String res_message = "";
//						Log.e(TAG," 77 inside query star "+startport);
						try {


							String data = " ";
							File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort) / 2 + "/");
							if(myObj.isDirectory()) {
								String contents[] = myObj.list();
								for (int i = 0; i < contents.length; i++) {
									File myObj1 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort) / 2 + "/" + contents[i]);
									Scanner myReader = new Scanner(myObj1);
//								Log.i("reader", String.valueOf(myReader));
									while (myReader.hasNextLine()) {
										data = myReader.nextLine();
//									Log.i("data", data);
									}
									result = result + contents[i] + ":" + data + ",";
								}
							} } catch (Exception e) {
							Log.e("111 login activity", "77 File not found: " + e.toString());
						}
						try { String data = "";
							File myObj2 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred1) / 2 + "/");
							String contents2[] = myObj2.list();
							for (int i = 0; i < contents2.length; i++) {
								File myObj3 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred1) / 2 + "/" + contents2[i]);
								Scanner myReader = new Scanner(myObj3);
//								Log.i("reader", String.valueOf(myReader));
								while (myReader.hasNextLine()) {
									data = myReader.nextLine();
//									Log.i("data", data);
								}
								result= result+contents2[i]+":"+data+",";
							} } catch (Exception e) {
							Log.e("login activity", "112 77 File not found: " + e.toString());
						}
						try {
							String data = "";
							File myObj4 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred2) / 2 + "/");
							String contents3[] = myObj4.list();
							for (int i = 0; i < contents3.length; i++) {
								File myObj5 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred2) / 2 + "/" + contents3[i]);
								Scanner myReader = new Scanner(myObj5);
//								Log.i("reader", String.valueOf(myReader));
								while (myReader.hasNextLine()) {
									data = myReader.nextLine();
//									Log.i("data", data);
								}
								result = result + contents3[i] + ":" + data + ",";
							}
						} catch (Exception e) {
							Log.e("113 login activity", "77 File not found: " + e.toString());
						}


//							return qCursor;



//
//						Log.e(TAG," current before server port msg result : "+result );
//						Log.e(TAG," checkig succ : "+startport );
//						Log.e(TAG," checkig succ : "+shavalues.get(succ1) );
						try{
							if(!(shavalues.get(succ1).toString().equals(startport))) {
								Socket socket1 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ1));
								String msgToSend = "queryingStar," + startport ;                         // sending msg : queryingStar
								DataOutputStream output1 = new DataOutputStream(socket1.getOutputStream());
//							Log.i(TAG," 77 Sending from query to succ : "+msgToSend);
								output1.writeUTF(msgToSend);
								DataInputStream inp1 = new DataInputStream(socket1.getInputStream());

								res_message = inp1.readUTF();
//							Log.i(TAG," getting message from succ : "+res_message);
								inp1.close();
								output1.close();
								socket1.close();

							} } catch (Exception e) {
							Log.e("login activity", "77 File not found: " + e.toString());
							try{
								if(!(shavalues.get(succ2).toString().equals(startport))) {    // heree
									Socket socket45 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ2));
									String msgToSend45 = "queryingStar," + startport;                         // sending msg : queryingStar
									DataOutputStream output45 = new DataOutputStream(socket45.getOutputStream());
//							Log.i(TAG," 77 Sending from query to succ : "+msgToSend);
									output45.writeUTF(msgToSend45);
									DataInputStream inp45 = new DataInputStream(socket45.getInputStream());
									res_message = inp45.readUTF();
//							Log.i(TAG," getting message from succ : "+res_message);
									inp45.close();
									output45.close();
									socket45.close();
								}
							}catch (Exception ae) {
								Log.e("login activity", "77 File not found: " + ae.toString()); } }
//						Log.e(TAG," current port msg result : "+result);
						String sendmessage = result;
						if( result != null && result != "")
							sendmessage = result + res_message;
						else
							sendmessage = res_message;
//						Log.e(TAG," sending result to pred : "+sendmessage);
						output.writeUTF(sendmessage);   // sending key1:value1 , key2 : value2 , key3 : value3
						input.close();
						output.close();
						socket.close();
					}
					else if(message.equals("Querying")){   // QueryingAt , presentkey , prehashkey , foldername
						String data = "";
						try{
							try {

								File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + msg_array[3] + "/" + msg_array[1]);
								Scanner myReader = new Scanner(myObj);
//								Log.i("reader", String.valueOf(myReader));

								while (myReader.hasNextLine()) {
									data = myReader.nextLine();
									Log.i("data", data);
								}

							} catch (FileNotFoundException e) {
								Log.e("login activity", "File not found: " + e.toString());
							}
							output.writeUTF(data);
							input.close();
							output.close();
							socket.close();
						}catch (Exception e) {
							Log.e("ExceptionQuerying At", " 4 File write failed: " + e.toString());
						}
					}

					else if(message.equals("Insertion")) {  // got message  Insertion , presentkey , prehashkey , prevalue , foldername
						try {
//							File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + msg_array[3] + "/" + msg_array[1]);

							Log.e(TAG," in server Insertion: "+ msg_array[1]);
							File Files = new File(getContext().getApplicationInfo().dataDir+"/files/" + msg_array[4] + "/" + msg_array[1]);
//						Log.i(TAG," in server created file: "+ Files);
							//delete file before write
							if (Files.delete()) {
								Log.i("File", "deleted key successfully");
							} else {
								Log.i("File", " not deleted key successfully");
							}


							Log.e(TAG," ---inserting!!!!: "+ msg_array[1]);
							BufferedWriter buf = new BufferedWriter(new FileWriter(Files, true));
							buf.append(msg_array[3]);
							Log.i(TAG, " Buffer1 in file: " + buf.toString());
							Log.i(TAG," appended in file: "+ Files);
							//buf.newLine();  // pointer will be nextline
							buf.close();
							output.writeUTF("done" + myPort);
							input.close();
							output.close();
							socket.close();
						} catch (Exception e) {
							Log.e("login activity", "File not found: " + e.toString());
						}


					}
					else if(message.equals("DeletingKey")){
						try {
							File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + msg_array[3] + "/" + msg_array[1]);
							if (myObj.delete()) {
								Log.i("File", "deleted key successfully");
							} else {
								Log.i("File", " not deleted key successfully");
							}
						} catch (Exception e) {
							Log.e("login activity", "File not found: " + e.toString());
						}
						output.writeUTF("done");
						input.close();
						output.close();
						socket.close();

					}


					else if(message.equals("deletingStar")) {   // got message: deletingStar,

						try {
							String data = " ";
							File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort) / 2 + "/");
							String contents[] = myObj.list();
							for (int i = 0; i < contents.length; i++) {
								try {
									File myObj1 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort) / 2 + "/" + contents[i]);
									if (myObj1.delete()) {
//										Log.i("File", "deleted successfully");

									} else {
//										Log.i("File", " not deleted successfully");

									}
								} catch (Exception e) {
									Log.e("login activity", "File not found: " + e.toString());
								}
							}
						} catch (Exception e) {
							Log.e("1111 login activity", "File not found: " + e.toString());
						}
						try {

							File myObj2 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred1) / 2 + "/");
							String contents2[] = myObj2.list();
							for (int i = 0; i < contents2.length; i++) {
								try {
									File myObj3 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred1) / 2 + "/" + contents2[i]);
									if (myObj3.delete()) {
//										Log.i("File", "deleted successfully");

									} else {
//										Log.i("File", " not deleted successfully");

									}
								} catch (Exception e) {
									Log.e("login activity", "File not found: " + e.toString());
								}
							}
						} catch (Exception e) {
							Log.e("1111 login activity", "File not found: " + e.toString());
						}
						try{

							File myObj4 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/"+shavalues.get(pred2)/2+"/");
							String contents3[] = myObj4.list();
							for(int i=0; i<contents3.length; i++) {
								try {

									File myObj5 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred2) / 2 + "/" + contents3[i]);
									if (myObj5.delete()) {
//										Log.i("File", "deleted successfully");

									} else {
//										Log.i("File", " not deleted successfully");

									}
								} catch (Exception e) {
									Log.e("login activity", "File not found: " + e.toString());
								}
							}


//				return qCursor;
						} catch (Exception e) {
							Log.e("login activity", "77 File not found: " + e.toString());
						}
						try {
							Socket socket21 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ1));
							String msgToSend21 = "deletingStar," ;                         // sending msg : deletingStar
							DataOutputStream output21 = new DataOutputStream(socket21.getOutputStream());
//							Log.i(TAG," Sending from deleteStar to succ : "+msgToSend21);
							output.writeUTF(msgToSend21);


						}  catch (IOException e) {
							try {
								Log.e("Exception", "2 File write failed: " + e.toString());
								Socket socket71 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ2));
								String msgToSend71 = "deletingStar,";                         // sending msg : deletingStar
								DataOutputStream output71 = new DataOutputStream(socket71.getOutputStream());
//							Log.i(TAG," Sending from deleteStar to succ : "+msgToSend71);
								output.writeUTF(msgToSend71);
							} catch(Exception ae){
								Log.e(TAG, "ServerTask UnknownHostException"+ae.getMessage());
							}
						}
					}

				}
				catch(Exception e)
				{
					Log.e(TAG, "ServerTask UnknownHostException"+e.getMessage());
				}

				finally {
					try{
						socket.close();
					}

					catch(Exception e)
					{
						Log.e(TAG, "ServerTask UnknownHostException"+e.getMessage());
					}
				}

			}



		}
	}








	public void createDirToStoreInCP() {


		try {
			Log.i(TAG, "in createdirtostore" + myPort);
			File dir = new File(getContext().getApplicationInfo().dataDir + "/files/");

			//File dir = new File(Environment.getExternalStorageDirectory()+"Dir_name_here");
			if (dir.isDirectory()) {
				String[] children = dir.list();
				for (int i = 0; i < children.length; i++) {
					new File(dir, children[i]).delete();
				}
			}


		} catch (Exception e) {
			Log.i(TAG, "Exception at file delete");
		}


		File mainDir = new File(getContext().getApplicationInfo().dataDir + "/files");

		Log.i(TAG, "print dataDir" + getContext().getApplicationInfo().dataDir);

		if (!mainDir.exists())
			mainDir.mkdir();
		File selfFolder = new File(getContext().getApplicationInfo().dataDir + "/files/" + Integer.parseInt(myPort)/2);
		Log.i(TAG, "directory1" + selfFolder);
		if (!selfFolder.exists())
			selfFolder.mkdir();

		List<String> targetNodes = new ArrayList<String>();


		Log.i(TAG, "pred1 in direc" + pred1);
		Log.i(TAG, "pred2 in direc" + pred2);
		File createStructure1 = new File(getContext().getApplicationInfo().dataDir + "/files/" + shavalues.get(pred1)/2);
		Log.i(TAG, "directory2" + createStructure1);
		if (!createStructure1.exists())
			createStructure1.mkdir();

		File createStructure2 = new File(getContext().getApplicationInfo().dataDir + "/files/" + shavalues.get(pred2)/2);
		Log.i(TAG, "directory3" + createStructure2);
		if (!createStructure2.exists())
			createStructure2.mkdir();


	}

	@Override
	public synchronized boolean onCreate() {
		// TODO Auto-generated method stub
		lockobj.lock();

		TelephonyManager tel = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
		portStr = tel.getLine1Number().substring(tel.getLine1Number().length() - 4);
		myPort = String.valueOf((Integer.parseInt(portStr) * 2));
		mUri = buildUri("content", "edu.buffalo.cse.cse486586.simpledht.provider");

		for (int item = 0 ;( item < REMOTE_PORT_ARRAY.length ) ; item ++) {

			try{
				String Sport=String.valueOf((REMOTE_PORT_ARRAY[item])/2);
				String Skey = genHash(Sport);
				shavalues.put(Skey,REMOTE_PORT_ARRAY[item]);
				list.add(Skey);
			} catch (Exception e) {
				Log.e(TAG, "Shavalues UnknownHostException" + e.getMessage());
			}
		}

		Collections.sort(list);
		try {
			currenthash = genHash(portStr);
			currentindex = list.indexOf(currenthash);
			if(currentindex>0){  // 1 2 3 4
				if(currentindex>1) {   // 2 3 4 5
					pred1 = list.get(currentindex - 1);
					pred2 = list.get(currentindex - 2);
					Log.i(TAG, " item grt 0 , pred1: " + pred1);
				}
				else  // 1
				{
					pred1 = list.get(currentindex - 1);
					pred2 = list.get(list.size() - 1);
				}
			}
			else //  0
			{
				pred1 = list.get(list.size() - 1);
				pred2 = list.get(list.size() - 2);
			}
			String firstnode = list.get(0);
			if(currentindex < list.size()-1){ // 0 1 2 3
				if(currentindex < list.size()-2) // 0 1 2
				{
					succ1 = list.get(currentindex+1);
					succ2 = list.get(currentindex+2);

					Log.i(TAG," item less than size , succ1: "+succ1);
				}
				else   //3
				{
					succ1 = list.get(currentindex+1);
					succ2 = list.get(0);
				}
			}
			else //4
			{
				succ1 = list.get(0);
				succ2 = list.get(1);
			}

			Log.e(TAG, "pred succ values" +","+currentindex+","+ pred1+"," + pred2 +"," +succ1 + ","+succ2);


			createDirToStoreInCP();
			try {
				ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
				new ServerTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, serverSocket);
			} catch (IOException e) {
				Log.e(TAG, "server not able create ServerSocket");
			}
			new ClientTask().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,"",myPort);
			Log.i(TAG,"Client created");
		}catch (Exception e) {
			Log.e(TAG, "Shavalues UnknownHostException" + e.getMessage());
		}
		//finally {
		if (lockobj.isHeldByCurrentThread()) {
			lockobj.unlock();
		}
		//	}
		return false;
	}

	@Override
	public synchronized   Cursor query(Uri uri, String[] projection, String selection,
									   String[] selectionArgs, String sortOrder) {
		synchronized (this) {
			Log.i("in query5", selection);

//		lock.lock();
//		if (lock.isHeldByCurrentThread()) {
//			lock.unlock();
//		}

			Log.i("in query", selection);
			// TODO Auto-generated method stub
			if (selection.equals("*")) {
				MatrixCursor qCursor = new MatrixCursor(new String[]{"key", "value"});
				try {

					String data = " ";
					File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort) / 2 + "/");
					String contents[] = myObj.list();
					for (int i = 0; i < contents.length; i++) {
						File myObj1 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort) / 2 + "/" + contents[i]);
						Scanner myReader = new Scanner(myObj1);
						Log.i("reader", String.valueOf(myReader));
						while (myReader.hasNextLine()) {
							data = myReader.nextLine();
							Log.i("data", data);
						}
						qCursor.addRow(new Object[]{contents[i], data});
					}
				} catch (Exception e) {
					Log.e(TAG, "Shavalues UnknownHostException" + e.getMessage());
				}
				try {
					String data = "";
					File myObj2 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred1) / 2 + "/");
					String contents2[] = myObj2.list();
					for (int i = 0; i < contents2.length; i++) {
						File myObj3 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred1) / 2 + "/" + contents2[i]);
						Scanner myReader = new Scanner(myObj3);
						Log.i("reader", String.valueOf(myReader));
						while (myReader.hasNextLine()) {
							data = myReader.nextLine();
							Log.i("data", data);
						}
						qCursor.addRow(new Object[]{contents2[i], data});
					}
				} catch (Exception e) {
					Log.e(TAG, "Shavalues UnknownHostException" + e.getMessage());
				}
				try {
					String data = "";
					File myObj4 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred2) / 2 + "/");
					String contents3[] = myObj4.list();
					for (int i = 0; i < contents3.length; i++) {
						File myObj5 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred2) / 2 + "/" + contents3[i]);
						Scanner myReader = new Scanner(myObj5);
						Log.i("reader", String.valueOf(myReader));
						while (myReader.hasNextLine()) {
							data = myReader.nextLine();
							Log.i("data", data);
						}
						qCursor.addRow(new Object[]{contents3[i], data});
					}


//				return qCursor;
				} catch (FileNotFoundException e) {
					Log.e("login activity", "77 File not found: " + e.toString());
				}
				String message6 = "";
				String got_key = "";
				String got_value = "";
				try {

					Socket socket6 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ1));
					String msgToSend6 = "queryingStar," + myPort;                         // sending msg : queryingStar + 11108
					DataOutputStream output6 = new DataOutputStream(socket6.getOutputStream());
					Log.i(TAG, " 55 Sending from query to succ : " + msgToSend6);
					output6.writeUTF(msgToSend6);
					DataInputStream inp6 = new DataInputStream(socket6.getInputStream());
					message6 = inp6.readUTF();     // getting msg :key1:value1 , key2 : value2 , key3 : value3
					Log.i(TAG, " 55 bgetting message from succ : " + message6);
					Log.i(TAG, " 55 bgetting message from succ : " + message6.length());
				} catch (Exception e) {
					try {
						Log.e("1116 login activity", "77 File not found: " + e.toString());
						Socket socket47 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ2));
						String msgToSend6 = "queryingStar," + myPort;                         // sending msg : queryingStar + 11108
						DataOutputStream output47 = new DataOutputStream(socket47.getOutputStream());
						Log.i(TAG, " 55 Sending from query to succ : " + msgToSend6);
						output47.writeUTF(msgToSend6);
						DataInputStream inp47 = new DataInputStream(socket47.getInputStream());
						message6 = inp47.readUTF();     // getting msg :key1:value1 , key2 : value2 , key3 : value3
						Log.i(TAG, " 55 bgetting message from succ : " + message6);
						Log.i(TAG, " 55 bgetting message from succ : " + message6.length());
					} catch (Exception ae) {
						Log.e(TAG, "Shavalues UnknownHostException" + ae.getMessage());
					}
				}


				if (message6 != null && message6 != "" && !message6.isEmpty() && message6.length() != 0 && !message6.equalsIgnoreCase(null) && !message6.equalsIgnoreCase("null") &&
						!message6.equalsIgnoreCase("NULL")) {


					Log.i(TAG, " 55 bgetting message from succ : " + message6);
					String[] compound = message6.split(",");                   // split gotmessage
					Log.i(TAG, " 55 compoundcompoundcompound message from succ : " + compound.length);
					for (int i = 0; i < compound.length; i++) {    //  key1 : value1
						String[] oneMsg = compound[i].split(":");
						Log.i(TAG, " oneMsg[0]" + oneMsg[0]);
						Log.i(TAG, " oneMsg[0]" + oneMsg[1]);
						got_key = oneMsg[0];
						got_value = oneMsg[1];
						qCursor.addRow(new Object[]{got_key, got_value});
					}
				}


				return qCursor;
			}


			else if (selection.equals("@")) {
				try {
					MatrixCursor qCursor = new MatrixCursor(new String[]{"key", "value"});
					String data = " ";
					File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort) / 2 + "/");
					String contents[] = myObj.list();
					for (int i = 0; i < contents.length; i++) {
						File myObj1 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort) / 2 + "/" + contents[i]);
						Scanner myReader = new Scanner(myObj1);
						Log.i("reader", String.valueOf(myReader));
						while (myReader.hasNextLine()) {
							data = myReader.nextLine();
							Log.i("data", data);
						}
						qCursor.addRow(new Object[]{contents[i], data});
					}

					File myObj2 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred1) / 2 + "/");
					String contents2[] = myObj2.list();
					for (int i = 0; i < contents2.length; i++) {
						File myObj3 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred1) / 2 + "/" + contents2[i]);
						Scanner myReader = new Scanner(myObj3);
						Log.i("reader", String.valueOf(myReader));
						while (myReader.hasNextLine()) {
							data = myReader.nextLine();
							Log.i("data", data);
						}
						qCursor.addRow(new Object[]{contents2[i], data});
					}

					File myObj4 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred2) / 2 + "/");
					String contents3[] = myObj4.list();
					for (int i = 0; i < contents3.length; i++) {
						File myObj5 = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + shavalues.get(pred2) / 2 + "/" + contents3[i]);
						Scanner myReader = new Scanner(myObj5);
						Log.i("reader", String.valueOf(myReader));
						while (myReader.hasNextLine()) {
							data = myReader.nextLine();
							Log.i("data", data);
						}
						qCursor.addRow(new Object[]{contents3[i], data});
					}


					return qCursor;
				} catch (FileNotFoundException e) {
					Log.e("login activity", "77 File not found: " + e.toString());
				}
			} else {
				String originalnode = "";
				int originalport = 0;
				String present_key = selection; //k
				String present_key_hash = "";
				String thisPorthash = "";
				try {
					present_key_hash = genHash(present_key);   // hashk
				} catch (Exception e) {

				}
				Log.i(TAG, "-------------in Insert-----Key, keyhashhhh-" + present_key + "val : " + present_key_hash);
				for (int item = 0; item < list.size(); item++) { // 4 0
					try {
//				Log.i(TAG, "first item " + list.get(0) + " ," + item + " " + list.size());
						String predecessor1 = "";
						String predecessor2 = "";
						String successor1 = "";
						String successor2 = "";
						String currentnode = list.get(item);    //1ghjkoiuhg``
						int currentPort = shavalues.get(currentnode);
						Log.i(TAG, " currentnode , currentPort: " + currentnode + "," + currentPort + "," + item + "," + myPort);

						if (item > 0) {  // 1 2 3 4
							if (item > 1) {   // 2 3 4 5
								predecessor1 = list.get(item - 1);
								predecessor2 = list.get(item - 2);
//						Log.i(TAG, " item grt 0 , predecessor1: " + predecessor1);
							} else  // 1
							{
								predecessor1 = list.get(item - 1);
								predecessor2 = list.get(list.size() - 1);
							}
						} else //  0
						{
							predecessor1 = list.get(list.size() - 1);
							predecessor2 = list.get(list.size() - 2);
						}
						String firstnode = list.get(0);
						if (item < list.size() - 1) { // 0 1 2 3
							if (item < list.size() - 2) // 0 1 2
							{
								successor1 = list.get(item + 1);
								successor2 = list.get(item + 2);

//						Log.i(TAG, " item less than size , succ1: " + successor1);
							} else   //3
							{
								successor1 = list.get(item + 1);
								successor2 = list.get(0);
							}
						} else //4
						{
							successor1 = list.get(0);
							successor2 = list.get(1);
						}

						Log.e(TAG, "pred succ values" + "," + item + "," + predecessor1 + "," + predecessor2 + "," + successor1 + "," + successor2);
						// presentkey 		presentkeyhash	 	presentvalue : befroe for loop
						// currentnode - 33djhfgvad        currentport - 11108    predecessor1  p2   s1  s2  firstnode     : in for loop
						int rangecheck1 = predecessor1.compareTo(present_key_hash);  //neg        // -1 greater than predecessor1....1 lesser than predecessor1
						int rangecheck2 = currentnode.compareTo(present_key_hash);   //pos or eq       // -1 greater than currentnode....1 lesser than currentnode
						if ((rangecheck2 >= 0 && rangecheck1 <= 0) || ((predecessor1.compareTo(currentnode) >= 0) && (rangecheck1 <= 0)) || ((predecessor1.compareTo(currentnode) >= 0) && rangecheck2 >= 0)) { // in range

							originalnode = currentnode;
							originalport = currentPort;
							p1 = predecessor1;
							p2 = predecessor2;
							s1 = successor1;
							s2 = successor2;
							Log.i(TAG, " in range--original node,port---: " + originalnode + originalport);
							Log.i(TAG, " in range--p1,p2,s1,s2---: " + p1 + p2 + s1 + s2);
							break;
						}

					} catch (Exception e) {
						Log.e(TAG, "ServerTask UnknownHostException" + e.getMessage());
					}
				}
				try {
					thisPorthash = genHash(portStr);
				} catch (Exception e) {
					Log.e(TAG, "ServerTask UnknownHostException" + e.getMessage());
				}

				if (thisPorthash.equals(originalnode)) {
					String data1 = "";
					String msg1 = "";
					String msg2 = "";
					try {

						File myObj = new File("/data/data/edu.buffalo.cse.cse486586.simpledynamo/files/" + Integer.parseInt(myPort) / 2 + "/" + selection);
						Scanner myReader = new Scanner(myObj);
						Log.i("reader", String.valueOf(myReader));

						while (myReader.hasNextLine()) {
							data1 = myReader.nextLine();
							Log.i("data", data1);
						}


					} catch (Exception e) {
						Log.e("login activity", "File not found: " + e.toString());
					}

					try {
						Log.i(TAG, " connectng succ1: " + shavalues.get(succ1));
						Socket socket1 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ1));
						String msgToSend = "Querying" + "," + present_key + "," + present_key_hash + "," + Integer.parseInt(myPort) / 2;
						DataOutputStream output1 = new DataOutputStream(socket1.getOutputStream());
						Log.i(TAG, " sending msg to s1 from insert " + msgToSend);
						output1.writeUTF(msgToSend);
						msg1 = "";
						DataInputStream inp = new DataInputStream(socket1.getInputStream());                          // getting msg :   done             <if no msg> ioexception type="single"
						msg1 = inp.readUTF();
						Log.i(TAG, " got msg from s1 in insert: " + msg1);
						socket1.close();
					} catch (Exception e) {
						Log.e("login activity", "File not found: " + e.toString());

					}
					try {
						Log.i(TAG, " connectng succ2: " + shavalues.get(succ2));
						Socket socket2 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(succ2));
						String msgToSend2 = "Querying" + "," + present_key + "," + present_key_hash + "," + Integer.parseInt(myPort) / 2;
						DataOutputStream output2 = new DataOutputStream(socket2.getOutputStream());
						Log.i(TAG, " sendng msg to s2 from insert " + msgToSend2);
						output2.writeUTF(msgToSend2);
						msg2 = "";
						DataInputStream inp2 = new DataInputStream(socket2.getInputStream());                          // getting msg :   done             <if no msg> ioexception type="single"
						msg2 = inp2.readUTF();
						Log.i(TAG, " got msg from s2 from insert: " + msg2);
						socket2.close();
					} catch (Exception e) {
						Log.e("login activity", "File not found: " + e.toString());
					}

					String[] strs = {data1, msg1, msg2};
					HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
					int max = Integer.MIN_VALUE;
					String ans = data1;
					for (String s : strs) {
						int val;
						if (!hashmap.containsKey(s))
							val = 0;
						else
							val = hashmap.get(s);
						val++;
						hashmap.put(s, val);
						if (val > max) {
							max = val;
							ans = s;
						}
					}


					//Map<String, Integer> valueSotredMap = sortByValue(hashmap);

					//ans = valueSotredMap.keySet().toArray()[0].toString();


					MatrixCursor mCursor = new MatrixCursor(new String[]{"key", "value"});
					Log.i("mcursor created", ans);
					mCursor.newRow().add("key", selection).add("value", ans);
					Log.i("mcursor added smtng", ans);
					return mCursor;


				} else {          // out of range
					String msg7 = "";
					String msg8 = "";
					String msg81 = "";
					try {
						Log.i(TAG, " connectng ori: " + originalport);
						Socket socket7 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), originalport);
						String msgToSend7 = "Querying" + "," + present_key + "," + present_key_hash + "," + originalport / 2;
						DataOutputStream output7 = new DataOutputStream(socket7.getOutputStream());
						Log.i(TAG, " sendng to original in query: " + msgToSend7);
						output7.writeUTF(msgToSend7);
						msg7 = "";
						DataInputStream inp7 = new DataInputStream(socket7.getInputStream());
						msg7 = inp7.readUTF();
						Log.i(TAG, " got from original in query: " + msg7);
						socket7.close();

					} catch (Exception e) {
						Log.e("login activity", "File not found: " + e.toString());

					}

					try {
						Log.i(TAG, " connectng s1: " + shavalues.get(s1));
						Socket socket8 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(s1));
						String msgToSend8 = "Querying" + "," + present_key + "," + present_key_hash + ","+ originalport/2;
						DataOutputStream output8 = new DataOutputStream(socket8.getOutputStream());
						Log.i(TAG, " sendng to original in query: " + msgToSend8);
						output8.writeUTF(msgToSend8);
						msg8 = "";
						DataInputStream inp8 = new DataInputStream(socket8.getInputStream());
						msg8 = inp8.readUTF();
						Log.i(TAG, " got from original in query: " + msg8);
						socket8.close();


					} catch (Exception e) {
						Log.e("login activity", "File not found: " + e.toString());

					}
					try {
						Log.i(TAG, " connectng s2: " + shavalues.get(s2));
						Socket socket81 = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}), shavalues.get(s2));
						String msgToSend81 = "Querying" + "," + present_key + "," + present_key_hash + ","+ originalport/2;
						DataOutputStream output81 = new DataOutputStream(socket81.getOutputStream());
						Log.i(TAG, " sendng to original in query: " + msgToSend81);
						output81.writeUTF(msgToSend81);
						msg81 = "";
						DataInputStream inp81 = new DataInputStream(socket81.getInputStream());
						msg81 = inp81.readUTF();
						Log.i(TAG, " got from original in query: " + msg81);
						socket81.close();
					} catch (Exception e) {
						Log.e("login activity", "File not found: " + e.toString());
					}

					String[] strs = {msg7, msg8, msg81};
					HashMap<String, Integer> hashmap1 = new HashMap<String, Integer>();
					int max1 = Integer.MIN_VALUE;
					String ans1 = msg7;
					for(String s: strs) {
						int val;
						if (!hashmap1.containsKey(s))
							val = 0;
						else
							val = hashmap1.get(s);
						val++;
						hashmap1.put(s, val);
						if(val>max1) {
							max1 = val;
							ans1 = s;
						}
					}

					MatrixCursor mCursor = new MatrixCursor(new String[]{"key", "value"});
					Log.i("mcursor created", ans1);
					mCursor.newRow().add("key", selection).add("value", ans1);
					Log.i("mcursor added smtng", ans1);
					return mCursor;

				}

			}
			return null;
		} }
//	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
//	{
//		// Create a list from elements of HashMap
//		List<Map.Entry<String, Integer> > list =
//				new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
//
//		// Sort the list
//		Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
//			public int compare(Map.Entry<String, Integer> o1,
//							   Map.Entry<String, Integer> o2)
//			{
//				return (o2.getValue()).compareTo(o1.getValue());
//			}
//		});
//
//		// put data from sorted list to hashmap
//		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
//		for (Map.Entry<String, Integer> aa : list) {
//			temp.put(aa.getKey(), aa.getValue());
//		}
//		return temp;
//	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
					  String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	private String genHash(String input) throws NoSuchAlgorithmException {
		MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
		byte[] sha1Hash = sha1.digest(input.getBytes());
		Formatter formatter = new Formatter();
		for (byte b : sha1Hash) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}

}