package com.magic.magicprojectbaseapp.mvp.entity;

import java.util.List;

public class StudentBean {
	List<Student> data;

	public List<Student> getData() {
		return data;
	}

	public void setData(List<Student> data) {
		this.data = data;
	}

	public class Student {
		private int id;
		private String name;
		private int age;

		public Student() {

		}

		public Student(int id, String name, int age) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}


	}

	
}

