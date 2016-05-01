package com.briup.ch12;
import java.util.Scanner;

public class Tms{
	//��ʦ���飬�����������н�ʦ��Ϣ
	private Teacher[] teas=new Teacher[3];
	private int index = 0;//��ʦ����
	
    //��ӽ�ʦ
    public void add(Teacher tea){
		//������չ
		if(index>=teas.length){
			Teacher[] demo=new Teacher[teas.length+3];
			//teas-->demo
			System.arraycopy(teas,0,demo,0,teas.length);
			teas=demo;
		}
		teas[index++]=tea;
	}
	//ͨ��idɾ����ʦ
	public void deleteById(long id){
        int teaIndex = queryIndexById(id);
		if(index!=-1){
			for(int i=teaIndex;i<index-1;i++){
				teas[i] = teas[i+1];
			}
			teas[--index] = null;
		}
	}
	//ͨ��id���Ҹý�ʦ���ڵ�λ��
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
	//ͨ��id��ѯ��ʦ
	public Teacher queryById(long id){
		//���÷�����ȡidΪָ�����������������е�λ��
		int teaIndex = queryIndexById(id);
		return teaIndex==-1?null:teas[teaIndex];
	}
	//��ѯ���н�ʦ��Ϣ
	public Teacher[] queryAll(){
		Teacher[] demo=new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
	    return demo;
	}
    //�˵�
	public void menu(){
		System.out.println("*********��ʦ��Ϣ����ϵͳ********");
		System.out.println("*1.�鿴���н�ʦ��Ϣ*");
		System.out.println("*2.��ӽ�ʦ��Ϣ*");
		System.out.println("*3.ɾ����ʦ��Ϣ*");
		System.out.println("*4.��ѯ��ʦ��Ϣ*");
		System.out.println("*exit �˳�*");
		System.out.println("*help ����*");
		System.out.println("*********************************");

	}
	public static void main(String[] args){
		//����tms����
        Tms tms=new Tms();
		tms.menu();//��ʾ������
		while(true){
			Scanner scanner=new Scanner(System.in);
		    System.out.print("�����빦�ܱ�ţ�");
		    String option=scanner.nextLine();
		    switch(option){
		    case "1"://��ѯ����
				System.out.println("����Ϊ���н�ʦ����Ϣ��");
			    Teacher[] teas = tms.queryAll();
				for (Teacher tea:teas){
					System.out.println(tea);
				}
			    System.out.println("�ܼƣ�"+teas.length+" ��");
				break;
			case "2"://��ӽ�ʦ��Ϣ
				while(true){
				System.out.println("�������ʦ��Ϣ��id#name#age����������break�ص���һ��Ŀ¼");
				String teaStr=scanner.nextLine();
				if(teaStr.equals("break")){
					break;
				}
				String[] teaArr =teaStr.split("#");
				long id = Long.parseLong(teaArr[0]);
				String name = teaArr[1];
				int age = Integer.parseInt(teaArr[2]);
				//��װ����
				Teacher tea = new Teacher(id,name,age);
				tms.add(tea);
				System.out.println("��ӳɹ���");
			}
				break;
			case "3"://ɾ��
			    while(true){
                    System.out.print("��������Ҫɾ����ʦ��id��������break������һ��Ŀ¼:");
				    String id = scanner.nextLine();
					if(id.equals("break")){
						break;
					}
                   tms.deleteById(Long.parseLong(id)); 
				   System.out.println("ɾ���ɹ�����ʦ����Ϊ��"+tms.index);
				}
				break;
			case "4"://��ѯ
			    while(true){
                    System.out.print("��������Ҫ��ѯ��ʦ��id��������break������һ��Ŀ¼:");
				    String id = scanner.nextLine();
					if(id.equals("break")){
						break;
					}
                    Teacher tea = tms.queryById(Long.parseLong(id));
				    System.out.println("��������Ҫ���ҵĽ�ʦ��Ϣ��");
				    System.out.println(tea!=null?tea:"not found!");
				}
				break;
			case "help":
				tms.menu();
				break;
			case "exit":
				System.out.println("��ӭ�´�����~");
				System.exit(0);
			default:
				System.out.println("����������������룡");
		    }
		}		
	}

}
