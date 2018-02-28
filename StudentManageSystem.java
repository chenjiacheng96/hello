package cqh.test.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManageSystem {
	// 属性
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Student> stus = new ArrayList<Student>();

	public static void main(String[] args) throws IOException {

		// 持续一旦运行 立刻执行
		readerArrayListToFile();

		while (true) {

			System.out.println("-----欢迎使用学生管理系统-----");
			System.out.println("1:查看所有学生");
			System.out.println("2:添加学生");
			System.out.println("3:删除学生");
			System.out.println("4:修改学生");
			System.out.println("5:退出");
			System.out.println("请输入你的选择");

			String choice = sc.nextLine();

			switch (choice) {
			case "1":
				findAll();
				break;
			case "2":
				addStudent();
				break;
			case "3":
				deleteStudent();
				break;
			case "4":
				updateStudent();
				break;
			case "5":
				System.out.println("退出程序,欢迎下次使用~~~");
				System.exit(0);
				break;
			default:
				System.out.println("输入有误,请重新输入");

			}

		}

	}

	// 定义一个方法 将集合中的数据写到文件
	public static void writerArrayListToFile() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("stus.txt"));

		for (int i = 0; i < stus.size(); i++) {
			Student stu = stus.get(i);

			StringBuilder sb = new StringBuilder();

			sb.append(stu.getId()).append(stu.getName()).
				append(stu.getAge()).append(stu.getAddress());

			bw.write(sb.toString());
			bw.newLine();
			bw.flush();
		}

		bw.close();

	}

	// 定义一个方法 将文件中的数据写到集合
	public static void readerArrayListToFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("stus.txt"));

		String line = null;

		while ((line = br.readLine()) != null) {
			String[] str = line.split(" +");
			String id = str[0];
			String name = str[1];
			int age = Integer.parseInt(str[2]);
			String address = str[3];

			Student stu = new Student(id, name, age, address);
			stus.add(stu);

		}

		br.close();

	}

	public static void findAll() {
		if (stus.size() != 0) {
			for (int i = 0; i < stus.size(); i++) {
				Student stu = stus.get(i);
				System.out.println("学号:\t\t姓名:\t年龄:\t地址:\t");
				
				System.out.println(stu.getId() + "\t\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getAddress());
			}
		} else {
			System.out.println("现在没有学生可以查询！");
		}
	}

	public static void addStudent() throws IOException {
		String id = null;

		// 死循环
		while (true) {
			// 标记 另外 相当于每次循环重新赋值为false
			boolean flag = false;
			// 提示输入数据
			System.out.println("输入学生ID");
			id = sc.nextLine();
			// 遍历集合 查看是否重复
			for (int i = 0; i < stus.size(); i++) {
				Student stu = stus.get(i);
				if (stu.getId().equals(id)) {
					flag = true;
					break;
				}
			}

			if (flag == false) {
				break;
			} else {
				System.out.println("ID重复!请重新输入.");
			}

		}

		System.out.println("输入学生姓名");
		String name = sc.nextLine();
		System.out.println("输入学生年龄");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.println("输入学生地址");
		String address = sc.nextLine();

		// 将学生相关的数据 封装到学生类中储存
		Student stu = new Student(id, name, age, address);
		// 将封装完成的学生对象添加到学生集合中
		stus.add(stu);

		writerArrayListToFile();

		System.out.println("添加学生信息成功！");
	}

	public static void deleteStudent() throws IOException {
		// 标记
		int index = -1;
		System.out.println("输入要删除的学生学号");
		String id = sc.nextLine();

		// 遍历学生集合
		for (int i = 0; i < stus.size(); i++) {
			Student stu = stus.get(i);
			if (stu.getId().equals(id)) {
				index = i;
				break;
			}
		}

		if (index != -1) {

			stus.remove(index);
			System.out.println("删除成功!");
			writerArrayListToFile();

		} else {

			System.out.println("学号有误,请重新输入!");

		}
	}

	public static void updateStudent() throws IOException {

		int index = -1;

		System.out.println("输入要修改的学生号:");
		String id = sc.nextLine();

		for (int i = 0; i < stus.size(); i++) {
			Student stu = stus.get(i);

			if (stu.getId().equals(id)) {
				index = i;
				break;
			}
		}

		if (index != -1) {

			System.out.println("输入姓名");
			String name = sc.nextLine();
			System.out.println("输入年龄");
			int age = Integer.parseInt(sc.nextLine());
			System.out.println("输入地址");
			String address = sc.nextLine();

			Student stu = new Student(id, name, age, address);

			stus.set(index, stu);
			System.out.println("修改成功!");
			writerArrayListToFile();

		} else {

			System.out.println("查无此人,重新输入!");

		}

	}

}
