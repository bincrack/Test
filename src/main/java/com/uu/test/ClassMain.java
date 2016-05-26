//package com.uu.test;
//
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import org.gjt.jclasslib.io.ClassFileWriter;
//import org.gjt.jclasslib.structures.CPInfo;
//import org.gjt.jclasslib.structures.ClassFile;
//import org.gjt.jclasslib.structures.InvalidByteCodeException;
//import org.gjt.jclasslib.structures.constants.ConstantStringInfo;
//
///**
// * javap -c Class.class
// * CPInfo.setStringIndex(index) OR CPInfo.setString(String)
// */
//public class ClassMain
//{
//	public static void main(String[] args) throws FileNotFoundException, InvalidByteCodeException, IOException
//	{
//		ClassFile cf = new ClassFile();
//		cf.read(new DataInputStream(new FileInputStream("D:\\Develop\\Workspaces\\trust\\Test\\WebRoot\\WEB-INF\\classes\\com\\uu\\test\\Main.class")));
//		CPInfo[] infos = cf.getConstantPool(); 
//		for (int i = 0; i < infos.length; i++)
//		{
//			if (infos[i] == null)
//				continue;
//			
//			if (infos[i] instanceof ConstantStringInfo)
//			{
//				ConstantStringInfo uInfo = (ConstantStringInfo) infos[i];
//				if (i == 318)
//				{
//					uInfo.setStringIndex(317);
//				}
//			}
//			
//			System.out.println(i + " = " + infos[i].getVerbose() + " = " + infos[i].getTagVerbose() + " " + infos[i].getTag());
//		}
//		
//		cf.setConstantPool(infos); 
//		ClassFileWriter.writeToFile(new File("C:\\Users\\UUT01\\Desktop\\New.App.class"), cf);
//	}
//
//}
