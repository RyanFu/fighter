package cn.com.uangel.aidlservice;
//  IMyService.aidl文件的内容与Java代码非常相似，但要注意，不能加修饰符（例如，public、private）、AIDL服务不支持的数据类型（例如，InputStream、OutputStream）等内容。


interface IMyService { 
	String getValue(); 
}