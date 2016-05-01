package com.briup.ch12;
import java.util.Scanner;

public class Tms{
	//教师数组，用来保存所有教师信息
	private Teacher[] teas=new Teacher[3];
	private int index = 0;//教师个数
	
    //添加教师
    public void add(Teacher tea){
		//数组扩展
		if(index>=teas.length){
			Teacher[] demo=new Teacher[teas.length+3];
			//teas-->demo
			System.arraycopy(teas,0,demo,0,teas.length);
			teas=demo;
		}
		teas[index++]=tea;
	}
	//通过id删除教师
	public void deleteById(long id){
        int teaIndex = queryIndexById(id);
		if(index!=-1){
			for(int i=teaIndex;i<index-1;i++){
				teas[i] = teas[i+1];
			}
			teas[--index] = null;
		}
	}
	//通过id查找该教师所在的位置
	public int queryIndexById(long id){
		int teaIndex = -1;
		for(int i=0;i<index;i++){
			if(teas[i].GetId() == id){
				teaIndex=i;
				break;
			}
		}
		return teaIndex;
	}
	//通过id查询教师
	public Teacher queryById(long id){
		//调用方法获取id为指定参数，所在数组中的位置
		int teaIndex = queryIndexById(id);
		return teaIndex==-1?null:teas[teaIndex];
	}
	//查询所有教师信息
	public Teacher[] queryAll(){
		Teacher[] demo=new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
	    return demo;
	}
    //菜单
	public void menu(){
		System.out.println("*********教师信息管理系统********");
		System.out.println("*1.查看所有教师信息*");
		System.out.println("*2.添加教师信息*");
		System.out.println("*3.删除教师信息*");
		System.out.println("*4.查询教师信息*");
		System.out.println("*exit 退出*");
		System.out.println("*help 帮助*");
		System.out.println("*********************************");

	}
	public static void main(String[] args){
		//创建tms对象
        Tms tms=new Tms();
		tms.menu();//显示主界面
		while(true){
			Scanner scanner=new Scanner(System.in);
		    System.out.print("请输入功能编号：");
		    String option=scanner.nextLine();
		    switch(option){
		    case "1"://查询所有
				System.out.println("以下为所有教师的信息：");
			    Teacher[] teas = tms.queryAll();
				for (Teacher tea:teas){
					System.out.println(tea);
				}
			    System.out.println("总计："+teas.length+" 人");
				break;
			case "2"://添加教师信息
				while(true){
				System.out.println("请输入教师信息【id#name#age】或者输入break回到上一级目录");
				String teaStr=scanner.nextLine();
				if(teaStr.equals("break")){
					break;
				}
				String[] teaArr =teaStr.split("#");
				long id = Long.parseLong(teaArr[0]);
				String name = teaArr[1];
				int age = Integer.parseInt(teaArr[2]);
				//封装对象
				Teacher tea = new Teacher(id,name,age);
				tms.add(tea);
				System.out.println("添加成功！");
			}
				break;
			case "3"://删除
			    while(true){
                    System.out.print("请输入您要删除教师的id或者输入break返回上一级目录:");
				    String id = scanner.nextLine();
					if(id.equals("break")){
						break;
					}
                   tms.deleteById(Long.parseLong(id)); 
				   System.out.println("删除成功！教师个数为："+tms.index);
				}
				break;
			case "4"://查询
			    while(true){
                    System.out.print("请输入您要查询教师的id或者输入break返回上一级目录:");
				    String id = scanner.nextLine();
					if(id.equals("break")){
						break;
					}
                    Teacher tea = tms.queryById(Long.parseLong(id));
				    System.out.println("以下是您要查找的教师信息：");
				    System.out.println(tea!=null?tea:"not found!");
				}
				break;
			case "help":
				tms.menu();
				break;
			case "exit":
				System.out.println("欢迎下次再来~");
				System.exit(0);
			default:
				System.out.println("输入出错，请重新输入！");
		    }
		}		
	}

}
