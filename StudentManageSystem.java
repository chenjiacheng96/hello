package cn.itcast.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManageSystem {
	// ����
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Student> stus = new ArrayList<Student>();

	public static void main(String[] args) throws IOException {

		// ����һ������ ����ִ��
		readerArrayListToFile();

		while (true) {

			System.out.println("-----��ӭʹ��ѧ������ϵͳ-----");
			System.out.println("1:�鿴����ѧ��");
			System.out.println("2:���ѧ��");
			System.out.println("3:ɾ��ѧ��");
			System.out.println("4:�޸�ѧ��");
			System.out.println("5:�˳�");
			System.out.println("���������ѡ��");

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
				System.out.println("�˳�����,��ӭ�´�ʹ��~~~");
				System.exit(0);
				break;
			default:
				System.out.println("��������,����������");

			}

		}

	}

	// ����һ������ �������е�����д���ļ�
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

	// ����һ������ ���ļ��е�����д������
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
				System.out.println("ѧ��:\t\t����:\t����:\t��ַ:\t");
				
				System.out.println(stu.getId() + "\t\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getAddress());
			}
		} else {
			System.out.println("����û��ѧ�����Բ�ѯ��");
		}
	}

	public static void addStudent() throws IOException {
		String id = null;

		// ��ѭ��
		while (true) {
			// ��� ���� �൱��ÿ��ѭ�����¸�ֵΪfalse
			boolean flag = false;
			// ��ʾ��������
			System.out.println("����ѧ��ID");
			id = sc.nextLine();
			// �������� �鿴�Ƿ��ظ�
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
				System.out.println("ID�ظ�!����������.");
			}

		}

		System.out.println("����ѧ������");
		String name = sc.nextLine();
		System.out.println("����ѧ������");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.println("����ѧ����ַ");
		String address = sc.nextLine();

		// ��ѧ����ص����� ��װ��ѧ�����д���
		Student stu = new Student(id, name, age, address);
		// ����װ��ɵ�ѧ��������ӵ�ѧ��������
		stus.add(stu);

		writerArrayListToFile();

		System.out.println("���ѧ����Ϣ�ɹ���");
	}

	public static void deleteStudent() throws IOException {
		// ���
		int index = -1;
		System.out.println("����Ҫɾ����ѧ��ѧ��");
		String id = sc.nextLine();

		// ����ѧ������
		for (int i = 0; i < stus.size(); i++) {
			Student stu = stus.get(i);
			if (stu.getId().equals(id)) {
				index = i;
				break;
			}
		}

		if (index != -1) {

			stus.remove(index);
			System.out.println("ɾ���ɹ�!");
			writerArrayListToFile();

		} else {

			System.out.println("ѧ������,����������!");

		}
	}

	public static void updateStudent() throws IOException {

		int index = -1;

		System.out.println("����Ҫ�޸ĵ�ѧ����:");
		String id = sc.nextLine();

		for (int i = 0; i < stus.size(); i++) {
			Student stu = stus.get(i);

			if (stu.getId().equals(id)) {
				index = i;
				break;
			}
		}

		if (index != -1) {

			System.out.println("��������");
			String name = sc.nextLine();
			System.out.println("��������");
			int age = Integer.parseInt(sc.nextLine());
			System.out.println("�����ַ");
			String address = sc.nextLine();

			Student stu = new Student(id, name, age, address);

			stus.set(index, stu);
			System.out.println("�޸ĳɹ�!");
			writerArrayListToFile();

		} else {

			System.out.println("���޴���,��������!");

		}

	}

}
