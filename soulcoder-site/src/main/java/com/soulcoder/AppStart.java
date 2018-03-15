/*
 * Copyright (c) 2018.閹碉拷閺堝鍞惍浣哄閺夊啫缍婄紓鏍垳閼板懏澧嶉張锟�!
 */

package com.soulcoder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Aministrator on 2018-01-15.
 * 更新工程内的文件编码
 */
public class AppStart {
   public static void main(String[] args) {
      // System.out.println(ShiroUtils.sha256("admin","MTIzNDU2"));
//       HashMap<String,Object> map = new HashMap<String, Object>();
//       for(int i=0;i<18;i++){
//           if(i==11){
//               System.out.println("rehash");
//           }
//           map.put(String.valueOf(i),i);
//       }
//       System.out.println(map);
       //String a ="1";
      // String a= new String("a");
      // String a = "ab";
       //int hashc=a.hashCode();

     ExecutorService executorService= Executors.newCachedThreadPool();
     executorService.execute(new Runnable() {
         public void run() {
             System.out.println("i execute");
         }
     });
       System.out.println("ok");
      // Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread,new Throwable("exception"));
//       ConcurrentHashMap<String,Object> concurrentMap = new ConcurrentHashMap<String, Object>();
//       concurrentMap.put("1","a");
//       concurrentMap.put("2","a");
//       map.put("1","a");
//       map.put("1","b");
//      Object obj= map.get("1");
//       map.put("2","c");
//        String srcDir = "D:\\test Project\\ND.JavaProject\\soulcoder\\src";
//        List<String> fileList = new ArrayList<String>();
//        fetchFileList(srcDir,fileList,".java");
//        for(String fileName : fileList){
//            convert(fileName, "GBK", fileName, "UTF-8");
//        }
//    }
//
//    public static void convert(String oldFile, String oldCharset, String newFlie, String newCharset){
//        BufferedReader binReader;
//        FileOutputStream fos;
//        StringBuffer content = new StringBuffer();
//        try{
//            System.out.println(oldFile);
//            binReader = new BufferedReader(new InputStreamReader(new FileInputStream(oldFile),"gbk" ));
//                    String line=null;
//            while ((line=binReader.readLine()) !=null){
//                content.append(line);
//                content.append(System.getProperty("line.separator"));
//            }
//            binReader.close();
//            File dir = new File(newFlie.substring(0,newFlie.lastIndexOf("\\")));
//            if(!dir.exists()){
//                dir.mkdirs();
//            }
//            fos = new FileOutputStream(newFlie);
//            Writer out = new OutputStreamWriter(fos,newCharset);
//            out.write(content.toString());
//            out.close();
//            fos.close();
//        }catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//    * @Author:Aministrator
//    * @Description:闁秴宸婚弬鍥︽
//    * @Date:2018-01-15
//    * @param
//    * @return
//    */
//    public static void fetchFileList(String srcDir,List<String> fileList,String patten){
//        File dir = new File(srcDir);
//        File[] files = dir.listFiles();
//        Pattern p = Pattern.compile(patten);
//        if(files ==null){
//            return;
//        }
//        for (int i = 0; i <files.length ; i++) {
//            if(files[i].isDirectory()){
//                fetchFileList(files[i].getAbsolutePath(),fileList,patten);
//            }else{
//                String strFileName = files[i].getAbsolutePath().toLowerCase();
//                Matcher m = p.matcher(strFileName);
//                if(m.find()){
//                    fileList.add(strFileName);
//                }
//            }
//        }
    }
}
